import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pageObject.PageObjectBuyBook;
import pageObject.PageObjectRegister;

import static com.codeborne.selenide.Selenide.*;

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
    String testCountryId = "2";
    private PageObjectRegister pageObjectRegister;
    private PageObjectBuyBook pageObjectBuyBook;

    @BeforeEach
    void startBrowser() {
        pageObjectRegister = new PageObjectRegister();
        pageObjectBuyBook = new PageObjectBuyBook();
        open(sours);                                                                                                    //Открытие браузера с адресом указанным в переменной sours
    }

    @Test
    @Order(1)
    void registerUser() {
        pageObjectRegister.registerForm(testName, testLastName, testEmail, testPassword);

    }








    @Test
    @Order(2)
    void payBook(){
       pageObjectBuyBook.buyBook(testName,testLastName,testEmail,testCountryId,testCity,testAddress,testZipCode,testPhone);
    }


}