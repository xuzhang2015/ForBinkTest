import model.Model;
import presenter.Presenter;
import view.MyView;

import javax.swing.*;

/**
 * Created by zx on 04/07/2018.
 */
public class CSVReader {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyView myView = new MyView();
            myView.setPresenter(new Presenter(myView, new Model()));
        });
    }
}

