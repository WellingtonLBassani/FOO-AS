package model;

public class GeradorRelatorio {

    public void imprimirRelatorioItens(Pedido pedido) {
        System.out.println("\n=== RELATÓRIO DE ITENS: " + pedido.getClienteNome() + " ===");
        for (Produto p : pedido.getItens()) {
            System.out.println("Item: " + p.getNome() + " | Preço: R$" + p.getPreco());
        }
    }

    public void imprimirRelatorioFinanceiro(Pedido pedido) {
        System.out.println("\n=== RELATÓRIO FINANCEIRO ===");
        System.out.println("Valor Final a pagar: R$" + pedido.calcularTotal());
    }
}