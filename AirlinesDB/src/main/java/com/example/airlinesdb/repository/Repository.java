package com.example.airlinesdb.repository;

import lombok.Getter;
import org.jooq.DSLContext;

@Getter
public class Repository {

    public static Repository db = new Repository();

    public AirplaneRepository airplanes;
    public AirportRepository airports;
    public CountryRepository countries;
    public FlightRepository flights;
    public PilotRepository pilots;

    public void init(DSLContext context) {

        airplanes = new AirplaneRepository(context);
        airports = new AirportRepository(context);
        countries = new CountryRepository(context);
        flights = new FlightRepository(context);
        pilots = new PilotRepository(context);

    }

}
