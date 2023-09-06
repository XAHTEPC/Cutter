package com.example.cutter.View;

import com.example.cutter.Front;
import com.example.cutter.Logic.Toast;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class IntroFront {
    static Button enter;
    public static PasswordField password;
    public static TextField login;
    public static Pane getStartFront() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url;
        if(Front.theme ==1)
            Url = new FileInputStream("png/intro_day.png");
        else
            Url = new FileInputStream("png/intro_night.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        login = new TextField();
        login.setMinWidth(380);
        login.setMaxWidth(380);
        login.setMinHeight(70);
        login.setMaxHeight(70);
        login.setLayoutX(410);
        login.setLayoutY(245);
        login.setPromptText("login");
        login.setFont(Font.font("STXihei", 20));
        login.setBackground(null);
        pane.getChildren().add(login);

        password = new PasswordField();
        password.setPrefSize(380,70);
        password.setBackground(null);
        password.setPromptText("password");
        password.setLayoutX(410);
        password.setLayoutY(394);
        password.setFont(Font.font("STXihei", 20));
        pane.getChildren().add(password);
        if(Front.theme == 0){
            password.setStyle("-fx-text-fill: white;");
            login.setStyle("-fx-text-fill: white;");
        }

        enter = new Button();
        enter.setBackground(null);
        enter.setLayoutX(400);
        enter.setLayoutY(515);
        enter.setMaxSize(400,70);
        enter.setMinSize(400,70);
        enter.setOnAction(t->{
            try {
                Front.login(password.getText(),login.getText());

            } catch (SQLException | FileNotFoundException e) {
                System.out.println("ERROR");
                password.setText("");
                Toast.show("Неверный логин или пароль",pane,232,700);
            }
        });
        pane.getChildren().add(enter);

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
