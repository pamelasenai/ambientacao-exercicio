import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {
    private final List<Conta> contasExistentes = new ArrayList<>();
    public void realizarDeposito(String nome, BigDecimal valorDeposito) {
        boolean possuiConta = this.ehCliente(nome);

        if (!possuiConta) {
            System.out.println("Não encontramos uma conta em nome de: " + nome);
            return;
        }

        if (valorDeposito.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Não é possível realizar deposito para este valor: R$" + valorDeposito);
            return;
        }

        this.realizarDepositoPorNome(nome, valorDeposito);
        this.consultarSaldo(nome);
    }

    public void realizarSaque(String nome, BigDecimal valorSaque) {
        boolean possuiConta = this.ehCliente(nome);
        BigDecimal saldoAtual = this.buscarSaldoAtual(nome);

        if (!possuiConta) {
            System.out.println("Não encontramos uma conta em nome de: " + nome);
            return;
        }

        if (valorSaque.compareTo(saldoAtual) > 0) {
            System.out.println("Você não possui saldo disponível para este saque no valor de: R$" + valorSaque);
            this.consultarSaldo(nome);
            return;
        }

        this.realizarSaquePorNome(nome, valorSaque);
        this.consultarSaldo(nome);
    }


    private void realizarSaquePorNome(String nome, BigDecimal valorSaque){
        for (Conta conta : contasExistentes) {
            if (conta.nome.equals(nome)) {
                conta.saldoAtual = conta.saldoAtual.subtract(valorSaque);
            }
        }
        System.out.println("Saque realizado com sucesso da conta em nome de " + nome + " no valor de R$" + valorSaque);
    }

    private void realizarDepositoPorNome(String nome, BigDecimal valorDeposito){
        for (Conta conta : contasExistentes) {
            if (conta.nome.equals(nome)) {
                conta.saldoAtual = conta.saldoAtual.add(valorDeposito);
            }
        }
        System.out.println("Deposito realizado com sucesso para " + nome + " no valor de R$" + valorDeposito);
    }

    public void realizarTransferencia(String nome, String nomeDestinatario, BigDecimal valorTransferencia) {
        boolean possuiConta = this.ehCliente(nome);
        BigDecimal saldoAtual = this.buscarSaldoAtual(nome);
        if (!possuiConta) {
            System.out.println("Não encontramos uma conta em nome de " + nome);
            return;
        }

        boolean destinatarioPossuiConta = this.ehCliente(nomeDestinatario);
        if (!destinatarioPossuiConta) {
            System.out.println("Não encontramos uma conta para o destinatário " + nomeDestinatario);
            return;
        }

        if (valorTransferencia.compareTo(saldoAtual) > 0) {
            System.out.println(
                    "Você não possui saldo disponível para esta transferência no valor de R$" +
                    valorTransferencia
            );
            this.consultarSaldo(nome);
            return;
        }

        this.realizarSaquePorNome(nome, valorTransferencia);
        this.realizarDepositoPorNome(nomeDestinatario, valorTransferencia);
        this.consultarSaldo(nome);
    }

    private BigDecimal buscarSaldoAtual(String nome) {
        BigDecimal saldoAtual = BigDecimal.valueOf(0);
        for (Conta conta : contasExistentes) {
            if (conta.nome.equals(nome)) {
                saldoAtual = conta.saldoAtual;
            }
        }
        return saldoAtual;
    }
    public void consultarSaldo(String nome) {
        BigDecimal saldoAtual = this.buscarSaldoAtual(nome);
        System.out.println("Saldo atual de " + nome + " é: R$" + saldoAtual);
    }

    public void criarConta(String nome) {
        boolean possuiConta = this.ehCliente(nome);
        if (possuiConta) {
            System.out.println(nome + " você já possuí conta, não podemos criar uma nova conta!");
            return;
        }

        Conta conta = new Conta();
        conta.nome = nome;
        contasExistentes.add(conta);
        System.out.println("Sua nova conta em nome de " + nome + " foi criada com sucesso!");
    }

    private boolean ehCliente(String nome) {
        for (Conta conta : contasExistentes) {
            if (conta.nome.equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public void verificarPossiuConta(String nome) {
        boolean possuiConta = this.ehCliente(nome);

        if (possuiConta) {
            System.out.println("Ficamos felizes em informar que você " + nome + " já possui uma conta conosco.");
            return;
        }
        System.out.println("Parece que ainda não temos o prazer de tê-lo(a) " + nome + " como cliente.");
    }

    public void criarMultiplasContas(List<String> nomes) {
        System.out.println("Você entrou no modo de criação de múltiplas contas.");

        for (String nome : nomes) {
            this.criarConta(nome);
        }
    }
}
