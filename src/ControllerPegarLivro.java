package TrabalhoPOO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
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
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ControllerPegarLivro implements Initializable{
     private Parent root;
     private Scene scene;
     private Stage stage;

     @FXML
     private ListView<String> livrosList;
     @FXML
     private ListView<String> selecionadosList;
     @FXML
     private DatePicker dataSelector;
     private int id;
     private String path;
     private String teste1;

     File file = new File("/home/joaqm_/Documentos/TrabalhoFinal/Livros");
     String[] lista1 = file.list();
     ArrayList<String> lista = new ArrayList<String>();
     String currentLivro = "";
     ArrayList<String> listaSelecionados = new ArrayList<String>();
     String[] listaComentarios = {};
     String[] listaSugestoes = {};
     int qtd = 0;
     
     LocalDate data;
     int max = 0;
     int valor = 0;

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

     public void pegarDados(int nome)
     {
          id = nome;
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

     public void comentarios(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("Comentarios.fxml"));
          stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     public void sugestoes(ActionEvent e) throws IOException
     {
          root = FXMLLoader.load(getClass().getResource("Sugestoes.fxml"));
          stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.setResizable(false);
          stage.show();
     }

     @Override
     public void initialize(URL location, ResourceBundle resources) {
          pegar();

          FileReader fr;
          try {
               fr = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/dado.txt");
               BufferedReader br = new BufferedReader(fr);
               path = br.readLine();

               JSONParser parser = new JSONParser();
               JSONObject obj = (JSONObject) parser.parse(new FileReader(path));
               String tipo = obj.get("matricula").toString();

               if(tipo.equals("Aluno"))
               {
                    max = 2;
               }
               else
               {
                    max = 3;
               }

               livrosList.getItems().addAll(lista);
               livrosList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                         valor++;
                         if(valor >= max)
                         {
                              return;
                         }
                         currentLivro = livrosList.getSelectionModel().getSelectedItem();
                         listaSelecionados.add(currentLivro);
                         selecionadosList.getItems().add(currentLivro);
                    }
               });
          } catch (FileNotFoundException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          } catch (IOException e)
          {
               e.printStackTrace();
          } catch (ParseException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
     }

     /*public void mudanca()
     {
          //listaSelecionados[qtd] = currentLivro;
          selecionadosList.getItems().addAll(listaSelecionados);
     }*/

     public boolean verificar()
     {
          FileReader fr;
          try {
               fr = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/dado.txt");
               BufferedReader br = new BufferedReader(fr);
               path = br.readLine();

               JSONParser parser = new JSONParser();
               JSONObject obj = (JSONObject) parser.parse(new FileReader(path));
               boolean atraso = (boolean) obj.get("atraso");
               if(atraso)
               {
                    return true;
               }
               else
               {
                    return false;
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
          return false;
     }

     public void mudarCena(ActionEvent e) throws IOException
     {
          if(verificar())
          {
               FileReader fr = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/dado.txt");
               BufferedReader br = new BufferedReader(fr);
               path = br.readLine();

               JSONParser parser = new JSONParser();
               JSONObject obj;
               try {
                    obj = (JSONObject) parser.parse(new FileReader(path));
                    double multa = (Double) obj.get("multa");

                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setHeaderText("Caloteiro");
                    dialogoAviso.setContentText("Pague suas multas para pegar um livro emprestado\nValor das multas: R$" + multa);
                    dialogoAviso.showAndWait();
                    return;
               } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
               }
          }
          salvar();

          
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

     /*public String procurar(String tituloo)
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
     }*/

     public void salvar()
     {
          JSONObject objeto = new JSONObject();

          Date teste = new Date();
          String index = new SimpleDateFormat("ddMMyyyy").format(teste);
          String index1 = new SimpleDateFormat("HHmmss").format(teste);
          String index2 = index + index1;

          try {
               FileReader fr = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/dado.txt");
               BufferedReader br = new BufferedReader(fr);
               path = br.readLine();

               JSONParser parser = new JSONParser();
               JSONObject obj = (JSONObject) parser.parse(new FileReader(path));
               int id = Integer.parseInt(obj.get("matricula").toString());

               String dia = data.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
               objeto.put("idTransacao", index2);
               objeto.put("usuario", id);
               objeto.put("data", dia);

               JSONArray livros = new JSONArray();
               for(int i = 0; i < listaSelecionados.size(); i++)
               {
                    livros.add(listaSelecionados.get(i));
               }

               objeto.put("livros", livros);
               FileWriter file1;
               try {
                    file1 = new FileWriter("/home/joaqm_/Documentos/TrabalhoFinal/Emprestimos/" + index2 + ".json");
                    file1.write(objeto.toJSONString());
                    file1.close(); 

                    for(int i = 0; i < listaSelecionados.size(); i++)
                    {
                         FileWriter fw = new FileWriter("/home/joaqm_/Documentos/TrabalhoFinal/RelatoriosLivro/relatorio_" + listaSelecionados.get(i) + ".txt", true);
                         BufferedWriter bw = new BufferedWriter(fw);
                         bw.write(index2 + "\t");
                         bw.close();
                         fw.close();

                         FileWriter fw1 = new FileWriter("/home/joaqm_/Documentos/TrabalhoFinal/RelatoriosUsuario/relatorio_" + id + ".txt", true);
                         BufferedWriter bw1 = new BufferedWriter(fw1);
                         bw1.write(listaSelecionados.get(i) + "\t");
                         bw1.close();
                         fw1.close();
                    }

                    Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAviso.setHeaderText("Pegando Livro");
                    dialogoAviso.setContentText("Operação realizada com sucesso!\nNúmero do emprestimo: " + index2);
                    dialogoAviso.showAndWait();
               } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
               }
               } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
               } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
               } catch (ParseException e)
               {
                    e.printStackTrace();
               }
     }

     public void getDate(ActionEvent e)
     {
          data = dataSelector.getValue();
     }
}
