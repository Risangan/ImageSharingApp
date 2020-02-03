package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MainController implements Initializable {


    @FXML
    protected TableView<Post> table;

    @FXML private TableColumn postID;
    @FXML private TableColumn PostContent;
    @FXML private TableColumn postVotes;
    @FXML private TableColumn photoCol;

    @FXML private Button addPostButton;
    static ImageView imgg = new ImageView(new Image("sample/images/Snapchat-1595421788.jpg"));
    static Image testImage = new Image ("sample/images/Snapchat-1595421788.jpg");

    public static int postArrayID; //int to keep track of which post to display in post page, incremented in UploadController

    final static ObservableList<Post> data
            = FXCollections.observableArrayList(
            new Post("Post 1", "test post", 1,imgg,0)
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgg.setPreserveRatio(true);
        imgg.setFitHeight(100);
        imgg.setFitWidth(100);

        postID.setMinWidth(10);
        PostContent.setMinWidth(100);
        postVotes.setMinWidth(10);
        photoCol.setMinWidth(100);
        table.getItems().setAll(this.data);

        postID.setCellValueFactory(new PropertyValueFactory<Post,String>("pid"));
        PostContent.setCellValueFactory(new PropertyValueFactory("pc"));
        postVotes.setCellValueFactory(new PropertyValueFactory("pv"));
        photoCol.setCellValueFactory(new PropertyValueFactory("image"));
    }



    public void addPostButton (ActionEvent event) throws IOException {
        System.out.println("Add post");
        changeScene("/sample/fxml/sampleUpload.fxml");
    }

    public void clickItem(MouseEvent event) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.printf("You clicked on: " + (table.getSelectionModel().getSelectedCells().get ));
                System.out.println(table.getSelectionModel().getSelectedItem().getPc());
                System.out.println(String.valueOf(table.getSelectionModel().getSelectedItem().getPv()));
                System.out.println(table.getSelectionModel().getSelectedItem().getPid());
                System.out.printf(String.valueOf(table.getSelectionModel().getSelectedItem().getArrayID()));

                try {
                    postArrayID = table.getSelectionModel().getSelectedItem().getArrayID();
                    String currentPost = table.getSelectionModel().getSelectedItem().getPid();
                    changeScene("/sample/fxml/PostController.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void closeApp(ActionEvent actionEvent) throws IOException {
        dbWriter d = new dbWriter();
        /**for (Post p : data) {
            d.save(p);
        }
        **/

        d.save2();
        d.closerWriter();
        Platform.exit();
    }

    public static class Post {
        private SimpleStringProperty postID;
        private SimpleStringProperty PostContent;
        private SimpleIntegerProperty PostVotes;
        private ImageView image;
        private SimpleIntegerProperty arrayID;

        protected Post(String pid, String pc, int pv, ImageView img,int arrayID) {
            this.postID = new SimpleStringProperty(pid);
            this.PostContent = new SimpleStringProperty(pc);
            this.PostVotes = new SimpleIntegerProperty(pv);
            this.image = img;
            this.arrayID = new SimpleIntegerProperty(arrayID);
        }

        public String getPid() {
            return postID.get();
        }

        public void setPid (String id) {
            postID.set(id);
        }

        public String getPc () {
            return PostContent.get();
        }

        public void setPc (String content) {
            PostContent.set(content);
        }

        public int getPv() {
            return PostVotes.get();
        }

        public void setPv (int votes) {
            PostVotes.set(votes);
        }

        public void setImage (ImageView val) {
            image = val;
        }

        public ImageView getImage() {
            return image;
        }

        public void setArrayID (int val) {
            arrayID.set(val);
        }

        public Image getImageFromImageView () {
            return image.getImage();
        }

        public int getArrayID () {
            return arrayID.get();
        }
    }


    public void changeScene (String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        // replace "null" with an .fxml to send user to that page when the login button pressed
        Stage stage = (Stage) table.getScene().getWindow();
        stage.setScene(new Scene(root, 346, 624));
        stage.setResizable(false);
        stage.show();
    }



}

