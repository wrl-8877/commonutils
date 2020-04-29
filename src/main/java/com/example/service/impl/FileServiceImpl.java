package com.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.service.FileService;
import com.example.utils.HttpUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    String uploadpath ="http://127.0.0.1:8880/file/upload";
    String downloadpath ="http://127.0.0.1:8880/file/download";

    @Override
    public String upload(JSONObject json) throws IOException{
        String fileId = HttpUtil.httpURLPOST(uploadpath,json.toJSONString());
        System.out.println(json.toJSONString());
        return fileId;
    }

    @Override
    public String fileDownload(String id) throws IOException {
        String rootPath = "D:/ruoyi/";
       String massage =  HttpUtil.sendGet(downloadpath,id);
       JSONObject json =JSONObject.parseObject(massage);
       String data = json.getString("data");
       String code = conserveFile(rootPath,"fileName.jpg",data);
       return code;
    }

    /**
     *  byte 转文件 下载到本地
     * @param fileName
     * @param
     */
    public String conserveFile(String path ,String fileName, String bytes) {
        InputStream inputStream = null;
        InputStream inputStreams = null;
        try {
            String tempPath = "Files";
            String filePath = path+tempPath+File.separator+fileName;
            // 相对路径
            String relativePath =File.separator+ tempPath+File.separator+fileName;
            inputStream = new ByteArrayInputStream(bytes.getBytes());
            // 进行解码
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] byt = base64Decoder.decodeBuffer(inputStream);
            inputStreams = new ByteArrayInputStream(byt);
            File folder = new File(path+tempPath+File.separator); // 创建文件夹
            if (!folder.exists()){
                folder.mkdir();
            }
            File file = new File(filePath);
            if (file.exists()) {
                //如果文件存在，则删除文件
                file.delete();
            }
            Files.copy(inputStreams, Paths.get(filePath));
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null && inputStreams != null) {
                try {
                    inputStream.close();
                    inputStreams.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
