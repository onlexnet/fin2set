package onlexnet.fin2set.nordigen;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

@SpringBootTest
@ActiveProfiles("dev")
public class NordigenFacadeTest {

  @Autowired
  NordigenFacade nordigen;

  @Test
  void loadData() {
    var banks = nordigen.getListBanks("PL");
    // var mBank = banks.stream().filter(it -> it.getName().equals("mBank
    // Retail")).findFirst().get();
    // var link = nordigen.createLinkToConnect(mBank.getId());
    var link = nordigen.createLinkToConnect("SANDBOXFINANCE_SFIN0000");

    try (Playwright playwright = Playwright.create()) {

      // Host system is missing dependencies to run browsers. ║
      // ║ Please install them with the following command: ║
      // ║ ║
      // ║ sudo mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D
      // exec.args="install-deps" ║
      // ║ ║
      // ║ Alternatively, use apt: ║
      // ║ sudo apt-get install libgbm1 ║
      // ║ ║
      // ║ [3 Playwright Team

      var browser = playwright.chromium().launch();
      var page = browser.newPage();
      page.navigate(link.toASCIIString());
      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
      var a = page.locator("INPUT[type='submit']");
      var items = a.all();
      var i = items.get(0);
      i.click();
      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example2.png")));
    }

  }
}
