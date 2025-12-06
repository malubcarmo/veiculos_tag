package com.mycompany.lojaonline.service;

import com.mycompany.lojaonline.entity.Peca;
import com.mycompany.lojaonline.entity.Produto;
import com.mycompany.lojaonline.entity.Veiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BancoDeDados {
    private static List<Produto> catalogo = new ArrayList<>();

    public static void inicializar() {
        // Veículos (Adicionei o estoque antes do ano)
        // Ex: ... "Descricao", 5, 2022); -> 5 é o estoque
        catalogo.add(new Veiculo(1, "Honda Civic", 120000.00, "Carro", "Sedan completo, automático", 3, 2022));
        catalogo.add(new Veiculo(2, "Toyota Corolla", 130000.00, "Carro", "Híbrido, econômico", 2, 2023));
        catalogo.add(new Veiculo(3, "Yamaha MT-03", 28000.00, "Moto", "321cc, Naked, Azul", 5, 2024));

        // Peças (Adicionei o estoque antes do peso)
        catalogo.add(new Peca(5, "Pneu Aro 17", 600.00, "Peça Carro", "Michelin Primacy 4", 20, 10.0));
        catalogo.add(new Peca(7, "Escapamento", 1200.00, "Peça Moto", "Ponteira em carbono", 8, 3.0));
    }

    public static List<Produto> getCatalogo() {
        return catalogo;
    }

    public static List<Produto> filtrarPorCategoria(String categoria) {
        return catalogo.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public static Produto buscarPorId(int id) {
        return catalogo.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}