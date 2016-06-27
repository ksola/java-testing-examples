package bank.ocr.batch;

import bank.ocr.service.TransferNumberToCore;

public class DefaultBankSender extends AbstractBankSender {

    public DefaultBankSender(TransferNumberToCore transferNumberToCore) {
        super(transferNumberToCore);
    }

    @Override
    protected MessageToCore prepareMessage(String accountNumber) {
        boolean isMessageConfirmed = isAccountNumberConfirmed(accountNumber);
        MessageToCore messageToCore = new MessageToCore("Golaya Bank", null, accountNumber, isMessageConfirmed);
        return messageToCore;
    }

}
