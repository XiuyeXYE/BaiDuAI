package com.xy.words.demo;

import com.xiuye.util.log.LogUtil;
import com.xy.util.WordsUtil;
import com.xy.words.AuthService;
import com.xy.words.URL;

/**
 * 手写文字识别
 * @author admin
 *
 */
public class HandWriting {

	public static void main(String[] args) {

		String filename = "pic/handwriting.png";
		
		LogUtil.log(WordsUtil.resultJson(URL.handwriting, AuthService.getAuth(), WordsUtil.image(filename).orElse("NONE"))
				.orElse("NONE"));

	}

}
