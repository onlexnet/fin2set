package onlexnet.fin2set.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.host.AuthProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/debug")
class DebugApi {

  private final AuthProvider ap;

  @GetMapping("whoAmI")
  String whoAmI() {
    return ap.current().toString();
  }

}
