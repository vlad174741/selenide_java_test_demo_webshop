package testClass;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pageObject.PageObjectBuyBook;
import pageObject.PageObjectRegister;


@ExtendWith(AllureTestWatcher.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Устанавливаем порядок тестов
public class TestDemoWebShop extends ConfigurationTest {
    static Boolean startFullTest = false;
    String testName = "Igor";
    String testLastName = "Gogol";
    String testPhone = "+788899911522";
    String testAddress = "Albert Street 156";
    String testCity = "Ottawa";
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
    }

    @Test
    @Order(1)
    void registerUser() {
        startFullTest = true;
        pageObjectRegister.registerForm(testName, testLastName, testEmail, testPassword);
    }








    @Test
    @Order(2)
    void payBook(){
        System.out.println(startFullTest);
       if (!startFullTest){
           pageObjectRegister.logInUser(testEmail,testPassword);
       }
       pageObjectBuyBook.selectBooks();
       pageObjectBuyBook.shoppingCartDesign(testName,testLastName,testEmail,testCountryId,testCity,testAddress,testZipCode,testPhone);
    }


}