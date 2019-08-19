package cn.yichuan.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class AdminApplication {

    //http://localhost:8090/#/applications
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}