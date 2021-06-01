package com.example.airlinesdb.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airplane {

    private Integer id;
    private List<Pilot> pilots;

}
