package iPublisher;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//Manager parent for local and top managers
public abstract class Manager {

    //both contain a name and employee ID
    private StringProperty name;
    private StringProperty employeeID;
    private ObjectProperty<PublishingUnit> unit;

    //Manager constructor
    public Manager(String name, String employeeID){
        this.name = new SimpleStringProperty(name);
        this.employeeID = new SimpleStringProperty(employeeID);
    }

    //GETTERS AND SETTERS
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() {
        return this.name;
    }
    public String getName() { return this.name.get(); }


    public void setEmployeeID(String employeeID) { this.employeeID.set(employeeID); }
    public StringProperty employeeIDProperty() { return employeeID;  }
    public String getEmployeeID() {return employeeID.get(); }

    public PublishingUnit getUnit() {
        return unit.get();
    }

    public ObjectProperty<PublishingUnit> unitProperty() {
        return unit;
    }

    public void setUnit(PublishingUnit unit) {
        this.unit.set(unit);
    }
}
