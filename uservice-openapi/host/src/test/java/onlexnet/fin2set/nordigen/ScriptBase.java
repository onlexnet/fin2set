package onlexnet.fin2set.nordigen;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class ScriptBase {
  
  protected static Playwright playwright;
  protected static Browser browser;

  protected static BrowserContext context;
  protected static Page page;

  @BeforeAll
  static void launchBrowser() {
    playwright = Playwright.create();
    browser = playwright.chromium().launch();
  }

  @BeforeEach
  void createContextAndPage() {
    context = browser.newContext();
    page = context.newPage();
  }

  @AfterAll
  static void closeBrowser() {
    browser.close();
    playwright.close();
  }

  @AfterEach
  void closeContext() {
    context.close();
  }
}
