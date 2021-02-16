package edu.eci.arsw.blueprints.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
public class filterB implements filter {

    @Override
    public Set<Blueprint> filterBluePrints(Set<Blueprint> set) {
        boolean esteSi = true;
        Set<Blueprint> newSet = new HashSet<Blueprint>();
        for(Blueprint bp: set){
            if(esteSi){
                List<Point> newListPoint = new ArrayList<>();
                newListPoint.add(bp.getPoints().get(0));
                bp.setPoints(newListPoint);
                newSet.add(bp);
                esteSi = false;
            } else {
                newSet.add(bp);
                esteSi = true;
            }
        }
        return newSet;    
    }

    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
