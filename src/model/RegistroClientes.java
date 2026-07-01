package model;

import java.util.ArrayList;
import java.util.List;

public class RegistroClientes {
    private List<Cliente> clientes;

    public RegistroClientes() {
        this.clientes = new ArrayList<>();
        // Cliente inicial para testes
        clientes.add(new Cliente("João", "joao@email.com"));
    }

    public void cadastrarCliente(Cliente cliente) {
        this.clientes.add(cliente);
        System.out.println("[SUCESSO] Cliente " + cliente.getNome() + " cadastrado!");
    }

    public Cliente getCliente(int id) {
        if (id >= 0 && id < clientes.size()) {
            return clientes.get(id);
        }
        return null;
    }

    public void exibirClientes() {
        System.out.println("\n--- CLIENTES CADASTRADOS ---");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + " - " + clientes.get(i).getNome());
        }
    }
}