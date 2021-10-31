package com.example.airlinesdb.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    private Integer id;
    private LocalDate date;
    private Integer airplaneId;
    private Integer departureAirportId;
    private Integer destinationAirportId;

}
