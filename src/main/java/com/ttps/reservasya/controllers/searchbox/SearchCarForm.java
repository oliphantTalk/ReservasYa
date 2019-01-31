package com.ttps.reservasya.controllers.searchbox;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchCarForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    private String pickup;
    private String retrieve;
    private String dateFrom;
    private String dateTo;
    private int carPassenger;

}
