package ru.berdnikov.avitohunterbot.util.http;

public enum HttpRequestValues {
    AVITO_REQUEST_VALUES(
            "div.iva-item-title-CdRXl",
            "div.iva-item-title-CdRXl a",
            "div.price-price-j2OjU",
//            "div.iva-item-dateInfoStep-_acjp",
            "div.iva-item-dateInfoStep-qcDJA div p.styles-module-root-s4tZ2",
            "href"
    );

    public final String productElements;
    public final String title;
    public final String price;
//    public final String childrenInfo;
    public final String date;
    public final String attr;


    HttpRequestValues(
            String productElements,
            String title,
            String price,
//            String childrenInfo,
            String date,
            String attr
    ) {
        this.productElements = productElements;
        this.title = title;
        this.price = price;
//        this.childrenInfo = childrenInfo;
        this.date = date;
        this.attr = attr;
    }
}
