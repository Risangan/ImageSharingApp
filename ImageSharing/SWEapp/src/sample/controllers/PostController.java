package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Table;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.time.LocalDate;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;

public class PostController implements Initializable
{

    @FXML
    TableView<Table> tableID;
    @FXML
    TableColumn<Table, Integer> iID;
    @FXML
    TableColumn<Table, String> iName;
    @FXML
    TableColumn<Table, String> iDate;
    @FXML
    TableColumn<Table, Integer> iRating;

    @FXML
    javafx.scene.image.ImageView postImage;

    @FXML
    TextField nameInput;

    @FXML
    Button submit;

    @FXML Button upVoteButton;
    @FXML Button downVoteButton;
    @FXML Button backButton;

    @FXML
    TextField postTitle = new TextField();

    @FXML
    TextField postDescription = new TextField();




    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate localDate = LocalDate.now();






            //Just a constructor to test if comments get stored





    private int rating = 0;

    private int iNumber = 0;

    int apos = MainController.postArrayID; //apos is the variable that refers to the current post, use it to get the current post

    int votes = MainController.data.get(apos).getPv(); // get post votes
    ArrayList<ObservableList<Table>> comments = new ArrayList<>();
    final ObservableList<Table> currentData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //model.Table.data.add(new Table(0,"hello",dtf.format(localDate),0,0));
        //comments.add(data);
        iID.setCellValueFactory(new PropertyValueFactory<Table, Integer>("rID"));
        iName.setCellValueFactory(new PropertyValueFactory<Table, String>("rName"));
        iDate.setCellValueFactory(new PropertyValueFactory<Table, String>("rDate"));
        iRating.setCellValueFactory(new PropertyValueFactory<Table, Integer>("rRating"));
        //postImage = MainController.data.get(0).getImage();

        postTitle.setText(MainController.data.get(apos).getPid());
        postDescription.setText(MainController.data.get(apos).getPc());


        if (apos > Table.comments.size()){
            Table.comments.add(Table.data);
        }

        postImage.setImage(MainController.data.get(apos).getImageFromImageView()); //gets arrayindex and gets image from MainController.Post

        sortAndSet();



    }

    public void sortAndSet () {

        for (int i=0; i<Table.data.size();i++){
            if (Table.data.get(i).getPostIndex() == apos) {
                int rid;
                String rname;
                String rdate;
                int rrating;
                int index;
                //Loop through list to find comments that are not related to current post and hide them
                rid = Table.data.get(i).getRID();
                rname = Table.data.get(i).getRName();
                rdate = Table.data.get(i).getRDate();
                rrating = Table.data.get(i).getRRating();
                index = Table.data.get(i).getPostIndex();
                currentData.add(new Table(rid,rname,rdate,rrating,index));

            }

        }

        tableID.setItems(currentData);




        postTitle.setEditable(false);

        postDescription.setEditable(false);

    }

    public void AddItem(ActionEvent event) throws IOException {

        Table entry = new Table(iNumber, nameInput.getText(), dtf.format(localDate), rating,apos); //added apos to keep track of comments

        iNumber++;
        model.Table.data.add(entry);

        refresh();

        clearForm();

        tableID.setItems(currentData);







    }

    public void upVote (ActionEvent event) {

        MainController.data.get(apos).setPv(votes++);
        System.out.print(votes);
    }

    public void downVote (ActionEvent event) {

        MainController.data.get(apos).setPv(votes--);
        System.out.print(votes);
    }

    public void goBack () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/MainPage.fxml"));
        // replace "null" with an .fxml to send user to that page when the login button pressed
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root, 346, 624));
        stage.setResizable(false);
        stage.show();
    }

    public void refresh () throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/PostController.fxml"));
        // replace "null" with an .fxml to send user to that page when the login button pressed
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root, 346, 624));
        stage.setResizable(false);
        stage.show();
    }

    private void clearForm()
    {
        nameInput.clear();
    }
}