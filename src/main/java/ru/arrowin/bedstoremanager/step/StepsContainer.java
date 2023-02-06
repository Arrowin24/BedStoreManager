package ru.arrowin.bedstoremanager.step;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StepsContainer {
    private final Map<Long, Step> steps = new HashMap<>();

/*
    public Step getStep(long id){
            return steps.getOrDefault(id, )
    }
*/

}
