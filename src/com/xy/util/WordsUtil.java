package com.xy.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Optional;

public class WordsUtil {

	public static Optional<String> image(String filename) {
		try {
			byte[] imgData = FileUtil.readFileByBytes(filename);
			String imgStr = Base64Util.encode(imgData);
			String im = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
			return Optional.ofNullable(im);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public static Optional<String> resultJson(String url, String token, String params) {
		try {
			return Optional.ofNullable(GsonUtils.format(HttpUtil.post(url, token, params)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public static Optional<String> result(String url, String token, String params) {
		try {
			return Optional.ofNullable(HttpUtil.post(url, token, params));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

}
