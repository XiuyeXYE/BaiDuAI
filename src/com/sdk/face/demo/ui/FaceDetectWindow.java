package com.sdk.face.demo.ui;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.json.JSONObject;

import com.sdk.face.util.Utils;
import com.xiuye.util.OpW;
import com.xiuye.util.U;
import com.xiuye.util.UI;
import com.xiuye.util.log.LogUtil;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FaceDetectWindow extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Button btn = new Button("本地上传");
		ImageView iv = new ImageView();
		TextArea result = new TextArea();
		result.setEditable(false);
		result.setStyle("-fx-font-size:18");
		TextArea info = new TextArea();
		info.setEditable(false);
		info.setStyle("-fx-font-size:18");
		SimpleObjectProperty<Image> imgPrty = new SimpleObjectProperty<>();
		iv.imageProperty().bind(imgPrty);
		btn.setOnAction(e -> {

			FileChooser fc = new FileChooser();
			fc.setInitialDirectory(new File("."));
			File f = fc.showOpenDialog(primaryStage);
			if (Objects.nonNull(f)) {

				String filename = OpW.of(f).ifPresent(file -> {
					System.out.println(file);
					return file.exists();
				}).ifTrue(() -> {
					return f.canRead();
				}).ifTrue(() -> {
					return f.getAbsolutePath();
				}).get();

				Utils.client().ifPresent(c -> {
					HashMap<String, String> options = new HashMap<String, String>();
					// 要检测的面部的参数
					options.put("face_field",
							"age,beauty,expression,face_shape,gender,glasses,race,quality,eye_status,emotion,face_type");
					// 最大检测人脸数10个
					options.put("max_face_num", "10");// 最大的10个
					// 人脸的类型
					options.put("face_type", "LIVE");
					// 活体控制 检测结果中不符合要求的人脸会被过滤
					// options.put("liveness_control", "LOW");
					Utils.base64Image(filename).ifPresent(im -> {
						JSONObject jsonObj = c.detect(im, "BASE64", options);
						UI.threadStart(() -> {
							result.clear();
							info.clear();
							result.appendText("结果:\n" + jsonObj.toString(4));
						});
						LogUtil.log(jsonObj.toString(4));
						Map<String, Object> m = jsonObj.toMap();
						if (m.get("error_code").equals(0)) {
							Map<String, Object> res = Utils.map(m.get("result"));
							int faceNum = Utils.I(res.get("face_num"));
							List<?> faces = Utils.list(res.get("face_list"));
							Image image = U.toImage(filename);
							for (int i = 0; i < faceNum; i++) {
								Map<String, Object> face = Utils.map(faces.get(i));
								int num = i + 1;
								UI.threadStart(() -> {
									// LogUtil.log("gender=", face.get("gender"));
									info.appendText("识别" + num + "号 :\n" + "性别 : " + face.get("gender") + "\n" + "年龄 : "
											+ face.get("age") + "\n" + "种族 : " + face.get("race") + "\n" + "颜值 : "
											+ face.get("beauty") + "\n" + "表情 : " + face.get("expression") + "\n"
											+ "眼镜 : " + face.get("glasses") + "\n" + "情绪 : " + face.get("emotion")
											+ "\n" + "人脸特征码 : " + face.get("face_token") + "\n" + "位置 : "
											+ face.get("location") + "\n");
									info.appendText("\n");
								});

								Map<String, Object> location = Utils.map(face.get("location"));
								// LogUtil.log("face_probability=",
								// face.get("face_probability"));
								LogUtil.log(location);
								int top = Utils.toInt(location.get("top"));
								int left = Utils.toInt(location.get("left"));
								int width = Utils.toInt(location.get("width"));
								int height = Utils.toInt(location.get("height"));
								image = U.rect(image, Color.RED, left, top, width, height, 2);
							}
							// IV.imshow(image);
							// IV.waitKey();

							imgPrty.set(image);
						} else {
							imgPrty.set(U.toImage(Utils.error_pic));
						}

					});
				});
			}
		});

		ToolBar tb = new ToolBar(btn);

		ScrollPane scrollPaneImg = new ScrollPane(iv);
		// ScrollPane scrollPaneImg = new ScrollPane();

		SplitPane sp = new SplitPane();
		sp.getItems().addAll(scrollPaneImg, info);
		sp.setDividerPositions(0.5, 0.5);

		BorderPane bp = new BorderPane();
		bp.setTop(tb);
		bp.setBottom(result);
		bp.setCenter(sp);

		Scene scene = new Scene(bp);

		primaryStage.setScene(scene);
		primaryStage.setTitle("人脸检测");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
