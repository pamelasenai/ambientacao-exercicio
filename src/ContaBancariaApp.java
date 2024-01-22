import java.util.ArrayList;
import java.util.Scanner;

public class ContaBancariaApp {
    private ArrayList<Conta> contasExistentes = new ArrayList<>();
    public void realizarDeposito() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Qual o nome da conta que deseja realizar o deposito: ");
        String nome = scanner.next();
        boolean possuiConta = this.possuiContaComNome(nome);

        if (!possuiConta) {
            System.out.println("Não encontramos uma conta para a pessoa informada.");
            return;
        }

        System.out.print("Informe o valor do deposito R$: ");
        double valorDeposito = scanner.nextDouble();

        if (valorDeposito < 0) {
            System.out.println("Não é possível realizar esta operação.");
            return;
        }

        this.realizarDepositoPorNome(nome, valorDeposito);
        this.consultarSaldo(nome);
    };

    public void realizarSaque() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Qual o nome da conta que deseja realizar o saque: ");
        String nome = scanner.next();
        boolean possuiConta = this.possuiContaComNome(nome);
        double saldoAtual = this.buscarSaldoAtual(nome);

        if (!possuiConta) {
            System.out.println("Não encontramos uma conta para a pessoa informada.");
            return;
        }

        System.out.print("Informe o valor do saque R$: ");
        double valorSaque = scanner.nextDouble();

        if (valorSaque > saldoAtual) {
            System.out.println("Você não possui saldo disponível para este saque.");
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
        System.out.println("Saque realizado com sucesso.");
    }

    private void realizarDepositoPorNome(String nome, double valorDeposito){
        for (Conta conta : contasExistentes) {
            if (conta != null && conta.nome.equals(nome)) {
                conta.saldoAtual = conta.saldoAtual + valorDeposito;
            }
        }
        System.out.println("Deposito realizado com sucesso.");
    }

    public void realizarTransferencia() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Qual o nome da conta que deseja realizar o saque: ");
        String nome = scanner.next();
        boolean possuiConta = this.possuiContaComNome(nome);
        double saldoAtual = this.buscarSaldoAtual(nome);
        if (!possuiConta) {
            System.out.println("Não encontramos uma conta para a pessoa informada.");
            return;
        }

        System.out.print("Qual o nome da conta que irá receber o deposito: ");
        String nomeDestinatario = scanner.next();
        boolean destinatarioPossuiConta = this.possuiContaComNome(nomeDestinatario);
        if (!destinatarioPossuiConta) {
            System.out.println("Não encontramos uma conta para o destinatário informado.");
            return;
        }

        System.out.print("Informe o valor a ser transferido R$: ");
        double valorTransferencia = scanner.nextDouble();
        if (valorTransferencia > saldoAtual) {
            System.out.println("Você não possui saldo disponível para esta transferencia.");
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

    public void criarConta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe em nome de quem será a conta: ");
        String nome = scanner.next();
        boolean possuiConta = this.possuiContaComNome(nome);
        if (possuiConta) {
            System.out.println("Você já possuí conta, não podemos criar uma nova conta!");
            return;
        }
        Conta conta = new Conta();
        conta.nome = nome;
        contasExistentes.add(conta);
        System.out.println("Sua nova conta foi criada com sucesso!");
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
            System.out.println("Ficamos felizes em informar que você já possui uma conta conosco.");
            return;
        }
        System.out.println("Parece que ainda não temos o prazer de tê-lo(a) como cliente.");
    }

    public void criarMultiplasContas() {
        Scanner scanner = new Scanner(System.in);
        String continuar = "s";
        System.out.println("Você entrou no modo de criação de multiplas contas.");

        do {
            this.criarConta();
            System.out.print("Para criar uma nova conta digite s, para encerrar digite n: ");
            continuar = scanner.next();
        } while (continuar.equals("s"));
    }

    public void iniciarBanco() {
        Scanner scanner = new Scanner(System.in);
        String sair = "n";

        System.out.println(
                "                                                                                                                                                    \n" +
                        "            ████████████                              ████████████                                ████████                                  ████████\n" +
                        "        ████░░░░░░░░░░░░████                        ██░░░░░░░░░░░░██                            ██░░░░░░░░██                                ██░░░░██\n" +
                        "      ██░░░░▒▒▒▒▒▒▒▒▒▒▒▒░░░░██                    ██░░▒▒▒▒▒▒▒▒▒▒░░░░██                          ██░░▒▒░░░░██                                ██░░░░██\n" +
                        "    ██░░░░▒▒░░░░░░░░░░░░▒▒░░░░██                ██░░▒▒░░░░░░░░░░  ░░░░██                      ██░░▒▒░░  ░░░░██                              ██░░░░██\n" +
                        "    ██░░▒▒░░░░      ░░░░░░  ░░██                ██░░▒▒░░    ░░░░  ░░░░██                      ██░░▒▒    ░░░░██                              ██░░░░██\n" +
                        "  ██░░▒▒░░░░░░  ░░░░▒▒░░░░░░  ░░██            ██░░▒▒░░░░  ░░▒▒░░░░  ░░░░██                    ██░░▒▒░░  ░░░░██                              ██░░░░██\n" +
                        "  ██░░▒▒░░      ░░░░░░  ░░░░  ░░██            ██░░▒▒      ░░░░  ░░  ░░░░██                    ██░░▒▒    ░░░░██                              ██░░░░██\n" +
                        "  ██░░▒▒░░  ░░░░░░░░░░░░▒▒░░  ░░██            ██░░▒▒  ░░░░░░░░░░▒▒  ░░▒▒██                    ██░░▒▒░░  ▒▒▒▒██                              ██▒▒▒▒██\n" +
                        "  ██░░▒▒░░  ░░░░░░░░░░░░▒▒░░  ░░██            ██░░▒▒  ░░░░░░░░░░▒▒  ░░▒▒██                    ██░░▒▒░░  ▒▒▒▒██                              ██▒▒▒▒██\n" +
                        "  ██░░▒▒░░░░▒▒░░░░░░▒▒▒▒▒▒░░  ░░██            ██░░▒▒░░▒▒░░░░▒▒▒▒▒▒  ░░▒▒██                    ██░░▒▒▒▒  ▒▒▒▒██                              ██▒▒▒▒██\n" +
                        "  ██░░▒▒░░░░░░  ░░░░▒▒░░░░░░  ░░██            ██░░▒▒░░░░  ░░▒▒░░░░  ░░▒▒██                    ██░░▒▒▒▒  ▒▒▒▒██                              ██▒▒▒▒██\n" +
                        "    ██░░▒▒░░░░░░▒▒▒▒▒▒░░░░  ░░██                ██░░▒▒░░░░▒▒▒▒░░  ░░▒▒██                      ██░░▒▒▒▒  ▒▒▒▒██                              ██▒▒▒▒██\n" +
                        "    ██░░░░  ░░░░░░░░░░░░  ░░░░██                ██░░▒▒░░░░░░░░░░  ░░▒▒██                      ██░░░░░░  ▒▒▒▒██                              ██▒▒▒▒██\n" +
                        "      ██░░░░            ░░░░██                    ██░░          ░░▒▒██                          ██░░  ▒▒▒▒██                                ██▒▒▒▒██\n" +
                        "        ████░░░░░░░░░░░░████                        ██░░░░░░░░░░▒▒██                            ██░░░░▒▒▒▒██                                ██▒▒▒▒██\n" +
                        "            ████████████                              ████████████                                ████████                                  ████████\n"
        );
        System.out.println("Olá! Bem-vindo de volta ao seu banco favorito!");
        System.out.println("Estamos aqui para ajudar. Segue operações que estão a sua disposição: ");
        do {
            System.out.println(
                    "1- Saque \n" +
                            "2- Deposito \n" +
                            "3- Transferência \n" +
                            "4- Consulta de saldo \n" +
                            "5- Criar nova conta \n" +
                            "6- Criar multiplas contas \n" +
                            "7- Verifique se você já possui uma conta \n" +
                            "8- SAIR"
            );
            System.out.print("O que você gostaria de fazer hoje? ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.realizarSaque();
                    break;
                case 2:
                    this.realizarDeposito();
                    break;
                case 3:
                    this.realizarTransferencia();
                    break;
                case 4:
                    System.out.print("Informe o nome da conta que deseja consultar o saldo: ");
                    String nomeSaldo = scanner.next();
                    this.consultarSaldo(nomeSaldo);
                    break;
                case 5:
                    this.criarConta();
                    break;
                case 6:
                    this.criarMultiplasContas();
                    break;
                case 7:
                    System.out.print("Informe o nome da conta que deseja consultar: ");
                    String nomeConta = scanner.next();
                    this.verificarPossiuConta(nomeConta);
                    break;
                case 8:
                    sair = "s";
                    System.out.println("Finalizando...");
                    break;
                default:
                    System.out.println("Opção invalida!");
                    break;
            }
        } while (sair.equals("n"));
    }
}
