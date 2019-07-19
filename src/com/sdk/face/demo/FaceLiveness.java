package com.sdk.face.demo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.baidu.aip.face.FaceVerifyRequest;
import com.sdk.face.util.Utils;
import com.xiuye.util.U;
import com.xiuye.window.IV;

import javafx.scene.paint.Color;

import java.util.Map;

/**
 * 在线活体检测
 * @author admin
 *
 */
public class FaceLiveness {

	public static void main(String[] args) {
		Utils.client().ifPresent(c->{
			//斯大林
//			String filename = "faces/political_stalin_gray_male_face.jpg";
//			String filename = "faces/whiteMaleFrontFace.png";
			String filename = "faces/multi_faces.jpg";
//			String image = Utils.base64Image("faces/whiteMaleFrontFace.png").get();
			
			String image = Utils.base64Image(filename).get();
			FaceVerifyRequest req = new FaceVerifyRequest(image, "BASE64");
			ArrayList<FaceVerifyRequest> list = new ArrayList<FaceVerifyRequest>();
			list.add(req);
			JSONObject res = c.faceverify(list);
			System.out.println(res.toString(4));
			List<Map<String,Object>> fl = Utils.list(Utils.map(res.toMap().get("result")).get("face_list"));
			
			for(Map<String,Object> m : fl) {
				Map<String,Object> loc = Utils.map(m.get("location"));
				double left = (double) loc.get("left");							
				double top = (double) loc.get("top");							
				int width = (int) loc.get("width");							
				int height = (int) loc.get("height");
				IV.imshow(U.rect(U.toImage(filename), Color.RED, (int)left, (int)top, width, height));
			}
			IV.waitKey();
		});
	}

}
