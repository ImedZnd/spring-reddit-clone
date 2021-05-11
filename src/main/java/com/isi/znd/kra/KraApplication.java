package com.isi.znd.kra;

import com.isi.znd.kra.config.SwaggerConfiguration;
import com.isi.znd.kra.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class KraApplication {
	public static void main(String[] args) {
		SpringApplication.run(KraApplication.class, args);
	}
}
