package com.common.server.controller;

import com.common.server.excelModel.ImportUser;
import com.common.server.service.ExcelUntilService;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.MapMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("excelUtils")
public class ExcelUntilController {
    @Autowired
    private ExcelUntilService excelUntilService;
    /**
     * excel批量导入用户
     */
    @PostMapping("/import")
    @ApiOperation(value = "excel批量导入用户", notes = "excel批量导入用户")
    public ResponseEntity<Void> importUsers(@RequestParam("file") MultipartFile file) {
        excelUntilService.importUsers(file);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/importScore")
    @ApiOperation(value = "excel批量导入分数", notes = "excel批量导入分数")
    public ResponseEntity<Void> importScore(@RequestParam("file") MultipartFile file) {
        excelUntilService.importScore(file);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
