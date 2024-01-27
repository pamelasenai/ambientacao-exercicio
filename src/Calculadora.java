import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calculadora {
    private int total = 0;

    public void iniciarCalculadora() {
        Scanner entrada = new Scanner(System.in);
        final List<Integer> OPERACOES = Arrays.asList(1, 2, 3, 4);
        int opcao;

        System.out.print("Informe um número para iniciar seu calculo: ");
        total = entrada.nextInt();

        do {
            System.out.print(
                    "Informe a operação que você deseja usar " +
                    "(1- soma, 2- subtração, 3- multiplicação, 4- divisão, 0- SAIR): "
            );
            opcao = entrada.nextInt();

            switch (opcao){
                case  0:
                    System.out.println("TOTAL: " + total);
                    System.out.println("Calculadora finalizada...");
                    break;
                case 1:
                    this.adicionar(total);
                    break;
                case 2:
                    this.subtrair(total);
                    break;
                case 3:
                    this.multiplicar(total);
                    break;
                case 4:
                    this.dividir(total);
                    break;
                default:
                    System.out.println("Operação selecionada é invalida!");
                    System.out.println("TOTAL: " + total);
            }
        } while (OPERACOES.contains(opcao));
        entrada.close();
    }

    private  void adicionar(Integer numeroInicial){
        int numeroSolicitado = this.solicitarNumero();

        total = numeroInicial + numeroSolicitado;
        System.out.println(numeroInicial + " + " + numeroSolicitado + " = " + total);
    }

    private  void subtrair(Integer numeroInicial){
        int numeroSolicitado = this.solicitarNumero();

        total = numeroInicial - numeroSolicitado;
        System.out.println(numeroInicial + " - " + numeroSolicitado + " = " + total);
    }

    private  void multiplicar(Integer numeroInicial){
        int numeroSolicitado = this.solicitarNumero();

        total = numeroInicial * numeroSolicitado;
        System.out.println(numeroInicial + " * " + numeroSolicitado + " = " + total);
    }

    private  void dividir(Integer numeroInicial){
        int numeroSolicitado = this.solicitarNumero();

        total = numeroInicial / numeroSolicitado;
        System.out.println(numeroInicial + " / " + numeroSolicitado + " = " + total);
    }

    private int solicitarNumero(){
        Scanner entrada = new Scanner(System.in);

        System.out.print("Informe um número para seguir com seu calculo: ");
        return entrada.nextInt();
    }
}
