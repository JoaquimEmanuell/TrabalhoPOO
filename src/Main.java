package TrabalhoPOO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
     public static void main(String[] args) {
          launch(args);
     }

     @Override
     public void start(Stage primaryStage) throws Exception {
          Parent rootLogin = FXMLLoader.load(getClass().getResource("Login.fxml"));
          Scene login = new Scene(rootLogin);

          primaryStage.setScene(login);
          primaryStage.setResizable(false);
          primaryStage.show();
     }
}
