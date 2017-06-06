package bank.ocr.batch;

public class MessageToCore {
    private String bankName;
    private String bankBranchName;
    private String number;
    private boolean isConfirmed;

    public MessageToCore(String bankName, String bankBranchName, String number, boolean isConfirmed) {
        this.bankName = bankName;
        this.bankBranchName = bankBranchName;
        this.number = number;
        this.isConfirmed = isConfirmed;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public String getNumber() {
        return number;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    @Override
    public String toString() {
        if (isConfirmed) {
            return "Account Number " + number + " is send to core for Bank " + bankName
                    + ((bankBranchName != null) ? " and branch " + bankBranchName : "");
        }
        else {
            return "Account Number " + number + " have to be checked for Bank " + bankName
                    + ((bankBranchName != null) ? " and branch " + bankBranchName : "");
        }
        
    }
}
