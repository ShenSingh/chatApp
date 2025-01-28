package com.ZenVeus.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    public Text clienName;
    public TextArea showMsgArea;
    public TextField clientMsgField;

    private PrintWriter out;
    private BufferedReader in;

    private String sendMsg;

    File file1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Socket socket = new Socket("localhost", 8081);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(() -> {
                String message;
                try {
                    while ((message = in.readLine()) != null) {

                        if (!message.equals(sendMsg)){
                            showMsgArea.appendText("client : "+message + "\n");
                        }else {
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void msgSendBtn(ActionEvent actionEvent) {
        String message = clientMsgField.getText();
        if (message != null && !message.trim().isEmpty()) {
            out.println(message);
            showMsgArea.appendText("Me: " + message + "\n");
            sendMsg = clientMsgField.getText();
            clientMsgField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Enter a message!");
            alert.show();
        }
    }

    public void openFile(ActionEvent actionEvent) {
        File filePath = new File(openSystemFolder());
        sendFileOnChat(filePath);

    }

    private void sendFileOnChat(File file) {
        System.out.println(file);

        if (file.isFile()){
            file1 = file;
            out.println(file);
        }
    }

    private String openSystemFolder() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            return file.getAbsolutePath();
        }
        return null;
    }


//    ///////////////////////////////////



}