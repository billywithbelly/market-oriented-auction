package city;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.libs.In;
import utils.shortestPath.Path;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CityTest {

    private City vCity;
    private int sourceNode = 41;
    private int destinationNode = 2;
    private int distance = 3;
    private In in = new In("src/main/resources/v_city.txt");
    private Intersection intersection = new Intersection(sourceNode, sourceNode, destinationNode);

    @Before
    public void setUp() throws Exception {
        vCity = new City();
    }

    @After
    public void tearDown() throws Exception {
        vCity.clear();
    }

    @Test
    public void generateCity() throws Exception {
        vCity.clear();
        vCity.generateCity(in);
        assertNotNull(vCity);
        System.out.println("@Test - generateCity");

    }

    @Test
    public void clear() throws Exception {
        vCity.clear();
        assertNull(vCity.intersections);
        assertNull(vCity.dropoffPoints);
        assertNull(vCity.G);
        assertNull(vCity.sp);
        assertTrue(vCity.totalCalls == 0);
        assertTrue(vCity.totalPassengers == 0);
        System.out.println("@Test - clear");
    }

    @Test
    public void getShortestPaths() throws Exception {
        vCity.getShortestPaths(vCity.G, sourceNode);
        assertNotNull(vCity.sp);
        System.out.println("@Test - getShortestPaths");
    }

    @Test
    public void extractIntersections() throws Exception {
        vCity.extractIntersections(vCity.G);
        assertNotNull(vCity.intersections);
        System.out.println("@Test - extractIntersections");
    }

    @Test
    public void extractDropoffPoints() throws Exception {
        vCity.extractDropoffPoints(vCity.G);
        assertNotNull(vCity.dropoffPoints);
        assertTrue(vCity.intersections.size() < vCity.dropoffPoints.size());
        System.out.println("@Test - extractDropoffPoints");
    }

    @Test
    public void extendGraph() throws Exception {
        int n = vCity.G.V();
        vCity.clear();
        vCity.generateCity(in, 0);
        assertTrue(n >= vCity.G.V());
        System.out.println("@Test - extendGraph");
    }

    @Test
    public void getRoutes() throws Exception {
        vCity.getShortestPaths(vCity.G, sourceNode);
        ArrayList<Path> res = vCity.getRoutes(vCity.G, vCity.sp, sourceNode, distance);
        assertNotNull(res);
        System.out.println("@Test - getRoutes");
    }

    @Test
    public void printSP() throws Exception {
        vCity.getShortestPaths(vCity.G, sourceNode);
        System.out.println("\nPrinting SP Table");
        vCity.printSP(vCity.G, vCity.sp, sourceNode);
        System.out.println("@Test - printSP");
    }

    @Test
    public void printRoutes() throws Exception {
        vCity.getShortestPaths(vCity.G, sourceNode);
        ArrayList<Path> res = vCity.getRoutes(vCity.G, vCity.sp, sourceNode, distance);
        System.out.println("\nPrinting Routes");
        vCity.printRoutes(res);
        System.out.println("@Test - printRoutes");
    }

    @Test
    public void addPassenger() throws Exception {
        vCity.addPassenger(intersection);
        assertNotNull(vCity.passengerArrayList);
        assertTrue(vCity.passengerArrayList.size() > 0);
        System.out.println("@Test - addPassenger");
    }

    @Test
    public void setPassengerRoute() throws Exception {
        vCity.addPassenger(intersection);
        Passenger p = vCity.passengerArrayList.get(0);
        vCity.setPassengerRoute(p);
        assertNotNull(p.route);
        System.out.print(p);
        System.out.println("@Test - setPassengerRoute");
    }
}