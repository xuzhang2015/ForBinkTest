package test;

import bean.CouncilProperty;
import junit.framework.TestCase;
import model.Model;
import org.junit.Test;

import java.util.List;

/**
 * Created by zx on 05/07/2018.
 */
public class ModelTest extends TestCase {
    Model model;

    public void setUp() throws Exception {
        super.setUp();
        model = new Model();
    }

    public void tearDown() throws Exception {
    }


    @Test
    public void testGetTheDataFromCSV() {
        String path = "Mobile Phone Masts.csv";
        List<CouncilProperty> data = model.GetTheDataFromCSV(path);
        assertEquals(data.size(), 42);
    }

    @Test
    public void testAddNewRow() {
        String[] newRow = new String[]{"1", "", "", "", "", "", "", "28 Jan 2058", "28 Jan 2058", "1", "1000"};
        List<CouncilProperty> data = model.addNewRow(newRow);
        assertEquals(data.size(), 1);
        assertEquals(data.get(0).getCurrentRent(), 1000f);
    }

    @Test
    public void testGetTheSearchResult() {

        String path = "Mobile Phone Masts.csv";
        model.GetTheDataFromCSV(path);
        List<CouncilProperty> data = model.getTheSearchResult(928191600000l, 1188514800000l);
        assertEquals(data.size(), 5);
    }

    @Test
    public void testGetTheDateColumnIndex() {

        String path = "Mobile Phone Masts.csv";
        model.GetTheDataFromCSV(path);
        int index = model.getTheDateColumnIndex("Lease Start Date");
        assertEquals(index, 7);
    }

}