package com.ByteBazaar.ByteBazaarBackend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "items")
public class ItemEntity {
    @Id
    public String itemId;
    public String name;
    public String description;
    public String picture;
    public Float price;
    public Boolean in_stock;
    public Integer stock_num;
    public List<CategoryEnum> categories;

    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getIn_stock() {
        return in_stock;
    }
    public void setIn_stock(Boolean in_stock) {
        this.in_stock = in_stock;
    }

    public Integer getStock_num() {
        return stock_num;
    }
    public void setStock_num(Integer stock_num) {
        this.stock_num = stock_num;
    }

    public List<CategoryEnum> getCategories() {
        return categories;
    }
    public void setCategories(List<CategoryEnum> categories) {
        this.categories = categories;
    }
}
