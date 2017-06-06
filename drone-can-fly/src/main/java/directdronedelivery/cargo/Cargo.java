package directdronedelivery.cargo;


public class Cargo {

    private Integer cargoID;
    private String cargoName;
    private String recipientName;
    private String recipientPhone;
    private String recipientEmail;

    public Integer getCargoID() {
        return cargoID;
    }

    public String getCargoName() {
        return cargoName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setCargoID(Integer cargoID) {
        this.cargoID = cargoID;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

}
