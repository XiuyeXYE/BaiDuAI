package com.sdk.words.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.baidu.aip.ocr.AipOcr;
import com.sdk.words.WordsGlobal;
import com.xiuye.util.cls.TypeUtil;
import com.xy.util.Base64Util;
import com.xy.util.FileUtil;

public class Utils {

	public static final String error_pic = "resources/error.png";

	public static Optional<String> base64Image(String fileName) {
		try {
			return Optional.ofNullable(Base64Util.encode(FileUtil.readFileByBytes(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public static Optional<AipOcr> client() {
		// 初始化一个AipFace
		AipOcr client = new AipOcr(WordsGlobal.APP_ID, WordsGlobal.API_KEY, WordsGlobal.SECURITY_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		return Optional.ofNullable(client);
		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
		// client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
		// client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理

		// 调用接口
		// String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
		// String imageType = "BASE64";

		// 人脸检测
		// JSONObject res = client.detect(image, imageType, options);
		// System.out.println(res.toString(2));
	}

	public static <K, V, T> Map<K, V> map(T t) {
		return TypeUtil.dynamic_cast(t);
	}

	public static <T> int I(T t) {
		return TypeUtil.dynamic_cast(t);
	}

	public static <T, R> List<R> list(T t) {
		return TypeUtil.dynamic_cast(t);
	}

	public static <T> double D(T t) {
		return TypeUtil.dynamic_cast(t);
	}

	public static <K, V> Map<K, V> map() {
		return new HashMap<K, V>();
	}

	public static <T> int toInt(T t) {
		if (t instanceof Double) {
			return (int) (double) t;
		}
		return (int) t;
	}
}
