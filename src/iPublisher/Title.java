package iPublisher;

import javafx.beans.property.*;

public class Title {

    //Title attributes
    private IntegerProperty titleID;
    private StringProperty name;
    private StringProperty ISBN;
    private IntegerProperty editionNo;
    private StringProperty publicationDate;
    private IntegerProperty publicationQTY;
    private ObjectProperty<Author> author;
    private IntegerProperty authorID;
    private ObjectProperty<SalesInformation> salesInfo;

    //Getters and setters. Only use necessary fields and ones recommended in class
    public Title(int titleID, String name, String ISBN, int editionNo,
                 String publicationDate, int publicationQTY, int authorID){

        this.titleID = new SimpleIntegerProperty(titleID);
        this.name = new SimpleStringProperty(name);
        this.ISBN = new SimpleStringProperty(ISBN);
        this.editionNo = new SimpleIntegerProperty(editionNo);
        this.publicationDate = new SimpleStringProperty(publicationDate);
        this.publicationQTY = new SimpleIntegerProperty(publicationQTY);
        this.authorID = new SimpleIntegerProperty(authorID);

        this.author = new SimpleObjectProperty<>();
        this.salesInfo = new SimpleObjectProperty<>(null);

    }

    //Getters and setters
    public void setAuthorID(int authorID) {
        this.authorID.set(authorID);
    }
    public IntegerProperty authorIDProperty() {
        return authorID;
    }
    public int getAuthorID() {
        return authorID.get();
    }

    public void setTitleID(int titleID) {
        this.titleID.set(titleID);
    }
    public IntegerProperty titleIDProperty() {
        return titleID;
    }
    public int getTitleID() {
        return titleID.get();
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

    public void setISBN(String code) {
        ISBN.set(code);
    }
    public StringProperty ISBNProperty() {
        return ISBN;
    }
    public String getISBN() {
        return ISBN.get();
    }

    public void setEditionNo(int num) {
        editionNo.set(num);
    }
    public IntegerProperty editionNoProperty() {
        return editionNo;
    }
    public int getEditionNo() {
        return editionNo.get();
    }

    public void setPublicationDate(String date) {
        publicationDate.set(date);
    }
    public StringProperty publicationDateProperty() {
        return publicationDate;
    }
    public String getPublicationDate() {
        return publicationDate.get();
    }

    public void setPublicationQTY(int num) {
        publicationQTY.set(num);
    }
    public IntegerProperty publicationQTYProperty() {
        return publicationQTY;
    }
    public int getPublicationQTY() {
        return publicationQTY.get();
    }

    public void setAuthor(Author author) {
        this.author.set(author);
    }
    public ObjectProperty<Author> authorProperty() {
        return author;
    }
    public Author getAuthor() {
        return author.get();
    }

    public void setSalesInfo(SalesInformation salesInfo) {this.salesInfo.set(salesInfo);}
    public ObjectProperty<SalesInformation> salesInfoProperty(){return salesInfo;}
    public SalesInformation getSalesInfo () {return salesInfo.get();}


}
