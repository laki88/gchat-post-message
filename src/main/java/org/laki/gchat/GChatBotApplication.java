package org.laki.gchat;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GChatBotApplication extends Application {

//    @FXML
//    ListView spaceListView;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GChatBotApplication.class.getResource("post-message.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        ObservableList<String> list = FXCollections.observableArrayList();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}