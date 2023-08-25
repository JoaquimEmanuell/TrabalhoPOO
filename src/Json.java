package TrabalhoPOO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.text.html.parser.Parser;
import java.io.*;

public class Json {
    public void arquivoUsuario(Usuario usuario,String caminho){
        JSONObject prog1 = new JSONObject();
        prog1.put("nome", usuario.getNome());
        prog1.put("matricula", usuario.getId());
        prog1.put("senha" , usuario.getSenha());
        prog1.put("tipo", usuario.getTipo());
        prog1.put("atraso", usuario.getAtraso());
        prog1.put("email", usuario.getEmail());
        //prog1.put("qntd_livros",usuario.getQntdLivrosAlugados());
        prog1.put("multa", usuario.getMulta());



        try(FileWriter arquivoJson = new FileWriter(caminho, false)){
            arquivoJson.write(prog1.toJSONString());
            arquivoJson.flush();
            arquivoJson.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void arquivoEmprestimo(Emprestimo emprestimo){
        JSONObject emprestimoJson = new JSONObject();
        emprestimoJson.put("id_usuario", emprestimo.getUsuario().getNome());
        emprestimoJson.put("id_livro", emprestimo.getLivro().getId());
        emprestimoJson.put("data_emprestimo",emprestimo.getDataEmprestimo());

        try(FileWriter arquivoJson = new FileWriter("emprestimos.json",true)){
            arquivoJson.write(emprestimoJson.toJSONString());
            arquivoJson.flush();
            arquivoJson.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void arquivoLivros(Livros livro, String caminho){
        JSONObject livrosJson = new JSONObject();
        livrosJson.put("nome",livro.getNome());
        livrosJson.put("id",livro.getId());
        livrosJson.put("qntd_estoque",livro.getQntdEstoque());
        livrosJson.put("autor",livro.getAutor());
        livrosJson.put("tipo",livro.getTipo());

        try(FileWriter arquivoJson = new FileWriter(caminho +".json",true)){
            arquivoJson.write(livrosJson.toJSONString());
            arquivoJson.flush();
            arquivoJson.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }


    public  Usuario leituraArquivoUsuario(String caminho){
        JSONParser parser = new JSONParser();
        JSONObject usuarios= new JSONObject();
        try(FileReader reader = new FileReader(caminho)){
        usuarios = (JSONObject) parser.parse(reader);
        Usuario u = new Usuario();
        u.setNome((String) usuarios.get("nome"));
        u.setSenha((String) usuarios.get("senha"));
        u.setTipo((String) usuarios.get("tipo"));
        u.setId((Integer.parseInt(usuarios.get("id").toString())));
        //u.setQntdLivrosAlugados((Integer.parseInt(usuarios.get("qntd_livros").toString())));
        //u.setMulta((Integer.parseInt(usuarios.get("multa").toString())));
        return u;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public  Livros leituraArquivoLivro(String caminho){
        JSONParser parser = new JSONParser();
        JSONObject livros= new JSONObject();
        try(FileReader reader = new FileReader(caminho)){
            livros = (JSONObject) parser.parse(reader);
            Livros l = new Livros();
            l.setNome((String) livros.get("nome"));
            l.setAutor((String) livros.get("autor"));
            l.setTipo((boolean) livros.get("tipo"));
            l.setId((Integer.parseInt(livros.get("id").toString())));
            l.setQntdEstoque((Integer.parseInt(livros.get("quantidade").toString())));
            l.setAssunto((String) livros.get("categoria"));
            return l;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public  Emprestimo leituraArquivoEmprestimo(String caminho){
        JSONParser parser = new JSONParser();
        JSONObject emprestimo= new JSONObject();
        try(FileReader reader = new FileReader(caminho)){
            emprestimo = (JSONObject) parser.parse(reader);
            Emprestimo e = new Emprestimo();
            Usuario u = new Usuario();
            u = leituraArquivoUsuario((String) emprestimo.get("id_usuario")+"_usuario");
            Livros l = new Livros();
            l = leituraArquivoLivro((String) emprestimo.get("id_livro")+"_livros");
            e.setUsuario(u);
            e.setLivro(l);
            e.setDataEmprestimo((Integer.parseInt(emprestimo.get("data_emprestimo").toString())));
            return e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



}
