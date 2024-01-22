import java.text.DecimalFormat;
import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        System.out.println("Ex 5 -  Calculadora de IMC (Índice de Massa Corporal)");

        Pessoa pessoa = new Pessoa();
        Scanner scanner = new Scanner(System.in);
        Scanner scannerPeso = new Scanner(System.in);
        Scanner scannerAltura = new Scanner(System.in);
        System.out.print("Informe o seu nome: ");
        pessoa.nome = scanner.next();
        System.out.print("Informe sua idade: ");
        pessoa.idade = scanner.nextInt();
        System.out.print("Informe sua altura em metros (ex: 1,62): ");
        pessoa.altura = scannerAltura.nextDouble();
        System.out.print("Informe seu peso em kg (ex: 60,5): ");
        pessoa.peso = scannerPeso.nextDouble();

        calcularImc(pessoa.altura, pessoa.peso);
    }

    public static void calcularImc(double altura, double peso) {
        DecimalFormat format = new DecimalFormat("#.##");
        double calculoImc = peso / (altura * altura);
        String imc = format.format(calculoImc).replace(",", ".");
        double imcFormatado = Double.parseDouble(imc);
        System.out.println("Seu IMC é de: " + imcFormatado);
        classificarImc(imcFormatado);
    }

    public static void classificarImc(double imc){
        String message = "";
        if (imc < 18.5) {
            message = "Abaixo do peso";
        } else if (imc >= 18.5 && imc < 25) {
            message = "Peso normal";
        } else if (imc >= 25 && imc < 30) {
            message = "Sobrepeso";
        } else if (imc >= 30 && imc < 35) {
            message = "Obesidade Grau I";
        } else if (imc >= 35 && imc < 40) {
            message = "Obesidade Grau II";
        } else if (imc >= 40) {
            message = "Obesidade Grau III (ou mórbida)";
        }
        System.out.println("A classificação do seu IMC é: " + message);
    }
}
