package TrabalhoPOO;

import java.util.ArrayList;

public class Livros {

    private String nome;
    private int id;
    private String autor;
    private String assunto;
    private int qntdEstoque;
    private boolean tipo;
    private ArrayList<String> comentarios;
    
    public Livros(String nome, int id, String autor, String assunto,int qntdEstoque, boolean tipo) { 
        this.nome=nome;
        this.id=id;
        this.autor=autor;
        this.assunto=assunto;
        this.qntdEstoque=qntdEstoque;
        this.tipo=tipo;
        comentarios = new ArrayList<String>();
    }

    public Livros(String nome, String autor, String assunto, int qntdEstoque) {
        this.nome=nome;
        this.autor=autor;
        this.assunto=assunto;
        this.qntdEstoque=qntdEstoque;
        comentarios = new ArrayList<String>();
    }


    public Livros() {

    }

    public void realizarComentario(String comentario) {
        comentarios.add(comentario);
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getAssunto() {
        return assunto;
    }
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    public int getQntdEstoque() {
        return qntdEstoque;
    }
    public void setQntdEstoque(int qntdEstoque) {
        this.qntdEstoque = qntdEstoque;
    }
    public boolean getTipo() {
        return tipo;
    }
    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    
}
