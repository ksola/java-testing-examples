package bank.ocr.batch;

import bank.ocr.service.TransferNumberToCore;

public abstract class AbstractBankSender implements SendToBank {
    
    private TransferNumberToCore transferNumberToCore;

    public AbstractBankSender(TransferNumberToCore transferNumberToCore) {
        this.transferNumberToCore = transferNumberToCore;
    }

    @Override
    public void sendToBank(String accountNumber) {
        MessageToCore messageToCore = prepareMessage(accountNumber);
        transferNumberToCore.transferNumber(messageToCore);
    }

    protected abstract MessageToCore prepareMessage(String accountNumber);
    
    protected boolean isAccountNumberConfirmed(String accountNumber) {
        return accountNumber.endsWith("C");
    }
}
