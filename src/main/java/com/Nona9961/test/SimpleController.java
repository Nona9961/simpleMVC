package com.Nona9961.test;

import com.Nona9961.annotation.Controller;
import com.Nona9961.annotation.Mapping;

@Controller
@Mapping("/hello")
public class SimpleController {
    @Mapping("/test")
    public String test() {
        return "test well!";
    }
}
