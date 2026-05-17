/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

public class Pedido {

    private List<ItemCarrito> items;
    private double total;

    public Pedido(List<ItemCarrito> items, double total) {
        this.items = items;
        this.total = total;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Pedido con " + items.size() + " productos - Total: $"
                + String.format("%,.0f", total);
    }
}