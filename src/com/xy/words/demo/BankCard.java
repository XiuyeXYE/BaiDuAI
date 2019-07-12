package com.xy.words.demo;

import com.xiuye.util.log.LogUtil;
import com.xy.util.WordsUtil;
import com.xy.words.AuthService;
import com.xy.words.URL;

/**
 * 银行卡识别
 * @author admin
 *
 */
public class BankCard {

	public static void main(String[] args) {

		String filename = "bankcard.jpg";
		
		LogUtil.log(WordsUtil.resultJson(URL.bankcard, AuthService.getAuth(), WordsUtil.image(filename).orElse("NONE"))
				.orElse("NONE"));

	}

}
