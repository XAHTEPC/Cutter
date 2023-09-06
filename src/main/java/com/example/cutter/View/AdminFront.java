package com.example.cutter.View;

import com.example.cutter.DataBase.Postgre;
import com.example.cutter.Front;
import com.example.cutter.PaneModel.Employee;
import com.example.cutter.PaneModel.Structure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class AdminFront {

    public static ScrollPane scrollPane;
    static Pane pane_scroll;
    public static Pane getStartFront() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url;
        if (Front.theme == 1)
            Url = new FileInputStream("png/admin_day.png");
        else
            Url = new FileInputStream("png/admin_night.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        scrollPane = new ScrollPane();
        scrollPane.setLayoutX(195);
        scrollPane.setLayoutY(105);
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

        Button structure = new Button();
        structure.setBackground(null);
        structure.setLayoutY(55);
        structure.setLayoutX(208);
        structure.setPrefSize(192,21);
        pane.getChildren().add(structure);
        structure.setOnAction(t ->{
            try {
                pane_scroll = Structure.getPane(scrollPane,true);
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

        Button employee = new Button();
        employee.setBackground(null);
        employee.setLayoutX(800);
        employee.setLayoutY(55);
        employee.setPrefSize(214,21);
        pane.getChildren().add(employee);
        employee.setOnAction(t1 ->{
            try {
                pane_scroll = Employee.getPane2(scrollPane, true, false, true);
                scrollPane.setContent(pane_scroll);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        Button addStructure = new Button();
        addStructure.setBackground(null);
        addStructure.setLayoutX(420);
        addStructure.setLayoutY(45);
        addStructure.setMaxSize(40,40);
        addStructure.setMinSize(40,40);
        pane.getChildren().add(addStructure);
        addStructure.setOnAction(t1 -> {
            try {
                Structure.addStructure(scrollPane);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button addEmployee = new Button();
        addEmployee.setBackground(null);
        addEmployee.setLayoutX(1034);
        addEmployee.setLayoutY(45);
        addEmployee.setMaxSize(40,40);
        addEmployee.setMinSize(40,40);
        pane.getChildren().add(addEmployee);
        addEmployee.setOnAction(t1 -> {
            try {
                Employee.addEmployee(scrollPane);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button addWork = new Button("Добавить новое место работы");
        addWork.setLayoutY(600);
        addWork.setLayoutX(500);
        pane.getChildren().add(addWork);
        addWork.setOnAction(t1 ->{
            Group root_add = new Group();
            Scene scene_add = new Scene(root_add, 410, 210);
            Stage newWindow = new Stage();
            newWindow.initStyle(StageStyle.DECORATED);
            Pane pane1 = new Pane();
            pane1.setPrefSize(410,210);
            pane1.setLayoutX(0);
            pane1.setLayoutY(0);
            root_add.getChildren().addAll(pane1);

            FileInputStream Url1;
            if(Front.theme ==1) {
                try {
                    Url1 = new FileInputStream("png/addWork_day.png");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                try {
                    Url1 = new FileInputStream("png/addWork_night.png");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            Image url1 = new Image(Url1);
            ImageView front1 = new ImageView(url1);
            front1.setX(0);
            front1.setY(0);
            pane1.getChildren().add(front1);


            String[] mas = new String[0];
            try {
                mas = Postgre.getEmployee_name();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ObservableList<String> employee1 = FXCollections.observableArrayList(mas);
            ComboBox<String> comboBox3 = new ComboBox<String>(employee1);
            comboBox3.setMaxWidth(215);
            comboBox3.setMinWidth(215);
            comboBox3.setBackground(null);
            comboBox3.setLayoutX(180);
            comboBox3.setLayoutY(70);

            String[] mas1 = new String[0];
            try {
                mas1 = Postgre.getStructure_name();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ObservableList<String> work = FXCollections.observableArrayList(mas1);
            ComboBox<String> comboBox2 = new ComboBox<String>(work);
            comboBox2.setMaxWidth(215);
            comboBox2.setMinWidth(215);
            comboBox2.setBackground(null);
            comboBox2.setLayoutX(180);
            comboBox2.setLayoutY(114);
            if(Front.theme==0){
                comboBox3.setStyle("-fx-text-fill: white;");
                comboBox2.setStyle("-fx-text-fill: white;");
            }
            Button save = new Button();
            save.setLayoutX(125);
            save.setLayoutY(150);
            save.setBackground(null);
            save.setPrefSize(150,32);
            root_add.getChildren().addAll(save,comboBox2,comboBox3);
            save.setOnAction(t2 ->{
                String w1 = comboBox3.getSelectionModel().getSelectedItem();
                String w2 = comboBox2.getSelectionModel().getSelectedItem();
                try {
                    Postgre.addWork(w1,w2);
                    Pane pane2 = Employee.getPane2(scrollPane,true,true,true);
                    scrollPane.setContent(pane2);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                newWindow.close();
            });
            newWindow.setTitle("Добавление сотрудника");
            newWindow.setScene(scene_add);
            newWindow.show();
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
