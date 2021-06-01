/*
 * This file is generated by jOOQ.
 */
package com.example.airlinesdb;


import com.example.airlinesdb.tables.Airplanes;
import com.example.airlinesdb.tables.Airports;
import com.example.airlinesdb.tables.Countries;
import com.example.airlinesdb.tables.Flights;
import com.example.airlinesdb.tables.Pilots;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PUBLIC</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>PUBLIC.AIRPLANES</code>.
     */
    public final Airplanes AIRPLANES = Airplanes.AIRPLANES;

    /**
     * The table <code>PUBLIC.AIRPORTS</code>.
     */
    public final Airports AIRPORTS = Airports.AIRPORTS;

    /**
     * The table <code>PUBLIC.COUNTRIES</code>.
     */
    public final Countries COUNTRIES = Countries.COUNTRIES;

    /**
     * The table <code>PUBLIC.FLIGHTS</code>.
     */
    public final Flights FLIGHTS = Flights.FLIGHTS;

    /**
     * The table <code>PUBLIC.PILOTS</code>.
     */
    public final Pilots PILOTS = Pilots.PILOTS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("PUBLIC", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Airplanes.AIRPLANES,
            Airports.AIRPORTS,
            Countries.COUNTRIES,
            Flights.FLIGHTS,
            Pilots.PILOTS);
    }
}