package presenter;

import model.Model;
import utilities.UtilitiesMethod;
import view.MyView;

/**
 * Created by zx on 05/07/2018.
 * this is presenter
 * including function:
 * OpenFile
 * addNewRow
 * sortTheList
 * searchByDate
 */
public class Presenter {
    private MyView myView;
    private Model model;

    //
    public Presenter(MyView myView, Model model) {
        this.myView = myView;
        this.model = model;
    }

    //open the CSV file and update the table
    public void OpenFile() {
        String path = model.openTheFileForPath();
        if (path != null) {
            myView.showTable(model.GetTheDataFromCSV(path), true, model.getColumnTitle(), true);
        } else {
            myView.showMessage("Please choose CSV type file");
        }
    }

    //add new row and update the table
    public void addNewRow(String[] item) {
        myView.showTable(model.addNewRow(item), false, model.getColumnTitle(), true);
    }

    //sort and update the table
    public void sortTheList(boolean isAsc) {
        myView.showTable(model.sortTheList(isAsc), false, model.getColumnTitle(), true);
    }

    //search and update the table
    public void searchByDate(String startDate, String endDate) {
        try {
            //covert string to long for sort
            long startDateLong = UtilitiesMethod.GetTheDate(startDate);
            long endDateLong = UtilitiesMethod.GetTheDate(endDate);

            myView.showTable(model.getTheSearchResult(startDateLong, endDateLong), false, model.getColumnTitle(), false);
            //change the date format in table
            myView.ChangeDateFormat(model.getTheDateColumnIndex("Lease Start Date"));
            myView.ChangeDateFormat(model.getTheDateColumnIndex("Lease End Date"));
        } catch (Exception e) {
            e.printStackTrace();
            myView.showMessage("Inner Error appear!, please try it again!");
        }
    }
}
