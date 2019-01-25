package com.ttps.reservasya.controllers.panel.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nahuelbarrena on 20/01/19
 */
@Getter @Setter @NoArgsConstructor
public class ABMAirlineForm {

    private String addAirlineName;
    private String editAirlineName;
    private String addAirlineShortName;
    private String editAirlineShortName;
    private long editAirlineId;
    private long deleteAirlineId;

}
