package com.common.server.controller;

import com.common.server.domain.Actions;
import com.common.server.service.ActionsService;
import com.common.server.utils.PageResult;
import com.common.server.utils.TabulatorParm;
import com.common.server.utils.TabulatorRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("actions")
public class ActionsController {
    @Autowired
    private ActionsService actionsService;

    @GetMapping("list")
    public ResponseEntity<List<Actions>> list() {
        return ResponseEntity.ok(actionsService.list());
    }

    @PostMapping
    public ResponseEntity<Void> Add(Actions actions) {
        actionsService.insert(actions);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> Edit(Actions actions) throws IOException {
        actionsService.update(actions);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> Del(@PathVariable("id") Long id) {
        actionsService.del(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("findOne/id/{id}")
    public ResponseEntity<Actions> findOne(@PathVariable("id") Long id) {
        Actions actions = actionsService.findOne(id);
        return ResponseEntity.ok(actions);
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
    public ResponseEntity<PageResult<Actions>> page(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc

    ) {

        PageResult<Actions> result = this.actionsService.findAllByPage(page, rows, key, sortBy, desc);
        return ResponseEntity.ok(result);
    }

    @GetMapping("newpage")
    public ResponseEntity<TabulatorRes<Actions>> newpage(
            TabulatorParm tabulatorParm,
            @RequestParam(value = "tid", required = false) Integer tid,
            @RequestParam(value = "uid", required = false) Long uid,
            @RequestParam(value = "status", required = false) Integer status
    ) {

        TabulatorRes<Actions> result = this.actionsService.findTabulatorRes(tabulatorParm.getPage(), tabulatorParm.getSize(), tabulatorParm.getKey(), tabulatorParm.getFilter(), tabulatorParm.getSorters(), tid,uid,status);
        return ResponseEntity.ok(result);
    }

}
