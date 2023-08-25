package TrabalhoPOO;

import java.io.BufferedReader;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerHomeUsuario {
     private Scene scene;
     private Stage stage;
     private Parent root;
     
     @FXML
     private Label welcomeText;

     public void mostrarNome(String nome)
     {
          welcomeText.setText("Seja bem-vindo(a) " + nome);
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

     public void pegarLivro(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("PegarLivro.fxml"));
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

     public void listarHistorico(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("RelatorioUsuario.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void editarDados(ActionEvent e) throws IOException
     {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("EditarDados.fxml"));
          root = loader.load();

          FileReader fr = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/dado.txt");
          BufferedReader br = new BufferedReader(fr);
          String path = br.readLine();

          JSONParser parser = new JSONParser();
          JSONObject obj;
          try {
               obj = (JSONObject) parser.parse(new FileReader(path));

               ControllerEditarDados ed = new ControllerEditarDados();
               ed.iniciar(path);

               stage = (Stage)((Node)e.getSource()).getScene().getWindow();
               scene = new Scene(root);
               stage.setScene(scene);
               stage.setResizable(false);
               stage.show();
          } catch (ParseException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
          }
     }

     public void pagar(ActionEvent e)
     {
          FileReader fr;
          try {
               fr = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/dado.txt");
               BufferedReader br = new BufferedReader(fr);
               String path = br.readLine();

               JSONParser parser = new JSONParser();
               JSONObject obj;
               obj = (JSONObject) parser.parse(new FileReader(path));
               boolean tipo = (boolean) obj.get("atraso");
               int matricula = Integer.parseInt(obj.get("matricula").toString());
               double multa = (Double) obj.get("multa");

               multa = 0;
               tipo = false;

               System.out.println(obj.toString());

               obj.put("atraso", tipo);
               obj.put("multa", multa);

               Usuario u = new Usuario();
               u.setAtraso(tipo);
               u.setMulta(multa);
               u.setEmail((String) obj.get("email"));
               u.setNome((String) obj.get("nome"));
               u.setSenha((String) obj.get("senha"));
               u.setTipo((String) obj.get("tipo"));
               u.setId((Integer.parseInt(obj.get("matricula").toString())));
               Json j1 = new Json();
               j1.arquivoUsuario(u, "/home/joaqm_/Documentos/TrabalhoFinal/Usuarios/" + matricula + ".json");

               Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
               dialogoAviso.setHeaderText("Multas pagas");
               dialogoAviso.setContentText("voce quitou suas dividas");
               dialogoAviso.showAndWait();
          } catch (FileNotFoundException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
          } catch ( IOException e1)
          {
               e1.printStackTrace();
          } catch (ParseException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
          }
     }
}
