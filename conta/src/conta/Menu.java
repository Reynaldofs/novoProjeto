package conta;


import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	
	public static void main(String[] args) {

		
		
		ContaController Contas = new ContaController();	
		
		Scanner leia = new Scanner(System.in);
		
        int opcao, numero, agencia,tipo,aniversario;
        String titular;
        float saldo, limite;

        ContaCorrente cc1 = new ContaCorrente(Contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		Contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(Contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		Contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(Contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		Contas.cadastrar(cp1);

		ContaPoupanca cp2 = new ContaPoupanca(Contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		Contas.cadastrar(cp2);

		Contas.listarTodas();
		
		while (true) {
			System.out.println(Cores.TEXT_BLUE+ Cores.ANSI_BLACK_BACKGROUND+"*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1-Criar Conta                            ");
			System.out.println("            2-Listar Todas as  Contas                ");
			System.out.println("            3-Buscar Conta por Número                ");
			System.out.println("            4-Atualizar Dados da Conta               ");
			System.out.println("            5-Apagar Conta                           ");
			System.out.println("            6-Sacar                                  ");
			System.out.println("            7-Depositar                              ");
			System.out.println("            8-Transferir valores entre Contas        ");
			System.out.println("            9-Sair                                   ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com  a opão desejada                           ");
			System.out.println("                                                     "+ Cores.TEXT_RESET);
			
			try {
	                opcao = leia.nextInt();
	            } catch (InputMismatchException e) {
	                System.out.println("\nDigite valores inteiros!");
	                leia.nextLine();
	                opcao = 0;
	            }
	            ;

	            if (opcao == 9) {
	                System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
	                leia.close();
	                System.exit(0);
	            }

	            switch (opcao) {
	                case 1:
	                    System.out.println("\n Criar Conta\n\n");
	                    
	                    System.out.println("Digite o Numero da Agência: ");
	                    agencia = leia.nextInt();
	                    System.out.println("Digite o nome do Titular: ");
	                    leia.skip("\\R");
	                    titular = leia.nextLine();
	                    
	                    do {
	                    	System.out.println("Digite o Tipo da conta (1-CC ou 2-CP):");
	                    tipo = leia.nextInt();
	                    }while (tipo < 1 && tipo >  2);
	                    
	                    
	                    System.out.println("Digite o saldo da conta (R$):");
	                    saldo = leia.nextFloat();
	                    
	                    switch(tipo) {
	                    
	                    case 1 -> {
							System.out.println("Digite o Limite de Crédito (R$): ");
							limite = leia.nextFloat();
							Contas.cadastrar(
									new ContaCorrente(Contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
						}
						case 2 -> {
							System.out.println("Digite o dia do Aniversario da Conta: ");
							aniversario = leia.nextInt();
							Contas.cadastrar(new ContaPoupanca(Contas.gerarNumero(), agencia, tipo, titular, saldo,
									aniversario));
						}
					}
							
							
	                    keyPress();
	                    break;
	                case 2:
	                    System.out.println("\n Listar todas as Contas");
	                    Contas.listarTodas();
	                    
	                    keyPress();
	                    break;
	                case 3:
	                    System.out.println("\n Buscar Conta por número");
	                    
	                    System.out.println("Digite o número da conta: ");
						numero = leia.nextInt();

						Contas.procurarPorNumero(numero);

						keyPress();
	                    
	                    keyPress();
	                    break;
	                case 4:
	                    System.out.println("\n Atualizar dados da Conta");


						System.out.println("Digite o número da conta: ");
						numero = leia.nextInt();

						if (Contas.buscarNaCollection(numero) != null) {

							System.out.println("Digite o Numero da Agência: ");
							agencia = leia.nextInt();
							System.out.println("Digite o Nome do Titular: ");
							leia.skip("\\R?");
							titular = leia.nextLine();

							System.out.println("Digite o Saldo da Conta (R$): ");
							saldo = leia.nextFloat();

							tipo = Contas.retornaTipo(numero);

							switch (tipo) {
								case 1 -> {
									System.out.println("Digite o Limite de Crédito (R$): ");
									limite = leia.nextFloat();
									Contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
								}
								case 2 -> {
									System.out.println("Digite o dia do Aniversario da Conta: ");
									aniversario = leia.nextInt();
									Contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
								}
								default -> {
									System.out.println("Tipo de conta inválido!");
								}
							}

						} else
							System.out.println("\nConta não encontrada!");

						keyPress();
						break;
					case 5:
						System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");

						System.out.println("Digite o número da conta: ");
						numero = leia.nextInt();

						Contas.deletar(numero);
	                    
	                    keyPress();
	                    break;
	                
	                case 6:
	                    System.out.println("\n Sacar");

	                    keyPress();
	                    break;
	                case 7:
	                    System.out.println("\n Depositar");

	                    keyPress();
	                    break;
	                case 8:
	                    System.out.println("\n Transferir");

	                    keyPress();
	                    break;
	                default:
	                    System.out.println("\nOpção Inválida");

	                    keyPress();
	                    break;
	            }
	        }
	    }

	    private static void keyPress() {
	        try {

	            System.out.println("\n\nPressione Enter para Continuar...");
	            System.in.read();

	        } catch (IOException e) {

	            System.out.println("Você pressionou uma tecla diferente de enter!");
	        }

	    }
	}