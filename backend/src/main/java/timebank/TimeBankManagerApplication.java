package timebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;

@SpringBootApplication(exclude = SessionAutoConfiguration.class)
public class TimeBankManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(TimeBankManagerApplication.class, args);
  }
}
