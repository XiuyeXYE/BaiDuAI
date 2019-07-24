package com.sdk.words.demo.ui.basicgeneral;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.json.JSONObject;

import com.sdk.words.util.Utils;
import com.xiuye.util.U;
import com.xiuye.util.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class BasicGeneralController implements Initializable {

	@FXML
	private ImageView iv;

	@FXML
	private Button uploadBtn;

	@FXML
	private TextArea info;

	@FXML
	private TextArea result;

	public void click(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File("."));
		// filter
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));
		Optional.ofNullable(fc.showOpenDialog(null)).ifPresent(file -> {
			String filename = file.getAbsolutePath();
			UI.runLater(() -> {
				iv.setImage(U.toImage(filename));
			});
			U.threadStart(() -> {
				Utils.client().ifPresent(c -> {
					// 传入可选参数调用接口
					HashMap<String, String> options = new HashMap<String, String>();
//					options.put("language_type", "CHN_ENG");
//					options.put("detect_direction", "true");
//					options.put("detect_language", "true");
//					options.put("probability", "true");

					// 参数为本地路径
					String image = filename;
					JSONObject res = c.basicGeneral(image, options);
					System.out.println(res.toString(4));
					UI.runLater(() -> {
						result.setText(res.toString(4));
						info.clear();
					});
					List<Map<String, Object>> results = Utils.list(res.toMap().get("words_result"));
					if (Objects.nonNull(results) && !results.isEmpty()) {

						for (int i = 0; i < results.size(); i++) {
							String words = (String) results.get(i).get("words");
							int num = i + 1;
							if (Objects.nonNull(words)) {
								UI.runLater(() -> {
									info.appendText("第" + num + "行 : " + words + "\r\n");
								});
							}
						}
					}

					// 参数为二进制数组
//			    byte[] file = readFile(image);
//			    res = c.basicGeneral(file, options);
//			    System.out.println(res.toString(4));

					// 通用文字识别, 图片参数为远程url图片
//			    JSONObject res = c.basicGeneralUrl(url, options);
//			    System.out.println(res.toString(4));
				});
			});
		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
