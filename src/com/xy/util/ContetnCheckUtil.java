package com.xy.util;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class ContetnCheckUtil {

	public static Optional<String> image(String filename) {
		try {
			byte[] imgData = FileUtil.readFileByBytes(filename);
			String imgStr = Base64Util.encode(imgData);
			return Optional.ofNullable(imgStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public static Optional<String> resultJson(String url, String token, Map<String,Object> params) {
		try {
//			LogUtil.log(GsonUtils.toJson(params));
			return Optional.ofNullable(GsonUtils.format(HttpUtil.post(url, token,"application/json;charset=utf-8", GsonUtils.toJson(params))));
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
