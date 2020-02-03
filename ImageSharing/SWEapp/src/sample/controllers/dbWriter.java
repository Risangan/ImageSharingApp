package sample.controllers;

import javafx.collections.ObservableList;
import model.Table;

import java.io.*;

public class dbWriter {
    // a class to store and get posts for a model database

    private BufferedWriter bwriter;

    public dbWriter() throws IOException {
        bwriter = new BufferedWriter(new FileWriter("src/sample/database/posts.txt"));
    }
/**
    public boolean save(MainController.Post p) throws IOException {
        bwriter.write(p.getPid() + "\n");
        bwriter.write(p.getPc() + "\n");
        bwriter.write(p.getPv() + "\n");
        bwriter.write("image url \n"); // replace with image url once fixed
        bwriter.write("comment 1 \n"); // replace comments once fixed
        bwriter.write("comment 2 \n");
        bwriter.write("\n"); // posts are separated by a newline
        return true;
    }
**/
    public boolean save2 () throws IOException{
        for (int i =0;i<MainController.data.size();i++){
            bwriter.write(MainController.data.get(i).getPid());
            bwriter.write(MainController.data.get(i).getPc());
            bwriter.write(MainController.data.get(i).getPv());
            bwriter.write(String.valueOf(MainController.data.get(i).getImageFromImageView()));

            for (int j = 0; j< Table.data.size(); j++){
                if (Table.data.get(i).getPostIndex() == i){
                    bwriter.write(Table.data.get(j).getRID());
                    bwriter.write(Table.data.get(j).getRName());
                    bwriter.write(Table.data.get(j).getRDate());
                    bwriter.write(Table.data.get(j).getRRating());
                    bwriter.write(Table.data.get(j).getPostIndex());
                }
            }


        }
        bwriter.write("\n"); // posts are separated by a newline
        return true;
    }

    public void closerWriter() throws IOException {
        bwriter.close();
    }

}
