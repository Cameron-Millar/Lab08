package com.example.lab08;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab08 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        File file = new File("C:\\Users\\camer\\IdeaProjects\\lab08\\src\\main\\resources\\com\\example\\lab08\\Records.csv");
        stage.setTitle("Student Grades");
        GridPane pane1 = new GridPane();
        ObservableList<StudentRecord> students = load(file);


        TableColumn IDCol = new TableColumn("ID");
        IDCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,String>("StudentID"));

        TableColumn AssignCol = new TableColumn("Assignments");
        AssignCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,Double>("Assignments"));

        TableColumn MidCol = new TableColumn("Midterm");
        MidCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,Double>("Midterm"));

        TableColumn FinalCol = new TableColumn("Final Exam");
        FinalCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,Double>("FinalExam"));

        TableColumn TotalCol = new TableColumn("Final Grade");
        TotalCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,Double>("FinalMark"));

        TableColumn LetterCol = new TableColumn("Letter Grade");
        LetterCol.setCellValueFactory(new PropertyValueFactory<StudentRecord,String>("LetterGrade"));

        Menu menu = new Menu("File");
        MenuBar bar = new MenuBar(menu);
        MenuItem ne = new MenuItem("New");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As");
        MenuItem exit = new MenuItem("Exit");
        menu.getItems().addAll(ne,open,save,saveAs,exit);
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                stage.close();
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Writer writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file));
                    for (StudentRecord student : students) {
                        String text = student.getStudentID() + "," + student.getAssignments() + "," + student.getMidterm() +"," + student.getFinalExam() + "\n";
                        writer.write(text);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });
        ne.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                students.clear();
            }
        });
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(stage);
                try {
                    students.setAll(load(file));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        saveAs.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(stage);
                Writer writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file));
                    for (StudentRecord student : students) {
                        String text = student.getStudentID() + "," + student.getAssignments() + "," + student.getMidterm() +"," + student.getFinalExam() + "\n";
                        writer.write(text);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });
        TableView<StudentRecord> MarkTable = new TableView<>();
        MarkTable.getColumns().addAll(IDCol, AssignCol, MidCol, FinalCol, TotalCol, LetterCol);
        MarkTable.setItems(students);
        GridPane.setRowIndex(bar, 0);
        GridPane.setRowIndex(MarkTable, 1);
        pane1.getChildren().addAll(MarkTable, bar);
        Scene scene = new Scene(pane1, 800, 450);
        stage.setScene(scene);
        stage.show();
    }
    public ObservableList<StudentRecord> load(File file) throws FileNotFoundException {

        List<StudentRecord> studentList = new ArrayList<>();
        Scanner sc = new Scanner(file);
        List<String> thisLine;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            thisLine = List.of(line.split(","));
            studentList.add(new StudentRecord(thisLine.get(0), Float.parseFloat(thisLine.get(1)), Float.parseFloat(thisLine.get(2)), Float.parseFloat(thisLine.get(3))));
        }
        ObservableList<StudentRecord> students = FXCollections.observableList(studentList);
        return students;
    }

    public static void main(String[] args) {
        launch();
    }
}