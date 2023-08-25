package TrabalhoPOO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ControllerSugestoes implements Initializable {
     private Parent root;
     private Scene scene;
     private Stage stage;
     @FXML
     private ListView listaSugestoesList;

     String[] sugestoes;

     public void voltar(ActionEvent e) throws IOException {
          root = FXMLLoader.load(getClass().getResource("PegarLivro.fxml"));
          stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void sair(ActionEvent e) throws IOException {
          root = FXMLLoader.load(getClass().getResource("Login.fxml"));
          stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     @Override
     public void initialize(URL location, ResourceBundle resources) {
          //comentariosList.getItems().addAll(comentarios);
     }
}
