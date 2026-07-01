package state;

import model.Pedido;

public class EstadoPronto implements EstadoPedido {
    @Override
    public void avancar(Pedido pedido) {
        pedido.setEstado(new EstadoEntregue());
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("[ERRO] O pedido já está pronto e embalado. Não é possível cancelar.");
    }

    @Override
    public String getStatusNome() {
        return "Pronto / Aguardando Entrega";
    }

    @Override
    public boolean permiteAlteracao() {
        return false;
    }
}