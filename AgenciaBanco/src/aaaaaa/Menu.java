package aaaaaa;

import java.util.List;
import java.util.Scanner;

public class Menu {
        
    private static void printMenu() {
        System.out.println("0 -       --------- Menu ---------");
        System.out.println("1 - Registrar um novo cliente pessoa física.");
        System.out.println("2 - Registrar um novo cliente pessoa jurídica.");
        System.out.println("3 - Consultar informações de um cliente.");
        System.out.println("4 - Remover um cliente.");
        System.out.println("5 - Realizar um depósito.");
        System.out.println("6 - Realizar um saque.");
        System.out.println("7 - Realizar uma transferência.");
        System.out.println("8 - Listar todas as contas existentes.");
        System.out.println("9 - Calcular o saldo total das contas.");
        System.out.println("10 - Sair do sistema.");
    }
    
        public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner entrada = new Scanner(System.in);
        printMenu();
        int opcao = entrada.nextInt();
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    {
                        Cliente c = banco.createClienteFisico();
                        banco.addCliente(c);
                        System.out.println("----cadastro realizado com sucesso!----");
                        break;
                    }
                case 2:
                    {
                        Cliente c = banco.createClienteJuridico();
                        banco.addCliente(c);
                        System.out.println("----cadastro realizado com sucesso!----");
                        break;
                    }
                case 3:
                    {
                        System.out.println("----Digite o identificador: ----");
                        int identificador = entrada.nextInt();
                        Cliente c = banco.getCliente(identificador);
                        if (c == null) {
                            System.out.println("----Cliente nao encontrado!----");
                        }else {
                            System.out.println(c);
                        }       break;
                    }
                case 4:
                    {
                        System.out.println("----Digite o identificador: ----");
                        int identificador = entrada.nextInt();
                        Cliente c = banco.getCliente(identificador);
                        if (c == null) {
                            System.out.println("----Cliente nao encontrado!----");
                        }else {
                            banco.removeCliente(c);
                            System.out.println("----Cliente removido com sucesso!----");
                        }       break;
                    }
                case 5:
                    {
                        System.out.println("----Digite o identificador: ----");
                        int identificador = entrada.nextInt();
                        System.out.println("----Digite o valor do deposito: ----");
                        double valor = entrada.nextDouble();
                        Cliente c = banco.getCliente(identificador);
                        if (c == null) {
                            System.out.println("----Cliente nao encontrado!----");
                        }else {
                            boolean resultado = c.depositar(valor);
                            if (resultado == false) {
                                System.out.println("----Valor invalido para deposito!----");
                            }else {
                                System.out.println("----Deposito realizado com sucesso!----");
                                System.out.println("----Novo Saldo: ----" + c.getSaldo());
                            }
                        }       break;
                    }
                case 6:
                    {
                        System.out.println("----Digite o identificador: ----");
                        int identificador = entrada.nextInt();
                        System.out.println("----Digite o valor do saque: ----");
                        double valor = entrada.nextDouble();
                        Cliente c = banco.getCliente(identificador);
                        if (c == null) {
                            System.out.println("----Cliente nao encontrado!----");
                        }else {
                            boolean resultado = c.sacar(valor);
                            if (resultado == false) {
                                System.out.println("----Saldo insuficiente ou valor invalido!----");
                            }else {
                                System.out.println("----Saque realizado com sucesso!-----");
                                System.out.println("----Novo Saldo: ----" + c.getSaldo());
                            }
                        }       break;
                    }
                case 7:
                    {
                        System.out.println("----Digite o id da conta origem: ----");
                        int idOrigem = entrada.nextInt();
                        System.out.println("----Digite o id da conta destino: ----");
                        int idDestino = entrada.nextInt();
                        System.out.println("----Digite o valor da transferencia: ----");
                        double valor = entrada.nextDouble();
                        Cliente origem = banco.getCliente(idOrigem);
                        Cliente destino = banco.getCliente(idDestino);
                        if (origem == null) {
                            System.out.println("----Cliente Origem nao encontrado! Id: ----" + idOrigem);
                        }else if (destino == null) {
                            System.out.println("----Cliente Destino nao encontrado! Id: ----" + idDestino);
                        }else if (origem.getId() == destino.getId()) {
                            System.out.println("----Contas iguais! Impossivel fazer operacao!----");
                        }else {
                            boolean resultado = origem.transferir(destino, valor);
                            if (resultado == false) {
                                System.out.println("----Saldo insuficiente ou valor invalido!----");
                            }else {
                                System.out.println("----Transferencia realizada com sucesso!----");
                                System.out.println("----Saldo Origem.: ----" + origem.getSaldo());
                                System.out.println("----Saldo Destino: ----" + destino.getSaldo());
                            }
                        }       break;
                    }
                case 8:
                    {
                        List<Cliente> clientes = banco.getClientes();
                        if (clientes.isEmpty()) {
                            System.out.println("---- Não há contas para listar. ----");
                        } else {
                            System.out.println("---- Lista de Contas ----");
                            for (Cliente cliente : clientes) {
                                System.out.println(cliente);
                            }
                            
                        }           break;
                    }
                case 9:
                    {
                        List<Cliente> clientes = banco.getClientes();
                        if (clientes.isEmpty()) {
                            System.out.println("---- Não há contas para calcular o saldo total. ----");
                        } else {
                            double saldoTotal = calcularSaldoTotal(clientes);
                            System.out.println("---- Saldo Total das Contas: ----" + saldoTotal);
                        }   break;
                    }
                case 10:
                    System.out.println("----até logo----");
                    System.exit(0); 
                default:
                    break;
            }

                    
            printMenu();
            opcao = entrada.nextInt();
        }
    }

    private static double calcularSaldoTotal(List<Cliente> clientes) {
    double saldoTotal = 0.0;
    for (Cliente cliente : clientes) {
        if (cliente.getSaldo() >= 0) { 
            saldoTotal += cliente.getSaldo();
        }
    }
    return saldoTotal;

    }
    
}

