package com.nikkay.canedo.shoppingcartappmidterm.model;

/**
 * Created by NIKKAY on 9/6/2016.
 */
public class ShoppingList {

    private String itemName;
    private int itemQuantity;
    private double itemPrice;
    private double itemTotalPrice;

    public ShoppingList(String itemName, int itemQuantity, double itemPrice, double itemTotalPrice) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.itemTotalPrice = itemTotalPrice;
    }

    public ShoppingList() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(double itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }
}
