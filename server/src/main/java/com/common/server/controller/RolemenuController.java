package com.common.server.controller;

import com.common.server.domain.Rolemenu;
import com.common.server.service.RolemenuService;
import com.common.server.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("rolemenu")
public class RolemenuController {
    @Autowired
    private RolemenuService rolemenuService;

    @GetMapping("list")
    public ResponseEntity<List<Rolemenu>> list() {
        return ResponseEntity.ok(rolemenuService.list());
    }

    @PostMapping
    public ResponseEntity<Void> Add(Rolemenu rolemenu) {
        rolemenuService.insert(rolemenu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> Edit(Rolemenu rolemenu) {
        rolemenuService.update(rolemenu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> Del(@PathVariable("id") Long id) {
        rolemenuService.del(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/delByrm/{rid}/{mid}")
    public ResponseEntity<Void> delByrm(@PathVariable("rid") Long rid,@PathVariable("mid") Long mid) {
        rolemenuService.delByrm(rid,mid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("findOne/id/{id}")
    public ResponseEntity<Rolemenu> findOne(@PathVariable("id") Long id) {
        Rolemenu rolemenu = rolemenuService.findOne(id);
        return ResponseEntity.ok(rolemenu);
    }

    /**
     * 分页
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Rolemenu>> page(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc

    ) {

        PageResult<Rolemenu> result = this.rolemenuService.findAllByPage(page, rows, key, sortBy, desc);
        return ResponseEntity.ok(result);
    }


}
