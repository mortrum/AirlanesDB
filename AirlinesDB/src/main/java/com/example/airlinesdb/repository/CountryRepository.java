package com.example.airlinesdb.repository;

import com.example.airlinesdb.data.Airplane;
import com.example.airlinesdb.data.Airport;
import com.example.airlinesdb.data.Country;
import com.example.airlinesdb.tables.Airplanes;
import com.example.airlinesdb.tables.Airports;
import com.example.airlinesdb.tables.Countries;
import com.example.airlinesdb.tables.Flights;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class CountryRepository implements CrudRepository<Country>{

    private final DSLContext dsl;

    @Override
    public void insert(Country object) {
        dsl.insertInto(Countries.COUNTRIES)
                .values(object.getId(), object.getName())
                .returning()
                .fetchOne();
    }

    @Override
    public void update(Country object) {
        delete(object.getId());
        insert(object);
    }

    @Override
    public Country find(Integer id) {
        return dsl.selectFrom(Countries.COUNTRIES)
                .where(Countries.COUNTRIES.ID.eq(id))
                .fetchAny()
                .map(r -> {
                    Country country = r.into(Country.class);
                    country.setAirportsList(Repository.db.airports.findAll(Airports.AIRPORTS.COUNTRY_ID.eq(country.getId())));
                    return country;
                });
    }

    public List<Country> findAll(Condition condition) {
        List<Country> countries = new ArrayList<>();
        dsl.selectFrom(Countries.COUNTRIES)
                .where(condition)
                .fetchAny()
                .map( record -> {
                    Country country = record.into(Country.class);
                    country.setAirportsList(Repository.db.airports.findAll(Airports.AIRPORTS.COUNTRY_ID.eq(country.getId())));
                    countries.add(country);
                    return country;
                });
        return countries;
    }

    @Override
    public void delete(Integer id) {
        dsl.deleteFrom(Flights.FLIGHTS)
                .where(Flights.FLIGHTS.ID.eq(id));
    }
}
