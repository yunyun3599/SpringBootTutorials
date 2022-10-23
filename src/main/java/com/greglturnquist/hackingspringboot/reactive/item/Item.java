package com.greglturnquist.hackingspringboot.reactive.item;

import org.springframework.data.annotation.Id;

import java.awt.*;
import java.util.Date;

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
}
