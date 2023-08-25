package TrabalhoPOO;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    static Json j1 = new Json();
    private ArrayList<Livros> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;
    private String nome;
    private String senha;

    public Biblioteca(String nome, String senha) {
        livros = new ArrayList<Livros>();
        usuarios = new ArrayList<Usuario>();
        emprestimos = new ArrayList<Emprestimo>();
        this.nome = nome;
        this.senha = senha;

    }

    public void adicionarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public void adicionarLivro(Livros l) {
        livros.add(l);
    }

    public void listarLivros() {
        int i =0;
      //  for (Livros l : livros) {
        //    System.out.println(l.getId() + "->" + l.getAutor() + " " + l.getNome() + " ");
        //}
       Livros l = j1.leituraArquivoLivro(i+"_livros");
       System.out.println(l.getNome());
    }

    public Livros procurarLivros(int id) {
        for (Livros l : livros) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    public void verificarLivro(int id) {
        if (procurarLivros(id) == null) {
            throw new BusinessException("Livro inexistente com esse id");
        }
    }

    public Usuario procurarUsuario(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public void verificarEmprestimoAluno(Aluno aluno) {
        if (aluno.isAtraso() || aluno.getQntdLivrosAlugados() == 2) {
            throw new BusinessException("Usuario impossibilitado de alugar");
        }
    }

    public void verificarEmprestimoProfessor(Professor professor) {
        if (professor.isAtraso() || professor.getQntdLivrosAlugados() == 3) {
            throw new BusinessException("Usuario impossibilitado de realizar emprestimos");
        }
    }

    public void alugarLivro(Emprestimo e) {

        emprestimos.add(e);
    }

    public void devolverLivro(Emprestimo e) {

        emprestimos.remove(e);
    }

    public void gerarRelatorioUsuario(Usuario u) {

    }

    public void editarLivro(int opc) {
        Scanner input = new Scanner(System.in);
        int id;
        switch (opc) {
            case 1:
                //para editar titulo do livro
                System.out.println("Digite o ID do livro que deseja editar: ");
                id = input.nextInt();
                Livros l = procurarLivros(id);
                System.out.println("Digite o novo titulo: ");
                String novoTitulo = input.nextLine();
                l.setNome(novoTitulo);
                break;

            case 2:
                System.out.println("Digite o ID do livro que deseja editar: ");
                id = input.nextInt();
                l = procurarLivros(id);
                System.out.println("Digite o novo autor: ");
                String novoAutor = input.nextLine();
                l.setAutor(novoAutor);

                break;

            case 3:
                System.out.println("Digite o ID do livro que deseja editar: ");
                id = input.nextInt();
                l = procurarLivros(id);
                System.out.println("Digite a nova categoria: ");
                String novaCategoria = input.nextLine();
                l.setTipo(novaCategoria);
                break;

            case 4:
                System.out.println("Digite o ID do livro que deseja editar: ");
                id = input.nextInt();
                l = procurarLivros(id);
                System.out.println("Digite o novo estoque: ");
                int novoEstoque = input.nextInt();
                l.setQntdEstoque(novoEstoque);
                break;

            /*case 5:
                System.out.println("Digite o ID do livro que deseja editar: ");
                id = input.nextInt();
                l = procurarLivros(id);
                for (int i = 0; i < livros.size(); i++) {
                    if (titulolivro.equalsIgnoreCase(livros.get(i).getTitulo())) {
                        livros.get(i).verAssuntos();
                        System.out.println("deseja alterar-los?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Nao");
                        int resp = teclado.nextInt();

                        if (resp == 1) {
                            System.out.println("Digite quantos assustos deseja editar: ");
                            int qnt = teclado.nextInt();
                            try {
                                livros.get(i).editAssunto(qnt);
                            } catch (AssuntoNaoExiste e) {
                                // TODO Auto-generated catch block
                                System.out.println(e);
                            }
                        }
                    }
                }
                break;*/

            /*case 6:
                //Case 6 é para caso o bibliotecario deseje editar todos os atributos do livro

               System.out.println("Digite o titulo do livro que deseja editar: ");
                titulolivro = teclado.next();

                for(int i = 0; i < livros.size(); i++){
                    if(titulolivro.equalsIgnoreCase(livros.get(i).getTitulo())){
                        System.out.println("Digite o novo titulo: ");
                        String novotitulo = teclado.next();
                        livros.get(i).setTitulo(novotitulo);

                        System.out.println("Digite o novo autor: ");
                        String novoautor = teclado.next();
                        livros.get(i).setAutor(novoautor);

                        System.out.println("Digite a nova categoria: ");
                        String novacategoria = teclado.next();
                        livros.get(i).setTipo(novacategoria);

                        System.out.println("Digite o novo estoque: ");
                        int novoestoque = teclado.nextInt();
                        livros.get(i).setEstoque(novoestoque);

                        livros.get(i).verAssuntos();
                        System.out.println("deseja alterar-los?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Nao");
                        int resp = teclado.nextInt();

                        if(resp == 1){
                            System.out.println("Digite quantos assustos deseja editar: ");
                            int qnt = teclado.nextInt();
                            try {
                                livros.get(i).editAssunto(qnt);
                            } catch (AssuntoNaoExiste e) {
                                // TODO Auto-generated catch block
                                System.out.println(e);
                            }
                        }
                    }
                }
                break;
        }*/
        }

    /*public void removerLivro(String titulo){
        for(int i = 0; i < livros.size(); i++){
            if(titulo.equalsIgnoreCase(livros.get(i).getTitulo())){
                System.out.println("Esse livro que deseja remover?:\n"+ livros.get(i).getTitulo());
                System.out.println("1 - Sim");
                System.out.println("2 - Nao");
                int o = teclado.nextInt();
                if(o == 1){
                    livros.remove(livros.get(i));
                }
            }
        }
    }*/
    }
}
