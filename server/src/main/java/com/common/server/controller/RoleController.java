package com.common.server.controller;

import com.common.server.domain.Role;
import com.common.server.service.RoleService;
import com.common.server.utils.TabulatorParm;
import com.common.server.utils.TabulatorRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @GetMapping("list")
    public ResponseEntity<List<Role>> list() {
        List<Role> list = roleService.list();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Void> Add(Role role) {
        roleService.insert(role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> Edit(Role role) {
        roleService.update(role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> Del(@PathVariable("id") Long id) {
        roleService.del(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("findOne/id/{id}")
    public ResponseEntity<Role> findOne(@PathVariable("id") Long id) {
        Role role = roleService.findOne(id);
        return ResponseEntity.ok(role);
    }
    @GetMapping("newpage")
    public ResponseEntity<TabulatorRes<Role>> newpage(TabulatorParm tabulatorParm) {

        TabulatorRes<Role> result = this.roleService.findTabulatorRes(tabulatorParm.getPage(), tabulatorParm.getSize(), tabulatorParm.getKey(), tabulatorParm.getFilter(), tabulatorParm.getSorters());
        return ResponseEntity.ok(result);
    }
}
