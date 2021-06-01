/*
 * This file is generated by jOOQ.
 */
package com.example.airlinesdb.tables.records;


import com.example.airlinesdb.tables.Airports;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AirportsRecord extends TableRecordImpl<AirportsRecord> implements Record3<Integer, String, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.AIRPORTS.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.AIRPORTS.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>PUBLIC.AIRPORTS.NAME</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.AIRPORTS.NAME</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>PUBLIC.AIRPORTS.COUNTRY_ID</code>.
     */
    public void setCountryId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.AIRPORTS.COUNTRY_ID</code>.
     */
    public Integer getCountryId() {
        return (Integer) get(2);
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Airports.AIRPORTS.ID;
    }

    @Override
    public Field<String> field2() {
        return Airports.AIRPORTS.NAME;
    }

    @Override
    public Field<Integer> field3() {
        return Airports.AIRPORTS.COUNTRY_ID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Integer component3() {
        return getCountryId();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public Integer value3() {
        return getCountryId();
    }

    @Override
    public AirportsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public AirportsRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public AirportsRecord value3(Integer value) {
        setCountryId(value);
        return this;
    }

    @Override
    public AirportsRecord values(Integer value1, String value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AirportsRecord
     */
    public AirportsRecord() {
        super(Airports.AIRPORTS);
    }

    /**
     * Create a detached, initialised AirportsRecord
     */
    public AirportsRecord(Integer id, String name, Integer countryId) {
        super(Airports.AIRPORTS);

        setId(id);
        setName(name);
        setCountryId(countryId);
    }
}
