package com.ttps.reservasya.controllers.panel.form;

/**
 * @author nahuelbarrena on 20/01/19
 */
public class ABMHotelForm {

    private String addCity;
    private String addName;
    private int addStars;
    private String editCity;
    private String editName;
    private int editStars;
    private long editId;
    private long deleteId;

    public String getAddCity() {
        return addCity;
    }

    public void setAddCity(String addCity) {
        this.addCity = addCity;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public int getAddStars() {
        return addStars;
    }

    public void setAddStars(int addStars) {
        this.addStars = addStars;
    }

    public String getEditCity() {
        return editCity;
    }

    public void setEditCity(String editCity) {
        this.editCity = editCity;
    }

    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
    }

    public int getEditStars() {
        return editStars;
    }

    public void setEditStars(int editStars) {
        this.editStars = editStars;
    }

    public long getEditId() {
        return editId;
    }

    public void setEditId(long editId) {
        this.editId = editId;
    }

    public long getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(long deleteId) {
        this.deleteId = deleteId;
    }
}
