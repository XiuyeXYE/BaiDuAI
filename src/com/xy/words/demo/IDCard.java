package com.xy.words.demo;

import com.xiuye.util.log.LogUtil;
import com.xy.util.WordsUtil;
import com.xy.words.AuthService;
import com.xy.words.URL;

/**
 * 身份证识别
 * 
 * @author admin
 *
 */
public class IDCard {

	public static void main(String[] args) {

		String filename = "IDCardFront.jpg";
		LogUtil.log(WordsUtil.resultJson(URL.idcard, AuthService.getAuth(),
				WordsUtil.image(filename).orElse("NONE") + "&" + "id_card_side=front").orElse("NONE"));
		filename = "pic/IDCardBack.jpg";
		LogUtil.log(WordsUtil.resultJson(URL.idcard, AuthService.getAuth(),
				WordsUtil.image(filename).orElse("NONE") + "&" + "id_card_side=back").orElse("NONE"));

	}

}
