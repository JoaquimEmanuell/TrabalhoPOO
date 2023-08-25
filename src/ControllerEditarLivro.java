package TrabalhoPOO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
 
public class ControllerEditarLivro implements Initializable {
     private Parent root;
     private Scene scene;
     private Stage stage;

     @FXML
     private ListView<String> livrosList;

     File file = new File("/home/joaqm_/Documentos/TrabalhoFinal/Livros");
     String[] lista1 = file.list();
     ArrayList<String> lista = new ArrayList<String>();
     String currentLivro = "";

     public void pegar()
     {
          for(int i = 0; i < lista1.length; i++)
          {
               try {
                    JSONParser parser = new JSONParser();
                    JSONObject object = (JSONObject) parser.parse(new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Livros/" + lista1[i]));
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
          livrosList.getItems().addAll(lista);
          livrosList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
               @Override
               public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                    currentLivro = livrosList.getSelectionModel().getSelectedItem();
               }
          });
     }

     public String procurar(String tituloo)
     {
          for(int i = 0; i < lista.size(); i++)
          {
               JSONParser parser = new JSONParser();
               JSONObject object;
               try {
                    object = (JSONObject) parser.parse(new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Livros/" + lista1[i]));
                    String nome = (String) object.get("nome");
                    if(tituloo.equals(nome))
                    {
                         return lista1[i];
                    } 
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
          return null;
     }

     public void mudarCena(ActionEvent e) throws IOException
     {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("EditandoLivro.fxml"));
          root = loader.load();

          String nome = procurar(currentLivro);

          ControllerEditandoLivro ed = loader.getController();
          ed.iniciar(nome);

          stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     //Criar função para procurar livro no banco de dados
     
}
