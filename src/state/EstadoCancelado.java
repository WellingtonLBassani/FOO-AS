package state;

import model.Pedido;

public class EstadoCancelado implements EstadoPedido {
    @Override
    public void avancar(Pedido pedido) {
        System.out.println("[ERRO] Um pedido cancelado não pode mais avançar.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("O pedido já se encontra cancelado.");
    }

    @Override
    public String getStatusNome() {
        return "Cancelado";
    }

    @Override
    public boolean permiteAlteracao() {
        return false;
    }
}