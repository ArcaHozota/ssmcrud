package jp.co.toshiba.ppok;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * grssmcrud application
 *
 * @author Administrator
 * @date 2022-12-12
 */
@SpringBootApplication
@ServletComponentScan
public class GrSsmCrudApplication {

	private static final Logger log = LogManager.getLogger(GrSsmCrudApplication.class);

	public static void main(final String[] args) {
		SpringApplication.run(GrSsmCrudApplication.class, args);
		log.info("Application launched successfully!");
	}
}
