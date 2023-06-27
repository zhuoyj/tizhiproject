package com.common.server.controller;

import com.common.server.domain.Sys;
import com.common.server.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("sys")
public class SysController {
    @Autowired
    private SysService sysService;

    @GetMapping("list")
    public ResponseEntity<List<Sys>> list() {
        List<Sys> list = sysService.list();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Void> Add(Sys sys) {
        sysService.insert(sys);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> Edit(Sys sys) {
        sysService.update(sys);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> Del(@PathVariable("id") Long id) {
        sysService.del(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("findOne/id/{id}")
    public ResponseEntity<Sys> findOne(@PathVariable("id") Long id) {
        Sys sys = sysService.findOne(id);
        return ResponseEntity.ok(sys);
    }
}
