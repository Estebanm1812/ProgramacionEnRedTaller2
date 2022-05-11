package entity;

public class Order {

    private int id;

    private long creationDate;

    private int payed;

    private long payDate;

    private int userID;

    public Order(int id, long creationDate, int payed, long payDate, int userID) {
        this.id = id;
        this.creationDate = creationDate;
        this.payed = payed;
        this.payDate = payDate;
        this.userID = userID;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public int getPayed() {
        return payed;
    }

    public void setPayed(int payed) {
        this.payed = payed;
    }

    public long getPayDate() {
        return payDate;
    }

    public void setPayDate(long payDate) {
        this.payDate = payDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
