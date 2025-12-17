import java.io.File;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        VBox mainBox = new VBox();
        mainBox.setStyle("-fx-background-color: darkslateblue; -fx-padding: 20;");
        mainBox.setSpacing(15);

        HBox containerBox = new HBox();
        containerBox.setStyle(
                "-fx-background-color: dimgray; -fx-border-color: limegreen; -fx-border-width: 2; -fx-padding: 10;");
        containerBox.setSpacing(15);

        Label instructionsLBL = new Label();
        instructionsLBL.setText("Enter as much grades as you want and also write on how much they are 😉 ");
        instructionsLBL.setStyle("-fx-font-size: 16; -fx-text-fill: lightgray;");

        TextField gradeBox = new TextField();
        gradeBox.setMaxWidth(150);
        gradeBox.setPromptText("Example: 54,34...");
        gradeBox.setStyle(
                "-fx-background-color: darkgray; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-padding: 8;");

        TextField ovrBox = new TextField();
        ovrBox.setMaxWidth(150);
        ovrBox.setPromptText("Example: 100 or 50 or 20");
        ovrBox.setStyle("-fx-background-color: darkgray; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-padding: 8;");

        Button sendBtn = new Button("Calculate 🤞");
        sendBtn.setStyle("-fx-background-color: limegreen; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-weight: bold; -fx-padding: 10;");

        Button saveBtn = new Button("Save Grades 💾");
        saveBtn.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-weight: bold; -fx-padding: 10;");

        Label resultLBL = new Label();
        resultLBL.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-padding: 8;");
        mainBox.getChildren().add(resultLBL);

        // set actions.
        sendBtn.setOnAction(event -> {

            String[] notesText = gradeBox.getText().split(",");
            String[] totalsText = ovrBox.getText().split(",");

            double finalSum = 0;
            int count = Math.min(notesText.length, totalsText.length);

            try {
                for (int i = 0; i < count; i++) {
                    double note = Double.parseDouble(notesText[i].trim());
                    double total = Double.parseDouble(totalsText[i].trim());

                    double percent = (note / total) * 100;
                    finalSum += percent;
                }
            } catch (NumberFormatException e) {
                resultLBL.setText(" Enter valid numbers (ex: 54,33,70)");
                return;
            }

            HBox fileBox = new HBox();
            fileBox.setStyle("-fx-background-color: dimgray; -fx-border-color: limegreen; -fx-border-width: 2; -fx-padding: 10;");
            
            TextField fileName = new TextField();
            fileName.setMaxWidth(150);
            fileName.setPromptText("Example: MyGrades");
            fileName.setStyle("-fx-background-color: darkgray; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-padding: 8;");
            
            Label fileNameLabel = new Label("what name do you want the file to be saved as?");
            fileNameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-padding: 8;");

            double finalGrade = finalSum / count;
            resultLBL.setText("Final grade ≈ " + String.format("%.2f", finalGrade) + "% 😁");

            // fileOut.flush(); // Optional, tells it to save the file RIGHT NOW.
            saveBtn.setOnAction(even -> {
                try{
                    
                    String[] fileNameText = fileName.getText().split(",");
                    
                    
                    PrintWriter fileWriter = new PrintWriter(new FileWriter("" + fileNameText[0] + ".txt"));
                    fileWriter.println("High score: ");
                    fileWriter.print("High scorer: ");
                    fileWriter.close();

                } catch (IOException er) {
                    return;
                } catch (Exception ex) {
                    resultLBL.setText(" An error occurred while saving the file.");
                }
            });
            fileBox.getChildren().addAll(fileNameLabel, fileName);
            mainBox.getChildren().add(fileBox);
        });
        mainBox.getChildren().addAll(instructionsLBL, gradeBox, ovrBox, sendBtn, saveBtn);

        Scene scene = new Scene(mainBox, 700, 600);
        stage.setScene(scene);
        stage.setTitle("My Final Grades 😎😁👌");
        stage.show();
    }
}
