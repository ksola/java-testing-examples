package bank.ocr.batch;

import java.util.HashMap;
import java.util.Map;

import bank.ocr.service.TransferNumberToCore;

public class DeutscheBankSender extends AbstractBankSender {

    private Map<String, String> branchIdToBranchName = new HashMap<String, String>() {
        {
            put("111", "Wroclaw branch");
        }
    };
    
    public DeutscheBankSender(TransferNumberToCore transferNumberToCore) {
        super(transferNumberToCore);
    }

    
    @Override
    protected MessageToCore prepareMessage(String accountNumber) {
        String branchName = determineBranchName(accountNumber);
        boolean isMessageConfirmed = isAccountNumberConfirmed(accountNumber);
        MessageToCore messageToCore = new MessageToCore("Deutsche Bank", branchName, accountNumber, isMessageConfirmed);
        return messageToCore;
    }
    
    private String determineBranchName(String accountNumber) {
        String branchId = accountNumber.substring(4, 7);
        return branchIdToBranchName.get(branchId);
    }
    

}

