package iPublisher;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class TitlesPerAuthor extends JSONReport{

    //CONSTRUCTOR
    public TitlesPerAuthor(ObjectProperty<Manager> creator, StringProperty dateCreated, IntegerProperty reportId, StringProperty description) {
        super(creator, dateCreated, reportId, description);
    }

    @Override
    public void createReport() {
        //Todo
    }
}
