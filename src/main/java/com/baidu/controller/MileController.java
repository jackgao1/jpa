package com.baidu.controller;

import com.baidu.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 高鑫
 * @date 2020/10/21 14:41
 */
@RestController
public class MileController {
    private static final String TO1 = "1615522490@qq.com";
    private static final String SUBJECT = "高鑫给你发的第";
    private static final String CONTENT = "hello world";
    @Autowired
    private MailService mailService;
            @GetMapping("testMail")
    public void testMail(){
                for (int i = 0; i < 1000; i++) {
                    mailService.sendSimpleMailMessge(TO1, SUBJECT+i+"条测试邮件", CONTENT);
                }
            }
}
