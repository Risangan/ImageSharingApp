package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Table {

    public static final ArrayList<ObservableList<Table>> comments = new ArrayList<>();
   public static final ObservableList<Table> data = FXCollections.observableArrayList();


    private final SimpleIntegerProperty rID;
    private final SimpleStringProperty rName;
    private final SimpleStringProperty rDate;
    private final SimpleIntegerProperty rRating;
    SimpleIntegerProperty postIndex; //added new postIndex int to keep track of which post comments belong to

    public Table(int sID, String sName, String sDate, Integer sRating,int id)
    {
        this.rID = new SimpleIntegerProperty(sID);
        this.rName = new SimpleStringProperty(sName);
        this.rDate = new SimpleStringProperty(sDate);
        this.rRating = new SimpleIntegerProperty(sRating);
        this.postIndex = new SimpleIntegerProperty(id);
    }

    public Integer getRID()
    {
        return rID.get();
    }
    public void setRID(Integer v)

    {
        rID.set(v);
    }
    public String getRName()
    {
        return rName.get();
    }
    public void setRName(String v)
    {
        rName.set(v);
    }
    public String getRDate()
    {
        return rDate.get();
    }
    public void setRDate(String v)
    {
        rDate.set(v);
    }
    public Integer getRRating()
    {
        return rRating.get();
    }
    public void setRRating(Integer v)
    {
        rRating.set(v);
    }
    public int getPostIndex () {
        return postIndex.getValue();
    }
    public void setPostIndex (int id) {
        postIndex.set(id);
    }

}