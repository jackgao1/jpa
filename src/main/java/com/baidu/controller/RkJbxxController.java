package com.baidu.controller;

import com.baidu.service.RkJbxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mark
 */
@RestController
public class RkJbxxController {
    @Autowired
    private RkJbxxService rkJbxxService;

}
