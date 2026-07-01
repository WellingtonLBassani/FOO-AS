import builder.PedidoBuilder;
import model.*;
import strategy.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistroClientes registroClientes = new RegistroClientes();
        Cardapio cardapio = new Cardapio();

        PedidoBuilder builder = null;
        Pedido pedido = null;
        int opcao;

        do {
            System.out.println("\n==========================");
            System.out.println("    SISTEMA DE PEDIDOS    ");
            System.out.println("==========================");
            System.out.println("1 - Cadastrar novo Cliente");
            System.out.println("2 - Cadastrar Produto no Cardápio");
            System.out.println("3 - Ver cardápio");
            System.out.println("4 - Adicionar itens no pedido");
            System.out.println("5 - Remover item do pedido");
            System.out.println("6 - Finalizar e Criar pedido");
            System.out.println("7 - Avançar status");
            System.out.println("8 - Cancelar Pedido");
            System.out.println("9 - Relatórios");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> {
                    sc.nextLine();
                    System.out.print("\nNome: ");
                    String nome = sc.nextLine();
                    System.out.print("E-mail: ");
                    String email = sc.nextLine();

                    registroClientes.cadastrarCliente(new Cliente(nome, email));
                }

                case 2 -> {
                    sc.nextLine();
                    System.out.print("\nNome do produto: ");
                    String nome = sc.nextLine();
                    System.out.print("Preço: R$ ");
                    double preco = sc.nextDouble();

                    cardapio.adicionarProduto(new Produto(nome, preco));
                }

                case 3 -> cardapio.exibirCardapio();

                case 4 -> {
                    if (builder == null && pedido == null) {
                        registroClientes.exibirClientes();
                        System.out.print("ID do Cliente para este pedido: ");
                        int idCli = sc.nextInt();

                        Cliente clienteEscolhido = registroClientes.getCliente(idCli);
                        if (clienteEscolhido != null) {
                            builder = new PedidoBuilder(clienteEscolhido);
                            System.out.println("Pedido iniciado para: " + clienteEscolhido.getNome());
                        } else {
                            System.out.println("[ERRO] Cliente não encontrado.");
                            break;
                        }
                    }
                    cardapio.exibirCardapio();
                    int idProd;
                    do {
                        System.out.print("\nDigite o código do produto (ou -1 para voltar): ");
                        idProd = sc.nextInt();

                        Produto produtoEscolhido = cardapio.getProduto(idProd);

                        if (produtoEscolhido != null) {
                            if (pedido != null) {
                                pedido.adicionarItem(produtoEscolhido);
                            } else if (builder != null) {
                                builder.adicionarProduto(produtoEscolhido);
                                System.out.println("-> " + produtoEscolhido.getNome() + " adicionado ao carrinho!");
                            }
                        } else if (idProd != -1) {
                            System.out.println("[ERRO] Produto não encontrado.");
                        }
                    } while (idProd != -1);
                }

                case 5 -> {
                    if (pedido == null) {
                        System.out.println("[AVISO] Você precisa finalizar o pedido primeiro (Opção 6).");
                        break;
                    }
                    System.out.println("\n--- ITENS NO PEDIDO ---");
                    for (int i = 0; i < pedido.getItens().size(); i++) {
                        System.out.println(i + " - " + pedido.getItens().get(i).getNome());
                    }
                    System.out.print("Digite o índice do item para remover: ");
                    int id = sc.nextInt();
                    if (id >= 0 && id < pedido.getItens().size()) {
                        pedido.removerItem(pedido.getItens().get(id));
                    } else {
                        System.out.println("[ERRO] Índice inválido.");
                    }
                }

                case 6 -> {
                    if (pedido != null) {
                        System.out.println("[AVISO] O pedido já foi criado.");
                        break;
                    }
                    if (builder == null) {
                        System.out.println("[AVISO] Adicione itens primeiro (Opção 4).");
                        break;
                    }
                    try {
                        pedido = builder
                                .comPagamento(escolherPagamento(sc))
                                .comDesconto(escolherDesconto(sc))
                                .build();

                        System.out.println("\n[SUCESSO] Pedido finalizado!");
                        pedido.exibirResumo();
                    } catch (IllegalStateException e) {
                        System.out.println("\n[ERRO] " + e.getMessage());
                    }
                }

                case 7 -> {
                    if (pedido != null) pedido.avancarEstagio();
                    else System.out.println("[AVISO] Crie o pedido primeiro.");
                }

                case 8 -> {
                    if (pedido != null) pedido.tentarCancelar();
                    else System.out.println("[AVISO] Crie o pedido primeiro.");
                }

                case 9 -> {
                    if (pedido != null) {
                        pedido.exibirResumo();
                        GeradorRelatorio r = new GeradorRelatorio();
                        r.imprimirRelatorioItens(pedido);
                        r.imprimirRelatorioFinanceiro(pedido);
                    } else {
                        System.out.println("[AVISO] Não há pedido ativo.");
                    }
                }

                case 0 -> System.out.println("\nEncerrando o sistema...");
                default -> System.out.println("\n[ERRO] Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }

    private static String escolherPagamento(Scanner sc) {
        System.out.println("\nForma de Pagamento:");
        System.out.println("1 - Dinheiro | 2 - Cartão | 3 - Pix");
        System.out.print("Escolha: ");
        int op = sc.nextInt();
        return switch (op) {
            case 2 -> "Cartão";
            case 3 -> "Pix";
            default -> "Dinheiro";
        };
    }

    private static EstrategiaDesconto escolherDesconto(Scanner sc) {
        System.out.println("\nDesconto:");
        System.out.println("1 - Nenhum | 2 - Primeira compra (R$ 10) | 3 - 10% OFF");
        System.out.print("Escolha: ");
        int op = sc.nextInt();
        return switch (op) {
            case 2 -> new DescontoPrimeiraCompra();
            case 3 -> new DescontoPercentual(10.0);
            default -> new SemDesconto();
        };
    }
}