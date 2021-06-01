package com.example.airlinesdb.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    private Integer id;
    private Long date;
    private Integer airplaneId;
    private Integer departureAirportId;
    private Integer destinationAirportId;

}
