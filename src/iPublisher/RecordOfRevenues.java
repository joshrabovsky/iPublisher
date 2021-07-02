package iPublisher;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class RecordOfRevenues {

    //Properties
    private IntegerProperty recordID;
    private ListProperty<StringProperty> revenues;
    private ObjectProperty<PublishingUnit> unit;

    //Constructor
    public RecordOfRevenues(IntegerProperty recordID, ListProperty<StringProperty> revenues) {
        this.recordID = recordID;
        this.revenues = revenues;
    }

    //GETTERS AND SETTERS
    public int getRecordID() {
        return recordID.get();
    }

    public IntegerProperty recordIDProperty() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID.set(recordID);
    }

    public ObservableList<StringProperty> getRevenues() {
        return revenues.get();
    }

    public ListProperty<StringProperty> revenuesProperty() {
        return revenues;
    }

    public void setRevenues(ObservableList<StringProperty> revenues) {
        this.revenues.set(revenues);
    }

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