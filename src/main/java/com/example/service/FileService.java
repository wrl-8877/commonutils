package com.example.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String upload(JSONObject json) throws IOException;

    String fileDownload(String id) throws IOException;
}
