package com.ttps.reservasya.controllers.panel.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nahuelbarrena on 27/01/19
 */
@Getter @Setter @NoArgsConstructor
public class ABMCarForm {

    private Long addAgencyId;
    private String addCarDescription;
    private String addCarModel;
    private int addCarYear;
    private int addCarCapacity;
    private String addCarPatent;
    private double addCarPrice;


}
