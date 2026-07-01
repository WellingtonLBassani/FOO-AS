package model;

import state.EstadoPedido;
import state.EstadoNovo;
import strategy.EstrategiaDesconto;
import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private Cliente cliente;
    private List<Produto> itens;
    private String formaPagamento;
    private EstadoPedido estadoAtual;
    private EstrategiaDesconto estrategiaDesconto;

    public Pedido(Cliente cliente, List<Produto> itens, String formaPagamento, EstrategiaDesconto estrategiaDesconto) {
        this.cliente = cliente;
        this.itens = new ArrayList<>(itens);
        this.formaPagamento = formaPagamento;
        this.estrategiaDesconto = estrategiaDesconto;
        this.estadoAtual = new EstadoNovo();
    }
    public void setEstado(EstadoPedido novoEstado) {
        this.estadoAtual = novoEstado;
        String msg = "[STATUS]: " + estadoAtual.getStatusNome();
        System.out.println(msg);
        this.cliente.receberNotificacao(msg); // Notifica o cliente
    }

    public void avancarEstagio() { estadoAtual.avancar(this); }
    public void tentarCancelar() { estadoAtual.cancelar(this); }

    public void adicionarItem(Produto produto) {
        if (!estadoAtual.permiteAlteracao()) {
            System.out.println("[ERRO] Bloqueado! O pedido está com status '" + estadoAtual.getStatusNome() + "'.");
            return;
        }
        this.itens.add(produto);
        System.out.println("Item adicionado: " + produto.getNome());
    }

    public void removerItem(Produto produto) {
        if (!estadoAtual.permiteAlteracao()) {
            System.out.println("[ERRO] Bloqueado! O pedido está com status '" + estadoAtual.getStatusNome() + "'.");
            return;
        }

        if (this.itens.remove(produto)) {
            System.out.println("Item removido: " + produto.getNome());
        } else {
            System.out.println("Item não encontrado no pedido.");
        }
    }

    public double calcularTotal() {
        double subtotal = 0;
        for (Produto item : itens) subtotal += item.getPreco();
        return estrategiaDesconto.aplicarDesconto(subtotal);
    }

    public List<Produto> getItens() { return itens; }
    public String getClienteNome() { return cliente.getNome(); }

    public void exibirResumo() {
        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.println("Cliente: " + cliente.getNome());
        for (Produto p : itens) System.out.println("- " + p.getNome() + ": R$ " + p.getPreco());
        System.out.println("TOTAL: R$ " + calcularTotal());
        System.out.println("------------------------\n");
    }
}