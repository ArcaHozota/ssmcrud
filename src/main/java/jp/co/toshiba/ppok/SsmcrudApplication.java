package jp.co.toshiba.ppok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import jp.co.toshiba.ppok.utils.Messages;
import lombok.extern.log4j.Log4j2;

/**
 * Ssmcrudアプリケーション
 *
 * @author Administrator
 * @since 2022-12-12
 */
@Log4j2
@SpringBootApplication
@ServletComponentScan
public class SsmcrudApplication {
	public static void main(final String[] args) {
		SpringApplication.run(SsmcrudApplication.class, args);
		log.info(Messages.MSG003);
	}
}
