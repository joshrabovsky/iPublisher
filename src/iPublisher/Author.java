package iPublisher;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Author {

    //Stating all the fields
    private IntegerProperty authorID;
    private StringProperty name;
    private StringProperty address;
    private StringProperty emailAddress;
    private ObjectProperty<PublishingUnit> affiliatedUnit;
    private IntegerProperty affiliatedUnitId;
    private ListProperty<Title> titles;

    //Constructor
    public Author(int authorID, String name, String address,
                  String emailAddress, int affiliatedUnitId){

        this.authorID = new SimpleIntegerProperty(authorID);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.emailAddress = new SimpleStringProperty(emailAddress);
        this.affiliatedUnitId = new SimpleIntegerProperty(affiliatedUnitId);

        //Not passed directly
        this.affiliatedUnit = new SimpleObjectProperty<>();
        this.titles = new SimpleListProperty<>();

    }

    //GETTERS AND SETTERS

    public void addTitle(Title title){
        titles.add(title);
    }

    public ListProperty<Title> titlesProperty(){return titles;}
    public List<Title> getTitles(){return titles.get();}

    public void deleteTitle(Title title){
        titles.remove(title);
    }

    public void setAuthorID(int authorID) {
        this.authorID.set(authorID);
    }
    public IntegerProperty authorIDProperty() {
        return authorID;
    }
    public int getAuthorID() {
        return authorID.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        return name;
    }
    public String getName() {
        return name.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
    public StringProperty addressProperty() {
        return address;
    }
    public String getAddress() {
        return address.get();
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
    }
    public StringProperty emailAddressProperty() {
        return emailAddress;
    }
    public String getEmailAddress() {
        return emailAddress.get();
    }

    public void setAffiliatedUnit(PublishingUnit unit) { affiliatedUnit.set(unit); }
    public ObjectProperty<PublishingUnit> affiliatedUnitProperty() {
        return affiliatedUnit;
    }
    public PublishingUnit getAffiliatedUnit() {
        return affiliatedUnit.get();
    }

    public void setAffiliatedUnitId(int id) {
        this.affiliatedUnitId.set(id);
    }
    public IntegerProperty affiliatedUnitIdProperty() {
        return affiliatedUnitId;
    }
    public int getAffiliatedUnitId() {
        return affiliatedUnitId.get();
    }


}
