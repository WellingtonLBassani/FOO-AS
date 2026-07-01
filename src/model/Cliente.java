package model;

public class Cliente {
    private String nome;
    private String email;

    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public void receberNotificacao(String mensagem) {
        System.out.println("Enviando e-mail para " + email + ": " + mensagem);
    }

    public String getNome() {
        return nome;
    }
}