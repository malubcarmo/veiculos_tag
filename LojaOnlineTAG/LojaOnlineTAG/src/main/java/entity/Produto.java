package com.mycompany.lojaonline.entity;

public abstract class Produto {
    private int id;
    private String nome;
    private double precoBase;
    private String categoria;
    private String descricao;
    private int estoque; // <--- NOVO CAMPO

    // Construtor atualizado recebendo estoque
    public Produto(int id, String nome, double precoBase, String categoria, String descricao, int estoque) {
        this.id = id;
        this.nome = nome;
        this.precoBase = precoBase;
        this.categoria = categoria;
        this.descricao = descricao;
        this.estoque = estoque;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPrecoBase() { return precoBase; }
    public String getCategoria() { return categoria; }
    public String getDescricao() { return descricao; }
    public int getEstoque() { return estoque; } // <--- Getter do estoque

    // Método útil para debitar estoque no futuro
    public void debitarEstoque(int qtd) {
        if (this.estoque >= qtd) {
            this.estoque -= qtd;
        }
    }

    public abstract double calcularPrecoFinal();

    @Override
    public String toString() {
        // Adicionei a informação do Estoque na exibição
        return String.format("[%d] %s - R$ %.2f (%s) | Estoque: %d un.", id, nome, precoBase, categoria, estoque);
    }
}