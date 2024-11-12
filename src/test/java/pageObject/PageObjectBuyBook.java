package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class PageObjectBuyBook {

    PageObjectRegister pageObject = new PageObjectRegister();

    public void buyBook(String name, String lastName, String email, String countryId,String city,
                 String address, String zipCode, String phone) {
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

        $("#CountryId").selectOptionByValue("2");                                                          //Выбираем в разделе Country значение "2"
        $("#ZipPostalCode").setValue(zipCode);                                                            //Вписываем в строку Zip / postal code заготовленный zip код
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

        if (!$(byId("billing-address-select")).is(Condition.visible)) {
            pageObject.formBillingAddress(name,lastName,email,countryId,city,address,zipCode,phone);
        }


        // Находим все кнопки "Continue" на каждом этапе и кликаем на них
        ElementsCollection stepItems = $$(TestElements.continueButton);
        for (SelenideElement button : stepItems) {
            button.shouldBe(Condition.visible).click(); // Убедиться, что кнопка видима, и нажать на нее
        }






    }




}
