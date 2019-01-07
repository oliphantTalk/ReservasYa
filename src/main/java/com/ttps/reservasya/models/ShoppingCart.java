package com.ttps.reservasya.models;

import com.ttps.reservasya.models.businessitem.BusinessItem;
import com.ttps.reservasya.models.user.User;

import java.util.List;

public class ShoppingCart {

    private String id;
    private List<BusinessItem> items;
    private User user;
    private Boolean active = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BusinessItem> getItems() {
        return items;
    }

    public void setItems(List<BusinessItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
