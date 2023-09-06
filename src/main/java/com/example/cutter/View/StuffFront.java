package com.example.cutter.View;

import com.example.cutter.Front;
import com.example.cutter.PaneModel.Client;
import com.example.cutter.PaneModel.Visit;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class StuffFront {
    static ScrollPane scrollPane;
    static Pane pane_scroll;
    public static Pane getStartFront() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url;
        if (Front.theme == 1)
            Url = new FileInputStream("png/stuff_day.png");
        else
            Url = new FileInputStream("png/stuff_night.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);
        scrollPane = new ScrollPane();
        scrollPane.setLayoutX(195);
        scrollPane.setLayoutY(150);
        scrollPane.setMaxHeight(500);
        scrollPane.setMaxWidth(900);
        scrollPane.setMinHeight(500);
        scrollPane.setMinWidth(900);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        pane_scroll = new Pane();
        pane.getChildren().add(scrollPane);

        Button client = new Button();
        client.setBackground(null);
        client.setLayoutY(55);
        client.setLayoutX(246);
        client.setPrefSize(154,21);
        pane.getChildren().add(client);
        client.setOnAction(t ->{
            try {
                pane_scroll = Client.getPane(scrollPane,false, true,false);
                scrollPane.setContent(pane_scroll);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        Button add = new Button();
        add.setLayoutX(420);
        add.setLayoutY(45);
        add.setPrefSize(40,40);
        add.setBackground(null);
        pane.getChildren().add(add);

        add.setOnAction(t->{
            try {
                Client.addClient(scrollPane, false, false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button visit = new Button();
        visit.setBackground(null);
        visit.setLayoutX(800);
        visit.setLayoutY(55);
        visit.setPrefSize(134,21);
        pane.getChildren().add(visit);
        visit.setOnAction(t1 ->{
            try {
                pane_scroll = Visit.getPane(scrollPane, false);
                scrollPane.setContent(pane_scroll);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button exit = new Button();
        exit.setBackground(null);
        exit.setLayoutX(1130);
        exit.setLayoutY(730);
        exit.setPrefSize(50,50);
        pane.getChildren().add(exit);
        exit.setOnAction(t ->{
            try {
                Front.exit();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button changeTheme = new Button();
        changeTheme.setBackground(null);
        changeTheme.setLayoutX(20);
        changeTheme.setLayoutY(730);
        changeTheme.setMaxSize(50,50);
        changeTheme.setMinSize(50,50);
        pane.getChildren().add(changeTheme);
        changeTheme.setOnAction(t1 ->{
            if(Front.theme ==0)
                Front.theme = 1;
            else
                Front.theme = 0;
            Front.root.getChildren().remove(Front.pane);
            try {
                Front.pane = getStartFront();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Front.root.getChildren().add(Front.pane);
        });
        return pane;
    }
}
