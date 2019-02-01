package com.ttps.reservasya.controllers.searchbox;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SearchHotelForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    private String hotelTo;
    private String hotelDateFrom;
    private String hotelDateTo;
    private int hotelPassenger;
    private int hotelStars;

}
