package com.ttps.reservasya.controllers.searchbox;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SearchFlyForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    private String from;
    private String to;
    private String departureDate;
    private String returnDate;
    private String clase;
    private int flyPassenger;

}
