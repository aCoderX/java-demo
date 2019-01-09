package com.acoderx.demo.commons.mock;

import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.MockServerRule;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Description:
 *
 * @author xudi
 * @since 2019-01-09
 */
public class MockServiceDemo {
    @Rule
    public MockServerRule rule = new MockServerRule(this,5999);

    String expected = "{\"cpde\":\"23\"}";



    @Test
    public void test() {
        MockServerClient client = new MockServerClient("localhost", 5999);

        client.when(
                request().withMethod("GET")
                        .withPath("/test")
        ).respond(
                response().withBody(expected));

        try {
            URL url = new URL("http://localhost:5999/test");
            //得到connection对象。
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //连接
            connection.connect();
            //得到响应码
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = connection.getInputStream();
                //将响应流转换成字符串
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
