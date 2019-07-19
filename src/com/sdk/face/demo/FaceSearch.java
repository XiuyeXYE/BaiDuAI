package com.sdk.face.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.sdk.face.util.Utils;
import com.xiuye.util.U;
import com.xiuye.util.cls.TypeUtil;
import com.xiuye.util.log.LogUtil;
import com.xiuye.window.IV;
import com.xy.util.Base64Util;
import com.xy.util.FileUtil;

import javafx.scene.paint.Color;

/**
 * 人脸搜索
 * 去人脸管理库搜索的！！！
 * @author admin
 *
 */
public class FaceSearch {

	public static void main(String[] args) {

		Utils.client().ifPresent(c -> {
			Map<String, String> options = Utils.map();
			options.put("max_face_num", "3");
			options.put("match_threshold", "70");
			options.put("quality_control", "NORMAL");
			//活体检测
//			options.put("liveness_control", "LOW");
			//if had,user id 1:N
			//else M:N
//			options.put("user_id", "blue_woman");//不给userid 也能查找
			options.put("max_user_num", "3");

			try {
//				String filename = "faces/whiteMaleFrontFace.png";
				String filename = "faces/AndyLou/face-1.jpg";
				String image = Base64Util.encode(FileUtil.readFileByBytes(filename));
//				String image = Base64Util.encode(FileUtil.readFileByBytes("faces/AndyLou/face-1.jpg"));
//				String image = Base64Util.encode(FileUtil.readFileByBytes("faces/AndyLou/face-0.jpg"));
				String imageType = "BASE64";
				String groupIdList = "3,2,group1";
//				JSONObject jsonObj = c.search(image, imageType, groupIdList, TypeUtil.dynamic_cast(options));
				JSONObject jsonObj = c.multiSearch(image, imageType, groupIdList, TypeUtil.dynamic_cast(options));
				LogUtil.log(jsonObj.toString(4));
				List<Map<String,Object>> list = Utils.list(Utils.map(jsonObj.toMap().get("result")).get("face_list"));
				for(Map<String,Object> m : list) {
					Map<String,Object> loc = Utils.map(m.get("location"));
					double left = (double) loc.get("left");							
					double top = (double) loc.get("top");							
					int width = (int) loc.get("width");							
					int height = (int) loc.get("height");
					IV.imshow(U.rect(U.toImage(filename), Color.RED, (int)left, (int)top, width, height));
				}
				
				IV.waitKey();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}

}
