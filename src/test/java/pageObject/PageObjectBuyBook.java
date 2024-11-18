package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.extension.ExtendWith;
import testClass.AllureTestWatcher;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

@ExtendWith(AllureTestWatcher.class)
public class PageObjectBuyBook {

    PageObjectRegister pageObject = new PageObjectRegister();


    public void selectBooks() {
        $("ul.top-menu a[href='/books']").click();                                                            //Нажатие на кнопку Books в header-menu

        ElementsCollection productItems = $$(".item-box");                                                    // Находим все элементы item-box

        for (SelenideElement productItem : productItems) {                                                              // Проходим по каждому item-box
            SelenideElement addToCartButton = productItem.$(".button-2.product-box-add-to-cart-button");             // Ищем кнопку Add to cart внутри текущего item-box

            if (addToCartButton.exists()) {                                                                             // Если кнопка существует
                addToCartButton.click();                                                                                //Кликаем на нее
                sleep(1000);                                                                                 // Добавляем паузу, чтобы DOM успел обновиться
            }
        }
    }

    public void shoppingCartDesign(String name, String lastName, String email, String countryId,String city,
                                   String address, String zipCode, String phone){
        $(".header-links .ico-cart").click();                                                                 //Нажатие на кнопку Shopping cart в header-links
        $("#CountryId").selectOptionByValue("2");                                                          //Выбираем в разделе Country значение "2"
        $("#ZipPostalCode").setValue(zipCode);                                                                //Вписываем в строку Zip / postal code заготовленный zip код
        $(".inputs [name='estimateshipping']").click();

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


        $("#termsofservice").click();                                                                         //Нажимаем на checkBox отвечающий за согласие с условиями сервиса
        $("#checkout").click();                                                                               //Нажимаем на кнопку Checkout

        if (!$(byId("billing-address-select")).is(Condition.visible)) {                                                 //Если селектор выбора адресов не виден
            pageObject.formBillingAddress(name,lastName,email,countryId,city,address,zipCode,phone);                    //Функция для заполнения полей в форме добавления адреса доставки
        }


        // Находим все кнопки "Continue" на каждом этапе и кликаем на них
        ElementsCollection stepItems = $$(TestElements.continueButton);
        for (SelenideElement button : stepItems) {
            button.shouldBe(Condition.visible).click(); // Убедиться, что кнопка видима, и нажать на нее
        }

        $(".buttons input[value='Confirm']").click();                                                         //Нажатие на кнопку подтверждения оформления заказа
        $(byText("Click here for order details.")).click();                                                  //Переход к форме с информацией о заказе
        AllureTestWatcher.attachScreenshot("Форма информации о заказе.");                                         //Скриншот формы
        back();                                                                                                         //Возврат к предыдущей странице
        $(".buttons input[value='Continue']").shouldBe(Condition.visible).click();                           //Нажатие кнопки Continue для перехода на главную страницу

    }




}
