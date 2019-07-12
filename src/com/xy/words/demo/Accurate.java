package com.xy.words.demo;

import java.net.URLEncoder;

import com.xy.util.Base64Util;
import com.xy.util.FileUtil;
import com.xy.util.GsonUtils;
import com.xy.util.HttpUtil;
import com.xy.words.AuthService;

/**
 * 通用文字识别（高精度版）
 * @author admin
 *
 */
public class Accurate {
	
	public static String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate";
	
	public static void main(String[] args) throws Exception {
		String filename = "pic/image_advertise.jpeg";
		
		
		byte[] imgData = FileUtil.readFileByBytes(filename);
		String imgStr = Base64Util.encode(imgData);
		String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");


		/**
		 * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		 */
		String accessToken = AuthService.getAuth();
		String result = HttpUtil.post(url, accessToken, params);
		System.out.println(GsonUtils.format(result));
		
	}
}
