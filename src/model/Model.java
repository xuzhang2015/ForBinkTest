package model;

import bean.CouncilProperty;
import com.opencsv.CSVReader;
import utilities.UtilitiesMethod;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zx on 05/07/2018.
 * Model for read CSV and update the data
 */
public class Model {
    private List<CouncilProperty> data = new ArrayList<>(); //main data for table
    private String[] columnTitle = null; //record the column title for table

    public String[] getColumnTitle() {
        return columnTitle;
    }

    public List<CouncilProperty> getData() {
        return data;
    }

    //read data from the path
    public List<CouncilProperty> GetTheDataFromCSV(String path) {
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                CSVReader csvReader = new CSVReader(reader);
                List<String[]> records = csvReader.readAll();
                for (int i = 0; i < records.size(); i++) {
                    if (i == 0) columnTitle = records.get(i);//first line is the titles
                    else {
                        CouncilProperty councilProperty = new CouncilProperty(records.get(i));
                        data.add(councilProperty);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //sort the data as ascending order by default, true is ascending, false is descending
        UtilitiesMethod.GetOrderByRent(data, true);
        return data;
    }

    //read the csv from computer by using CSV reader
    public String openTheFileForPath() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            //
            if (UtilitiesMethod.getMyFileExtension(selectedFile.getName()).toLowerCase().equals("csv"))
                return selectedFile.getAbsolutePath();
            else return null;
        }
        return null;
    }

    //add new record into the data
    public List<CouncilProperty> addNewRow(String[] item) {
        CouncilProperty councilProperty = new CouncilProperty(item);
        data.add(councilProperty);
        return data;
    }

    //find the search result from the data
    public List<CouncilProperty> getTheSearchResult(long startDateLong, long endDateLong) {
        List<CouncilProperty> filterData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getLeaseStartDate() >= startDateLong && data.get(i).getLeaseStartDate() <= endDateLong) {
                filterData.add(data.get(i));
            }
        }
        //sort the data as ascending order by default, true is ascending, false is descending
        UtilitiesMethod.GetOrderByRent(filterData, true);
        return filterData;
    }

    //sort the data
    public List<CouncilProperty> sortTheList(boolean isAsc) {
        //sort the data as ascending order by default, true is ascending, false is descending
        UtilitiesMethod.GetOrderByRent(data, isAsc);
        return data;
    }


    //find the date field to update its format
    public int getTheDateColumnIndex(String field) {
        for (int i = 0; i < columnTitle.length; i++) {
            if (columnTitle[i].equals(field)) {
                return i;
            }
        }
        return -1;
    }
}
