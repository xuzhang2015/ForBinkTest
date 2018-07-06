package view;

import bean.CouncilProperty;
import presenter.Presenter;
import utilities.UtilitiesMethod;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by zx on 05/07/2018.
 * This view including the control panel to do sort and search function
 * the insert panel to insert a new row
 * menu button to open a new CSV file
 */
public class MyView implements MenuListener, ActionListener {
    private JFrame jf;
    private JMenu openFileMenu;
    private JTable table;
    private DefaultTableModel model;
    //
    private Boolean isOpenfile = false;// to check if the CSV file has been open or not


    //Insert panel
    private JLabel labelName;
    private JLabel labelAddress1;
    private JLabel labelAddress2;
    private JLabel labelAddress3;
    private JLabel labelAddress4;
    private JLabel labelUnitName;
    private JLabel labelTenantName;
    private JLabel labelLeaseStartDate;
    private JLabel labelLeaseEndDate;
    private JLabel labelLeaseYears;
    private JLabel labelCurrentRent;
    private JTextField textName;
    private JTextField textAddress1;
    private JTextField textAddress2;
    private JTextField textAddress3;
    private JTextField textAddress4;
    private JTextField textUnitName;
    private JTextField textTenantName;
    private JFormattedTextField textLeaseStartDate;
    private JFormattedTextField textLeaseEndDate;
    private JFormattedTextField textLeaseYears;
    private JFormattedTextField textCurrentRent;
    private JButton buttonSubmit;
    //
    //Control panel (Search and sort)
    private JButton buttonAscending;
    private JButton buttonDescending;
    //
    private JLabel labelSearchStartDate;
    private JLabel labelSearchEndDate;
    private JFormattedTextField textSearchStartDate;
    private JFormattedTextField textSearchEndDate;
    private JButton buttonSearch;


    private Presenter presenter;

    public MyView() {
        createUI();
    }

    //for test only
    public void setIsOpenfile(Boolean isOpenfile) {
        this.isOpenfile = isOpenfile;
    }

    public JButton getButtonSearch() {
        return buttonSearch;
    }

    public JFormattedTextField getTextSearchStartDate() {
        return textSearchStartDate;
    }

    public JFormattedTextField getTextSearchEndDate() {
        return textSearchEndDate;
    }

    public JButton getButtonSubmit() {
        return buttonSubmit;
    }

    public JButton getButtonAscending() {
        return buttonAscending;
    }

    public JButton getButtonDescending() {
        return buttonDescending;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }


    //main UI part
    private void createUI() {
        jf = new JFrame("Bink Test");
        jf.setLayout(new FlowLayout());
        //
        //Create menu and menu bar
        JMenuBar menuBar = new JMenuBar();
        openFileMenu = new JMenu("Open File");
        openFileMenu.addMenuListener(this);
        menuBar.add(openFileMenu);
        jf.setJMenuBar(menuBar);
        //
        CreateInsertPanel();
        CreateControlPanel();
        CreateTablePanel();
        //
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

    }

    //init table settings
    private void CreateTablePanel() {
        model = new DefaultTableModel();
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    //
    private void CreateControlPanel() {
        buttonAscending = new JButton("Ascending");
        buttonDescending = new JButton("Descending");
        //
        labelSearchStartDate = new JLabel("From: ");
        labelSearchEndDate = new JLabel("To: ");
        textSearchStartDate = new JFormattedTextField(UtilitiesMethod.sdf);
        textSearchStartDate.setColumns(10);
        textSearchStartDate.setToolTipText("Format: 05 Jul 2018");
        textSearchEndDate = new JFormattedTextField(UtilitiesMethod.sdf);
        textSearchEndDate.setColumns(10);
        textSearchEndDate.setToolTipText("Format: 05 Jul 2018");
        buttonSearch = new JButton("Search");
        //
        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        //
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(buttonAscending, constraints);
        constraints.gridx = 1;
        newPanel.add(buttonDescending, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelSearchStartDate, constraints);
        constraints.gridx = 1;
        newPanel.add(textSearchStartDate, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(labelSearchEndDate, constraints);
        constraints.gridx = 1;
        newPanel.add(textSearchEndDate, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonSearch, constraints);

        buttonSearch.addActionListener(this);
        buttonAscending.addActionListener(this);
        buttonDescending.addActionListener(this);
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Control Panel"));


        jf.add(newPanel);
    }

    //insert panel to insert new row
    private void CreateInsertPanel() {
        labelName = new JLabel("Property Name: ");
        labelAddress1 = new JLabel("Property Address [1]: ");
        labelAddress2 = new JLabel("Property Address [2]: ");
        labelAddress3 = new JLabel("Property Address [3]: ");
        labelAddress4 = new JLabel("Property Address [4]: ");
        labelUnitName = new JLabel("Unit Name: ");
        labelTenantName = new JLabel("Tenant Name: ");
        labelLeaseStartDate = new JLabel("Lease Start Date: ");
        labelLeaseEndDate = new JLabel("Lease End Date: ");
        labelLeaseYears = new JLabel("Lease Years: ");
        labelCurrentRent = new JLabel("Current Rent: ");
        //
        textName = new JTextField(10);
        textAddress1 = new JTextField(10);
        textAddress2 = new JTextField(10);
        textAddress3 = new JTextField(10);
        textAddress4 = new JTextField(10);
        textUnitName = new JTextField(10);
        textTenantName = new JTextField(10);
        buttonSubmit = new JButton("Submit");
        ////only accept the date format value
        textLeaseStartDate = new JFormattedTextField(UtilitiesMethod.sdf);
        textLeaseStartDate.setColumns(10);
        textLeaseStartDate.setToolTipText("Format: 05 Jul 2018");
        ////only accept the date format value
        textLeaseEndDate = new JFormattedTextField(UtilitiesMethod.sdf);
        textLeaseEndDate.setColumns(10);
        textLeaseEndDate.setToolTipText("Format: 05 Jul 2018");
        //
        textLeaseYears = new JFormattedTextField(0);
        textLeaseYears.setColumns(10);
        //only accept the integer value
        textLeaseYears.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {

            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = NumberFormat.getInstance();
                NumberFormatter formatter = new NumberFormatter(format);
                formatter.setValueClass(Integer.class);
                formatter.setMinimum(0);
                formatter.setMaximum(Integer.MAX_VALUE);
                formatter.setAllowsInvalid(false);
                // If you want the value to be committed on each keystroke instead of focus lost
                formatter.setCommitsOnValidEdit(true);
                return formatter;
            }
        });
        //only accept the float with 2 digital value
        textCurrentRent = new JFormattedTextField(0);
        textCurrentRent.setColumns(10);
        textCurrentRent.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setMinimum(0.00);
                formatter.setMaximum(1000000.00);
                return formatter;
            }
        });

        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        //
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelName, constraints);
        constraints.gridx = 1;
        newPanel.add(textName, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelAddress1, constraints);
        constraints.gridx = 1;
        newPanel.add(textAddress1, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(labelAddress2, constraints);
        constraints.gridx = 1;
        newPanel.add(textAddress2, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(labelAddress3, constraints);
        constraints.gridx = 1;
        newPanel.add(textAddress3, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 4;
        newPanel.add(labelAddress4, constraints);
        constraints.gridx = 1;
        newPanel.add(textAddress4, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 5;
        newPanel.add(labelUnitName, constraints);
        constraints.gridx = 1;
        newPanel.add(textUnitName, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 6;
        newPanel.add(labelTenantName, constraints);
        constraints.gridx = 1;
        newPanel.add(textTenantName, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 7;
        newPanel.add(labelLeaseStartDate, constraints);
        constraints.gridx = 1;
        newPanel.add(textLeaseStartDate, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 8;
        newPanel.add(labelLeaseEndDate, constraints);
        constraints.gridx = 1;
        newPanel.add(textLeaseEndDate, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 9;
        newPanel.add(labelLeaseYears, constraints);
        constraints.gridx = 1;
        newPanel.add(textLeaseYears, constraints);
        //
        constraints.gridx = 0;
        constraints.gridy = 10;
        newPanel.add(labelCurrentRent, constraints);
        constraints.gridx = 1;
        newPanel.add(textCurrentRent, constraints);

        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonSubmit, constraints);

        buttonSubmit.addActionListener(this::actionPerformed);
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Insert Panel"));
        jf.add(newPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isOpenfile) {
            showMessage("Please open the CSV file first!");
        } else {
            if (e.getSource() == buttonSubmit) {
                String[] item = new String[]{
                        textName.getText(),
                        textAddress1.getText(),
                        textAddress2.getText(),
                        textAddress3.getText(),
                        textAddress4.getText(),
                        textUnitName.getText(),
                        textTenantName.getText(),
                        textLeaseStartDate.getText(),
                        textLeaseEndDate.getText(),
                        String.valueOf(textLeaseYears.getValue()),
                        String.valueOf(textCurrentRent.getValue())
                };
                presenter.addNewRow(item);
            } else if (e.getSource() == buttonAscending) {
                presenter.sortTheList(true);
            } else if (e.getSource() == buttonDescending) {
                presenter.sortTheList(false);
            } else {
                if (textSearchStartDate.getText().equals("") || textSearchEndDate.getText().equals("")) {
                    showMessage("Please enter the start and end date for searching");
                } else {
                    presenter.searchByDate(textSearchStartDate.getText(), textSearchEndDate.getText());
                }
            }
        }
    }

    @Override
    public void menuSelected(MenuEvent e) {
        isOpenfile = false;
        presenter.OpenFile();
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(jf, message);
    }

    public void showTable(List<CouncilProperty> data, boolean isFirst, String[] columnTitle, boolean isShow5) {
        Vector<Vector> tableData = new Vector<Vector>();
        for (int i = 0; i < data.size(); i++) {
            if (i == 5 && isShow5) {
                break;
            }

            String[] items = data.get(i).getData();
            Vector<String> row = new Vector<String>();
            for (int j = 0; j < items.length; j++) {
                row.addElement(items[j]);
            }
            tableData.addElement(row);
        }
        Vector<String> columnNames = new Vector<String>();
        for (int i = 0; i < columnTitle.length; i++) {
            columnNames.addElement(columnTitle[i]);
        }
        columnNames.addElement("Total Rent");

        model.setDataVector(tableData, columnNames);
        model.fireTableStructureChanged();

        if (isFirst) {
            JScrollPane jsp = new JScrollPane(table);
            jf.add(jsp);
        }
        SwingUtilities.updateComponentTreeUI(table);
        isOpenfile = true;
    }

    //to update the date format from dd MMM yyyy to dd/MMM/yyyy int table
    public void ChangeDateFormat(int index) {
        TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {

                try {
                    long a = UtilitiesMethod.sdf.parse((String) value).getTime();
                    value = UtilitiesMethod.sdf2.format(new Date(a));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
            }
        };

        table.getColumnModel().getColumn(index).setCellRenderer(tableCellRenderer);
    }
}
