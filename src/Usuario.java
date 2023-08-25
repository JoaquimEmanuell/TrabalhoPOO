package TrabalhoPOO;

import java.util.ArrayList;
public class Usuario {

    protected String nome;
    protected int id;
    protected boolean atraso;
    protected String senha;
    protected int qntdLivrosAlugados;
    protected ArrayList<Livros> livrosAlugados;
    protected String tipo;
    protected double multa; 
    protected String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public ArrayList<Livros> getLivrosAlugados() {
        return livrosAlugados;
    }

    public void setLivrosAlugados(ArrayList<Livros> livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Usuario(){

    }
    public Usuario(String nome, int id, boolean atraso, String tipo, String senha) {
        ArrayList<Livros> livrosAlugados = new ArrayList<Livros>();
        this.nome=nome;
        this.id=id;
        this.atraso=atraso;
        this.qntdLivrosAlugados=0;
        this.tipo=tipo;
        this.senha = senha;
        this.multa = 0;
    }

    public Usuario(String nome, int id, String tipo, String senha)
    {
        ArrayList<Livros> livrosAlugados = new ArrayList<Livros>();
        this.nome=nome;
        this.id=id;
        this.atraso=false;
        this.qntdLivrosAlugados=0;
        this.tipo=tipo;
        this.senha = senha;
        this.multa = 0;
    }
    public void validacaoAluguel() {
        if(this.qntdLivrosAlugados==2) {
            throw new BusinessException("Quantida máxima de livros alugados");
        }
    }

    public void alugarLivro(Livros l) {
        livrosAlugados.add(l);
        qntdLivrosAlugados++;
        l.setQntdEstoque(l.getQntdEstoque()-1);
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public boolean getAtraso()
    {
        return this.atraso;
    }

    public void devolverLivro(Livros l) {
        livrosAlugados.remove(l);
        qntdLivrosAlugados--;
        l.setQntdEstoque(l.getQntdEstoque()+1);
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
    public boolean isAtraso() {
        return atraso;
    }
    public void setAtraso(boolean atraso) {
        this.atraso = atraso;
    }
    public int getQntdLivrosAlugados() {
        return qntdLivrosAlugados;
    }
    public void setQntdLivrosAlugados(int qntdLivrosAlugados) {
        this.qntdLivrosAlugados = qntdLivrosAlugados;
    }
}
