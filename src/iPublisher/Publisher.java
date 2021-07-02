package iPublisher;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Publisher {

    //Publisher properties
    private StringProperty name;
    private StringProperty employeeID;
    private ObjectProperty<PublishingUnit> unit;

    //Constructor
    public Publisher(String name, String employeeID){
        this.name = new SimpleStringProperty(name);
        this.employeeID = new SimpleStringProperty(employeeID);
    }

    //Able to view local reports
    public void viewLocalReport(){
        //Todo
    }

    //GETTERS AND SETTERS
    public PublishingUnit getUnit() {
        return unit.get();
    }

    public ObjectProperty<PublishingUnit> unitProperty() {
        return unit;
    }

    public void setUnit(PublishingUnit unit) {
        this.unit.set(unit);
    }

    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() {
        return this.name;
    }
    public String getName() { return this.name.get(); }


    public void setEmployeeID(String employeeID) { this.employeeID.set(employeeID); }
    public StringProperty employeeIDProperty() { return employeeID;  }
    public String getEmployeeID() {return employeeID.get(); }

}
