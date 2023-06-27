package com.common.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

//验证码控制器
@Controller
@RequestMapping("code")
public class CodeController {
    @Autowired
    private HttpServletRequest request;
    //生成验证码
    @GetMapping("yzm")
    public ResponseEntity<Integer> list()
    {
        int x;//定义两变量
        Random ne=new Random();//实例化一个random的对象ne
        x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999

        HttpSession session = request.getSession();
        System.out.println("serverCodeId:"+session.getId());
        session.removeAttribute("yzm");
        session.setAttribute("yzm",String.valueOf(x));

        return ResponseEntity.ok(x);
    }
}
