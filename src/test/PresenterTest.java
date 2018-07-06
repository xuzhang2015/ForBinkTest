package test;

import bean.CouncilProperty;
import junit.framework.TestCase;
import model.Model;
import org.junit.Test;
import org.mockito.Mockito;
import presenter.Presenter;
import view.MyView;

import java.util.List;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by zx on 05/07/2018.
 */
public class PresenterTest extends TestCase {

    Model model;
    MyView myView;
    Presenter presenter;
    List<CouncilProperty> mockData;


    public void setUp() throws Exception {
        super.setUp();
        myView = Mockito.mock(MyView.class);
        model = Mockito.mock(Model.class);
        presenter = new Presenter(myView, model);
        //
        mockData = Mockito.mock(List.class);

    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testOpenFile() {
        when(model.openTheFileForPath()).thenReturn("a.csv");
        when(model.GetTheDataFromCSV(anyString())).thenReturn(mockData);
        presenter.OpenFile();
        verify(myView).showTable(mockData, true, null, true);
        verify(myView, never()).showMessage(anyString());

    }

    @Test
    public void testAddNewRow() {
        String[] newRow = new String[]{"1", "", "", "", "", "", "", "28 Jan 2058", "28 Jan 2058", "1", "1000"};
        when(model.addNewRow(newRow)).thenReturn(mockData);
        presenter.addNewRow(newRow);
        verify(myView).showTable(mockData, false, null, true);
        //
    }


    @Test
    public void testSortTheList() {
        when(model.sortTheList(anyBoolean())).thenReturn(mockData);
        presenter.sortTheList(anyBoolean());
        verify(myView).showTable(mockData, false, null, true);
    }

    @Test
    public void testSearchByDate() {
        when(model.getTheSearchResult(anyLong(), anyLong())).thenReturn(mockData);
        when(model.getTheDateColumnIndex("date")).thenReturn(1);
        presenter.searchByDate("12 Feb 2018", "12 Jan 2045");
        verify(myView).showTable(mockData, false, null, false);
        verify(myView, atLeast(2)).ChangeDateFormat(0);
    }
}