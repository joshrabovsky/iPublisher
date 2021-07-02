package iPublisher;

import javafx.beans.property.*;

import java.util.List;

public class PublishingUnit {

    //Properties of publlishig unit
    private StringProperty name;
    private StringProperty address;
    private StringProperty region;
    private StringProperty contracts;
    private IntegerProperty id;
    private IntegerProperty numOfPublications;
    private ObjectProperty<LocalManager>  localManager;
    private ObjectProperty<Publisher> publisher;
    private ListProperty<Author> authors;

    //Constructor --> null items are not needed for this assignment.
    public PublishingUnit(int id, String name, String address, String region){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.region = new SimpleStringProperty(region);


        this.contracts = new SimpleStringProperty(null);
        this.numOfPublications = new SimpleIntegerProperty(0);
        this.localManager = new SimpleObjectProperty<>(null);
        this.publisher = new SimpleObjectProperty<>(null);
        this.authors = new SimpleListProperty<>();

    }

    //Ability to add to the list of authors
    public void addAuthor(Author author){
        authors.add(author);
    }

    //Return a list of others
    public ListProperty<Author> titlesProperty(){return authors;}
    public List<Author> getTitles(){return authors.get();}

    //Delete authors
    public void deleteAuthor(Author author){
        authors.remove(author);
    }

    //GETTERS AND SETTERS
    public void setId(int num) { id.set(num); }
    public IntegerProperty idProperty() {
        return id;
    }
    public int getId() {
        return id.get();
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

    public void setRegion(String region) {
        this.region.set(region);
    }
    public StringProperty regionProperty() {
        return region;
    }
    public String getRegion() {
        return region.get();
    }

    public void setContracts(String contracts) {
        this.contracts.set(contracts);
    }
    public StringProperty contractsProperty() {
        return contracts;
    }
    public String getContracts() {
        return contracts.get();
    }

    public void setNumOfPublications(int num) {
        numOfPublications.set(num);
    }
    public IntegerProperty numOfPublicationsProperty() {
        return numOfPublications;
    }
    public int getNumOfPublications() {
        return numOfPublications.get();
    }

    public void setLocalManager(LocalManager manager) { localManager.set(manager); }
    public ObjectProperty<LocalManager> localManagerProperty() {
        return localManager;
    }
    public LocalManager getLocalManager() {
        return localManager.get();
    }

    public void setPublisher(Publisher publisher) { this.publisher.set(publisher); }
    public ObjectProperty<Publisher> publisherProperty() {
        return publisher;
    }
    public Publisher getPublisher() {
        return publisher.get();
    }


}
