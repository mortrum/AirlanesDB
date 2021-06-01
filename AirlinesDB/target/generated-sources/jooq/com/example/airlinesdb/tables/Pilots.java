/*
 * This file is generated by jOOQ.
 */
package com.example.airlinesdb.tables;


import com.example.airlinesdb.Public;
import com.example.airlinesdb.tables.records.PilotsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Pilots extends TableImpl<PilotsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PUBLIC.PILOTS</code>
     */
    public static final Pilots PILOTS = new Pilots();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PilotsRecord> getRecordType() {
        return PilotsRecord.class;
    }

    /**
     * The column <code>PUBLIC.PILOTS.ID</code>.
     */
    public final TableField<PilotsRecord, Integer> ID = createField(DSL.name("ID"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.PILOTS.NAME</code>.
     */
    public final TableField<PilotsRecord, String> NAME = createField(DSL.name("NAME"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.PILOTS.FLIGHT_HOURS</code>.
     */
    public final TableField<PilotsRecord, Integer> FLIGHT_HOURS = createField(DSL.name("FLIGHT_HOURS"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.PILOTS.AIRPLANE_ID</code>.
     */
    public final TableField<PilotsRecord, Integer> AIRPLANE_ID = createField(DSL.name("AIRPLANE_ID"), SQLDataType.INTEGER.nullable(false), this, "");

    private Pilots(Name alias, Table<PilotsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Pilots(Name alias, Table<PilotsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>PUBLIC.PILOTS</code> table reference
     */
    public Pilots(String alias) {
        this(DSL.name(alias), PILOTS);
    }

    /**
     * Create an aliased <code>PUBLIC.PILOTS</code> table reference
     */
    public Pilots(Name alias) {
        this(alias, PILOTS);
    }

    /**
     * Create a <code>PUBLIC.PILOTS</code> table reference
     */
    public Pilots() {
        this(DSL.name("PILOTS"), null);
    }

    public <O extends Record> Pilots(Table<O> child, ForeignKey<O, PilotsRecord> key) {
        super(child, key, PILOTS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Pilots as(String alias) {
        return new Pilots(DSL.name(alias), this);
    }

    @Override
    public Pilots as(Name alias) {
        return new Pilots(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Pilots rename(String name) {
        return new Pilots(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Pilots rename(Name name) {
        return new Pilots(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, Integer, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
