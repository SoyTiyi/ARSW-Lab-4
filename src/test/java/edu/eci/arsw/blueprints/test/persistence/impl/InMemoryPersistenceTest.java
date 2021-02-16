/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {

    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts0 = new Point[] { new Point(40, 40), new Point(15, 15) };
        Blueprint bp0 = new Blueprint("mack", "mypaint", pts0);

        ibpp.saveBlueprint(bp0);

        Point[] pts = new Point[] { new Point(0, 0), new Point(10, 10) };
        Blueprint bp = new Blueprint("john", "thepaint", pts);

        ibpp.saveBlueprint(bp);

        assertNotNull("Loading a previously stored blueprint returned null.",
                ibpp.getBlueprint(bp.getAuthor(), bp.getName()));

        assertEquals("Loading a previously stored blueprint returned a different blueprint.",
                ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);

    }

    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts = new Point[] { new Point(0, 0), new Point(10, 10) };
        Blueprint bp = new Blueprint("john", "thepaint", pts);

        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }

        Point[] pts2 = new Point[] { new Point(10, 10), new Point(20, 20) };
        Blueprint bp2 = new Blueprint("john", "thepaint", pts2);

        try {
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        } catch (BlueprintPersistenceException ex) {

        }

    }

    @Test
    public void testGetByAuthor() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);
        Point[] points = new Point[] { new Point(20, 20), new Point(10, 10) };
        Blueprint bp = new Blueprint("Marx", "El Capital", points);
        Blueprint bp2 = new Blueprint("Marx", "Manifiesto del partido comunista", points);
        try {
            bs.addNewBlueprint(bp);
            bs.addNewBlueprint(bp2);
            Set<Blueprint> set = bs.getBlueprintsByAuthor("Marx");
            assertEquals(2, set.size());
        } catch (Exception e) {
            fail("Error");
        }
    }

    @Test
    public void testGet() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);
        Point[] points = new Point[] { new Point(20, 20), new Point(10, 10) };
        Blueprint bp = new Blueprint("Marx", "El Capital", points);
        try {
            bs.addNewBlueprint(bp);
            Blueprint getBlue = bs.getBlueprint("Marx", "El Capital");
            assertEquals("Marx El Capital", getBlue.getAuthor() + " " + getBlue.getName());
        } catch (Exception e) {
            fail("Error");
        }
    }

    @Test
    public void testFilterA() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);
        Point[] points = new Point[] { new Point(20, 20), new Point(20, 20) };
        Blueprint bp = new Blueprint("Marx", "El Capital", points);
        Blueprint bp2 = new Blueprint("Marx", "Manifiesto del partido comunista", points);
        try {
            bs.addNewBlueprint(bp);
            bs.addNewBlueprint(bp2);
            Set<Blueprint> set = bs.getBlueprintsByAuthor("Marx");
            int countSize = 0;
            for (Blueprint blueprint : set) {
                if (blueprint.getPoints().size() == 1) {
                    countSize++;
                }
            }
            assertEquals(2, countSize);
        } catch (Exception e) {
            fail("Error");
        }
    }

    @Test
    public void testFilterB() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);
        Point[] points = new Point[] { new Point(20, 20), new Point(20, 20) };
        Blueprint bp = new Blueprint("Marx", "El Capital", points);
        Blueprint bp2 = new Blueprint("Marx", "Manifiesto del partido comunista", points);
        try {
            bs.addNewBlueprint(bp);
            bs.addNewBlueprint(bp2);
            Set<Blueprint> set = bs.getBlueprintsByAuthor("Marx");
            int countSize = 0;
            for (Blueprint blueprint : set) {
                if (blueprint.getPoints().size() == 1) {
                    countSize++;
                }
            }
            assertEquals(1, countSize);
        } catch (Exception e) {
            fail("Error");
        }
    }
}
