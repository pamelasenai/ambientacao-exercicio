import java.util.Scanner;

public class Imc {
    private  Pessoa pessoa = new Pessoa();
    public void iniciar() {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Informe o seu nome: ");
        pessoa.nome = entrada.next();
        System.out.print("Informe sua idade: ");
        pessoa.idade = entrada.nextShort();
        System.out.print("Informe sua altura em metros (ex: 1,62): ");
        pessoa.altura = entrada.nextDouble();
        System.out.print("Informe seu peso em kg (ex: 60,5): ");
        pessoa.peso = entrada.nextDouble();

        calcularImc(pessoa.altura, pessoa.peso);
        entrada.close();
    }

    public void calcularImc(double altura, double peso) {
        double calculoImc = peso / (altura * altura);
        double imcFormatado = Math.floor(calculoImc * 100) / 100;
        System.out.println("Seu IMC é de: " + imcFormatado);
        classificarImc(imcFormatado);
    }

    public void classificarImc(double imc){
        String mensagem = "";
        if (imc < 18.5) {
            mensagem = "abaixo do peso";
        } else if (imc >= 18.5 && imc < 25) {
            mensagem = "peso normal";
        } else if (imc >= 25 && imc < 30) {
            mensagem = "sobrepeso";
        } else if (imc >= 30 && imc < 35) {
            mensagem = "obesidade Grau I";
        } else if (imc >= 35 && imc < 40) {
            mensagem = "obesidade Grau II";
        } else if (imc >= 40) {
            mensagem = "obesidade Grau III (ou mórbida)";
        }
        System.out.println(this.pessoa.nome + " a classificação do seu IMC é: " + mensagem + "\n");
    }
}
