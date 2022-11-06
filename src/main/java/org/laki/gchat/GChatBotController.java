package org.laki.gchat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Properties;
import com.google.gson.Gson;

public class GChatBotController {
    @FXML
    private ListView<String> spaceListView;
    @FXML
    private TextArea messageView;
    @FXML
    private Button sendButton;

    @FXML
    private Label statusLabel;

    private Properties spaceToBotURL = new Properties();

    private Gson gson = new Gson();
    private HttpClient client = HttpClient.newHttpClient();

    public GChatBotController() {
        String resourceName = "space_to_webhook.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            spaceToBotURL.load(resourceStream);
        } catch (IOException e) {
            //todo log the error
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onMouseClickedOnItem() {
        ObservableList<String> selectedItems =  spaceListView.getSelectionModel().getSelectedItems();

        for(String s : selectedItems){
            System.out.println("selected item " + s);
        }
    }



    @FXML
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        spaceToBotURL.forEach((key, value) -> {
            list.add((String) key);
        });
        spaceListView.setItems(list);
        spaceListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void onSendButtonClicked() {
        String message = messageView.getText();
        ObservableList<String> list = spaceListView.getSelectionModel().getSelectedItems();
        list.forEach((value) -> {
            String botURL = (String) spaceToBotURL.get(value);
            HttpRequest request = HttpRequest.newBuilder(
                            URI.create(botURL))
                    .header("accept", "application/json; charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(Map.of("text", message))))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    statusLabel.setText("Sent Successfully");
                    statusLabel.setStyle("-fx-text-fill: green");
                } else {
                    statusLabel.setText("Sent Error");
                    statusLabel.setStyle("-fx-text-fill: red");
                }
                System.out.println(response.body());
            } catch (IOException | InterruptedException e) {
                statusLabel.setText("Sent Error");
                statusLabel.setStyle("-fx-text-fill: red");
                throw new RuntimeException(e);
            }
        });
    }
}