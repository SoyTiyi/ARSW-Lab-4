package edu.eci.arsw.blueprints.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

/* @Service */
public class filterA implements filter {

    @Override
    public Set<Blueprint> filterBluePrints(Set<Blueprint> set) {
        Set<Blueprint> newSet =  new HashSet<Blueprint>();
        for(Blueprint bp: set){
            if(comparatePoints(bp.getPoints())){
                List<Point> newListPoint = new ArrayList<>();
                newListPoint.add(bp.getPoints().get(0));
                bp.setPoints(newListPoint);
                newSet.add(bp);
            } else {
                newSet.add(bp);
            }
        }
        return newSet;
    }

    private boolean comparatePoints(List<Point> listOne){
        if((listOne.get(0).getX() == listOne.get(1).getX()) && (listOne.get(0).getY() == listOne.get(1).getY())){
            return true;
        }
        return false;
    }

    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        if(comparatePoints(bp.getPoints())){
            List<Point> newListPoint = new ArrayList<>();
                newListPoint.add(bp.getPoints().get(0));
                bp.setPoints(newListPoint);
                return bp;
        }
        return bp;
    }
}
