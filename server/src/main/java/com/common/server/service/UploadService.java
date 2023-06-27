package com.common.server.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class UploadService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif", "text/plain");

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    public String UploadImg(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        // 校验文件的类型
//        String contentType = file.getContentType();
//
//        if (!CONTENT_TYPES.contains(contentType)) {
//            // 文件类型不合法，直接返回null
//            LOGGER.info("文件类型不合法：{}", originalFilename);
//            return null;
//        }

        try {
            // 校验文件的内容
//            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
//            if (bufferedImage == null) {
//                LOGGER.info("文件内容不合法：{}", originalFilename);
//                return null;
//            }
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            //目录生成与新文件名
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String newFileName = sdf.format(d) + "." + suffix;

            // 保存到服务器
            file.transferTo(new File("E:\\uploads\\files\\" + newFileName));

            // 生成url地址，返回
            return "http://localhost/" + newFileName;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", originalFilename);
            e.printStackTrace();
        }
        return null;
    }


}

