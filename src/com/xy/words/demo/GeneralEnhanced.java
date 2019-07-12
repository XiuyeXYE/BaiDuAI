package com.xy.words.demo;

import com.xy.util.GsonUtils;
import com.xy.util.WordsUtil;
import com.xy.words.AuthService;

/**
 * 通用文字识别（含生僻字版）
 * 
 * @author admin
 *
 */
public class GeneralEnhanced {

	public static String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_enhanced";

	public static void main(String[] args) throws Exception {
		String filename = "pic/image_advertise.jpeg";

		String accessToken = AuthService.getAuth();
		String result = WordsUtil.result(url, accessToken, WordsUtil.image(filename).orElseGet(() -> {
			return "";
		})).orElse("NONE");
		System.out.println(GsonUtils.format(result));

	}
}
