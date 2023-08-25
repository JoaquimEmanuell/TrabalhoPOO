package TrabalhoPOO;

import java.util.ArrayList;

public class Emprestimo {
    private Usuario usuario;
    private Livros livro;
    private int dataEmprestimo;
    private int dataDevoluçao;
    private ArrayList<Emprestimo> emprestimos;
    public Emprestimo(Usuario usuario, int dataEmprestimo, int dataDevoluçao,Livros livro) {
        this.usuario=usuario;
        this.dataDevoluçao=dataDevoluçao;
        this.dataEmprestimo=dataEmprestimo;
        this.livro= livro;
        livro.setQntdEstoque(livro.getQntdEstoque()-1);
        emprestimos = new ArrayList<Emprestimo>();
    }
    public Emprestimo(){

    }

    public void sugerirLivro() {

    }
    public Usuario getUsuario() {

        return usuario;
    }
    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;
    }
    public Livros getLivro() {

        return livro;
    }
    public void setLivro(Livros livro) {

        this.livro = livro;
    }
    public int getDataEmprestimo() {

        return dataEmprestimo;
    }
    public void setDataEmprestimo(int dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public int getDataDevoluçao() {

        return dataDevoluçao;
    }
    public void setDataDevoluçao(int dataDevoluçao) {

        this.dataDevoluçao = dataDevoluçao;
    }
    public ArrayList<Emprestimo> getEmprestimos() {

        return emprestimos;
    }
    public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {

        this.emprestimos = emprestimos;
    }
}
