package com.sdk.face.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.sdk.face.util.Utils;
import com.xiuye.util.cls.TypeUtil;
import com.xiuye.util.log.LogUtil;

/**
 * 人脸检测
 * 
 * @author admin
 *
 */
public class FaceDetect {
	public static void main(String[] args) {
		Utils.client().ifPresent(c -> {
			LogUtil.log("API of face client:", c);
			HashMap<String, String> options = new HashMap<String, String>();
			// 要检测的面部的参数
			options.put("face_field",
					"age,beauty,expression,face_shape,gender,glasses,race,quality,eye_status,emotion,face_type");
			// 最大检测人脸数10个
			options.put("max_face_num", "10");// 最大的10个
			// 人脸的类型
			options.put("face_type", "LIVE");
			// 活体控制 检测结果中不符合要求的人脸会被过滤
			options.put("liveness_control", "LOW");
			Utils.base64Image("my/I1.jpg").ifPresent(img -> {
				LogUtil.log(c.detect(img, "BASE64", options).toString(4));
			});
			Utils.base64Image("my/I2.jpg").ifPresent(img -> {
				JSONObject jsonObj = c.detect(img, "BASE64", options);
				LogUtil.log(jsonObj.toString(4));
				Map<String, Object> m = jsonObj.toMap();
				Map<String, Object> result = Utils.map(m.get("result"));
				int faceNum = Utils.I(result.get("face_num"));
				List<?> faces = Utils.list(result.get("face_list"));
				for (int i = 0; i < faceNum; i++) {
					Map<String, Object> face = Utils.map(faces.get(i));
					Map<String, Object> location = Utils.map(face.get("location"));
					LogUtil.log(location);
				}
			});

		});

	}
}
