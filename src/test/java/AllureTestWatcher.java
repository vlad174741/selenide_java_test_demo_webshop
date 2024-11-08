import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;

public class AllureTestWatcher implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        attachScreenshot("Скриншот при ошибке");
    }

    @Attachment(value = "{0}", type = "image/png")
    public static void attachScreenshot(String name) {
        //noinspection ResultOfMethodCallIgnored
        Selenide.screenshot(OutputType.BYTES);
    }
}