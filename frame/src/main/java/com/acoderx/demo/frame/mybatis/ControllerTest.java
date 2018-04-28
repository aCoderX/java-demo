package com.acoderx.demo.frame.mybatis;

import com.acoderx.demo.frame.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xudi on 2018/4/28.
 */
@RestController
public class ControllerTest {
    @Autowired
    private UserDao userDao;
    @GetMapping("/test")
    public String test(){
        return "ss";
    }
    @GetMapping("/test2")
    public String test(@RequestParam int id){
        User user = userDao.getUser(id);
        return user.toString();
    }
}
