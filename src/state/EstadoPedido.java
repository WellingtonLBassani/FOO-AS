package state;

import model.Pedido;

public interface EstadoPedido {
    void avancar(Pedido pedido);
    void cancelar(Pedido pedido);
    String getStatusNome();
    boolean permiteAlteracao();
}