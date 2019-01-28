package com.ttps.reservasya.controllers.panel.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nahuelbarrena on 27/01/19
 */
@Getter @Setter @NoArgsConstructor
public class ABMRoomForm {

    private Long addHotelId;
    private String addRoomId;
    private int addRoomBeds;
    private double addRoomPrice;


}
