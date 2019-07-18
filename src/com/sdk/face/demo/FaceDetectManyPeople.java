package com.sdk.face.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.sdk.face.util.Utils;
import com.xiuye.util.U;
import com.xiuye.util.cls.TypeUtil;
import com.xiuye.util.log.LogUtil;
import com.xiuye.window.IV;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * 人脸检测
 * 
 * @author admin
 *
 */
public class FaceDetectManyPeople {
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
//			options.put("liveness_control", "LOW");
			String filename = "faces/multi_faces.jpg";
			Utils.base64Image(filename).ifPresent(img -> {
				JSONObject jsonObj = c.detect(img, "BASE64", options);
				LogUtil.log(jsonObj.toString(4));
				Map<String, Object> m = jsonObj.toMap();
				Map<String, Object> result = Utils.map(m.get("result"));
				int faceNum = Utils.I(result.get("face_num"));
				List<?> faces = Utils.list(result.get("face_list"));
				Image image = U.toImage(filename);
				for (int i = 0; i < faceNum; i++) {
					Map<String, Object> face = Utils.map(faces.get(i));
					
					LogUtil.log("gender=", face.get("gender"));
					
					Map<String, Object> location = Utils.map(face.get("location"));
					LogUtil.log("face_probability=", face.get("face_probability"));
					LogUtil.log(location);
					int top = TypeUtil.<Double,Object>dynamic_cast(location.get("top")).intValue();
					int left = TypeUtil.<Double,Object>dynamic_cast(location.get("left")).intValue();
					int width = TypeUtil.<Integer,Object>dynamic_cast(location.get("width")).intValue();
					int height = TypeUtil.<Integer,Object>dynamic_cast(location.get("height")).intValue();
					image = U.rect(image, Color.RED, left, top, width, height,2);
				}
				IV.imshow(image);
				IV.waitKey();
			});

		});

	}
}
