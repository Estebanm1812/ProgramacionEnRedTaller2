package model;

public class OrderChange {

    private int orderId;

    private String productName;

    private int quantity;

    public OrderChange(int orderId, String productName, int quantity) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public OrderChange() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
