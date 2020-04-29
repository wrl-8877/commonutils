package com.example.utils;
import org.springframework.util.FileCopyUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BinUtils {

    /**
     * 文件转为二进制数组
     * @param file
     * @return
     */
    public static byte[] fileToBinArray(File file){
        try {
            InputStream fis = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            return bytes;
        }catch (Exception ex){
            throw new RuntimeException("transform file into bin Array 出错",ex);
        }
    }

    /**
     * 文件转为二进制字符串
     * @param file
     * @return
     */
    public static String fileToBinStr(File file){
        try {
            InputStream fis = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            return new String(bytes,"ISO-8859-1");
        }catch (Exception ex){
            throw new RuntimeException("transform file into bin String 出错",ex);
        }
    }


    /**
     * 二进制字符串转文件
     * @param bin 二进制字符串
     * @param fileName 文件名称
     * @param path
     * @return
     */
    public static File binToFile(String bin,String fileName,String path){
        try {
            File fout = new File(path,fileName);
            fout.createNewFile();
            byte[] bytes1 = bin.getBytes("ISO-8859-1");
            FileCopyUtils.copy(bytes1,fout);
            return fout;
        }catch (Exception ex){
            throw new RuntimeException("transform bin into File 出错",ex);
        }
    }

    /**
     * 文件转为二进制数组
     * 等价于fileToBin
     * @param file 文件
     * @return
     */
    public static byte[] getFileToByte(File file) {
        byte[] by = new byte[(int) file.length()];
        try {
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            byte[] bb = new byte[2048];
            int ch;
            ch = is.read(bb);
            while (ch != -1) {
                bytestream.write(bb, 0, ch);
                ch = is.read(bb);
            }
            by = bytestream.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("transform file into bin Array 出错",ex);
        }
        return by;
    }

    /**
    * 将文件转成base64 字符串
    *
    * @param path文件路径
    * @return *
    * @throws Exception
    */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    /**
     *   byte 转文件 下载到本地
     * @param fileName
     * @param
     */
    public static String conserveFile(String rootPath ,String fileName, String bytes) {
        InputStream inputStream = null;
        InputStream inputStreams = null;
        try {
            String filePath = rootPath+File.separator+fileName;
            // 相对路径
            String relativePath = File.separator+fileName;
            inputStream = new ByteArrayInputStream(bytes.getBytes());
            // 进行解码
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] byt = base64Decoder.decodeBuffer(inputStream);
            inputStreams = new ByteArrayInputStream(byt);
            File folder = new File(rootPath+File.separator); // 创建文件夹
            if (!folder.exists()){
                folder.mkdir();
            }
            Files.copy(inputStreams, Paths.get(filePath));
            return relativePath;
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


    public static void main(String[] args) throws IOException {
      //  String path = "C:\\Users\\chao\\Downloads\\5.jpg";
        File file = new File("C:\\Users\\chao\\Downloads\\5.jpg");
//        String paths = "C:\\Users\\chao\\Downloads\\6.jpg";
        byte[] bytes = getFileToByte(file);
        //byte2File(bytes,paths);
        System.out.println(bytes);
    }

}
