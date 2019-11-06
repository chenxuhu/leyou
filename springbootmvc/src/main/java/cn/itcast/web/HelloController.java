package cn.itcast.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    /*  @Autowired
      private DataSource dataSource;*/
    @GetMapping("hello.do")
    public String hello() {
        return "hello";
    }
}
