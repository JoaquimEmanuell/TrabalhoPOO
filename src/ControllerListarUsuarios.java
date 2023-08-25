package TrabalhoPOO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ControllerListarUsuarios implements Initializable{
     private Parent root;
     private Scene scene;
     private Stage stage;

     @FXML
     private ListView<String> usersList;

     File file = new File("/home/joaqm_/Documentos/TrabalhoFinal/Usuarios");
     String[] lista1 = file.list();
     ArrayList<String> lista = new ArrayList<String>();

     public void pegar()
     {
          for(int i = 0; i < lista1.length; i++)
          {
               try {
                    JSONParser parser = new JSONParser();
                    JSONObject object = (JSONObject) parser.parse(new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Usuarios/" + lista1[i]));
                    String nome = (String) object.get("nome");
                    lista.add(nome);
               } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
               } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
               } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
               }
          }
     }

     public void voltar(ActionEvent e) throws IOException {
          root = FXMLLoader.load(getClass().getResource("Home.fxml"));
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
          pegar();
          usersList.getItems().addAll(lista);
     }
}
