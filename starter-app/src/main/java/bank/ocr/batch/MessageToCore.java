package bank.ocr.batch;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class MessageToCore {
    @Getter private String bankName;
    @Getter private String bankBranchName;
    @Getter private String number;
    @Getter private boolean isConfirmed;
    
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
