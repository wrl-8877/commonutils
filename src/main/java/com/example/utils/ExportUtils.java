package com.example.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;


/**
 * JSON数据写入json文件
 */
public class ExportUtils {

    // 缓冲区大小
    private final static int buffer_size = 1024;

    // 日志格式工具
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    // 小数的格式化工具,设置最大小数位为10
    private final static NumberFormat numFormatter = NumberFormat.getNumberInstance();
    static {
        numFormatter.setMaximumFractionDigits(10);
    }

    // 换行符
    @SuppressWarnings("restriction")
    private final static String lineSeparator = java.security.AccessController
            .doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));

    /**
     * 以指定编码格式写入JSON字符串到JSON文件
     *
     * @param jsonStr        要输出的JSON字符串
     * @param filePath        JSON文件的具体路径
     * @param charsetName    UTF-8
     * @throws Exception
     */
    public static void writeJsonStr2JsonFile(String jsonStr, String filePath, String charsetName) throws Exception {
        // JSON字符串格式化
        jsonStr = JsonFormat.formatJson(jsonStr);
        // 将格式化后的JSON字符串以指定编码写入文件
        ExportUtils.writeString2SimpleFile(jsonStr, filePath, charsetName);

    }


    /**
     * 以指定编码格式写入字符串到简单文件
     *
     * @param str            要输出的字符串
     * @param filePath        简单文件的具体路径
     * @param charsetName    UTF-8 | GB2312
     * @throws Exception
     */
    public static void writeString2SimpleFile(String str, String filePath, String charsetName) throws Exception {
        BufferedWriter out = null;
        try {
            File file = new File(filePath);
            createNewFileIfNotExists(file);
            OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file), charsetName);
            out = new BufferedWriter(os, ExportUtils.buffer_size);
            out.write(str);
            out.flush();
        } finally {
            ExportUtils.close(out);
        }
    }

    /**
     * 如果文件不存在,创建一个新文件
     */
    private static void createNewFileIfNotExists(File file) throws IOException {
        if (!file.exists()) {
            // 创建目录
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            // 创建文件
            file.createNewFile();
        }
    }
    /**
     * 关闭输出流
     */
    private static void close(Writer out) {
        if (null != out) {
            try {
                out.close();
            } catch (IOException e) {
                // e.printStackTrace();
            }
        }
    }
    /**
     * JSON字符串的格式化工具
     */
    static class JsonFormat {
        // 返回格式化JSON字符串。
        public static String formatJson(String json) {
            StringBuffer result = new StringBuffer();
            int length = json.length();
            int number = 0;
            char key = 0;
            for (int i = 0; i < length; i++) {
                key = json.charAt(i);
                if ((key == '[') || (key == '{')) {
                    if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                        result.append('\n');
                        result.append(indent(number));
                    }
                    result.append(key);
                    result.append('\n');
                    number++;
                    result.append(indent(number));
                    continue;
                }
                if ((key == ']') || (key == '}')) {
                    result.append('\n');
                    number--;
                    result.append(indent(number));
                    result.append(key);
                    if (((i + 1) < length) && (json.charAt(i + 1) != ',')) {
                        result.append('\n');
                    }
                    continue;
                }
                if ((key == ',') && (json.charAt(i+1) =='"')) {
                    result.append(key);
                    result.append('\n');
                    result.append(indent(number));
                    continue;
                }
                result.append(key);
            }
            return result.toString();
        }
        // 单位缩进字符串,3个空格。
        private static String SPACE = "   ";
        // 返回指定次数(number)的缩进字符串。每一次缩进一个单个的SPACE。
        private static String indent(int number) {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < number; i++) {
                result.append(SPACE);
            }
            return result.toString();
        }
    }

}
