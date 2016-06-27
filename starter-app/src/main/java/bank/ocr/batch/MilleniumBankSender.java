package bank.ocr.batch;

import java.util.HashMap;
import java.util.Map;

import bank.ocr.service.TransferNumberToCore;

public class MilleniumBankSender extends AbstractBankSender {

    private Map<String, String> branchIdToBranchName = new HashMap<String, String>() {
        {
            put("111", "Wroclaw branch");
            put("787", "Poznan branch");
        }
    };

    public MilleniumBankSender(TransferNumberToCore transferNumberToCore) {
        super(transferNumberToCore);
    }

    
    private String determineBranchName(String accountNumber) {
        String[] prefixes = accountNumber.split(" ");
        return branchIdToBranchName.containsKey(prefixes[1]) ? branchIdToBranchName.get(prefixes[1]) : "Warszawa branch";
    }

    @Override
    protected MessageToCore prepareMessage(String accountNumber) {
        String branchName = determineBranchName(accountNumber);
        boolean isMessageConfirmed = isAccountNumberConfirmed(accountNumber);
        MessageToCore messageToCore = new MessageToCore("Millenium", branchName, accountNumber, isMessageConfirmed);
        return messageToCore;
    }
    
}
