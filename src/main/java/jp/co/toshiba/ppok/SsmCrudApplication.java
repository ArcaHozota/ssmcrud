package jp.co.toshiba.ppok;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Ssmcrud application
 *
 * @author Administrator
 * @since  2022-12-12
 */
@Log4j2
@SpringBootApplication
@ServletComponentScan
public class SsmCrudApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SsmCrudApplication.class, args);
		log.info("Application launched successfully!");
	}
}
