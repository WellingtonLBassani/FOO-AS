package builder;

import model.Cliente;
import model.Pedido;
import model.Produto;
import strategy.EstrategiaDesconto;
import strategy.SemDesconto;
import java.util.ArrayList;
import java.util.List;

public class PedidoBuilder {
    private Cliente cliente;
    private List<Produto> itens = new ArrayList<>();
    private String formaPagamento = "Dinheiro";
    private EstrategiaDesconto estrategia = new SemDesconto();

    public PedidoBuilder(Cliente cliente) {
        this.cliente = cliente;
    }

    public PedidoBuilder adicionarProduto(Produto p) {
        this.itens.add(p);
        return this;
    }

    public PedidoBuilder comPagamento(String forma) {
        this.formaPagamento = forma;
        return this;
    }

    public PedidoBuilder comDesconto(EstrategiaDesconto est) {
        this.estrategia = est;
        return this;
    }

    public Pedido build() {
        if (itens.isEmpty()) {
            throw new IllegalStateException("[ERRO] Um pedido não pode ser criado sem produtos!");
        }
        return new Pedido(cliente, itens, formaPagamento, estrategia);
    }
}