package TrabalhoPOO;

import java.io.IOException;

import org.json.simple.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerHome 
{
     private Scene scene;
     private Stage stage;
     private Parent root;

     private int path;
     @FXML
     private Label welcomeText;

     public void mostrarNome(String nome)
     {
          welcomeText.setText("Seja bem-vindo(a) " + nome);
     }

     public void pegarId(int nome)
     {
          path = nome;
     }

     public void sair(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("Login.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void addLivro(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("AdicionarLivro.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }
     
     public void pegarLivro(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("PegarLivro.fxml"));

          ControllerPegarLivro p = new ControllerPegarLivro();
          p.pegarDados(path);

          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void devolverLivro(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("DevolverLivro.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void relatorioLivro(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("RelatorioLivro.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void relatorioUsuario(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("RelatorioUsuario.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void editarLivro(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("EditarLivro.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void removerLivro(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("RemoverLivro.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void listarUsuarios(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("ListarUsuarios.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }
}
