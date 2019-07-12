package com.xy.words.demo;

import java.io.IOException;
import java.net.URLEncoder;

import com.xiuye.util.log.LogUtil;
import com.xy.util.Base64Util;
import com.xy.util.FileUtil;
import com.xy.util.GsonUtils;
import com.xy.util.HttpUtil;
import com.xy.words.AuthService;

/**
 * 通用文字识别
 * @author admin
 *
 */
public class BasicGeneral {
	
	public static String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
	
	public static void main(String[] args) throws Exception {
		String filename = "image_advertise.jpeg";
		
		String token = AuthService.getAuth();
		
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
