package com.xy.words.demo;

import com.xiuye.util.log.LogUtil;
import com.xy.util.WordsUtil;
import com.xy.words.AuthService;
import com.xy.words.URL;

/**
 * 车牌识别
 * @author admin
 *
 */
public class CarPlateNumber {

	public static void main(String[] args) {

		String filename = "pic/carplate.jpg";
		
		LogUtil.log(WordsUtil.resultJson(URL.license_plate, AuthService.getAuth(), WordsUtil.image(filename).orElse("NONE"))
				.orElse("NONE"));

	}
	
}
