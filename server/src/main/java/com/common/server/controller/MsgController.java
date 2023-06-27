package com.common.server.controller;

import com.common.server.domain.Msg;
import com.common.server.service.MsgService;
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
@RequestMapping("msg")
public class MsgController {
    @Autowired
    private MsgService msgService;

    @GetMapping("list")
    public ResponseEntity<List<Msg>> list(@RequestParam(value = "aid",required = false)Long aid,@RequestParam(value = "uid",required = false)Long uid) {

        return ResponseEntity.ok(msgService.list(aid,uid));
    }

    @PostMapping
    public ResponseEntity<Void> Add(Msg msg) throws IOException {
        msgService.insert(msg);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("changeStatus")
    public ResponseEntity<Void> changeMsgStatus(Msg msg) {
        msgService.changeMsgStatus(msg);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping
    public ResponseEntity<Void> Edit(Msg msg) {
        msgService.update(msg);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> Del(@PathVariable("id") Long id) {
        msgService.del(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("findOne/id/{id}")
    public ResponseEntity<Msg> findOne(@PathVariable("id") Long id) {
        Msg msg = msgService.findOne(id);
        return ResponseEntity.ok(msg);
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
    public ResponseEntity<PageResult<Msg>> page(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc

    ) {

        PageResult<Msg> result = this.msgService.findAllByPage(page, rows, key, sortBy, desc);
        return ResponseEntity.ok(result);
    }

    @GetMapping("newpage")
    public ResponseEntity<TabulatorRes<Msg>> newpage(TabulatorParm tabulatorParm) {

        TabulatorRes<Msg> result = this.msgService.findTabulatorRes(tabulatorParm.getPage(), tabulatorParm.getSize(), tabulatorParm.getKey(), tabulatorParm.getFilter(), tabulatorParm.getSorters());
        return ResponseEntity.ok(result);
    }


}
