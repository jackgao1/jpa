package com.baidu.controller;

import com.baidu.entity.repository.UserRepository;
import com.baidu.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("findAll")
    public Page findAll(@RequestParam Map<String, String> params, Pageable pageable) {
        return userRepository.findAll(new QueryBuilder(params), pageable);
    }
//    @GetMapping("findByNameMatch")
//    List<User> findByNameMatch(){
//        userRepository.findByNameMatch(name,)
//    }
}
