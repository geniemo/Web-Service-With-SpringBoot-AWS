package study.spring.WebServiceWithSpringBootAWS.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.spring.WebServiceWithSpringBootAWS.web.dto.HelloResponseDto;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto hello(@RequestParam(name = "name") String name, @RequestParam(name = "amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}