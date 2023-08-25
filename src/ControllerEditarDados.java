package TrabalhoPOO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerEditarDados {
     private Parent root;
     private Scene scene;
     private Stage stage;

     @FXML
     private TextField nomeText;
     @FXML
     private TextField matriculaText;
     @FXML
     private TextField emailText;
     @FXML
     private PasswordField senhaText;

     String local;

     public void voltar(ActionEvent e) throws IOException {
          root = FXMLLoader.load(getClass().getResource("HomeUsuario.fxml"));
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
     
     public void adicicionar(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("HomeUsuario.fxml"));
          stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void iniciar(String index)
     {
          JSONObject object;
          JSONParser parser = new JSONParser();
          try {
               object = (JSONObject) parser.parse(new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Usuarios/123.json"));
               System.out.println(object.get("nome").toString());
               String nome = (String) object.get("nome");
               String email = (String) object.get("email");
               String matricula = object.get("matricula").toString();
               String senha = (String) object.get("senha");

               nomeText.setText(nome);
               emailText.setText(email);
               matriculaText.setText(matricula);
               senhaText.setText(senha);
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

     public void adicicionar()
     {

     }
}
