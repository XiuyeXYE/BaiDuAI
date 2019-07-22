package com.sdk.face.demo.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import org.json.JSONObject;

import com.baidu.aip.face.MatchRequest;
import com.sdk.face.util.Utils;
import com.xiuye.util.OpW;
import com.xiuye.util.U;
import com.xiuye.util.log.LogUtil;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FaceComparedWindow extends Application {

	private String filename1 = null;
	private String filename2 = null;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Button btn1 = new Button("本地上传1");
		Button btn2 = new Button("本地上传2");
		ImageView iv1 = new ImageView();
		ImageView iv2 = new ImageView();
		Text scoreInfo = new Text();
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

		scoreInfo.setEffect(ds);
		scoreInfo.setCache(true);
//		scoreInfo.setX(10.0f);
//		scoreInfo.setY(270.0f);
		scoreInfo.setFill(Color.RED);
//		scoreInfo.setText("JavaFX drop shadow...");
		scoreInfo.setFont(Font.font(null, FontWeight.BOLD, 32));
		SimpleStringProperty ssp = new SimpleStringProperty();
		scoreInfo.textProperty().bind(ssp);
		TextArea result = new TextArea();
		result.setEditable(false);
		result.setStyle("-fx-font-size:18");
		SimpleObjectProperty<Image> imgPrty1 = new SimpleObjectProperty<>();
		iv1.imageProperty().bind(imgPrty1);
		SimpleObjectProperty<Image> imgPrty2 = new SimpleObjectProperty<>();
		iv2.imageProperty().bind(imgPrty2);
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File("."));
		btn1.setOnAction(e -> {
			File f = fc.showOpenDialog(primaryStage);
			if (Objects.nonNull(f)) {
				filename1 = OpW.of(f).ifPresent(file -> {
					return file.exists();
				}).ifTrue(() -> {
					return f.canRead();
				}).ifTrue(() -> {
					return f.getAbsolutePath();
				}).get();
//				UI.threadStart(() -> {
				imgPrty1.set(U.toImage(filename1));
//				});
				U.threadStart(() -> {
					if (Objects.nonNull(filename2)) {
						Utils.client().ifPresent(c -> {
							MatchRequest req1 = new MatchRequest(Utils.base64Image(filename1).get(), "BASE64");
							MatchRequest req2 = new MatchRequest(Utils.base64Image(filename2).get(), "BASE64");
							ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
							requests.add(req1);
							requests.add(req2);
							JSONObject jsonObj = c.match(requests);
							Platform.runLater(() -> {
								result.clear();
								result.appendText("结果:\n" + jsonObj.toString(4));
							});
							LogUtil.log(jsonObj.toString(4));
							Map<String, Object> m = jsonObj.toMap();
							if (!m.get("error_code").equals(0)) {
								Platform.runLater(() -> {
									imgPrty1.set(U.toImage(Utils.error_pic));
									imgPrty2.set(U.toImage(Utils.error_pic));
								});
							} else {
								Platform.runLater(() -> {
									ssp.set(("相似度 : " + Utils.map(m.get("result")).get("score").toString()));
								});
							}
						});
					}
				});

			}
		});

		btn2.setOnAction(e -> {
			File f = fc.showOpenDialog(primaryStage);
			if (Objects.nonNull(f)) {
				filename2 = OpW.of(f).ifPresent(file -> {
					return file.exists();
				}).ifTrue(() -> {
					return f.canRead();
				}).ifTrue(() -> {
					return f.getAbsolutePath();
				}).get();
//				UI.threadStart(() -> {
				imgPrty2.set(U.toImage(filename2));
//				});

				U.threadStart(() -> {
					if (Objects.nonNull(filename1)) {
						Utils.client().ifPresent(c -> {
							MatchRequest req1 = new MatchRequest(Utils.base64Image(filename1).get(), "BASE64");
							MatchRequest req2 = new MatchRequest(Utils.base64Image(filename2).get(), "BASE64");
							ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
							requests.add(req1);
							requests.add(req2);
							JSONObject jsonObj = c.match(requests);
							Platform.runLater(() -> {
								result.clear();
								result.appendText("结果:\n" + jsonObj.toString(4));
							});

							LogUtil.log(jsonObj.toString(4));
							Map<String, Object> m = jsonObj.toMap();
							if (!m.get("error_code").equals(0)) {
								Platform.runLater(() -> {
									imgPrty1.set(U.toImage(Utils.error_pic));
									imgPrty2.set(U.toImage(Utils.error_pic));
								});
							} else {
								Platform.runLater(() -> {
									ssp.set(("相似度 : " + Utils.map(m.get("result")).get("score").toString()));
								});
							}
						});
					}
				});

			}
		});

		ToolBar tb1 = new ToolBar(btn1);
		ToolBar tb2 = new ToolBar(btn2);
		tb2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		HBox.setHgrow(tb1, Priority.ALWAYS);
		HBox.setHgrow(scoreInfo, Priority.ALWAYS);
		HBox.setHgrow(tb2, Priority.ALWAYS);
		HBox hb = new HBox(tb1, scoreInfo, tb2);
		ScrollPane scrollPaneImg1 = new ScrollPane(iv1);
		ScrollPane scrollPaneImg2 = new ScrollPane(iv2);
		SplitPane sp = new SplitPane();
		sp.getItems().addAll(scrollPaneImg1, scrollPaneImg2);
		sp.setDividerPositions(0.5, 0.5);
		BorderPane bp = new BorderPane();
		bp.setTop(hb);
		bp.setBottom(result);
		bp.setCenter(sp);
		Scene scene = new Scene(bp);
		primaryStage.setScene(scene);
		primaryStage.setTitle("人脸对比");
		primaryStage.show();
	}

	public static void main(String[] args) {
//		launch(args);
		U.runApplication(FaceComparedWindow.class, args);
	}

}
