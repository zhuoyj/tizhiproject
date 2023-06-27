package com.common.server.controller;

import com.common.server.domain.UserClass;
import com.common.server.service.UserClassService;
import com.common.server.service.UserService;
import com.common.server.utils.PageResult;
import com.common.server.utils.TabulatorParm;
import com.common.server.utils.TabulatorRes;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("userClass")
public class UserClassController {
    @Autowired
    private UserClassService userClassService;
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public ResponseEntity<List<UserClass>> list(@RequestParam(value = "cid",required = false)Long cid) {

        return ResponseEntity.ok(userClassService.list(cid));
    }

    @PostMapping
    public ResponseEntity<Void> Add(UserClass userClass) {
    userClassService.insert(userClass);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> Edit(UserClass userClass) {
        userClassService.update(userClass);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> Del(@PathVariable("id") Long id) {
            userClassService.del(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("findOne/id/{id}")
    public ResponseEntity<UserClass> findOne(@PathVariable("id") Long id) {
            UserClass userClass = userClassService.findOne(id);
        return ResponseEntity.ok(userClass);
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
    public ResponseEntity<PageResult<UserClass>> page(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc

    ) {

        PageResult<UserClass> result = this.userClassService.findAllByPage(page, rows, key, sortBy, desc);
        return ResponseEntity.ok(result);
    }
@GetMapping("newpage")
public ResponseEntity<TabulatorRes<UserClass>> newpage(TabulatorParm tabulatorParm) {

        TabulatorRes<UserClass> result = this.userClassService.findTabulatorRes(tabulatorParm.getPage(), tabulatorParm.getSize(), tabulatorParm.getKey(), tabulatorParm.getFilter(), tabulatorParm.getSorters());
        return ResponseEntity.ok(result);
        }

}
