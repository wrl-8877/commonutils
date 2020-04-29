package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.domain.FileDto;
import com.example.service.FileService;
import com.example.utils.BinUtils;
import com.example.utils.MultipartFileToFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import static com.example.utils.BinUtils.binToFile;

@Slf4j
@RestController
@RequestMapping("/sdk")
public class FileController {

    @Autowired
    FileService  fileService;

    /**
     * 文件上传
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        byte[] byte11 = file.getBytes();
        System.out.println(new String(byte11,"ISO-8859-1"));

       Long size =  file.getSize();
       String fileName = file.getOriginalFilename();
       System.out.println(file.getContentType());
       File files = MultipartFileToFile.multipartFileToFile(file);
       byte[] bytes = BinUtils.fileToBinArray(files);
       JSONObject json = new JSONObject();
       FileDto fileDto = new FileDto();
       fileDto.setSize(size);
       fileDto.setName(fileName);
       fileDto.setData(new String(bytes,"ISO-8859-1"));
       json.put("fileDto",fileDto);
       String fileId = fileService.upload(json);
       return fileId;
    }


    /**
     * 文件下载
     * @param response
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/download")
    public String  fileDownload(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String responses = fileService.fileDownload(id);
        return responses;
    }
}
