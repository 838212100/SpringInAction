package com.httpclient;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "http://admin.tingwen.me/index.php/api/interfaceXykj/touList";
        Map<String,String> params = new HashMap<>();
        params.put("page","100");
        HttpClientTest client = new HttpClientTest();
        String result = client.doPost(url,params,"UTF-8");
        //System.out.println(result);
        System.out.println(unicodeDecode(result));
    }
	
	/**
     * unicode编码转中文
     *
     * @param unicodeString
     * @return
     */
    public static String unicodeDecode(String unicodeString) {
        char aChar;
        int len = unicodeString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = unicodeString.charAt(x++);
            if (aChar == '\\') {
                aChar = unicodeString.charAt(x++);
 
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = unicodeString.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

	
	public String doPost(String url ,Map<String ,String> map ,String charset) {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		 //设置代理IP、端口、协议（请分别替换）
        HttpHost proxy = new HttpHost("172.17.18.84", 8080, "http");

        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .build();
		try {
			httpClient = HttpClients.createDefault();
			httpPost = new HttpPost(url);
			//post加入代理
			httpPost.setConfig(defaultRequestConfig);
			//设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey() ,elem.getValue()));
			}
			if(list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list ,charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if(response != null) {
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null) {
					result = EntityUtils.toString(resEntity ,charset);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	

}
