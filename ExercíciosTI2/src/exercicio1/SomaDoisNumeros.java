package exercicio1;

import java.util.Scanner;

public class SomaDoisNumeros {

	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]) {
		//declaracao de variaveis
		int num1, num2, soma;
		
		//leitura dos dois numeros 
		System.out.println("Digite um numero: ");
		num1 = sc.nextInt();
		System.out.println("Digite outro numero: ");
		num2 = sc.nextInt();
		
		//operacao de soma
		soma = num1 + num2;
		
		//imprimir o resultado da soma na tela
		System.out.println("Soma: " + soma);
	}
}
