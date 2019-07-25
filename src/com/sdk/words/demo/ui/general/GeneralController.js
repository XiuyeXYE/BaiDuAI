var File = Java.type("java.io.File");
var URL = Java.type("java.net.URL");
var HashMap = Java.type("java.util.HashMap");
var Map = Java.type("java.util.Map");
var Objects = Java.type("java.util.Objects");
var List = Java.type("java.util.List");
var Optional = Java.type("java.util.Optional");

var JSONObject = Java.type("org.json.JSONObject");

var Utils = Java.type("com.sdk.words.util.Utils");
var U = Java.type("com.xiuye.util.U");
var UI = Java.type("com.xiuye.util.UI");

var FileChooser = Java.type("javafx.stage.FileChooser");



function click(e){

	var fc = new FileChooser();
	fc.setInitialDirectory(new File("."));
	Optional.ofNullable(fc.showOpenDialog(null)).ifPresent(function(file){
		var filename = file.getAbsolutePath();
		UI.runLater(function() {
			iv.setImage(U.toImage(filename));
		});
		U.threadStart(function() {
			Utils.client().ifPresent(function(c) {
				// 传入可选参数调用接口
				var options = new HashMap();
				options.put("recognize_granularity", "big");
				options.put("language_type", "CHN_ENG");
				options.put("detect_direction", "true");
				options.put("detect_language", "true");
				options.put("vertexes_location", "true");
				options.put("probability", "true");
				// 参数为本地路径
				var image = filename;
				var res = c.general(image, options);
				print(res.toString(4));
				UI.runLater(function() {
					result.setText(res.toString(4));
					info.clear();
				});
				var results = Utils.list(res.toMap().get("words_result"));
				print(results);
				if (Objects.nonNull(results) && !results.isEmpty()) {
					for (var i = 0; i < results.size(); i++) {
						print(i);
							var words = results.get(i).get("words");
							var num = i + 1;
							if (Objects.nonNull(words)) {
								UI.runLater(function() {
									info.appendText("第" + num + "行 : " + words + "\r\n");
								});
							}
					}
				}
				print(words);

			});
		});
	});
	
}



