package com.example.airlinesdb.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pilot {

    private Integer id;
    private String name;
    private Integer flightHours;
    private Boolean isWorking;

}
