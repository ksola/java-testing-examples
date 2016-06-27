package bank.ocr.batch;

import java.util.HashMap;
import java.util.Map;

import bank.ocr.service.BankOCRConverter;
import bank.ocr.service.TransferNumberToCore;

public class SendAccountNumbersToCore {

    private BankOCRConverter bankOCRConverter;
    private Map<String, SendToBank> sendToBank = new HashMap<>();
    private DefaultBankSender defaultBankSender;
    private TransferNumberToCore transferNumberToCore;
    
    public SendAccountNumbersToCore(BankOCRConverter bankOCRConverter, TransferNumberToCore transferNumberToCore){
        this.bankOCRConverter = bankOCRConverter;
        this.transferNumberToCore = transferNumberToCore;
        defaultBankSender = new DefaultBankSender(transferNumberToCore);
        sendToBank.put("888", new BZWBKBankSender(transferNumberToCore));
        sendToBank.put("111", new DeutscheBankSender(transferNumberToCore));
        sendToBank.put("222", new MilleniumBankSender(transferNumberToCore));
    }
    
    
    public void sendAccountNumberToCore(char[][] ocrNumber) {
        String accountNumber = bankOCRConverter.converAndFormatOCR(ocrNumber);
        String prefix = accountNumber.substring(0, 3);
        if (sendToBank.containsKey(prefix)) {
            sendToBank.get(prefix).sendToBank(accountNumber);
        }
        else {
            defaultBankSender.sendToBank(accountNumber);
        }
    }
}
