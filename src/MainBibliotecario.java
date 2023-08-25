package TrabalhoPOO;

import java.util.ArrayList;
import java.util.Scanner;
public class MainBibliotecario {
    static Biblioteca bib = new Biblioteca("UECE", "UECE2023");
    static int id_livro=1;
    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        int o = 0;
        System.out.println("Bem Vindo A Bibliotecario");
        System.out.println("Qual seu nome?");
        String bibnome = teclado.next();

        System.out.println("Qual seu cadrastro?");
        int bibcad = teclado.nextInt();

        do{
            System.out.println("O que deseja fazer?");
            System.out.println("1. Cadrastrar Livro");
            System.out.println("2. Editar Livro");
            System.out.println("3. Remover Livro");
            System.out.println("4. Listar Usuário");
            System.out.println("5. sair");
            o = teclado.nextInt();

            switch(o){
                case 1:
                    cadastrarLivro();
                    break;

                case 2:
                    editarLivro();
                    break;

                case 3:
                    //removerLivro();
                    break;

                case 4:
                    System.out.println("Fiz os usuarios ainda nao patrao");
                    break;

                case 5:
                    System.out.println("Volte sempre!!!");
                    break;
            }
        }while(o != 5);
    }
    public static void cadastrarLivro() {
        int op=1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("Digite o nome do livro");
            String nome = input.nextLine();
            System.out.println("Digite o nome do autor");
            String autor = input.nextLine();
            System.out.println("Digite a quantidade de livros");
            int qntd = input.nextInt();
            System.out.println("Digite o assunto do livro");
            String assunto = input.nextLine();
            System.out.println("Digite o tipo do livro");
            String tipo = input.nextLine();
            Livros l = new Livros(nome,id_livro,autor,assunto,qntd,tipo);
            bib.adicionarLivro(l);
            id_livro++;
            System.out.println("Deseja adicionar mais livros?");
            System.out.println("1-Continuar");
            System.out.println("0-Sair");
            op = input.nextInt();
        }while(op!=0);
    }
    public static void editarLivro(){
        Scanner input = new Scanner(System.in);
        int opedit = 0;
        do{
            System.out.println("O que voce deseja fazer?");
            System.out.println("1 - Editar titulo");
            System.out.println("2 - Editar autor");
            System.out.println("3 - Editar exclusividade academica");
            System.out.println("4 - Editar estoque");
            System.out.println("5 - Editar Assuntos");
            System.out.println("6 - Editar todo o livro");
            System.out.println("7 - Sair de Edicao");
            opedit = input.nextInt();
            bib.editarLivro(opedit);
        }while(opedit != 7);
    }
    /*public static void removerLivro(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o titulo do livro que deseja remover");
        String tituloremove = input.nextLine();
        bib.removerLivro(tituloremove);
    }*/
}
