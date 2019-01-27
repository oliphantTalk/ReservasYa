package com.ttps.reservasya.controllers.panel.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

/**
 * @author nahuelbarrena on 25/01/19
 */
@Getter @Setter @NoArgsConstructor
public class ABMFlightForm {
    Long addAirlineId;
    String addFlightFrom;
    String addFlightTo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime addFlightDepartureDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime addFlightArrivalDate;
    double addFlightPrice;
    int addEconomic;
    int addBusiness;
    int addFirst;
    int addScales;
    int addGapMax;


}
