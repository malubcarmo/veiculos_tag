package com.mycompany.lojaonline.entity;

import com.mycompany.lojaonline.entity.Produto;

public class Peca extends Produto {
    private double pesoKg;

    // Recebe estoque e passa para o super
    public Peca(int id, String nome, double precoBase, String categoria, String descricao, int estoque, double pesoKg) {
        super(id, nome, precoBase, categoria, descricao, estoque);
        this.pesoKg = pesoKg;
    }

    @Override
    public double calcularPrecoFinal() {
        return getPrecoBase() + (pesoKg * 20.0);
    }

    @Override
    public String toString() {
        return super.toString() + " | Peso: " + pesoKg + "kg";
    }
}