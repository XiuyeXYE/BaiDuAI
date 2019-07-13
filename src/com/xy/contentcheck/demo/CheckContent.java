package com.xy.contentcheck.demo;

import java.util.List;
import java.util.Map;

import com.xiuye.util.cls.TypeUtil;
import com.xiuye.util.log.LogUtil;
import com.xy.contentcheck.URL;
import com.xy.util.ContetnCheckUtil;
import com.xy.words.AuthService;

public class CheckContent {

	public static void main(String[] args) {
		String filename = "pic/image_advertise.jpeg";
		
		
		Map<String, Object> sceneConf = TypeUtil.createMap();
        Map<String, Object> ocrConf = TypeUtil.createMap();
        ocrConf.put("recognize_granularity", "big");
        ocrConf.put("language_type", "CHN_ENG");
        ocrConf.put("detect_direction", true);
        ocrConf.put("detect_language", true);
        sceneConf.put("ocr", ocrConf);

        Map<String, Object> input = TypeUtil.createMap();
        List<Object> scenes = TypeUtil.createList();
        scenes.add("ocr");
        scenes.add("face");
        scenes.add("public");
        scenes.add("politician");
        scenes.add("antiporn");
        scenes.add("terror");
        scenes.add("webimage");
        scenes.add("disgust");
        scenes.add("watermark");

//        input.put("imgUrl", ContetnCheckUtil.image(filename).orElse("NONE"));//与image二者选一
        input.put("image", ContetnCheckUtil.image(filename).orElse("NONE"));//与image二者选一
        input.put("scenes", scenes);
        input.put("sceneConf", sceneConf);
		
		LogUtil.log(ContetnCheckUtil.resultJson(URL.img_censor, AuthService.getAuth(),input )
				.orElse("NONE"));
		
		filename = "pic/hentai.jpg";
		input.put("image", ContetnCheckUtil.image(filename).orElse("NONE"));//与image二者选一
		LogUtil.log(ContetnCheckUtil.resultJson(URL.img_censor, AuthService.getAuth(),input )
				.orElse("NONE"));
		
		filename = "pic/facepu.jpg";
		input.put("image", ContetnCheckUtil.image(filename).orElse("NONE"));//与image二者选一
		LogUtil.log(ContetnCheckUtil.resultJson(URL.img_censor, AuthService.getAuth(),input )
				.orElse("NONE"));
		
		filename = "pic/aobama.jpg";
		input.put("image", ContetnCheckUtil.image(filename).orElse("NONE"));//与image二者选一
		LogUtil.log(ContetnCheckUtil.resultJson(URL.img_censor, AuthService.getAuth(),input )
				.orElse("NONE"));
		
		//效果不好
		filename = "pic/seqing.jpg";
		input.put("image", ContetnCheckUtil.image(filename).orElse("NONE"));//与image二者选一
		LogUtil.log(ContetnCheckUtil.resultJson(URL.img_censor, AuthService.getAuth(),input )
				.orElse("NONE"));
		//bad
		filename = "pic/fankong.jpg";
		input.put("image", ContetnCheckUtil.image(filename).orElse("NONE"));//与image二者选一
		LogUtil.log(ContetnCheckUtil.resultJson(URL.img_censor, AuthService.getAuth(),input )
				.orElse("NONE"));
		
		filename = "pic/laxi.jpg";
		input.put("image", ContetnCheckUtil.image(filename).orElse("NONE"));//与image二者选一
		LogUtil.log(ContetnCheckUtil.resultJson(URL.img_censor, AuthService.getAuth(),input )
				.orElse("NONE"));
		
	}

}
