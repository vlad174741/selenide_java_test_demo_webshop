import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;

@ExtendWith(AllureTestWatcher.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Устанавливаем порядок тестов
public class TestDemoWebShop extends BaseTest {
    String testName = "Igor";
    String testLastName = "Gogol";
    String testPhone = "+788899911522";
    String testAddress = "Albert Street 156";
    String testCity = "Ottawa";
    String sours = "https://demowebshop.tricentis.com/";
    String testEmail = "IgorGogol17@gmail.como";
    String testPassword = "12345678";
    String testZipCode = "K1R 6R2";

    @BeforeEach
    void startBrowser() {
        open(sours);                                                                                                    //Открытие браузера с адресом указанным в переменной sours
    }

    @Test
    @Order(1)
    void registerUser() {
        $(".header-links .ico-register").click();                                                             //Нажатие на кнопку Register в header-links
        $(".gender input[id='gender-male']").shouldBe(Condition.visible).click();                             //Выбор radioButton мужского пола
        $("#FirstName").setValue(testName);                                                                   //Вписываем имя в поле First name
        $("#LastName").setValue(testLastName);                                                                //Вписываем фамилию в поле Last name
        $("#Email").setValue(testEmail);                                                                      //Вписываем адрес электронной почты в поле Email
        $("#Password").setValue(testPassword);                                                                //Вписываем пароль в поле Password
        $("#ConfirmPassword").setValue(testPassword);                                                         //Вписываем пароль в поле Confirm password
        $(".buttons input[id='register-button']").click();                                                    //Нажатие на кнопку Register в окне регистрации
        if ($(byText("The specified email already exists")).is(Condition.visible)) {                         //Условие проверяет видна ли надпись "The specified email already exists" на экране
            // Если пользователь уже зарегистрирован
            logInUser();                                                                                                //Функция для входа в личный кабинет пользователя
        } else {
            // Если регистрация прошла успешно
            $(byText("Your registration completed")).shouldBe(Condition.visible);                            //Проверка видимости надписи об успешной регистрации
            $(".buttons input[value='Continue']").shouldBe(Condition.visible).click();                        //Нажатие кнопки continue после удачной регистрации
            logOutUser();                                                                                               //Функция для выхода из учетной записи пользователя
            logInUser();                                                                                                //Функция для входа в учетную запись пользователя
        }
    }





    void logInUser() {                                                                                                  //Функция для выхода из учетной записи пользователя
        $(".header-links .ico-login").click();                                                                //Нажатие на кнопку Log in в header-links
        $("#Email").setValue(testEmail).shouldBe(Condition.visible);                                          //Вписываем адрес электронной почты в поле Email
        $("#Password").setValue(testPassword);                                                                //Вписываем пароль в поле Password
        $("input.login-button").click();                                                                      //Нажатие кнопки Log in в окне авторизации

    }
    void logOutUser() {
        $(".header-links .ico-logout").click();                                                               //Нажатие на кнопку Log in в header-links
    }

    @Test
    @Order(2)
    void buyBook() {
        $("ul.top-menu a[href='/books']").click();                                                            //Нажатие на кнопку Books в header-menu

        ElementsCollection productItems = $$(".item-box");                                                    // Находим все элементы item-box

        for (SelenideElement productItem : productItems) {                                                              // Проходим по каждому item-box
            SelenideElement addToCartButton = productItem.$(".button-2.product-box-add-to-cart-button");             // Ищем кнопку Add to cart внутри текущего item-box

            if (addToCartButton.exists()) {                                                                             // Если кнопка существует
                addToCartButton.click();                                                                                //Кликаем на нее
                sleep(1000);                                                                                 // Добавляем паузу, чтобы DOM успел обновиться
            }
        }

        $(".header-links .ico-cart").click();                                                                 //Нажатие на кнопку Shopping cart в header-links

        if ($(".remove-from-cart").is(Condition.visible)) {                                                   //Проверка видимости .remove-from-cart
            ElementsCollection removeFromCarts = $$(".remove-from-cart");                                     // Находим все элементы remove-from-cart

            for (SelenideElement removeFromCart : removeFromCarts) {                                                    // Проходим по каждому remove-from-cart
                SelenideElement addToCheckBox = removeFromCart.$("input[type='checkbox']");                          // Ищем checkbox внутри текущего remove-from-cart
                addToCheckBox.click();                                                                                  //Убеждаемся что checkBox виден и кликаем на него
                sleep(100);                                                                                  // Добавляем паузу

            }
        }else {
            throw new AssertionError(
                    "Условие не выполнено, тест завершен с ошибкой.(.remove-from-cart не виден)");          //Уведомление об ошибке и прекращения теста
        }

        $("#CountryId").selectOptionByValue("2");                                                          //Выбираем в разделе Country значение "2"
        $("#ZipPostalCode").setValue(testZipCode);                                                            //Вписываем в строку Zip / postal code заготовленный zip код
        $(".inputs [name='estimateshipping']").click();
        $("#termsofservice").click();                                                                         //Нажимаем на checkBox отвечающий за согласие с условиями сервиса
        $("#checkout").click();                                                                               //Нажимаем на кнопку Checkout
        formBillingAddress();

    }

    void formBillingAddress (){
        if ($(byId("billing-address-select")).is(Condition.visible)) {
            $(".buttons [title = 'Continue']").click();

        }else {
            $("#BillingNewAddress_FirstName").setValue(testName);
            $("#BillingNewAddress_LastName").setValue(testLastName);
            $("#BillingNewAddress_Email").setValue(testEmail);
            $("#BillingNewAddress_CountryId").selectOptionByValue("2");
            $("#BillingNewAddress_City").setValue(testCity);
            $("#BillingNewAddress_Address1").setValue(testAddress);
            $("#BillingNewAddress_ZipPostalCode").setValue(testZipCode);
            $("#BillingNewAddress_PhoneNumber").setValue(testPhone);
            $(".buttons [title = 'Continue']").click();

        }
    }

}