package onlexnet.fin2set.nordigen;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import onlexnet.fin2set.db.DbExtension;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(DbExtension.class)
public class NordigenFacadeTest {

  @Autowired
  NordigenFacade nordigen;

  @Test
  void loadData() {
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
      
      var browserType = playwright.chromium();
      var browserSettings = new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(2000);
      var browser = browserType.launch(browserSettings);
      var page = browser.newPage();

      page.navigate(link.toString());
      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Step-1 Agreement and information for user.png")));
      page.click("INPUT[type='submit']");
      
      page.waitForSelector("INPUT[type='submit']");
      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Step-2 Bank login site.png")));
      page.click("INPUT[type='submit']");

      page.waitForSelector("text=approve");
      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Step-3 Accept agreement on bank site.png")));
      page.click("text=approve");

      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Step-4 Our page.png")));

    }

  }
}
