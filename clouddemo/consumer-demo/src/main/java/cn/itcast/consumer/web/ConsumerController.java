package cn.itcast.consumer.web;

import cn.itcast.client.UserFeignClient;
import cn.itcast.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "queryUserByIdFallback")
class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserFeignClient userFeignClient;
    @GetMapping("/{id}")
    public User queryUserById(@PathVariable("id") Long id){
        User user = userFeignClient.queryUserById(id);
        return user;
    }


  /*  @HystrixCommand(fallbackMethod = "queryUserByIdFallback")*/
 /*
    @GetMapping("/{id}")
    @HystrixCommand//处理时间过长，请求熔断
    public String queryById(@PathVariable("id") Long id){
         //负载均衡后，改变了
        String url="http://cloud-service/user/"+id;
        String user = restTemplate.getForObject(url, String.class);
        return user;
    }
    */
    //熔断，
   /* public String queryUserByIdFallback(){
        return "异常熔断";
    }*/
   /* @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id){
        //根据服务id获取实例
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-service");
        //从实例中取出端口和ip
        ServiceInstance instance = instances.get(0);
        String url="http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;
        System.out.println("url================================"+url);
//        String url="http://localhost:8081/user/"+id;
        //负载均衡后，改变了
        //String url="http://cloud-service/user/"+id;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }*/
}
