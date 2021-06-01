package com.example.airlinesdb.repository;

import com.example.airlinesdb.data.Airplane;
import com.example.airlinesdb.data.Flight;
import com.example.airlinesdb.tables.Airplanes;
import com.example.airlinesdb.tables.Flights;
import com.example.airlinesdb.tables.Pilots;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FlightRepository implements CrudRepository<Flight>{

    private final DSLContext dsl;

    @Override
    public void insert(Flight object) {
        dsl.insertInto(Flights.FLIGHTS)
                .values(object.getId(), object.getDate(), object.getAirplaneId(), object.getDepartureAirportId(), object.getDestinationAirportId())
                .returning()
                .fetchOne();
    }

    @Override
    public void update(Flight object) {
        delete(object.getId());
        insert(object);
    }

    @Override
    public Flight find(Integer id) {
        return dsl.selectFrom(Flights.FLIGHTS)
                .where(Flights.FLIGHTS.ID.eq(id))
                .fetchAny()
                .into(Flight.class);
    }

    public List<Flight> findAll(Condition condition) {
        List<Flight> flights = new ArrayList<>();
        dsl.selectFrom(Flights.FLIGHTS)
                .where(condition)
                .fetchAny()
                .map( record -> {
                    Flight flight = record.into(Flight.class);
                    flights.add(flight);
                    return flight;
                });
        return flights;
    }

    @Override
    public void delete(Integer id) {
        dsl.deleteFrom(Flights.FLIGHTS)
                .where(Flights.FLIGHTS.ID.eq(id));
    }
}
