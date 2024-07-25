package com.homebaby;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableFeignClients
@Slf4j
public class HomebabyApplication {
    public static void main(String[] args) {

        SpringApplication.run(HomebabyApplication.class, args);

        log.info("\n" +
                "   _____ _____   ______          _______ _   _  _____   _______ ____   _____ ______ _______ _    _ ______ _____  \n" +
                "  / ____|  __ \\ / __ \\ \\        / |_   _| \\ | |/ ____| |__   __/ __ \\ / ____|  ____|__   __| |  | |  ____|  __ \\ \n" +
                " | |  __| |__) | |  | \\ \\  /\\  / /  | | |  \\| | |  __     | | | |  | | |  __| |__     | |  | |__| | |__  | |__) |\n" +
                " | | |_ |  _  /| |  | |\\ \\/  \\/ /   | | | . ` | | |_ |    | | | |  | | | |_ |  __|    | |  |  __  |  __| |  _  / \n" +
                " | |__| | | \\ \\| |__| | \\  /\\  /   _| |_| |\\  | |__| |    | | | |__| | |__| | |____   | |  | |  | | |____| | \\ \\ \n" +
                "  \\_____|_|  \\_\\\\____/   \\/  \\/   |_____|_| \\_|\\_____|    |_|  \\____/ \\_____|______|  |_|  |_|  |_|______|_|  \\_\\\n" +
                "\n" +
                ":: Growing Together Application ::   (v0.0.1)");
    }
}
