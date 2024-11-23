package ru.berdnikov.avitohunterbot.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.berdnikov.avitohunterbot.service.LinkService;
import ru.berdnikov.avitohunterbot.service.TaskService;
import ru.berdnikov.avitohunterbot.util.errors.Errors;
import ru.berdnikov.avitohunterbot.util.http.HttpRequestValues;
import ru.berdnikov.avitohunterbot.util.http.TimeFilter;
import ru.berdnikov.avitohunterbot.util.info.Info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.berdnikov.avitohunterbot.util.http.Urls.AVITO_BASE_URL;
import static ru.berdnikov.avitohunterbot.util.info.Info.requestMessage;

@Service
public class TaskServiceImpl implements TaskService {
    private final LinkService linkService;

    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> searchTaskFuture;

    public TaskServiceImpl(LinkService linkService) {
        this.linkService = linkService;
    }

    public String startSearchTask(long profileId, TelegramLongPollingBot avitoHunterTelegramPollingBot) {
        if(linkService.getLinksAsAList(profileId).isEmpty()) return Errors.LINKS_NOT_FOUND;
        scheduler = Executors.newScheduledThreadPool(1);
        Runnable searchTask = () -> {
            List<String> newValue = listOfNewValues(profileId, HttpRequestValues.AVITO_REQUEST_VALUES);
            for (String value : newValue) {
                try {
                    avitoHunterTelegramPollingBot.execute(requestMessage(profileId, value));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        searchTaskFuture = scheduler.scheduleAtFixedRate(searchTask, 0, 30, TimeUnit.MINUTES);
        return Info.START_INFO;
    }

    private List<String> listOfNewValues(long profileId, HttpRequestValues values) {
        return linkService.getLinksAsAList(profileId).stream()
                .flatMap(link -> fetchLinksFromUrl(link.getLink(), values))
                .collect(Collectors.toList());
    }

    private Stream<String> fetchLinksFromUrl(String url, HttpRequestValues values) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements linkURL = doc.select(values.productElements);
            Elements titles = linkURL.select(values.title);
            Elements prices = doc.select(values.price);
//            Elements children = doc.select(values.childrenInfo);
            Elements dates = doc.select(values.date);
            List<String> result = new ArrayList<>();

            for (int i = 0; i < titles.size(); i++) {
                Element titleElement = titles.get(i);
                Element dateElement = dates.get(i);
//                if (children.get(i).childNodeSize() > 3) {
//                    continue;
//                }
                if (TimeFilter.ONE_MINUTE_AGO.equals(dateElement.text()) || TimeFilter.FEW_SECONDS_AGO.equals(dateElement.text())) {
                    String title = titleElement.text();
                    String price = prices.get(i).text();
                    String linkUrl = AVITO_BASE_URL + linkURL.get(i).select(values.title).attr(values.attr);
                    result.add(title + "\n" + "Цена: " + price + "\n" + linkUrl);
                }
            }
            return result.stream();
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    public String stopSearchTask() {
        if (scheduler != null) {
            scheduler.shutdown();
            if (searchTaskFuture != null) {
                searchTaskFuture.cancel(true);
                return Info.STOP_INFO;
            }
        }
        return Info.STOP_NOT_FOUND_INFO;
    }
}
