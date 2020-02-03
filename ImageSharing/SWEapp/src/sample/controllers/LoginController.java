package sample.controllers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;


public class LoginController {


    @FXML
    TextField uname;

    @FXML
    TextField pword;

    @FXML
    Button loginButton;


    public void handleLogIn(ActionEvent actionEvent) throws IOException {
        String username = uname.getText();
        String pwrod = pword.getText();
        if (checkLogin(username, pwrod)) {
            login("/sample/fxml/MainPage.fxml");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Username & Password combination");
            alert.showAndWait();
        }
    }

    private boolean checkLogin(String username, String pwrod) throws IOException {
        BufferedReader breader = new BufferedReader(new FileReader("src/sample/database/logins.txt"));
        String line = "";
        while ((line = breader.readLine()) != null) {
            String[] login = line.split(" ");
            if (login[0].equals(username) && login[1].equals(pwrod)) return true;
        }
        return false;
    }

    public void login (String newPage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(newPage));
        // replace "null" with an .fxml to send user to that page when the login button pressed
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root, 346, 624));
        stage.setResizable(false);
        stage.show();
    }

    public void handleRegister(ActionEvent actionEvent) throws IOException {
        String username = uname.getText();
        String password = pword.getText();
        if (username.length() >= 1 && password.length() >= 1) {
            writeLogin(username, password);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Account created");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Username & password needs to be at least 6 characters long");
            alert.showAndWait();
        }
    }

    private static void writeLogin(String uname, String pword) {
        try {
            FileWriter writer = new FileWriter("src/sample/database/logins.txt", true);
            writer.append(uname + " " + pword);
            writer.append("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleClose(ActionEvent actionEvent) {
        Platform.exit();
    }


    public class UserInfo {
        private String userName;
        private String password;

        public UserInfo (String user,String pass) {
            this.userName = user;


        }
        public void setUserName (String val) {
            this.userName = val;
        }

        public void setPassword (String pass){
            this.password = pass;
        }

        public String getUserName () {
            return userName;
        }

        public String getPassword () {
            return password;
        }
    }
}

