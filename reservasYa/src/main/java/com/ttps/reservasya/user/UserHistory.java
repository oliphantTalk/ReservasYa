package com.ttps.reservasya.user;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserHistory implements Serializable {

    //FIXME: No tiene sentido esta clase asi!

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
