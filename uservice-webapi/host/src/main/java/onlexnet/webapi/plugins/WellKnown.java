package onlexnet.webapi.plugins;

import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;

@RestController
class WellKnown {
  
  @GetMapping(path = ".well-known/ai-plugin.json", produces = "text/json")
  @SneakyThrows
  String pluginMetadata(HttpServletRequest request) {
    var resource = new ClassPathResource("ai-plugin.json");

    // Ustalenie nagłówków dla Content-Disposition i Content-Type
    var contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    var headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
    headers.add(HttpHeaders.CONTENT_TYPE, contentType);
    
    var content = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
    return content;
  }
}
