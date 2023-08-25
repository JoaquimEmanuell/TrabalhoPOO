package TrabalhoPOO;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLogin
{
     private Parent root;
     private Scene scene;
     private Stage stage;

     @FXML
     private TextField userText;
     @FXML
     private PasswordField passwordText;
     @FXML
     private Label avisoText;

     public void criarConta(ActionEvent event) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("CriarConta.fxml"));
          stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void logar(ActionEvent e) throws IOException
     {    
          String mat = userText.getText();
          String senha = passwordText.getText();

          if(mat.isEmpty() || senha.isEmpty())
          {
               avisoText.setText("Usuario ou senha inválido");
               return;
          }
          int matricula = Integer.parseInt(mat);

          JSONObject object;
          JSONParser parser = new JSONParser();

          int user;
          String password;

          try {
               object = (JSONObject) parser.parse(new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Usuarios/" + matricula + ".json"));
               user = Integer.parseInt(object.get("matricula").toString());
               password = (String) object.get("senha");
               String nome = (String) object.get("nome");
               FileWriter file = new FileWriter("/home/joaqm_/Documentos/TrabalhoFinal/dado.txt");
               BufferedWriter bw = new BufferedWriter(file);
               bw.write("/home/joaqm_/Documentos/TrabalhoFinal/Usuarios/" + matricula + ".json");
               bw.close();
               file.close();
               if(password.equals(senha) && user == matricula)
               {
                    String tipo = (String) object.get("tipo");
                    if(tipo.equalsIgnoreCase("aluno") || tipo.equalsIgnoreCase("professor"))
                    {
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeUsuario.fxml"));
                         root = loader.load();

                         ControllerHomeUsuario home = loader.getController();
                         home.mostrarNome(nome);
                                        
                         stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                         scene = new Scene(root);
                         stage.setScene(scene);
                         stage.setResizable(false);
                         stage.show(); 
                    }
                    else
                    {
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                         root = loader.load();

                         ControllerHome home = loader.getController();
                         home.mostrarNome(nome);
                         home.pegarId(matricula);
                                   
                         stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                         scene = new Scene(root);
                         stage.setScene(scene);
                         stage.setResizable(false);
                         stage.show();
                    }
               }
               else
               {
                    avisoText.setText("Usuario ou senha incorreta");
               }
          } catch (ParseException e1) {
               avisoText.setText("Usuario nao encontrado");
          } catch (IOException e1)
          {
               avisoText.setText("Usuario nao encontrado");
          }
     }
}
