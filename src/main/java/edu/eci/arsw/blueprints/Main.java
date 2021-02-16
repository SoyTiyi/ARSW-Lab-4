package edu.eci.arsw.blueprints;

import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

public class Main {

    public static void main(String a[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);
        Point[] points = new Point[]{new Point(20,20), new Point(10,10)};
        Blueprint blueprint = new Blueprint("Marx","El Capital",points);
        try {
            /* System.out.println("ahskjahksjaksjkajska"); */
            bs.addNewBlueprint(blueprint);
            /* System.out.println("-------------"); */
            Blueprint book =  bs.getBlueprint("Marx", "El Capital");
            List<Point> listPoint = book.getPoints();
            listPoint.get(0);
            System.out.println(listPoint.get(2).getY()+" "+listPoint.get(1).getY());
            System.out.println(book.getName());
            Set<Blueprint> set =  bs.getBlueprintsByAuthor("Marx");
            for(Blueprint bp: set){
                System.out.println(bp.getAuthor()+" "+bp.getName());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}