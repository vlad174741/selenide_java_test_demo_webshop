import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith(AllureTestWatcher.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Устанавливаем порядок тестов
public class TestDemoWebShop extends BaseTest {
    String name = "Igor";
    String lastName = "Gogol";
    String sours = "https://demowebshop.tricentis.com/";
    String testEmail = "IgorGogol17@gmail.como";
    String testPassword = "12345678";
    String zipCode = "45678";

    @BeforeEach
    void startBrowser() {
        open(sours);                                                                                                    //Открытие браузера с адресом указанным в переменной sours
    }

    @Test
    @Order(1)
    void registerUser() {
        $(".header-links .ico-register").click();                                                             //Нажатие на кнопку Register в header-links
        $(".gender input[id='gender-male']").shouldBe(Condition.visible).click();                             //Выбор radioButton мужского пола
        $("#FirstName").setValue(name);                                                                       //Вписываем имя в поле First name
        $("#LastName").setValue(lastName);                                                                    //Вписываем фамилию в поле Last name
        $("#Email").setValue(testEmail);                                                                      //Вписываем адрес электронной почты в поле Email
        $("#Password").setValue(testPassword);                                                                //Вписываем пароль в поле Password
        $("#ConfirmPassword").setValue(testPassword);                                                         //Вписываем пароль в поле Confirm password
        $(".buttons input[id='register-button']").click();                                                    //Нажатие на кнопку Register в окне регистрации
    }


}
