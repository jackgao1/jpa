package com.baidu.controller;

import com.baidu.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author 高鑫
 * @date 2020/10/21 14:41
 */
@RestController
public class MailController {
    private static final String TO1 = "1615522490@qq.com";
    private static final String CONTENT = "１９７５年二、三月间，一个平平常常的日子，细蒙蒙的雨丝夹着一星半点的雪花，正纷纷淋淋地向大地飘洒着。时令已快到惊蛰，雪当然再不会存留，往往还没等落地，就已经消失得无踪无影了。黄土高原严寒而漫长的冬天看来就要过去，但那真正温暖的春天还远远地没有到来。";
    private static final String SUBJECT = "平凡的世界";
    @Autowired
    private MailService mailService;
     @GetMapping("testMail")
    public void testMail(){
//         mailService.sendSimpleMailMessge(TO1, SUBJECT, CONTENT);
         File file = new File("C:\\Users\\mark\\Downloads\\15201403854540.txt");
         System.out.println(txtString(file));
     }
    static class Demo implements Runnable{
        @Override
        public void run() {
            System.out.println("撒旦");
        }
    }

    public String txtString(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
                mailService.sendSimpleMailMessge(TO1, SUBJECT, System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
