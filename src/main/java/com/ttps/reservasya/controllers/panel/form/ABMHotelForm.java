package com.ttps.reservasya.controllers.panel.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nahuelbarrena on 20/01/19
 */
@Getter @Setter @NoArgsConstructor
public class ABMHotelForm {

    private String addHotelCity;
    private String addHotelName;
    private int addHotelStars;
    private String editHotelCity;
    private String editHotelName;
    private int editHotelStars;
    private long editHotelId;
    private long deleteHotelId;

}
