package com.mycompany.lojaonline.entity;
import com.mycompany.lojaonline.entity.Produto;

public class Veiculo extends Produto {
    private int ano;

    // Recebe estoque e passa para o super
    public Veiculo(int id, String nome, double precoBase, String categoria, String descricao, int estoque, int ano) {
        super(id, nome, precoBase, categoria, descricao, estoque);
        this.ano = ano;
    }

    @Override
    public double calcularPrecoFinal() {
        return getPrecoBase() * 1.05;
    }

    @Override
    public String toString() {
        return super.toString() + " | Ano: " + ano + " | Doc. inclusa";
    }
}