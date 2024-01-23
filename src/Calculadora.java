import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calculadora {
    private int total = 0;

    public void iniciarCalculadora() {
        Scanner scanner = new Scanner(System.in);
        final List<Integer> OPERACOES = Arrays.asList(1, 2, 3, 4);
        int operacao;

        System.out.print("Informe um número para iniciar sua operação: ");
        total = scanner.nextInt();

        do {
            System.out.print("Informe a operação que você deseja usar (1 +, 2 -, 3 *, 4 /, 0 sair): ");
            operacao = scanner.nextInt();
            switch (operacao){
                case  0:
                    System.out.println("TOTAL: " + total);
                    System.out.println("Calculadora finalizada...");
                    break;
                case 1:
                    adicionar(total);
                    break;
                case 2:
                    subtrair(total);
                    break;
                case 3:
                    multiplicar(total);
                    break;
                case 4:
                    dividir(total);
                    break;
                default:
                    System.out.println("Operação selecionada é invalida!");
                    System.out.println("TOTAL: " + total);
            }
        } while (OPERACOES.contains(operacao));
    }

    private  void adicionar(Integer numero1){
        int numero2 = solicitarNumero();

        total = numero1 + numero2;
        System.out.println(numero1 + " + " + numero2 + " = " + total);
    }

    private  void subtrair(Integer numero1){
        int numero2 = solicitarNumero();

        total = numero1 - numero2;
        System.out.println(numero1 + " - " + numero2 + " = " + total);
    }

    private  void multiplicar(Integer numero1){
        int numero2 = solicitarNumero();

        total = numero1 * numero2;
        System.out.println(numero1 + " * " + numero2 + " = " + total);
    }

    private  void dividir(Integer numero1){
        int numero2 = solicitarNumero();

        total = numero1 / numero2;
        System.out.println(numero1 + " / " + numero2 + " = " + total);
    }

    private int solicitarNumero(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe um número para seguir com sua operação: ");
        return scanner.nextInt();
    }
}
