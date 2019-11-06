package cn.itcast.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@PropertySource("classpath:application.properties")
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfig {

//    @Value("${driverClassName}")
//    private String driverClassName;
//    @Value("${url}")
//    private String url;
//    @Value("${username}")
//    private String username;
//    @Value("${Password}")
//    private String Password;

//方法一
   /* @Bean
    public DataSource dataSource(JdbcProperties prop) {
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(prop.getDriverClassName());
        dataSource.setUrl(prop.getUrl());
        dataSource.setUsername(prop.getUsername());
        dataSource.setPassword(prop.getPassword());
        return  dataSource;
    }*/

   //方法二
   @Bean
    @ConfigurationProperties(prefix = "jdbc")
    public DruidDataSource dataSource(){
        return new DruidDataSource();
    }
}
