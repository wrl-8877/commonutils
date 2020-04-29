package com.example.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.portable.ResponseHandler;

/**
*
*/
/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 *
 * @author 中科软科技 qlujooe
 * @since 2019年6月6日
 */
@Slf4j
public class HttpUtil {

    private static final String USER_AGENT = "user-agent";
    private static final String USER_AGENT_VALUE = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";
    private static final String CONNECTION = "connection";
    private static final String CONNECTION_VALUE = "Keep-Alive";
    private static final String ACCEPT = "accept";
    private static final String UTF8 = "utf-8";
    private static final String ACCEPT_CHARSET = "Accept-Charset";
    private static final String CONTENTTYPE = "contentType";
    private static final String SSL = "ssl";

    protected HttpUtil() {

    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) throws IOException {
        String urlNameString = url;
        if (StringUtils.isNotBlank(param)) {
            urlNameString += "?id=" + param;
        }
        URL realUrl = new URL(urlNameString);
        URLConnection connection = realUrl.openConnection();
        StringBuilder result = new StringBuilder();
        connection.setRequestProperty(USER_AGENT, USER_AGENT_VALUE);
        connection.setRequestProperty(CONNECTION, CONNECTION_VALUE);
        connection.setRequestProperty(ACCEPT, "*/*");
        connection.connect();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        }
        catch (Exception e) {
            log.error("发送GET请求出现异常！", e);
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url) throws IOException {
        return sendGet(url, null);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) throws IOException {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?bytes=" + param;
        URL realUrl = new URL(urlNameString);
        URLConnection conn = realUrl.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty(CONTENTTYPE, UTF8);
        conn.setRequestProperty(ACCEPT_CHARSET, UTF8);
        conn.setRequestProperty(USER_AGENT, USER_AGENT_VALUE);
        conn.setRequestProperty(CONNECTION, CONNECTION_VALUE);
        conn.setRequestProperty(ACCEPT, "*/*");
        try (PrintWriter out = new PrintWriter(conn.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),
                        StandardCharsets.UTF_8))) {
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            out.flush();
            out.print(param);
        }
        catch (Exception e) {
            log.error("发送 POST 请求出现异常！", e);
        }
        return result.toString();
    }

    public static String sendSslPost(String url, String param) {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?bytes =" + param;
        try {
            SSLContext sc = SSLContext.getInstance(SSL);
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            URL console = new URL(urlNameString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestProperty(ACCEPT, "*/*");
            conn.setRequestProperty(CONNECTION, CONNECTION_VALUE);
            conn.setRequestProperty(USER_AGENT, USER_AGENT_VALUE);
            conn.setRequestProperty(ACCEPT_CHARSET, UTF8);
            conn.setRequestProperty(CONTENTTYPE, UTF8);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader indata = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            while (ret != null) {
                ret = indata.readLine();
                if (ret != null && !ret.trim().equals("")) {
                    result.append(ret);
                }
            }
            conn.disconnect();
            indata.close();
        }
        catch (Exception e) {
            log.error("发送SSL POST 请求出现异常！", e);
        }
        return result.toString();
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
            //trust anything
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
            //trust anything
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
    
    /**
     * 
     * <B>方法名称：</B>httpURLPOSTCase<BR>
     * <B>概要说明：</B><BR>
     * @param methodUrl  url
     * @param params  参数
     * @return
     */
    public static String  httpURLPOSTCase(String methodUrl, String params) {
        
        HttpURLConnection connection = null;
        OutputStream out = null;
        BufferedReader reader = null;
        String line = null;
        try {
            URL url = new URL(methodUrl);
            // 根据URL生成HttpURLConnection
            connection = (HttpURLConnection) url.openConnection();
            // 设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true,默认情况下是false
            connection.setDoOutput(true);
            // 设置是否从connection读入，默认情况下是true;
            connection.setDoInput(true); 
            // 设置请求方式为post
            connection.setRequestMethod("POST");
            // post请求不能使用缓存设为false
            connection.setUseCaches(false);
            // 连接主机的超时时间
            connection.setConnectTimeout(3000);
            // 从主机读取数据的超时时间
            connection.setReadTimeout(3000);
            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);
            // 连接复用
            connection.setRequestProperty("connection", "Keep-Alive");
            // 编码格式
            connection.setRequestProperty("charset", "utf-8");
            
            connection.setRequestProperty("Content-Type", "application/json");
            // 建立TCP连接,getOutputStream会隐含的进行connect,所以此处可以不要
            connection.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数
            out = new DataOutputStream(connection.getOutputStream());           
            out.write(params.getBytes());
            out.flush();
            out.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 发送http请求
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuilder result = new StringBuilder();
                // 循环读取流
                while ((line = reader.readLine()) != null) {
                    result.append(line).append(System.getProperty("line.separator"));//
                }
                return result.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }
        return "FAIL";
    }

    public static String  httpURLPOST(String methodUrl, String params) {
        HttpURLConnection connection = null;
        OutputStream out = null;
        BufferedReader reader = null;
        String line = null;
        try {
            URL url = new URL(methodUrl);
            // 根据URL生成HttpURLConnection
            connection = (HttpURLConnection) url.openConnection();
            // 设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true,默认情况下是false
            connection.setDoOutput(true);
            // 设置是否从connection读入，默认情况下是true;
            connection.setDoInput(true);
            // 设置请求方式为post
            connection.setRequestMethod("POST");
            // post请求不能使用缓存设为false
            connection.setUseCaches(false);
            // 连接主机的超时时间
            connection.setConnectTimeout(3000);
            // 从主机读取数据的超时时间
            connection.setReadTimeout(3000);
            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);
            // 连接复用
            connection.setRequestProperty("connection", "Keep-Alive");
            // 编码格式
            connection.setRequestProperty("charset", "utf-8");

            connection.setRequestProperty("Content-Type", "application/json");
            // 建立TCP连接,getOutputStream会隐含的进行connect,所以此处可以不要
            connection.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数
            out = new DataOutputStream(connection.getOutputStream());
            out.write(params.getBytes());
            out.flush();
            out.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 发送http请求
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuilder result = new StringBuilder();
                // 循环读取流
                while ((line = reader.readLine()) != null) {
                    result.append(line).append(System.getProperty("line.separator"));//
                }
                return result.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }
        return null;
    }

}
