package com.banking.opb.api;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloController {

    @GetMapping("/")
    public Map<String, Date> Home() {       
    	return Collections.singletonMap("response", new Date());
    }

}
