package jp.co.toshiba.ppok.config;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import jp.co.toshiba.ppok.utils.JacksonObjectMapper;

/**
 * @author Administrator
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	private static final Logger log = LogManager.getLogger(WebMvcConfig.class);

	/**
	 * SpringMVCフレームワークを拡張するメッセージ・コンバーター
	 *
	 * @param converters コンバーター
	 */
	@Override
	protected void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
		log.info("The advanced message converter configuration is complete.");
		// 創建消息轉換器對象；
		final MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		// 設置對象轉換器，底層使用Jackson將Java對象轉為JSON；
		messageConverter.setObjectMapper(new JacksonObjectMapper());
		// 將上述消息轉換器追加到SpringMVC框架的轉換器容器中；
		converters.add(0, messageConverter);
	}

	/**
	 * 設置靜態資源映射
	 *
	 * @param registry 注冊説明
	 */
	@Override
	protected void addResourceHandlers(final ResourceHandlerRegistry registry) {
		log.info("Static resource mapping started.");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
	}
}
