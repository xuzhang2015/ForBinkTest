package test;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;
import presenter.Presenter;
import view.MyView;

import javax.swing.event.MenuEvent;
import java.awt.event.ActionEvent;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by zx on 06/07/2018.
 */
public class MyViewTest extends TestCase {
    MyView myView;
    Presenter presenter;


    public void setUp() throws Exception {
        super.setUp();
        myView = Mockito.spy(new MyView());
        presenter = Mockito.mock(Presenter.class);
        myView.setPresenter(presenter);

    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testSubmitButtonEvent() {
        ActionEvent e = new ActionEvent(myView, 0, "test action");
        e.setSource(myView.getButtonSubmit());
        myView.setIsOpenfile(true);
        myView.actionPerformed(e);
        verify(myView).actionPerformed(e);
        String[] newRow = new String[]{"", "", "", "", "", "", "", "", "", "0", "0"};
        verify(presenter).addNewRow(newRow);
    }

    @Test
    public void testButtonSearchEvent() {
        myView.getTextSearchStartDate().setText("12 Feb 2018");
        myView.getTextSearchEndDate().setText("12 Jan 2045");
        ActionEvent e = new ActionEvent(myView, 1, "test action");
        e.setSource(myView.getButtonSearch());
        myView.setIsOpenfile(true);
        myView.actionPerformed(e);
        verify(myView).actionPerformed(e);
        verify(presenter).searchByDate("12 Feb 2018", "12 Jan 2045");
        //
    }

    @Test
    public void testSortButton() {
        ActionEvent e = new ActionEvent(myView, 1, "test action");
        e.setSource(myView.getButtonAscending());
        myView.setIsOpenfile(true);
        myView.actionPerformed(e);
        verify(myView).actionPerformed(e);
        presenter.sortTheList(anyBoolean());
        //
        ActionEvent e1 = new ActionEvent(myView, 2, "test action");
        e1.setSource(myView.getButtonDescending());
        myView.setIsOpenfile(true);
        myView.actionPerformed(e1);
        verify(myView).actionPerformed(e1);
        presenter.sortTheList(anyBoolean());
    }

    @Test
    public void testMenuButton() {
        MenuEvent menuEvent = mock(MenuEvent.class);
        myView.menuSelected(menuEvent);
        verify(myView).menuSelected(menuEvent);
        verify(presenter).OpenFile();
    }


}