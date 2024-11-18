package pageObject;

import com.codeborne.selenide.Condition;


import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class PageObjectRegister {



    public void registerForm(String name, String lastName, String email, String password){
        $(".header-links .ico-register").click();                                                             //Нажатие на кнопку Register в header-links
        $(".gender input[id='gender-male']").shouldBe(Condition.visible).click();                             //Выбор radioButton мужского пола
        $(TestElements.nameInputFiled).setValue(name);                                                                  //Вписываем имя в поле First name
        $(TestElements.lastInputFiled).setValue(lastName);                                                              //Вписываем фамилию в поле Last name
        $(TestElements.emailInputFiled).setValue(email);                                                                //Вписываем адрес электронной почты в поле Email
        $(TestElements.passwordInputFiled).setValue(password);                                                          //Вписываем пароль в поле Password
        $(TestElements.confirmPasswordInputFiled).setValue(password);                                                   //Вписываем пароль в поле Confirm password
        $(".buttons input[id='register-button']").click();                                                    //Нажатие на кнопку Register в окне регистрации
        if ($(byText("The specified email already exists")).is(Condition.visible)) {                         //Условие проверяет видна ли надпись "The specified email already exists" на экране
            // Если пользователь уже зарегистрирован
            logInUser(email, password);                                                                                 //Функция для входа в личный кабинет пользователя
        } else {
            // Если регистрация прошла успешно
            $(byText("Your registration completed")).shouldBe(Condition.visible);                            //Проверка видимости надписи об успешной регистрации
            $(".buttons input[value='Continue']").shouldBe(Condition.visible).click();                        //Нажатие кнопки continue после удачной регистрации
            logOutUser();                                                                                               //Функция для выхода из учетной записи пользователя
            logInUser(email, password);                                                                                 //Функция для входа в учетную запись пользователя
        }
    }

    public void logInUser(String email, String password) {                                                              //Функция для выхода из учетной записи пользователя
        $(".header-links .ico-login").click();                                                                //Нажатие на кнопку Log in в header-links
        $(TestElements.emailInputFiled).setValue(email);                                                                 //Вписываем адрес электронной почты в поле Email
        $(TestElements.passwordInputFiled).setValue(password);                                                           //Вписываем пароль в поле Password
        $("input.login-button").click();                                                                      //Нажатие кнопки Log in в окне авторизации

    }

    public void logOutUser() {
        $(".header-links .ico-logout").click();                                                               //Нажатие на кнопку Log in в header-links
    }

    public void formBillingAddress (String name, String lastName, String email, String countryId,String city,
                             String address, String zipCode, String phone){                                             //Функция для заполнения формы адреса доставки

        $("#BillingNewAddress_FirstName").setValue(name);
        $("#BillingNewAddress_LastName").setValue(lastName);
        $("#BillingNewAddress_Email").setValue(email);
        $("#BillingNewAddress_CountryId").selectOptionByValue(countryId);
        $("#BillingNewAddress_City").setValue(city);
        $("#BillingNewAddress_Address1").setValue(address);
        $("#BillingNewAddress_ZipPostalCode").setValue(zipCode);
        $("#BillingNewAddress_PhoneNumber").setValue(phone);

        if ($(byValue("Save")).is(Condition.visible)) {
            $(".buttons [value = 'Save'").click();
        }
    }

}
