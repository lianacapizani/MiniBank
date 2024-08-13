package br.com.fiap.faculdade.service;

import java.util.ArrayList;
import java.util.Scanner;
import br.com.fiap.faculdade.model.Cliente;
import br.com.fiap.faculdade.model.ContaBancaria;


public class Banco {

    ArrayList<ContaBancaria> contas = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.exibirMenu();
    }

    public void exibirMenu() {
        int op;
        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar conta bancária");
            System.out.println("2 - Excluir conta bancária");
            System.out.println("3 - Depósito");
            System.out.println("4 - Saque");
            System.out.println("5 - Transferência");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    excluirConta();
                    break;
                case 3:
                    deposito();
                    break;
                case 4:
                    saque();
                    break;
                case 5:
                    transferencia();
                    break;
                case 6:
                    System.out.println("Finalizando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (op != 6);
    }

    private void cadastrarConta() {
        System.out.println("Digite o nome do cliente:");
        String nome = sc.nextLine();
        System.out.println("Digite o CPF do cliente:");
        String cpf = sc.nextLine();
        System.out.println("Digite o e-mail do cliente:");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(nome, cpf, email);

        System.out.println("Digite o número da Agência:");
        int agencia = sc.nextInt();
        System.out.println("Digite o número da Conta:");
        int numero = sc.nextInt();
        System.out.println("Digite o saldo da conta:");
        double saldo = sc.nextDouble();

        ContaBancaria cb = new ContaBancaria(agencia, numero, saldo, cliente);
        contas.add(cb);
        System.out.println("Conta cadastrada com sucesso!");
    }

    private void excluirConta() {
        System.out.println("Digite o número da Conta que deseja excluir:");
        int numero = sc.nextInt();

        for (int i = 0; i < contas.size(); i++) {
            ContaBancaria cb = contas.get(i);
            if (cb.getNumero() == numero) { // Alteração aqui
                contas.remove(i);
                System.out.println("Conta excluída com sucesso!");
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    private void deposito() {
        System.out.println("Digite o número da Conta:");
        int numero = sc.nextInt();
        System.out.println("Digite o valor do depósito:");
        double valor = sc.nextDouble();

        for (ContaBancaria cb : contas) {
            if (cb.getNumero() == numero) { // Alteração aqui
                cb.depositar(valor);
                System.out.println("Depósito realizado com sucesso!");
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    private void saque() {
        System.out.println("Digite o número da Conta:");
        int numero = sc.nextInt();
        System.out.println("Digite o valor do saque:");
        double valor = sc.nextDouble();

        for (ContaBancaria cb : contas) {
            if (cb.getNumero() == numero) {
                if (cb.sacar(valor)) {
                    return;
                }
                System.out.println("Saque realizado com sucesso!");
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    private void transferencia() {
        System.out.print("Informe o número da conta de origem: ");
        int numeroOrigem = sc.nextInt();
        System.out.print("Informe o número da conta de destino: ");
        int numeroDestino = sc.nextInt();
        System.out.print("Informe o valor da transferência: ");
        double valor = sc.nextDouble();

        ContaBancaria contaOrigem = null;
        ContaBancaria contaDestino = null;

        for (ContaBancaria conta : contas) {
            if (conta.getNumero() == numeroOrigem) { // Alteração aqui
                contaOrigem = conta;
            }
            if (conta.getNumero() == numeroDestino) { // Alteração aqui
                contaDestino = conta;
            }
        }

        if (contaOrigem != null && contaDestino != null) {
            if (contaOrigem.transferir(contaDestino, valor)) {
                System.out.println("Transferência realizada com sucesso!");
            } else {
                System.out.println("Saldo insuficiente na conta de origem!");
            }
        } else {
            System.out.println("Conta de origem e/ou destino não encontrada!");
        }
    }
}


