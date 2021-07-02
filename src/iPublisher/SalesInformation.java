package iPublisher;

import javafx.beans.property.*;

public class SalesInformation {

    //Sales information attributes
    private IntegerProperty accountID;
    private DoubleProperty totalRevenue;
    private DoubleProperty marginPercent;
    private ObjectProperty<Title> title;

    //Attributes
    public SalesInformation(int accountID, double totalRevenue, double marginPercent){
        this.accountID = new SimpleIntegerProperty(accountID);
        this.totalRevenue = new SimpleDoubleProperty(totalRevenue);
        this.marginPercent = new SimpleDoubleProperty(marginPercent);
    }

    //Getters and setters
    public Title getTitle() {
        return title.get();
    }

    public ObjectProperty<Title> titleProperty() {
        return title;
    }

    public void setTitle(Title title) {
        this.title.set(title);
    }

    public void setAccountID(int id) {accountID.set(id);}
    public IntegerProperty accountIDProperty() {return accountID;}
    public int getAccountID() {return accountID.get();}

    public void setTotalRevenue(double num) {totalRevenue.set(num);}
    public DoubleProperty totalRevenueProperty() {return totalRevenue;}
    public double getTotalRevenue() {return totalRevenue.get();}

    public void setMarginPercent(double num) {marginPercent.set(num);}
    public DoubleProperty marginPercentProperty() {return marginPercent;}
    public double getMarginPercent() {return marginPercent.get();}

}
