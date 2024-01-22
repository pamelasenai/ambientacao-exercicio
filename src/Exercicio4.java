import java.util.Arrays;
import java.util.List;

public class Exercicio4 {
    public static void main(String[] args) {
        System.out.println("Ex 4 -  Simulador de Banco\n");

        ContaBancaria contaBancaria = new ContaBancaria();

        String nome = "Pamela";
        String nomeSemConta = "Ana";
        String nomeDestinatario = "Rafael";
        List<String> nomes = Arrays.asList("Rafael", "Leonardo");
        double valorDeposito = 300;
        double valorSaque = 5;
        double valorSaqueComErro = 500000;
        double valorTransferencia = 100;

        System.out.println("Criando contas...");
        contaBancaria.criarConta(nome);
        contaBancaria.criarMultiplasContas(nomes);
        System.out.println("\nChecando se conta foi criada...");
        contaBancaria.verificarPossiuConta(nome);
        contaBancaria.verificarPossiuConta(nomeSemConta);
        System.out.println("\nRealizar deposito...");
        contaBancaria.realizarDeposito(nome, valorDeposito);
        contaBancaria.realizarDeposito(nome, valorDeposito);
        System.out.println("\nRealizar saque...");
        contaBancaria.realizarSaque(nome, valorSaque);
        contaBancaria.realizarSaque(nome, valorSaqueComErro);
        System.out.println("\nRealizar transferencia...");
        contaBancaria.realizarTransferencia(nome, nomeDestinatario, valorTransferencia);
        contaBancaria.realizarTransferencia(nome, nomeDestinatario, valorSaqueComErro);
        System.out.println("\nConsultar saldo...");
        contaBancaria.consultarSaldo(nome);
        System.out.println("\nFIM...");
    }
}
