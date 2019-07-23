package com.sdk.words.demo.ui.accurate_basic;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sdk.face.util.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class WordsController implements Initializable {

	@FXML
	private ImageView iv;

	@FXML
	private Button uploadBtn;

	public void click(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File("."));
		Optional.ofNullable(fc.showOpenDialog(null)).ifPresent(file -> {
			Utils.client().ifPresent(c -> {
				
			});
		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
