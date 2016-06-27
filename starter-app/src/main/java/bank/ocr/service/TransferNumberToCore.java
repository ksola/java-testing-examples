package bank.ocr.service;

import bank.ocr.batch.MessageToCore;

public interface TransferNumberToCore {

    public void transferNumber(MessageToCore messageToCore);
}
