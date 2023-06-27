package com.common.server.controller;

import com.common.server.domain.SysMenu;
import com.common.server.domain.bo.MenuEx;
import com.common.server.service.MenuService;
import com.common.server.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("list")
    public ResponseEntity<List<SysMenu>> list() {
        return ResponseEntity.ok(menuService.list());
    }

    @GetMapping("queryByRid")
    public ResponseEntity<List<SysMenu>> list(@RequestParam(value = "rid",required = false)Long rid) {

        return ResponseEntity.ok(menuService.queryByRid(rid));
    }


    @PostMapping
    public ResponseEntity<Void> Add(SysMenu menu) {
        menuService.insert(menu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> Edit(SysMenu menu) {
        menuService.update(menu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> Del(@PathVariable("id") Long id) {
        menuService.del(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("findOne/id/{id}")
    public ResponseEntity<SysMenu> findOne(@PathVariable("id") Long id) {
        SysMenu menu = menuService.findOne(id);
        return ResponseEntity.ok(menu);
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
    public ResponseEntity<PageResult<SysMenu>> page(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc

    ) {

        PageResult<SysMenu> result = this.menuService.findAllByPage(page, rows, key, sortBy, desc);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getMenus/{rid}")
    public ResponseEntity<List<SysMenu>> getMenus(@PathVariable("rid") Long rid){
        List<SysMenu> menuExes=this.menuService.getMenus(rid);
        return ResponseEntity.ok(menuExes);
    }


}
