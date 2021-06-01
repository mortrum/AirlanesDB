package com.example.airlinesdb.repository;

import com.example.airlinesdb.data.Airplane;
import com.example.airlinesdb.tables.Airplanes;
import com.example.airlinesdb.tables.Pilots;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class AirplaneRepository implements CrudRepository<Airplane>{

    private final DSLContext dsl;

    @Override
    public void insert(Airplane object) {
        dsl.insertInto(Airplanes.AIRPLANES)
                .values(object.getId())
                .returning()
                .fetchOne();
    }

    @Override
    public void update(Airplane object) {
        delete(object.getId());
        insert(object);
    }

    @Override
    public Airplane find(Integer id) {
        return dsl.selectFrom(Airplanes.AIRPLANES)
                .where(Airplanes.AIRPLANES.ID.eq(id))
                .fetchAny()
                .map(r -> {
                    Airplane airplane = r.into(Airplane.class);
                    airplane.setPilots(Repository.db.pilots.findAll(Pilots.PILOTS.AIRPLANE_ID.eq(airplane.getId())));
                    return airplane;
                });
    }

    public List<Airplane> findAll(Condition condition) {
        List<Airplane> airplanes = new ArrayList<>();
        dsl.selectFrom(Airplanes.AIRPLANES)
                .where(condition)
                .fetchAny()
                .map( record -> {
                    Airplane airplane = record.into(Airplane.class);
                    airplane.setPilots(Repository.db.pilots.findAll(Pilots.PILOTS.AIRPLANE_ID.eq(airplane.getId())));
                    airplanes.add(airplane);
                    return airplane;
                });
        return airplanes;
    }

    @Override
    public void delete(Integer id) {
        dsl.deleteFrom(Airplanes.AIRPLANES)
                .where(Airplanes.AIRPLANES.ID.eq(id));
    }

}
