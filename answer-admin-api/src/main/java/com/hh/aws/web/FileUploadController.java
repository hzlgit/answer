package com.hh.aws.web;

import com.alibaba.fastjson.JSON;
import com.hh.aws.bean.ResponseData;
import com.hh.aws.utils.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.UUID;

/**
 * 文件上传
 */
@RestController
public class FileUploadController {
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") CommonsMultipartFile file) throws Exception {
        String filePath = "/Users/upload";
        //用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        long  endTime=System.currentTimeMillis();
        System.out.println("采用流上传的方式的运行时间："+String.valueOf(endTime-startTime)+"ms");
        ResponseData responseData = new ResponseData();
        responseData.setMsg("上传成功！");
        responseData.setData(fileName);
        return JSON.toJSONString(responseData);
    }
}
