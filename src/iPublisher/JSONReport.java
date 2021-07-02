package iPublisher;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public abstract class JSONReport {

    //Properties of JSON report, parent class for all reports
    private ObjectProperty<Manager> creator;
    private StringProperty dateCreated;
    private IntegerProperty reportId;
    private StringProperty description;

    //CONSTRUCTOR
    public JSONReport(ObjectProperty<Manager> creator, StringProperty dateCreated, IntegerProperty reportId, StringProperty description) {
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.reportId = reportId;
        this.description = description;
    }

    //all reports must be able to create a report
    public abstract void createReport();

    //GETTERS AND SETTERS
    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Manager getCreator() {
        return creator.get();
    }

    public ObjectProperty<Manager> creatorProperty() {
        return creator;
    }

    public void setCreator(Manager creator) {
        this.creator.set(creator);
    }

    public String getDateCreated() {
        return dateCreated.get();
    }

    public StringProperty dateCreatedProperty() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated.set(dateCreated);
    }

    public int getReportId() {
        return reportId.get();
    }

    public IntegerProperty reportIdProperty() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId.set(reportId);
    }

}
