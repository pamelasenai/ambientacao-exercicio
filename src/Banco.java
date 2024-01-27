import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Banco {
    public void iniciar() {
        ContaBancaria contaBancaria = new ContaBancaria();

        final String NOME = "Pamela";
        final String NOME_SEM_CONTA = "Ana";
        final String DESTINATARIO = "Rafael";
        final List<String> NOMES = Arrays.asList("Rafael", "Leonardo", "Pamela");
        final BigDecimal VALOR_DEPOSITO = BigDecimal.valueOf(300);
        final BigDecimal VALOR_SAQUE = BigDecimal.valueOf(5);
        final BigDecimal VALOR_SAQUE_ERRO = BigDecimal.valueOf(500000);
        final BigDecimal VALOR_TRANSFERENCIA = BigDecimal.valueOf(100);

        System.out.println("Criando contas...");
        contaBancaria.criarConta(NOME);
        contaBancaria.criarMultiplasContas(NOMES);
        System.out.println("\nChecando se a conta foi criada...");
        contaBancaria.verificarPossiuConta(NOME);
        contaBancaria.verificarPossiuConta(NOME_SEM_CONTA);
        System.out.println("\nRealizar deposito...");
        contaBancaria.realizarDeposito(NOME, VALOR_DEPOSITO);
        contaBancaria.realizarDeposito(NOME, VALOR_DEPOSITO);
        contaBancaria.realizarDeposito(NOME_SEM_CONTA, VALOR_DEPOSITO);
        System.out.println("\nRealizar saque...");
        contaBancaria.realizarSaque(NOME, VALOR_SAQUE);
        contaBancaria.realizarSaque(NOME, VALOR_SAQUE_ERRO);
        contaBancaria.realizarSaque(NOME_SEM_CONTA, VALOR_SAQUE_ERRO);
        System.out.println("\nRealizar transferência...");
        contaBancaria.realizarTransferencia(NOME, DESTINATARIO, VALOR_TRANSFERENCIA);
        contaBancaria.realizarTransferencia(NOME, DESTINATARIO, VALOR_SAQUE_ERRO);
        contaBancaria.realizarTransferencia(NOME_SEM_CONTA, DESTINATARIO, VALOR_SAQUE_ERRO);
        contaBancaria.realizarTransferencia(NOME, NOME_SEM_CONTA, VALOR_SAQUE_ERRO);
        System.out.println("\nConsultar saldo...");
        contaBancaria.consultarSaldo(NOME);
        System.out.println("\nFIM...");
    }
}
