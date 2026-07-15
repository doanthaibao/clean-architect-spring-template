package bao.doan.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ="bao.doan")
public class AppApplication {

  static void main(String[] args) {
    SpringApplication.run(AppApplication.class, args);
  }

}
