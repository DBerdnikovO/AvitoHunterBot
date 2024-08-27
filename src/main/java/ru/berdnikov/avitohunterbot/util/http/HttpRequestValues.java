package ru.berdnikov.avitohunterbot.util.http;

public enum HttpRequestValues {
    AVITO_REQUEST_VALUES(
            "div.iva-item-title-py3i_",
            "div.iva-item-title-py3i_ a",
            "div.price-price-JP7qe",
            "div.iva-item-dateInfoStep-_acjp",
            "div.iva-item-dateInfoStep-_acjp div p.styles-module-root-_KFFt",
            "href"
    );

    public final String productElements;
    public final String title;
    public final String price;
    public final String childrenInfo;
    public final String date;
    public final String attr;


    HttpRequestValues(
            String productElements,
            String title,
            String price,
            String childrenInfo,
            String date,
            String attr
    ) {
        this.productElements = productElements;
        this.title = title;
        this.price = price;
        this.childrenInfo = childrenInfo;
        this.date = date;
        this.attr = attr;
    }
}
