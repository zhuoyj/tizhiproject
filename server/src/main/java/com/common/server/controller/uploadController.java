package com.common.server.controller;
import com.common.server.service.UploadService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("upload")
public class uploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("image")
    public ResponseEntity<String> uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
        //文件信息获取
        String fileName = file.getOriginalFilename();
        long size = file.getSize() / 1024; //单位 B>KB
        String contentType = file.getContentType();
        File directory=new File("../server/src/main/resources");
        String reportPath = directory.getCanonicalPath();
        String resource =reportPath+"\\imgs";
        String upload_fspath_base = resource;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //目录生成与新文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newFileName = sdf.format(d) + "." + suffix;

        File destDir = new File(upload_fspath_base);// /xxx/upload/20191220
        if (!destDir.exists()) {
            destDir.mkdirs(); //注意不是mkdir
        }


        file.transferTo(new File(destDir, newFileName));

        String url = "http://localhost:10086/imgs/" + newFileName;

        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }
    @PostMapping("uploadimage")
    @ResponseBody
    public Map<String, Object> uploadimage(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        //文件信息获取
        String fileName = file.getOriginalFilename();
        long size = file.getSize() / 1024; //单位 B>KB
        String contentType = file.getContentType();
        File directory=new File("../server/src/main/resources");
        String reportPath = directory.getCanonicalPath();
        String resource =reportPath+"\\imgs";
        String upload_fspath_base = resource;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //目录生成与新文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newFileName = sdf.format(d) + "." + suffix;

        File destDir = new File(upload_fspath_base);// /xxx/upload/20191220
        if (!destDir.exists()) {
            destDir.mkdirs(); //注意不是mkdir
        }


        file.transferTo(new File(destDir, newFileName));

        String url = "http://localhost:10086/imgs/" + newFileName;

        result.put("fileName", url);

        return result;
    }


    //单文件上传
    @PostMapping("files")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        //文件信息获取
        //文件信息获取
        String fileName = file.getOriginalFilename();
        long size = file.getSize() / 1024; //单位 B>KB
        String contentType = file.getContentType();
        File directory=new File("../server/src/main/resources");
        String reportPath = directory.getCanonicalPath();
        String resource =reportPath+"\\imgs";
        String upload_fspath_base = resource;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //目录生成与新文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newFileName = sdf.format(d) + "." + suffix;

        File destDir = new File(upload_fspath_base);// /xxx/upload/20191220
        if (!destDir.exists()) {
            destDir.mkdirs(); //注意不是mkdir
        }


        file.transferTo(new File(destDir, newFileName));

        String url = "http://localhost:10086/imgs/" + newFileName;

        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }


}
