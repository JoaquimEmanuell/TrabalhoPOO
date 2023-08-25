package TrabalhoPOO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerRelatorioLivro implements Initializable{
     private Parent root;
     private Scene scene;
     private Stage stage;

     @FXML
     private TextField nomeSearch;
     @FXML
     private Button searchButton;
     @FXML
     private ListView<String> historicoList;

     String[] nomes = {};
     String path;

     @Override
     public void initialize(URL location, ResourceBundle resources) {
          historicoList.getItems().setAll(nomes);     
     }

     public void voltar(ActionEvent e) throws IOException {
          try {
               FileReader fr = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/dado.txt");
               BufferedReader br = new BufferedReader(fr);
               path = br.readLine();

               JSONParser parser = new JSONParser();
               JSONObject obj;
               obj = (JSONObject) parser.parse(new FileReader(path));
               String tipo = (String) obj.get("tipo");

               if(tipo.equals("Bibliotecario"))
               {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                    root = loader.load();
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
               }
               else
               {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeUsuario.fxml"));
                    root = loader.load();
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
               }
          } catch (ParseException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
          }
     }

     public void sair(ActionEvent e) throws IOException {
          root = FXMLLoader.load(getClass().getResource("Login.fxml"));
          stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }
     
     public void buscar(ActionEvent e)
     {
          try {
               FileReader fr = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/RelatoriosLivro/relatorio_" + nomeSearch.getText() + ".txt");
               System.out.println(nomeSearch.getText());
               BufferedReader br = new BufferedReader(fr);
               String linha = br.readLine();
               nomes = linha.split("\t");
               historicoList.getItems().setAll(nomes);
          } catch (FileNotFoundException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
          } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
          }
     }
}
