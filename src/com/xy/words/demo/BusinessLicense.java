package com.xy.words.demo;

import com.xiuye.util.log.LogUtil;
import com.xy.util.WordsUtil;
import com.xy.words.AuthService;
import com.xy.words.URL;

/**
 * 营业执照识别
 * @author admin
 *
 */
public class BusinessLicense {

	public static void main(String[] args) {

		String filename = "businesslicese2.jpg";
		//效果有点不好
		LogUtil.log(WordsUtil.resultJson(URL.business_license, AuthService.getAuth(), WordsUtil.image(filename).orElse("NONE"))
				.orElse("NONE"));

	}

}
