package jp.co.toshiba.ppok.config;

/**
 * MyBatisPlus Pagination
 *
 * @author Administrator
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * 分頁插件屬性設置；
     *
     * @return mybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        final MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        final PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        innerInterceptor.setDbType(DbType.POSTGRE_SQL);
        innerInterceptor.setOverflow(true);
        mybatisPlusInterceptor.addInnerInterceptor(innerInterceptor);
        return mybatisPlusInterceptor;
    }
}
