package TrabalhoPOO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.text.html.HTMLEditorKit.Parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerDevolverLivro implements Initializable{
     private Parent root;
     private Scene scene;
     private Stage stage;

     @FXML
     private ListView<String> emprestimosList;
     @FXML
     private DatePicker dataSelector;
     @FXML
     private TextField searchText;
     @FXML
     private TextArea comenatrioText;

     ArrayList<String> resultadosBusca = new ArrayList<String>();
     String currentLivro = "";
     LocalDate data;
     String path;

     @Override
     public void initialize(URL location, ResourceBundle resources) {
          emprestimosList.getItems().addAll(resultadosBusca);
          emprestimosList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
               @Override
               public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                    currentLivro = emprestimosList.getSelectionModel().getSelectedItem();
               }
          });
          //comenatrioText.setWrapText(true);
     }

     public void buscar(ActionEvent e)
     {
          String nome = searchText.getText();

          File file = new File("/home/joaqm_/Documentos/TrabalhoFinal/Emprestimos");
          String[] listinha = file.list();

          //ObservableList<String> items = FXCollections.observableArrayList();
          for(int i = 0; i < listinha.length; i++)
          {
               //JSONObject object;
               JSONParser parser = new JSONParser();
               try {
                    String arq = listinha[i];
                    FileReader reader = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Emprestimos/" + arq);
                    JSONObject emprestimo = new JSONObject();
                    emprestimo = (JSONObject) parser.parse(reader);
                    JSONArray livros = (JSONArray) emprestimo.get("livros");
                    for(Object titulo : livros)
                    {
                         if(titulo.equals(nome))
                         {
                              emprestimosList.getItems().add(emprestimo.get("idTransacao").toString());
                         }
                    }
               } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
               } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
               } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
               }
          }
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

     public void mudarCena(ActionEvent e) throws IOException
     {
          verificar();

          Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
          dialogoAviso.setHeaderText("Operação Realizada");
          dialogoAviso.setContentText("Sua devolução foi realizada com sucesso!");
          dialogoAviso.showAndWait();

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

     public void salvar()
     {
          verificar();
          
          /*String comentario = comenatrioText.getText();

          try {
               FileReader reader = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Emprestimos/" + currentLivro + ".json");
               JSONObject emprestimo = new JSONObject();
               JSONParser parser = new JSONParser();
               emprestimo = (JSONObject) parser.parse(reader);
               JSONArray livros = (JSONArray) emprestimo.get("livros");

               
               
               FileWriter fw = new FileWriter("/home/joaqm_/Documentos/TrabalhoFinal/Emprestimos/" + currentLivro + ".json");

               JSONObject objeto = new JSONObject();
               //objeto.put("comentarios", );
          } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
          } catch (ParseException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
          }*/
     }

     public void getDate(ActionEvent e)
     {
          data = dataSelector.getValue();
     }

     public void verificar()
     {
          FileReader reader;
          try {
               reader = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Emprestimos/" + currentLivro + ".json");
               JSONObject emprestimo = new JSONObject();
               JSONParser parser = new JSONParser();
               emprestimo = (JSONObject) parser.parse(reader);
               String dia = (String) emprestimo.get("data");
               DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd");
               LocalDate d = LocalDate.parse(dia, formato);
               data.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
               int matricula = Integer.parseInt(emprestimo.get("usuario").toString());
               System.out.println(matricula);
               FileReader fr = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Usuarios/" + matricula + ".json");
               JSONObject usuario = new JSONObject();
               JSONParser par = new JSONParser();
               usuario = (JSONObject) parser.parse(fr);
               String tipo = (String) usuario.get("tipo");
               double taxa;
               if(tipo.equals("Aluno"))
               {
                    taxa = 0.5;
               }
               else
               {
                    taxa = 0.8;
               }
               Period p = Period.between(d, data);
               int dias = p.getDays();

               if(dias > 7)
               {
                    double valor = (dias - 7) * taxa;
                    FileWriter m = new FileWriter("/home/joaqm_/Documentos/TrabalhoFinal/multas.txt", true);
                    BufferedWriter bw = new BufferedWriter(m);
                    bw.write(matricula + "\t" + valor);
                    bw.newLine();
                    bw.close();
                    m.close();

                    FileReader ffr = new FileReader("/home/joaqm_/Documentos/TrabalhoFinal/Usuarios/" + matricula + ".json");
                    JSONObject uusuario = new JSONObject();
                    JSONParser ppar = new JSONParser();
                    uusuario = (JSONObject) ppar.parse(ffr);

                    boolean at = (boolean) uusuario.get("atraso");
                    double v = (double) uusuario.get("valorMultas");

                    v = v + valor;
                    at = true;

                    System.out.println(uusuario.toString());

                    uusuario.put("atraso", at);
                    uusuario.put("valorMultas", v);
                    Usuario u = new Usuario();
                    u.setAtraso(at);
                    u.setMulta(v);
                    u.setEmail((String) uusuario.get("email"));
                    u.setNome((String) uusuario.get("nome"));
                     u.setSenha((String) uusuario.get("senha"));
                    u.setTipo((String) uusuario.get("tipo"));
                    u.setId((Integer.parseInt(uusuario.get("matricula").toString())));
                    System.out.println(uusuario.toString());
                    System.out.println(matricula);
                    Json j1 = new Json();
                    j1.arquivoUsuario(u, "/home/joaqm_/Documentos/TrabalhoFinal/Usuarios/" + matricula + ".json");
                              
                   // FileWriter susu = new FileWriter("/home/joaqm_/Documentos/TrabalhoFinal/Usuarios/" + matricula + ".json", false);
                    //susu.write(uusuario.toJSONString());
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
}
