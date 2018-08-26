package team.redrock.downloadtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class DownloadtoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(DownloadtoolApplication.class, args);
    }
}
