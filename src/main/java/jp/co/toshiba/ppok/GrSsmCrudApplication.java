package jp.co.toshiba.ppok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.extern.log4j.Log4j2;

/**
 * grssmcrud application
 *
 * @author Administrator
 * @date 2022-12-12
 */
@Log4j2
@SpringBootApplication
@ServletComponentScan
public class GrSsmCrudApplication {
	public static void main(final String[] args) {
		SpringApplication.run(GrSsmCrudApplication.class, args);
		log.info("Application launched successfully!");
	}
}
