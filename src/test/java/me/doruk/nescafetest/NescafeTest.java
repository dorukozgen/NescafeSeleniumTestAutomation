package me.doruk.nescafetest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import me.doruk.nescafetest.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class NescafeTest {

    @Given("Tarayıcıdan {string} adresine git")
    public void goto_url(String url) {
        TestBase.driver.get(url);
        TestBase.waitPageLoad();
        System.out.println("Başarılı bir şekilde " + url + " adresine gidildi.");
    }

    @And("^Menüde buşunan Kahvelerimiz➝Nescafenizi Bulun butonuna tıkla$")
    public void click_kahvelerimiz_and_nescafeniziBulun() {
        WebElement kahvelerimiz = TestBase.wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/div[1]/div/div/section[2]/div[1]/div/ul[1]/li[1]")
                )
        );
        Actions action = new Actions(TestBase.driver);
        action.moveToElement(kahvelerimiz).build().perform();

        TestBase.sleep(0.5);

        WebElement nescafeniziBulun = TestBase.wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/div[1]/div/div/section[2]/div[2]/div/ul[1]/li[2]/a")
                )
        );
        nescafeniziBulun.click();

        TestBase.sleep(0.5);
    }

    @Then("^Şimdi Başla butonuna tıkla$")
    public void click_simdiBasla() {
        TestBase.wait.until(
                ExpectedConditions.urlToBe("https://www.nescafe.com/tr/size-ozel-nescafe")
        );

        WebElement simdiBasla = TestBase.wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/div[1]/div[1]/div/div[4]/main/section/div/section/form/section/div[1]/div[1]/div/div/div/div/div[2]/a")
                )
        );
        simdiBasla.click();

        TestBase.sleep(0.5);
    }

    @When("^çıkan formu doldurup adımları tamamlayınca$")
    public void do_steps() {

        for (int i = 0; i < 4; i++) {

            int counter = 0;
            List<WebElement> steps = (List<WebElement>) ((JavascriptExecutor) TestBase.driver).executeScript(
                    "return document.querySelectorAll('a[class=\"cell  coffee-finder-option background--black\"]')"
            );

            System.out.println("Step " + (i+1) + " started.");

            while (steps.size() < 1 && counter < 10) {
                TestBase.sleep(1);
                steps = (List<WebElement>) ((JavascriptExecutor) TestBase.driver).executeScript(
                        "return document.querySelectorAll('a[class=\"cell  coffee-finder-option background--black\"]')"
                );

                counter++;
            }

            assert steps.size() > 1 : "Step 1 options not found.";

            TestBase.sleep(5);

            int randInt = (int) (Math.random() * steps.size());
            Actions ac = new Actions(TestBase.driver);
            ac.moveToElement(steps.get(randInt)).click().build().perform();

            TestBase.sleep(5);
        }

    }

    @Then("^sonuç sayfasında doğru sonuçların gösterildiğini kontrol et$")
    public void control_result_page() {
        WebElement oZamanSiz = TestBase.wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/div[2]/div/div/div[4]/main/section/div/section/section/div/div/div[2]/div[1]/div[1]/div[1]/div/span")
                )
        );

        assert oZamanSiz.getText().equals("O zaman siz") : "Wrong result page.";
    }

}
