package state;

import model.Pedido;

public class EstadoEmPreparo implements EstadoPedido {
    @Override
    public void avancar(Pedido pedido) {
        pedido.setEstado(new EstadoPronto());
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("[ERRO] O prato já está no fogo! Impossível cancelar agora.");
    }

    @Override
    public String getStatusNome() {
        return "Em Preparo";
    }

    @Override
    public boolean permiteAlteracao() {
        return false;
    }
}