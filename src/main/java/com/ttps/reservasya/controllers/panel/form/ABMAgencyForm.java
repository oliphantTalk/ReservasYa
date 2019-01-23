package com.ttps.reservasya.controllers.panel.form;

/**
 * @author nahuelbarrena on 20/01/19
 */
public class ABMAgencyForm {

    private String addName;
    private String addCity;
    private String editName;
    private String editCity;
    private Long editId;
    private Long deleteId;

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
    }

    public String getAddCity() {
        return addCity;
    }

    public void setAddCity(String addCity) {
        this.addCity = addCity;
    }

    public String getEditCity() {
        return editCity;
    }

    public void setEditCity(String editCity) {
        this.editCity = editCity;
    }

    public Long getEditId() {
        return editId;
    }

    public void setEditId(Long editId) {
        this.editId = editId;
    }

    public Long getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(Long deleteId) {
        this.deleteId = deleteId;
    }
}
