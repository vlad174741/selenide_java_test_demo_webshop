package testClass;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class ConfigurationTest {

    @BeforeEach
    @Description("Открытие страницы браузера")
    public void setUp(){
        String sours = "https://demowebshop.tricentis.com/";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000; // Установка тайм-аута ожидания элементов
        open(sours);                                                                                                    //Открытие браузера с адресом указанным в переменной sours

    }

}
