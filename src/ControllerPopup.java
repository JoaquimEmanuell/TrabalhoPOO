package TrabalhoPOO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerPopup {
     @FXML
     private TextField nomeText;
     @FXML
     private Label avisoText;

     public void salvar(ActionEvent e)
     {
          try {
               FileWriter file = new FileWriter("/home/joaqm_/Documentos/TrabalhoFinal/Categorias/categorias.txt", true);
               BufferedWriter bw = new BufferedWriter(file);
               String nome = nomeText.getText();
               if(nome.isEmpty())
               {
                    avisoText.setText("Insira um nome valido");
               }
               bw.write(nome + "\t");
               bw.close();
               file.close();

               ControllerEditandoLivro ed = new ControllerEditandoLivro();
               ed.iniciarLista();

               ControllerAdicionarLivro ad = new ControllerAdicionarLivro();
               ad.iniciarLista();

               Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
               dialogoAviso.setHeaderText("Adicionar Categoria");
               dialogoAviso.setContentText("Operação realizada com sucesso!");
               dialogoAviso.showAndWait();

               /*ControllerEditandoLivro ed1 = new ControllerEditandoLivro();
               ed1.fechar();*/
          } catch (IOException e1) {
               e1.printStackTrace();
          }
     }
}
