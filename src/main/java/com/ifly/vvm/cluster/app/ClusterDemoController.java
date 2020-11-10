package com.ifly.vvm.cluster.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClusterDemoController {
    @Autowired
    private DemoService service;

    @GetMapping("/hello/{name}")
    public String apiHello(@PathVariable String name) throws Exception {
        return service.sayHello(name);
    }
}
