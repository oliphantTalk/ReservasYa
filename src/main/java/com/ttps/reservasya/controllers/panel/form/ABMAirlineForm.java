package com.ttps.reservasya.controllers.panel.form;

/**
 * @author nahuelbarrena on 20/01/19
 */
public class ABMAirlineForm {

    private String addName;
    private String addShortName;
    private Long editId;
    private String editName;
    private String editShortName;
    private Long deleteId;

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public String getAddShortName() {
        return addShortName;
    }

    public void setAddShortName(String addShortName) {
        this.addShortName = addShortName;
    }

    public Long getEditId() {
        return editId;
    }

    public void setEditId(Long editId) {
        this.editId = editId;
    }

    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
    }

    public String getEditShortName() {
        return editShortName;
    }

    public void setEditShortName(String editShortName) {
        this.editShortName = editShortName;
    }

    public Long getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(Long deleteId) {
        this.deleteId = deleteId;
    }
}
