package TrabalhoPOO;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerEditandoLivro implements Initializable{
     private Parent root;
     private Stage stage;
     private Scene scene;
     private Stage primaryStage;

     @FXML
     private TextField nomeText;
     @FXML
     private TextField autorText;
     @FXML
     private ChoiceBox<String> categoriaCheck;
     @FXML
     private Spinner<Integer> qtdeSpin;
     @FXML
     private ChoiceBox<String> tipoCheck;

     
     private String[] categorias;
     private String[] tipos = {"Sim", "Nao"};

     String local;
     int id;

     @Override
     public void initialize(URL arg0, ResourceBundle arg1) {
          SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
          valueFactory.setValue(0);
          qtdeSpin.setValueFactory(valueFactory);

          iniciarLista();
          categoriaCheck.getItems().addAll(categorias);
          tipoCheck.getItems().addAll(tipos);
     }

     public void iniciarLista()
     {
          FileReader fr;
          try {
               fr = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Categorias/categorias.txt");
               BufferedReader br = new BufferedReader(fr);
               String linha = br.readLine();
               categorias = linha.split("\t");
          } catch (FileNotFoundException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
     }

     public void voltar(ActionEvent e) throws IOException
     {
          Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
          dialogoAviso.setHeaderText("Sair sem salvar");
          dialogoAviso.setContentText("Você tem certeza que deseja sair sem salvar?");
          dialogoAviso.showAndWait();

          root = FXMLLoader.load(getClass().getResource("EditarLivro.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void iniciar(String index)
     {
          /*Json json = new Json();
          Livros livro = json.leituraArquivoLivro("/home/joaqm_/Documentos/TrabalhoFinal/Livros/" + index);*/

          local = index;

          JSONObject object;
          JSONParser parser = new JSONParser();
          try {
               object = (JSONObject) parser.parse(new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Livros/" + index));
               String nome = (String) object.get("nome");
               String autor = (String) object.get("autor");
               String categoria = (String) object.get("categoria");
               boolean tipo = (boolean) object.get("tipo");
               id = Integer.parseInt(object.get("id").toString());
               String t;
               if(tipo)
               {
                    t = "Sim";
               }
               else
               {
                    t = "Nao";
               }
               int qtd = Integer.parseInt(object.get("quantidade").toString());

               SpinnerValueFactory<Integer> factory = qtdeSpin.getValueFactory();
               factory.setValue(qtd);  

               nomeText.setText(nome);
               autorText.setText(autor);
               categoriaCheck.setValue(categoria);
               tipoCheck.setValue(t); 
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

     public void adiconar(ActionEvent e) throws IOException
     {
          String nome = nomeText.getText();
          String autor = autorText.getText();
          String categoria = categoriaCheck.getValue();
          int currentValue = qtdeSpin.getValue();
          String tipo = tipoCheck.getValue();
          boolean tipoo;

          if(tipo.equals("Sim"))
          {
               tipoo = true;
          }
          else
          {
               tipoo = false;
          }

          Livros livro = new Livros(nome, autor, categoria, currentValue);

          JSONObject objeto = new JSONObject();

          objeto.put("nome", nome);
          objeto.put("autor", autor);
          objeto.put("categoria", categoria);
          objeto.put("quantidade", currentValue);
          objeto.put("tipo", tipoo);
          objeto.put("id", id);

          FileWriter file;
          try {
               file = new FileWriter("/home/joaqm_/Documentos/TrabalhoFinal/Livros/" + local, false);
               file.write(objeto.toJSONString());
               file.close();

               Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
               dialogoAviso.setHeaderText("Adicionar Livro");
               dialogoAviso.setContentText("Operação realizada com sucesso!");
               dialogoAviso.showAndWait();

               root = FXMLLoader.load(getClass().getResource("Home.fxml"));
               stage = (Stage)((Node)e.getSource()).getScene().getWindow();
               scene = new Scene(root);
               stage.setScene(scene);
               stage.setResizable(false);
               stage.show();
          } catch (IOException e1) {
               Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
               dialogoAviso.setHeaderText("ERRO");
               dialogoAviso.setContentText("Ocorreu um erro ao adicionar o livro");
               dialogoAviso.showAndWait();
          }
     }

     public void addCategoria(ActionEvent e) throws IOException
     {
          Parent root1 = FXMLLoader.load(getClass().getResource("Popup.fxml"));
          primaryStage = new Stage();
          Scene scene = new Scene(root1);

          primaryStage.setScene(scene);
          primaryStage.setTitle("Adicionando Categoria");
          primaryStage.setResizable(false);
          primaryStage.show();
     }

     /*public void fechar()
     {
          primaryStage.close();
     }*/

     public void sair(ActionEvent e) throws IOException
     {
          Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
          dialogoAviso.setHeaderText("Sair sem salvar");
          dialogoAviso.setContentText("Você tem certeza que deseja sair sem salvar?");
          dialogoAviso.showAndWait();

          //Fazer outro esquema
          
          root = FXMLLoader.load(getClass().getResource("Login.fxml"));
          stage = (Stage)((Node)e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.showAndWait();
     }
}
