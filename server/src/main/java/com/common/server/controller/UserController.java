package com.common.server.controller;

import com.common.server.domain.User;
import com.common.server.service.UserService;
import com.common.server.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private HttpServletRequest request;

    @PostMapping("login")
    public ResponseEntity<User> doLogin(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("code") String code) {
        HttpSession session = request.getSession();
        String serverCode = (String) session.getAttribute("yzm");
        System.out.println("serverCodeId:"+session.getId());
//        System.out.println("scode:"+serverCode);
//        System.out.println("code:"+code);

        if (!code.equals(serverCode)) {
//            System.out.println("不一样");
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        User users = userService.login(username, password);
        if (users == null) {
            return ResponseEntity.status(HttpStatus.NOT_EXTENDED).build();
        } else {

            return ResponseEntity.ok(users);
        }

    }

    @GetMapping("list")
    public ResponseEntity<List<User>> list(@RequestParam(value = "rid",required = false)Long rid,@RequestParam(value = "cid",required = false)Long cid) {
        List<User> list = userService.list(rid,cid);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Void> Add(User user) {
        userService.insert(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> Edit(User user) {
        userService.update(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("changwPwd")
    public ResponseEntity<Void> changwPwd(@RequestParam("uid")Long uid,@RequestParam("pwd")String pwd) {
        userService.changwPwd(uid,pwd);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> Del(@PathVariable("id") Long id) {
        userService.del(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("findOne/id/{id}")
    public ResponseEntity<User> findOne(@PathVariable("id") Long id) {
        User user = userService.findOne(id);
        return ResponseEntity.ok(user);
    }
    /**
     * 分页
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<User>> page(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc,
            @RequestParam(value = "rid", required = false) Long rid

    ) {

        PageResult<User> result = this.userService.findAllByPage(page, rows, key, sortBy, desc,rid);
        return ResponseEntity.ok(result);
    }

    @PostMapping("weblogin")
    public ResponseEntity<User> weblogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        User users = userService.login(username, password);
        if (users == null) {
            return ResponseEntity.status(HttpStatus.NOT_EXTENDED).build();
        } else {

            return ResponseEntity.ok(users);
        }

    }
    @GetMapping("/getAccount")
    public ResponseEntity<User> getAccount(@RequestParam("account") String account) {
        User user = userService.getAccount(account);
        if (user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getQues")
    public ResponseEntity<String> getQues(@RequestParam("uid")Long uid){
        String ques = userService.getQues(uid);
        return ResponseEntity.ok(ques);
    }
}
