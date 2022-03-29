package ru.yandex.praktikum;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClient {


    public RequestSpecification getBaseSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://qa-scooter.praktikum-services.ru/")
                .build();
    }
}