import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;




public class LoginTest {



    public String generateDate(int addDays) {
        LocalDate tomorrow = LocalDate.now().plusDays(addDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return tomorrow.format(formatter);
    }

    @Test
    public void shouldCompleteRegistration() {
        open("http://localhost:9999");

        String plannedDate = generateDate(5);

        $("input[placeholder='Город']").setValue("Екатеринбург");

        $("[data-test-id='date'] input").doubleClick();

        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);

        $("[data-test-id='date'] input").setValue(plannedDate);

        $("input[name='name']").setValue("Валерия Шубина");

        $("input[name='phone']").setValue("+79122878771");

        $("[data-test-id='agreement']").click();

        $(".button__text").click();

        $("[data-test-id='notification']")
                .shouldBe(visible, Duration.ofSeconds(30))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + plannedDate));

    }

}
