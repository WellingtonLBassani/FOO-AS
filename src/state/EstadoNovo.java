package state;

import model.Pedido;

public class EstadoNovo implements EstadoPedido {
    @Override
    public void avancar(Pedido pedido) {
        pedido.setEstado(new EstadoEmPreparo());
    }

    @Override
    public void cancelar(Pedido pedido){
        pedido.setEstado(new EstadoCancelado());
    }

    @Override
    public String getStatusNome(){
        return "Novo / Recebido";
    }

    @Override
    public boolean permiteAlteracao() {
        return true;
    }
}