package onlexnet.fin2set.nordigen;

import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import onlexnet.fin2set.db.DbExtension;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(DbExtension.class)
public class NordigenFacadeTest {

  @Autowired
  NordigenFacade nordigen;

  static Playwright playwright;
  static Browser browser;

  static BrowserContext context;
  static Page page;

  @BeforeAll
  static void launchBrowser() {
    playwright = Playwright.create();
    browser = playwright.chromium().launch();
  }

  @BeforeEach
  static void createContextAndPage() {
    context = browser.newContext();
    page = context.newPage();
  }

  @AfterAll
  static void closeBrowser() {
    browser.close();
    playwright.close();
  }

  @AfterEach
  static void closeContext() {
    context.close();
  }

  // Host system is missing dependencies to run browsers. 
  // Read logs to install missing dependencies.
  @Test
  void loadData() {
    var link = nordigen.createLinkToConnect("SANDBOXFINANCE_SFIN0000");

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
