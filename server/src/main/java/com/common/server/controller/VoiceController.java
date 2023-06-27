package com.common.server.controller;

import com.common.server.domain.Voice;
import com.common.server.service.VoiceService;
import com.common.server.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("voice")
public class VoiceController {
    @Autowired
    private VoiceService voiceService;

    @GetMapping("list")
    public ResponseEntity<List<Voice>> list() {
        List<Voice> list = voiceService.list();
        return ResponseEntity.ok(list);
    }
    @GetMapping("TopDate")
    public ResponseEntity<List<Voice>> TopDate() {
        List<Voice> list = voiceService.TopDate();
        return ResponseEntity.ok(list);
    }
    @PostMapping
    public ResponseEntity<Void> Add(Voice voice) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        voice.setTimes(sdf.format(new Date()));

        voiceService.insert(voice);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> Edit(Voice voice) {
        voiceService.update(voice);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> Del(@PathVariable("id") Long id) {
        voiceService.del(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("findOne/id/{id}")
    public ResponseEntity<Voice> findOne(@PathVariable("id") Long id) {
        Voice voice = voiceService.findOne(id);
        return ResponseEntity.ok(voice);
    }
    @GetMapping("page")
    public ResponseEntity<PageResult<Voice>> page(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc

    ) {

        PageResult<Voice> result = this.voiceService.findAllByPage(page, rows, key, sortBy, desc);
        return ResponseEntity.ok(result);
    }
}
