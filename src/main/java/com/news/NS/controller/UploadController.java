package com.news.NS.controller;

import com.aliyuncs.exceptions.ClientException;
import com.news.NS.common.ResponseBodyResult;
import com.news.NS.util.AliOSSUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@ResponseBodyResult
@Api(tags="图片上传")
public class UploadController {

    /**
     * 阿里云OSS文件存储
     *
     * @param image
     * @return
     */

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Map<String, Object> upload(MultipartFile image) throws IOException, ClientException {
        log.info("文件上传，文件名：{}", image.getOriginalFilename());

        String url = aliOSSUtils.upload(image);

        log.info("图片上传成功，文件访问的url为：{}", url);

        Map<String, Object> result = new HashMap<>();

        result.put("imageUrl", url);

        return result;
    }
}
