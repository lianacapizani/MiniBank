package br.com.fiap.faculdade.model;

public class ContaBancaria {

    private int agencia;
    private int numero;
    private double saldo;
    private Cliente cliente;


    public ContaBancaria(int agencia, int numero, double saldo, Cliente cliente) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;  
        this.cliente = cliente;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente ou valor de saque inválido!");
        }
        return false;
    }
    

    public boolean transferir(ContaBancaria destino, double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            ContaBancaria contaDestino = null;
            contaDestino.depositar(valor);
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Saldo insuficiente ou valor de transferência inválido!");

        }
        return false;
    }

    @Override
    public String toString() {
        return "ContaBancaria{" +
                "agencia='" + agencia + '\'' +
                ", numero='" + numero + '\'' +
                ", saldo=" + saldo +
                '}';
    }

}