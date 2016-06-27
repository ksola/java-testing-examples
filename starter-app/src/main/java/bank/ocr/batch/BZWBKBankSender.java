package bank.ocr.batch;

import java.util.HashMap;
import java.util.Map;

import bank.ocr.service.TransferNumberToCore;

public class BZWBKBankSender extends AbstractBankSender {

    private Map<String, String> branchIdToBranchName = new HashMap<String, String>() {
        {
            put("857", "Wroclaw branch");
        }
    };

    public BZWBKBankSender(TransferNumberToCore transferNumberToCore) {
        super(transferNumberToCore);
    }

    
    private String determineBranchName(String accountNumber) {
        String branchId = accountNumber.substring(4, 7);
        return branchIdToBranchName.get(branchId);
    }

    @Override
    protected MessageToCore prepareMessage(String accountNumber) {
        String branchName = determineBranchName(accountNumber);
        boolean isMessageConfirmed = isAccountNumberConfirmed(accountNumber);
        MessageToCore messageToCore = new MessageToCore("BZ WBK", branchName, accountNumber, isMessageConfirmed);
        return messageToCore;
    }

}
