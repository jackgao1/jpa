package com.baidu.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author mark
 */
@RestController
public class TestController {
    @GetMapping("/login")
    public Object insertdic(@RequestParam Map<String, Object> map) {
        return map;
    }

    @PostMapping("/login")
    public Object login(@RequestParam Map<String, Object> map) {
        return map;
    }
}