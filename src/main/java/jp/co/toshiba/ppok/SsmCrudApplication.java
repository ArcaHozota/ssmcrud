package jp.co.toshiba.ppok;

import java.util.logging.LogManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Ssmcrud application
 *
 * @author Administrator
 * @date 2022-12-12
 */
@SpringBootApplication
@ServletComponentScan
public class SsmCrudApplication {

	private static final Logger log = LogManager.getLogger(GrSsmCrudApplication.class);

	public static void main(final String[] args) {
		SpringApplication.run(GrSsmCrudApplication.class, args);
		log.info("Application launched successfully!");
	}
}
