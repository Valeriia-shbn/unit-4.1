import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class LoginTest {
    public String generateDate() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return tomorrow.format(formatter);
    }

    @Test
    public void shouldCompleteRegistration() {

//        Configuration.browser = "chrome";
//        Configuration.headless = true;

        open("http://localhost:9999");


        $("input[placeholder='Город']").setValue("Екатеринбург");

        $("input[placeholder='Дата встречи']").setValue(generateDate());

        $("input[name='name']").setValue("Валерия Шубина");

        $("input[name='phone']").setValue("+79122878771");

        $("[data-test-id='agreement']").click();

        $(".button__text").click();

        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(30));

    }

}
