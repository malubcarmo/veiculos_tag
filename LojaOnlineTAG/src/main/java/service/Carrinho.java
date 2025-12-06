package com.mycompany.lojaonline.service;

import com.mycompany.lojaonline.entity.Produto;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> itens = new ArrayList<>();

    public void adicionar(Produto p) {
        // Regra simples: só adiciona se tiver estoque
        if (p.getEstoque() > 0) {
            itens.add(p);
            System.out.println(">> " + p.getNome() + " adicionado ao carrinho!");
        } else {
            System.out.println(">> ERRO: Produto sem estoque!");
        }
    }

    public void remover(int id) {
        boolean removeu = itens.removeIf(p -> p.getId() == id);
        if (removeu) System.out.println(">> Produto removido!");
        else System.out.println(">> Produto não estava no carrinho.");
    }

    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio.");
        } else {
            System.out.println("\n--- ITENS NO CARRINHO ---");
            itens.forEach(p -> System.out.println(p.getNome() + " | R$ " + p.getPrecoBase()));
        }
    }

    public double calcularTotal() {
        return itens.stream()
                .map(Produto::calcularPrecoFinal)
                .reduce(0.0, Double::sum);
    }

    // --- MÉTODOS NOVOS PARA FINALIZAÇÃO ---

    public void baixarEstoque() {
        // PARADIGMA FUNCIONAL: forEach
        // Percorre cada item e tira 1 unidade do estoque real
        itens.forEach(p -> p.debitarEstoque(1));
    }

    public void limpar() {
        itens.clear();
    }
}
//teste