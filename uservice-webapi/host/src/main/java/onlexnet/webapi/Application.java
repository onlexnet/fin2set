package onlexnet.webapi;

import java.lang.System.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application {

  public static void main(String[] args) {

    SpringApplication.run(Application.class, args);
  }

}
