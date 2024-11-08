import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    @Description("Открытие страницы браузера")
    public void setUp(){
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10000; // Установка тайм-аута ожидания элементов
    }

}
