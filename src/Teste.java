package TrabalhoPOO;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class Teste
{
     @SuppressWarnings("unchecked")
     public static void main(String[] args) {
          JSONObject objeto = new JSONObject();
          FileWriter file = null;

          objeto.put("nome", "Joaquim");
          objeto.put("pais", "Brasil");
          objeto.put("estado", "CE");

          try
          {
               file = new FileWriter("saida.json");
               file.write(objeto.toJSONString());
               file.close();
          }
          catch(IOException e)
          {
               e.printStackTrace();
          }

          System.out.println(objeto);
     }
}