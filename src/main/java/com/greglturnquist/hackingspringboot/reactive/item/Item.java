package com.greglturnquist.hackingspringboot.reactive.item;

import org.springframework.data.annotation.Id;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

public class Item {
    private @Id String id;
    private String name;
    private String description;
    private double price;
    private String distributionRegion;
    private Date releaseDate;
    private int availableUnits;
    private Point location;
    private boolean active;

    private Item() {}

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public String getDistributionRegion() {
        return distributionRegion;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public Point getLocation() {
        return location;
    }

    public boolean isActive() {
        return active;
    }

    public Item(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Item(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return "Item{id='"+getId()+"', name='"+getName()+"', description='"+getDescription()+"', price="+getPrice()+"}";
    }

    @Override
    public boolean equals(Object o) {
        Item item = (Item)o;
        if(getId() != item.getId()) return false;
        if(getName() != item.getName()) return false;
        if(getDescription() != item.getDescription()) return false;
        if(getPrice() != item.getPrice()) return false;
        return true;
    }

    // hashcode 재정의
    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
