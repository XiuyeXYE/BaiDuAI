package com.sdk.words.demo.ui.platelicense;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * 车牌识别
 * 
 * @author admin
 *
 */
public class PlateLicenseController implements Initializable {

	@FXML
	private ImageView iv;

	@FXML
	private Button uploadBtn;

	@FXML
	private TextArea info;

	@FXML
	private TextArea result;

	public void click(ActionEvent event) {
		i = 1;
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
					options.put("multi_detect", "true");

					// 参数为本地路径
					String image = filename;
					JSONObject res = c.plateLicense(image, options);
					System.out.println(res.toString(4));
					UI.runLater(() -> {
						result.setText(res.toString(4));
						info.clear();
					});
					List<Map<String, Object>> results = Utils.list(res.toMap().get("words_result"));
					if (Objects.nonNull(results) && !results.isEmpty()) {

						for (Map<String, Object> m : results) {

							Object num = m.get("number");
							if (Objects.nonNull(num)) {
								UI.runLater(() -> {
									info.appendText((i++) + " : " + num + "\r\n");
								});
							}
						}
					}

				});
			});
		});

	}

	int i = 1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public static void main(String[] args) {
//		System.out.println(WordsWindow.class.getResource("app.fxml"));
		FxmlWindow.running("车牌识别", PlateLicenseController.class.getResource("plateLicense.fxml"));
	}

}
