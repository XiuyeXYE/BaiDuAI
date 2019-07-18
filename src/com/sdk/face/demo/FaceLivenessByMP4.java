package com.sdk.face.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.baidu.aip.face.MatchRequest;
import com.baidu.aip.util.Base64Util;
import com.sdk.face.util.Utils;
import com.xiuye.util.U;
import com.xiuye.util.cls.TypeUtil;
import com.xiuye.window.IV;
/**
 * 人脸对比
 * @author admin
 *
 */
public class FaceLivenessByMP4 {

	public static void main(String[] args) {
		Utils.client().ifPresent(c -> {
			 // 传入可选参数调用接口
		    HashMap<String, String> options = new HashMap<String, String>();

//		    String sessionId = "110233112299822211";

		    // 参数为本地路径
		    String video = "faces/video/my.mp4";
//		    String video = "faces/video/j1.mp4";
		    JSONObject res = c.videoFaceliveness(null, video, options);
		    System.out.println(res.toString(4));
		    Map<String,Object> m = res.toMap();
		    m = TypeUtil.dynamic_cast(m.get("result"));
		    List<Map<String,Object>> pics = TypeUtil.dynamic_cast(m.get("pic_list"));
		    for(Map<String,Object> pic : pics) {
		    	IV.imshow(U.toImage(Base64Util.decode(pic.get("pic").toString())));
		    }
		    IV.waitKey();
		    // 参数为二进制数组
		});
	}

}
