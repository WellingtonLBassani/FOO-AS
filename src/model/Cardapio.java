package model;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    private List<Produto> produtos;

    public Cardapio() {
        this.produtos = new ArrayList<>();
        produtos.add(new Produto("Hambúrguer", 25.0));
        produtos.add(new Produto("Batata Frita", 12.0));
        produtos.add(new Produto("Refrigerante", 8.0));
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
        System.out.println("[SUCESSO] Produto adicionado ao cardápio!");
    }

    public Produto getProduto(int id) {
        if (id >= 0 && id < produtos.size()) {
            return produtos.get(id);
        }
        return null;
    }

    public void exibirCardapio() {
        System.out.println("\n--- CARDÁPIO ---");
        if (produtos.isEmpty()) {
            System.out.println("O cardápio está vazio.");
            return;
        }
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            System.out.println(i + " - " + p.getNome() + " (R$ " + p.getPreco() + ")");
        }
    }
}