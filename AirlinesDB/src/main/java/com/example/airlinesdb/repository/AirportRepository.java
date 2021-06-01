package com.example.airlinesdb.repository;

import com.example.airlinesdb.data.Airplane;
import com.example.airlinesdb.data.Airport;
import com.example.airlinesdb.tables.Airplanes;
import com.example.airlinesdb.tables.Airports;
import com.example.airlinesdb.tables.Flights;
import com.example.airlinesdb.tables.Pilots;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class AirportRepository implements CrudRepository<Airport>{

    private final DSLContext dsl;

    @Override
    public void insert(Airport object) {
        dsl.insertInto(Airports.AIRPORTS)
                .values(object.getId(), object.getName(), object.getCountryId())
                .returning()
                .fetchOne();
    }

    @Override
    public void update(Airport object) {
        delete(object.getId());
        insert(object);
    }

    @Override
    public Airport find(Integer id) {
        return dsl.selectFrom(Airports.AIRPORTS)
                .where(Airports.AIRPORTS.ID.eq(id))
                .fetchAny()
                .map(r -> {
                    Airport airport = r.into(Airport.class);
                    airport.setFlightList(Repository.db.flights.findAll(Flights.FLIGHTS.DEPARTURE_AIRPORT_ID.eq(airport.getId())));
                    return airport;
                });
    }

    public List<Airport> findAll(Condition condition) {
        List<Airport> airports = new ArrayList<>();
        dsl.selectFrom(Airports.AIRPORTS)
                .where(condition)
                .fetchAny()
                .map( record -> {
                    Airport airport = record.into(Airport.class);
                    airport.setFlightList(Repository.db.flights.findAll(Flights.FLIGHTS.DEPARTURE_AIRPORT_ID.eq(airport.getId())));
                    airports.add(airport);
                    return airport;
                });
        return airports;
    }

    @Override
    public void delete(Integer id) {
        dsl.deleteFrom(Airports.AIRPORTS)
                .where(Airports.AIRPORTS.ID.eq(id));
    }
}
