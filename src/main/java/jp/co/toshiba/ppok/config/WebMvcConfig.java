package jp.co.toshiba.ppok.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * SpringMVCフレームワークを拡張するメッセージ・コンバーター
     *
     * @param converters コンバーター
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("拡張メッセージコンバーターの設置は完了しました。");
        // 創建消息轉換器對象；
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
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
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("静的リソースのマッピングが開始しました。");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/jquery/**").addResourceLocations("classpath:/static/jquery/");
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("classpath:/static/bootstrap/");
    }
}
