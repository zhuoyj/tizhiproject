package com.common.server.controller;

import com.common.server.domain.ActionScore;
import com.common.server.service.ActionScoreService;
import com.common.server.utils.PageResult;
import com.common.server.utils.TabulatorParm;
import com.common.server.utils.TabulatorRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("actionScore")
public class ActionScoreController {
    @Autowired
    private ActionScoreService actionScoreService;

    @GetMapping("list")
    public ResponseEntity<List<ActionScore>> list() {
        return ResponseEntity.ok(actionScoreService.list());
    }
    @GetMapping("limtDatas")
    public ResponseEntity<List<ActionScore>> limtDatas(@RequestParam(value = "aid",required = false)Long aid) {
        return ResponseEntity.ok(actionScoreService.limtDatas(aid));
    }

    @PostMapping
    public ResponseEntity<Void> Add(ActionScore actionScore) {
        actionScoreService.insert(actionScore);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> Edit(ActionScore actionScore) {
        actionScoreService.update(actionScore);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> Del(@PathVariable("id") Long id) {
        actionScoreService.del(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("findOne/id/{id}")
    public ResponseEntity<ActionScore> findOne(@PathVariable("id") Long id) {
        ActionScore actionScore = actionScoreService.findOne(id);
        return ResponseEntity.ok(actionScore);
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
    public ResponseEntity<PageResult<ActionScore>> page(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc

    ) {

        PageResult<ActionScore> result = this.actionScoreService.findAllByPage(page, rows, key, sortBy, desc);
        return ResponseEntity.ok(result);
    }

    @GetMapping("newpage")
    public ResponseEntity<TabulatorRes<ActionScore>> newpage(
            TabulatorParm tabulatorParm,
            @RequestParam(value = "aid",required = false)Long aid,
            @RequestParam(value = "uid",required = false)Long uid,
            @RequestParam(value = "cid",required = false)Long cid
    ) {

        TabulatorRes<ActionScore> result = this.actionScoreService.findTabulatorRes(tabulatorParm.getPage(), tabulatorParm.getSize(), tabulatorParm.getKey(), tabulatorParm.getFilter(), tabulatorParm.getSorters(),aid,uid,cid);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getLv")
    public ResponseEntity<String> getLv(
            @RequestParam("aid")Long aid,
            @RequestParam("score")Double score,
            @RequestParam(value = "cid",required = false)Long cid
            ){
        return ResponseEntity.ok(actionScoreService.getLv(aid,score,cid));
    }

}
