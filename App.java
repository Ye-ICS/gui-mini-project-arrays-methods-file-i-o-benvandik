import java.io.File;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage stage){

        VBox mainBox = new VBox();
            mainBox.setStyle ("-fx-background-color: darkslateblue; -fx-padding: 20;");
            mainBox.setSpacing(15);

            HBox containerBox = new HBox();
                containerBox.setStyle("-fx-background-color: dimgray; -fx-border-color: limegreen; -fx-border-width: 2; -fx-padding: 10;");
                containerBox.setSpacing(15);


            Label instructionsLBL = new Label();
                instructionsLBL.setText("Enter as much grades as you want and also write on how much they are 😉 ");
                instructionsLBL.setStyle ("-fx-font-size: 16; -fx-text-fill: lightgray;");

            TextField gradeBox = new TextField();
                gradeBox.setMaxWidth(150);
                gradeBox.setPromptText("Example: 54,34...");
                gradeBox.setStyle("-fx-background-color: darkgray; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-padding: 8;");

                TextField ovrBox = new TextField();
                ovrBox.setMaxWidth(150);
                ovrBox.setPromptText("Example: 100 or 50 or 20");
                ovrBox.setStyle("-fx-background-color: darkgray; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-padding: 8;");
                
            Button sendBtn = new Button ("Calculate 🤞");
                sendBtn.setStyle ("-fx-background-color: limegreen; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-weight: bold; -fx-padding: 10;");

                Label resultLBL = new Label();
                resultLBL.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-padding: 8;");
                mainBox.getChildren().add(resultLBL);

                // set actions.
                sendBtn.setOnAction(event -> {
                    Double got = Double.parseDouble(gradeBox.getText());
                    Double total = Double.parseDouble(ovrBox.getText());

                    Double finalGrade = (got/ total) * 100 ;

                    Label mark = new Label("you got approximately " +finalGrade+ " / 100  😁😁😁");
                    instructionsLBL.setStyle ("-fx-font-size: 16; -fx-text-fill: lightgray;");

                });

        mainBox.getChildren().addAll(instructionsLBL,gradeBox, ovrBox, sendBtn);

        Scene scene = new Scene (mainBox, 700, 600);
        stage.setScene(scene);
        stage.setTitle("My Final Grades 😎😁👌");
        stage.show();
    }
}
