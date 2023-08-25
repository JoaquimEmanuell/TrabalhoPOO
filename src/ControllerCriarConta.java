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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;

public class ControllerCriarConta implements Initializable{
     private Parent root;
     private Scene scene;
     private Stage stage;

     @FXML
     private ChoiceBox<String> tipoChoiceBox;
     @FXML
     private TextField userText;
     @FXML
     private TextField matriculaText;
     @FXML
     private PasswordField senhaText;
     @FXML
     private TextField emailText;
     @FXML
     private Label avisoText;

     private String[] tipos = {"Professor", "Aluno", "Bibliotecario"};

     String usuario;
     String senha;
     String tipo;
     String matricula;
     String email;

     @Override
     public void initialize(URL arg0, ResourceBundle arg1) {
          tipoChoiceBox.getItems().addAll(tipos);
     }

     public void cadastrar(ActionEvent e) throws IOException
     {
          usuario = userText.getText();
          senha = senhaText.getText();
          matricula = matriculaText.getText();
          tipo = tipoChoiceBox.getValue();
          email = emailText.getText();
          boolean atraso = false;
          double valor = 0;

          File teste = new File("/home/joaqm_/Documentos/TrabalhoFinal/Usuarios/" + matricula + ".json");
          if(teste.exists())
          {
               avisoText.setText("A matricula ja esta em uso");
               return;
          }

          if(usuario.isEmpty() || senha.isEmpty() || matricula.isEmpty() || tipo.isEmpty() || email.isEmpty())
          {
               avisoText.setText("Preencha todos os campos");
               return;
          }

          JSONObject objeto = new JSONObject();

          objeto.put("nome", usuario);
          objeto.put("matricula", matricula);
          objeto.put("senha", senha);
          objeto.put("tipo", tipo);
          objeto.put("email", email);
          objeto.put("atraso", atraso);
          objeto.put("valorMultas", valor);
          objeto.put("qtdLivros", 0);

          FileWriter file1 = new FileWriter("/home/joaqm_/Documentos/TrabalhoFinal/Usuarios/" + matricula + ".json");
          file1.write(objeto.toJSONString());
          file1.close(); 

          root = FXMLLoader.load(getClass().getResource("Login.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }
}
