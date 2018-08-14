package com.httpclient;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
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
        System.out.println(result);
        System.out.println(unicodeToString(result));
    }
	
	private static String unicodeToString(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);// 转换出每一个代码点
            string.append((char) data);// 追加成string
        }
        return string.toString();
    }
	
	public String doPost(String url ,Map<String ,String> map ,String charset) {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			httpPost = new HttpPost(url);
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
