package com.itcuc.common.utils;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Classname HttpUtils
 * <p></p>
 *
 * @author z
 * @date 2018/7/10
 */
@SuppressWarnings("Duplicates")
@Slf4j
public class HttpUtils {

    /**
     * get请求
     *
     * @return 二进制数据
     */
    public static byte[] doGetBytes(String url) {
        try {
            HttpClient client = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                return EntityUtils.toByteArray(response.getEntity());
            } else {
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            log.error("请求["+url+"]失败",e);
        }
        return null;
    }

    /**
     * get请求
     *
     * @return 二进制数据
     */
    public static byte[] doGetBytes(String url,Map headParam,Map queryParam,Map<String,String> responseHeader) {
        try {
            HttpClient client = HttpClients.createDefault();
            //发送get请求
            StringBuilder sb = new StringBuilder(url);
            if(queryParam != null && queryParam.size() > 0) {
                sb.append("?");
                for (Iterator iter = queryParam.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(queryParam.get(name));
                    sb.append(name).append("=").append(value).append("&");
                }
            }
            HttpGet request = new HttpGet(sb.toString());
            if(headParam != null && headParam.size() > 0) {
                for (Iterator iter = headParam.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(headParam.get(name));
                    request.addHeader(name, value);
                }
            }
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                Header[] headers = response.getAllHeaders();
                for(Header header:headers) {
                    responseHeader.put(header.getName(),header.getValue());
                }
                return EntityUtils.toByteArray(response.getEntity());
            } else {
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            log.error("请求["+url+"]失败",e);
        }
        return null;
    }

    /**
     * get请求
     *
     * @return
     */
    public static Map doGetContent(String url) {
        Map<String,Object> map = Maps.newHashMap();
        try {
            HttpClient client = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                Header[] headers = response.getHeaders("content-type");
                map.put("content-type",headers[0].getValue());
                map.put("content",EntityUtils.toByteArray(response.getEntity()));
                return map;
            } else {
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            log.error("请求["+url+"]失败",e);
        }
        return null;
    }

    /**
     * get请求
     *
     * @return json串
     */
    public static String doGet(String url) {
        try {
            HttpClient client = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                return EntityUtils.toString(response.getEntity());
            } else {
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            log.error("请求["+url+"]失败",e);
        }
        return null;
    }

    /**
     * get请求
     *
     * @return json串
     */
    public static String doGet(String url,Map headParam,Map queryParam) {
        try {
            HttpClient client = HttpClients.createDefault();
            //发送get请求
            StringBuilder sb = new StringBuilder(url);
            if(queryParam != null && queryParam.size() > 0) {
                sb.append("?");
                for (Iterator iter = queryParam.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(queryParam.get(name));
                    sb.append(name).append("=").append(value).append("&");
                }
            }
            url = sb.toString();
            HttpGet request = new HttpGet(url);
            if(headParam != null && headParam.size() > 0) {
                for (Iterator iter = headParam.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(headParam.get(name));
                    request.addHeader(name, value);
                }
            }
            HttpResponse response = client.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                return EntityUtils.toString(response.getEntity());
            } else {
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            log.error("请求["+url+"]失败",e);
        }
        return null;
    }

    /**
     * post请求(用于key-value格式的参数)
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map params) {

        BufferedReader in = null;
        // 定义HttpClient
        HttpClient client = HttpClients.createDefault();
        try {
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            //请求成功
            if (code == HttpStatus.SC_OK) {
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(), "utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String nl = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line).append(nl);
                }

                return sb.toString();
            } else {   //
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
                return null;
            }
        } catch (Exception e) {
            log.error("请求["+url+"]失败",e);

            return null;
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("请求["+url+"]失败",e);
                }
            }
        }
    }
    /**
     * post请求(用于key-value格式的参数)
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url,Map headParam, Map params) {

        BufferedReader in = null;
        // 定义HttpClient
        HttpClient client = HttpClients.createDefault();
        try {
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));
            request.setHeader("Content-Type","application/json");
            if(headParam != null) {
                for (Iterator iter = headParam.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(headParam.get(name));
                    request.addHeader(name, value);
                }
            }
            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }

            request.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            //请求成功
            if (code == HttpStatus.SC_OK) {
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(), "utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String nl = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line).append(nl);
                }

                return sb.toString();
            } else {   //
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
                return null;
            }
        } catch (Exception e) {
            log.error("请求["+url+"]失败",e);

            return null;
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("请求["+url+"]失败",e);
                }
            }
        }
    }


    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, String params) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json,text/html");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                return EntityUtils.toString(responseEntity);
            } else {
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error("请求["+url+"]失败",e);
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                log.error("请求["+url+"]失败",e);
            }
        }
        return null;
    }

    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, String params, Map headParam) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json,text/html");
        httpPost.setHeader("Content-Type", "application/json");
        if(headParam != null) {
            for (Iterator iter = headParam.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(headParam.get(name));
                httpPost.addHeader(name, value);
            }
        }
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                return EntityUtils.toString(responseEntity);
            } else {
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error("请求["+url+"]失败",e);
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                log.error("请求["+url+"]失败",e);
            }
        }
        return null;
    }

    /**
     * post请求
     * 上传文件
     * @param url
     * @param files
     * @return
     */
    public static String doUpload(String url, MultipartFile[] files) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json,text/html");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for (MultipartFile file : files) {
                if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                    String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                    ContentType contentType = ContentType.MULTIPART_FORM_DATA;
                    if (".jpg".equals(suffix) || (".jpeg").equals(suffix)) {
                        contentType = ContentType.IMAGE_JPEG;
                    } else if (".png".equals(suffix)) {
                        contentType = ContentType.IMAGE_PNG;
                    }
                    builder.addBinaryBody("file", file.getInputStream(), contentType, new Random().nextInt() + suffix);
                }
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                return EntityUtils.toString(responseEntity);
            } else {
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            log.error("请求["+url+"]失败",e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error("请求["+url+"]失败",e);
            }
        }
        return result;
    }

    /**
     * post请求
     *
     * 上传文件和表单
     * @param url
     * @param params
     * @param file
     * @return
     */
    public static String doUpload(String url, Map params,MultipartFile file) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json,text/html");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            if(file != null) {
                if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                    String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                    ContentType contentType = ContentType.MULTIPART_FORM_DATA;
                    if (".jpg".equals(suffix) || (".jpeg").equals(suffix)) {
                        contentType = ContentType.IMAGE_JPEG;
                    } else if (".png".equals(suffix)) {
                        contentType = ContentType.IMAGE_PNG;
                    }
                    builder.addBinaryBody("files", file.getInputStream(), contentType, new Random().nextInt() + suffix);
                }
            }
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                builder.addTextBody(name,value);
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                return EntityUtils.toString(responseEntity);
            } else {
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            log.error("请求["+url+"]失败",e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error("请求["+url+"]失败",e);
            }
        }
        return result;
    }

    /**
     * 上传表单
     * @param url
     * @param headParam
     * @param params
     * @param paramName file的参数名
     * @param file
     * @return
     */
    public static String doUpload(String url, Map<String, String> headParam, Map<String, String> params, String paramName, MultipartFile file) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json,text/html");
            if(headParam != null && headParam.size() > 0) {
                for (Iterator iter = headParam.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(headParam.get(name));
                    httpPost.addHeader(name, value);
                }
            }
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            if(file != null) {
                if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                    String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                    ContentType contentType = ContentType.MULTIPART_FORM_DATA;
//                    if (".jpg".equals(suffix) || (".jpeg").equals(suffix)) {
//                        contentType = ContentType.IMAGE_JPEG;
//                    } else if (".png".equals(suffix)) {
//                        contentType = ContentType.IMAGE_PNG;
//                    }
                    builder.addBinaryBody(paramName, file.getInputStream(), contentType, new Random().nextInt() + suffix);
                }
            }
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                builder.addTextBody(name,value);
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                return EntityUtils.toString(responseEntity);
            } else {
                log.error("请求{}失败，返回值：{}",url,response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            log.error("请求["+url+"]失败",e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error("请求["+url+"]失败",e);
            }
        }
        return result;
    }
}
