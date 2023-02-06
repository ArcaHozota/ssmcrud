package jp.co.toshiba.ppok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.extern.slf4j.Slf4j;

/**
 * grssmcrud application
 *
 * @author Administrator
 * @date 2022-12-12
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
public class GrSsmCrudApplication {
	public static void main(final String[] args) {
		SpringApplication.run(GrSsmCrudApplication.class, args);
		log.info("Application launched successfully!");
	}
}
