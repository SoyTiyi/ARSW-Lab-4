package edu.eci.arsw.blueprints.filter;

import java.util.Set;
import edu.eci.arsw.blueprints.model.Blueprint;

public interface filter {
    public Set<Blueprint> filterBluePrints(Set<Blueprint> set);
    public Blueprint filterBlueprint(Blueprint bp);
}
