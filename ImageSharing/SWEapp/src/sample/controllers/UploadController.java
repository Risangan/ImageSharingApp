package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class UploadController {
    FileChooser path = new FileChooser();

    @FXML
    private TextField title;
    @FXML
    private TextField description;


    String filePath;
    Image imageUpload;
    public static ArrayList<MainController.Post> postList = new ArrayList<>();

    File f1;
    Image imageUploadPath;

    @FXML
    public void imagePath(ActionEvent event){

        path.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpg File", "*.jpg"));
        //TODO:
        f1 = path.showOpenDialog(null);
        filePath = f1.getAbsolutePath();


    }

    public void submit(ActionEvent event) throws IOException {
        String t = title.getText();
        String d = description.getText();

        int arrayPos = MainController.postArrayID;
        arrayPos++;// increments array position by 1

        /***********************
         *
         * TODO: Get imageUploadPath to work
         *
         *
         */

        //imageUploadPath = ImageIO.read(f1);
        ImageView toUpload = new ImageView(new Image( new FileInputStream(filePath)));
        toUpload.setPreserveRatio(true);
        toUpload.setFitWidth(100);
        toUpload.setFitHeight(100);

//Code below ensures uploaded image get updated in table and ensures that arraypos is corredt
        int apos = MainController.data.get(MainController.data.size()-1).getArrayID();

      //  MainController.Post p = new MainController.Post(t,d,1,toUpload,apos + 1);


        MainController.Post entry  = new MainController.Post(t, d, 1,toUpload,apos+1);
        MainController.data.add(entry);

        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/MainPage.fxml"));
        // replace "null" with an .fxml to send user to that page when the login button pressed
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 346, 624));
        stage.setResizable(false);

        stage.show();



    }

    //TO DO:
    // Close windows when changing scenes
    // Store to file
    // Read from file
    // Resize MainController images
    // Design ...



}
