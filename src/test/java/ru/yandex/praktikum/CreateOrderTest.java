package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class CreateOrderTest {
    private OrderClient orderClient;


    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    private final String color;

    public CreateOrderTest(String color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getColorData() {
        return new Object[][]{
                {"BLACK"},
                {"GREY"},
                {""},
                {"BLACK\", \"GREY"}
        };
    }

    @Test
    @DisplayName("Create order and check answer")
    @Description("Создание заказа с цветами BLACK, GRAY, BLACK+GRAY, без указания. " +
            "Проверка сожержания в ответе track и проверка ответа 201")
    public void createOrderCheck() {
        Response response = orderClient.createOrder(color);
        assertEquals("StatusCode is incorrect", 201, response.statusCode());
        assertThat("Order ID is incorrect", response.path("track"), is(CoreMatchers.not(0)));
    }
}