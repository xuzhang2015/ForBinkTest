package test;

import bean.CouncilProperty;
import junit.framework.TestCase;
import org.junit.Test;
import utilities.UtilitiesMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zx on 04/07/2018.
 */
public class UtilitiesMethodTest extends TestCase {

    String ex1 = "12 Jan 2018";
    String ex2 = "demo.exe";
    List<CouncilProperty> data = new ArrayList<>();

    public void setUp() throws Exception {
        super.setUp();
        String[] item1 = new String[]{"1", "", "", "", "", "", "", "28 Jan 2058", "28 Jan 2058", "1", "1000"};
        String[] item2 = new String[]{"3", "", "", "", "", "", "", "28 Jan 2058", "28 Jan 2058", "1", "3000"};
        String[] item3 = new String[]{"2", "", "", "", "", "", "", "28 Jan 2058", "28 Jan 2058", "1", "2000"};
        CouncilProperty councilProperty1 = new CouncilProperty(item1);
        CouncilProperty councilProperty2 = new CouncilProperty(item2);
        CouncilProperty councilProperty3 = new CouncilProperty(item3);
        data.add(councilProperty1);
        data.add(councilProperty2);
        data.add(councilProperty3);
    }

    public void tearDown() throws Exception {
        data.clear();
        data = null;
    }

    @Test
    public void testGetTheDate() {
        assertNotSame(UtilitiesMethod.GetTheDate(ex1), 0l);
    }

    @Test
    public void testGetOrderByRent() {
        UtilitiesMethod.GetOrderByRent(data, true);
        assertEquals(data.get(0).getName(), "3");
        //
        UtilitiesMethod.GetOrderByRent(data, false);
        assertEquals(data.get(0).getName(), "1");
    }

    @Test
    public void testgetMyFileExtension() {
        assertEquals(UtilitiesMethod.getMyFileExtension(ex2), "exe");

    }
}