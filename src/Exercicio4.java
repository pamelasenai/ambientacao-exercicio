import java.util.Arrays;
import java.util.List;

public class Exercicio4 {
    public static void main(String[] args) {
        System.out.println("Ex 4 -  Simulador de Banco\n");

        ContaBancaria contaBancaria = new ContaBancaria();

        final String NOME = "Pamela";
        final String NOME_SEM_CONTA = "Ana";
        final String DESTINATARIO = "Rafael";
        final List<String> NOMES = Arrays.asList("Rafael", "Leonardo");
        final double VALOR_DEPOSITO = 300;
        final double VALOR_SAQUE = 5;
        final double VALOR_SAQUE_ERRO = 500000;
        final double VALOR_TRANSFERENCIA = 100;

        System.out.println("Criando contas...");
        contaBancaria.criarConta(NOME);
        contaBancaria.criarMultiplasContas(NOMES);
        System.out.println("\nChecando se conta foi criada...");
        contaBancaria.verificarPossiuConta(NOME);
        contaBancaria.verificarPossiuConta(NOME_SEM_CONTA);
        System.out.println("\nRealizar deposito...");
        contaBancaria.realizarDeposito(NOME, VALOR_DEPOSITO);
        contaBancaria.realizarDeposito(NOME, VALOR_DEPOSITO);
        System.out.println("\nRealizar saque...");
        contaBancaria.realizarSaque(NOME, VALOR_SAQUE);
        contaBancaria.realizarSaque(NOME, VALOR_SAQUE_ERRO);
        System.out.println("\nRealizar transferência...");
        contaBancaria.realizarTransferencia(NOME, DESTINATARIO, VALOR_TRANSFERENCIA);
        contaBancaria.realizarTransferencia(NOME, DESTINATARIO, VALOR_SAQUE_ERRO);
        System.out.println("\nConsultar saldo...");
        contaBancaria.consultarSaldo(NOME);
        System.out.println("\nFIM...");
    }
}
