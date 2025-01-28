package com.ZenVeus.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientSaveController {
    public TextField clientsaveText;

    public void clientSaveBtn(ActionEvent actionEvent) throws IOException {
        String clientName = clientsaveText.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/chat.fxml"));
        Parent root = loader.load();

        ChatController chatController = loader.getController();
        chatController.clienName.setText(clientName);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Chat");
        stage.show();
    }
}