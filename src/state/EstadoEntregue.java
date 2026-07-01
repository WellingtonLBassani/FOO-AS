package state;

import model.Pedido;

public class EstadoEntregue implements EstadoPedido {
    @Override
    public void avancar(Pedido pedido) {
        System.out.println("O pedido já foi entregue ao cliente. Ciclo finalizado.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("[ERRO] O pedido já foi consumido/entregue! Impossível cancelar.");
    }

    @Override
    public String getStatusNome() {
        return "Entregue";
    }

    @Override
    public boolean permiteAlteracao() {
        return false;
    }
}