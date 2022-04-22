package com.company;

public class Phones {

    private String name;
    private String company;
    private int price;

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Название: '" + name + '\'' +
                ", Компания: '" + company + '\'' +
                ", Цена: " + price;
    }
}
