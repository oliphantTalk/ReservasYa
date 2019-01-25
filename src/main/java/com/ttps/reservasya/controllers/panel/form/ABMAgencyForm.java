package com.ttps.reservasya.controllers.panel.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nahuelbarrena on 20/01/19
 */
@Getter @Setter @NoArgsConstructor
public class ABMAgencyForm {

    private String addAgencyName;
    private String addAgencyCity;
    private String editAgencyName;
    private String editAgencyCity;
    private Long editAgencyId;
    private Long deleteAgencyId;

}
