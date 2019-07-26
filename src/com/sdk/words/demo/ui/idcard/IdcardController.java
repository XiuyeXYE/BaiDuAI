package com.sdk.words.demo.ui.idcard;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import org.json.JSONObject;

import com.sdk.words.util.Utils;
import com.xiuye.util.U;
import com.xiuye.util.UI;
import com.xiuye.window.FxmlWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * 身份证识别
 * 
 * @author admin
 *
 */
public class IdcardController implements Initializable {

	@FXML
	private ImageView iv;

	@FXML
	private ChoiceBox<String> choice;

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
					options.put("detect_direction", "true");
					options.put("detect_risk", "false");

					String idCardSide = this.choice.getValue();
					// 参数为本地路径
					String image = filename;
					JSONObject res = c.idcard(image, idCardSide, options);
					System.out.println(res.toString(4));
					UI.runLater(() -> {
						result.setText(res.toString(4));
						info.clear();
					});
					Map<String, Map<String, Object>> results = Utils.map(res.toMap().get("words_result"));
					if (Objects.nonNull(results) && !results.isEmpty()) {

						for (Entry<String, Map<String, Object>> en : results.entrySet()) {
							String key = en.getKey();
							String value = en.getValue().get("words").toString();
							if (Objects.nonNull(value)) {
								UI.runLater(() -> {
									info.appendText(key + " : " + value + "\r\n");
								});
							}
						}
					}

				});
			});
		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public static void main(String[] args) {
//		System.out.println(WordsWindow.class.getResource("app.fxml"));
		FxmlWindow.running("身份证识别",IdcardController.class.getResource("idcard.fxml"));
	}
	
}
