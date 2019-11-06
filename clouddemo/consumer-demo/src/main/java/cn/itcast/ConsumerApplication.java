package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//@EnableHystrix//服务降级EnableCircuitBreaker代替
//@EnableEurekaClient//使用服务
//@SpringBootApplication//运行
//@EnableCircuitBreaker//服务熔断

@SpringCloudApplication//代替上面的上个注解
@EnableFeignClients // 开启Feign功能
public class ConsumerApplication {
    @Bean
    @LoadBalanced//负载均衡，轮询
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }
}
