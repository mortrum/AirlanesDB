package com.example.airlinesdb.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    private Integer id;
    private String name;
    private Integer countryId;
    private List<Flight> flightList;

}
