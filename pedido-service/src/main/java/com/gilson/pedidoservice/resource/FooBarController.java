package com.gilson.pedidoservice.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido-service/")
@Validated
public class FooBarController {

    private Logger logger = LoggerFactory.getLogger(FooBarController.class);


    @GetMapping("/foo-bar")
    public String fooBar() {
        logger.info("Request FooBar is received!");
        return "Foo-Bar!!!";
    }
}
//  @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
//  @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
//  @RateLimiter(name = "default")
//  @Bulkhead(name = "default")
//       var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
//       return response.getBody();
//
//    public String fallbackMethod(Exception ex){
//    return "fallbackMethod foo-bar!!!";
