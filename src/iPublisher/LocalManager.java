package iPublisher;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class LocalManager extends Manager{

    private ListProperty<JSONReport> localPublishingReports;

    //Local Manager extends manager, creates a local report
    public LocalManager(String name, String employeeID){

        super(name, employeeID);
        localPublishingReports = new SimpleListProperty<>();
    }

    public void createReport(){
        //TODO
        localPublishingReports.add(null);
    }

}
