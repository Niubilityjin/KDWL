package com.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired  
    private LoadBalancerClient loadBalancerClient;  

    @RequestMapping(value = "/sub", method = RequestMethod.GET)
    public String sub(@RequestParam Integer a,@RequestParam Integer b) {
    	this.loadBalancerClient.choose("service-B");
        return restTemplate.getForEntity("http://service-B/sub?a="+a+"&b="+b, String.class).getBody();
    	
    }
    
}