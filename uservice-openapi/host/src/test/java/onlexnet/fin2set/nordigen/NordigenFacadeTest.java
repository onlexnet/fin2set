package onlexnet.fin2set.nordigen;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

import onlexnet.fin2set.db.DbExtension;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(DbExtension.class)
public class NordigenFacadeTest extends ScriptBase {

  @Autowired
  NordigenFacade nordigen;

  // Host system is missing dependencies to run browsers.
  // Read logs to install missing dependencies.
  @Test
  void loadData() {
    var bankId = "SANDBOXFINANCE_SFIN0000";
    var link = nordigen.createLinkToConnect(bankId);

    page.navigate(link.toString(), new Page.NavigateOptions()
        .setTimeout(5000)
        .setWaitUntil(WaitUntilState.NETWORKIDLE));
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
