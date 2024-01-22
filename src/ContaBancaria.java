import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContaBancaria {
    private ArrayList<Conta> contasExistentes = new ArrayList<>();
    public void realizarDeposito(String nome, double valorDeposito) {
        boolean possuiConta = this.possuiContaComNome(nome);

        if (!possuiConta) {
            System.out.println("Não encontramos uma conta em nome de " + nome);
            return;
        }

        if (valorDeposito < 0) {
            System.out.println("Não é possível realizar esta operação.");
            return;
        }

        this.realizarDepositoPorNome(nome, valorDeposito);
        this.consultarSaldo(nome);
    };

    public void realizarSaque(String nome, double valorSaque) {
        boolean possuiConta = this.possuiContaComNome(nome);
        double saldoAtual = this.buscarSaldoAtual(nome);

        if (!possuiConta) {
            System.out.println("Não encontramos uma conta para a pessoa informada.");
            return;
        }

        if (valorSaque > saldoAtual) {
            System.out.println("Você não possui saldo disponível para este saque no valor de " + valorSaque);
            this.consultarSaldo(nome);
            return;
        }

        this.realizarSaquePorNome(nome, valorSaque);
        this.consultarSaldo(nome);
    };


    private void realizarSaquePorNome(String nome, double valorSaque){
        for (Conta conta : contasExistentes) {
            if (conta != null && conta.nome.equals(nome)) {
                conta.saldoAtual = conta.saldoAtual - valorSaque;
            }
        }
        System.out.println("Saque realizado com sucesso da conta em nome de " + nome + " no valor de " + valorSaque);
    }

    private void realizarDepositoPorNome(String nome, double valorDeposito){
        for (Conta conta : contasExistentes) {
            if (conta != null && conta.nome.equals(nome)) {
                conta.saldoAtual = conta.saldoAtual + valorDeposito;
            }
        }
        System.out.println("Deposito realizado com sucesso para " + nome + " no valor de " + valorDeposito);
    }

    public void realizarTransferencia(String nome, String nomeDestinatario, double valorTransferencia) {
        boolean possuiConta = this.possuiContaComNome(nome);
        double saldoAtual = this.buscarSaldoAtual(nome);
        if (!possuiConta) {
            System.out.println("Não encontramos uma conta em nome de " + nome);
            return;
        }

        boolean destinatarioPossuiConta = this.possuiContaComNome(nomeDestinatario);
        if (!destinatarioPossuiConta) {
            System.out.println("Não encontramos uma conta para o destinatário " + nomeDestinatario);
            return;
        }

        if (valorTransferencia > saldoAtual) {
            System.out.println("Você não possui saldo disponível para esta transferencia no valor de " + valorTransferencia);
            this.consultarSaldo(nome);
            return;
        }

        this.realizarSaquePorNome(nome, valorTransferencia);
        this.realizarDepositoPorNome(nomeDestinatario, valorTransferencia);
        this.consultarSaldo(nome);
    }

    private double buscarSaldoAtual(String nome) {
        double saldoAtual = 0;
        for (Conta conta : contasExistentes) {
            if (conta != null && conta.nome.equals(nome)) {
                saldoAtual = conta.saldoAtual;
            }
        }
        return saldoAtual;
    }
    public void consultarSaldo(String nome) {
        double saldoAtual = this.buscarSaldoAtual(nome);
        System.out.println("Saldo atual de " + nome + " é: R$" + saldoAtual);
    }

    public void criarConta(String nome) {
        boolean possuiConta = this.possuiContaComNome(nome);
        if (possuiConta) {
            System.out.println("Você já possuí conta, não podemos criar uma nova conta!");
            return;
        }
        Conta conta = new Conta();
        conta.nome = nome;
        contasExistentes.add(conta);
        System.out.println("Sua nova conta em nome de " + nome + " foi criada com sucesso!");
    }

    private boolean possuiContaComNome(String nome) {
        for (Conta conta : contasExistentes) {
            if (conta.nome.equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public void verificarPossiuConta(String nome) {
        boolean possuiConta = this.possuiContaComNome(nome);

        if (possuiConta) {
            System.out.println("Ficamos felizes em informar que você " + nome + " já possui uma conta conosco.");
            return;
        }
        System.out.println("Parece que ainda não temos o prazer de tê-lo(a) " + nome + " como cliente.");
    }

    public void criarMultiplasContas(List<String> nomes) {
        System.out.println("Você entrou no modo de criação de multiplas contas.");

        for (String nome : nomes) {
            this.criarConta(nome);
        }
    }
}
