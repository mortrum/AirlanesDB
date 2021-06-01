package com.example.airlinesdb.repository;

import com.example.airlinesdb.data.Airplane;
import com.example.airlinesdb.data.Pilot;
import com.example.airlinesdb.tables.Pilots;
import com.example.airlinesdb.tables.records.PilotsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SelectConditionStep;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PilotRepository implements CrudRepository<Pilot> {

    private final DSLContext dsl;

    @Override
    public void insert(Pilot object) {
        dsl.insertInto(Pilots.PILOTS)
                .values(object.getId(), object.getName(), object.getFlightHours())
                .returning()
                .fetchOne();
    }

    @Override
    public void update(Pilot object) {
        delete(object.getId());
        insert(object);
    }

    @Override
    public Pilot find(Integer id) {
        return dsl.selectFrom(Pilots.PILOTS)
                .where(Pilots.PILOTS.ID.eq(id))
                .fetchAny()
                .into(Pilot.class);
    }

    public List<Pilot> findAll(Condition condition) {
        List<Pilot> pilots = new ArrayList<>();
        dsl.selectFrom(Pilots.PILOTS)
                .where(condition)
                .fetchAny()
                .map( record -> {
                    Pilot pilot = record.into(Pilot.class);
                    pilots.add(pilot);
                    return pilot;
                });
        return pilots;
    }

    @Override
    public void delete(Integer id) {
        dsl.deleteFrom(Pilots.PILOTS)
                .where(Pilots.PILOTS.ID.eq(id));
    }
}
