package bank.ocr.service;

import bank.ocr.batch.MessageToCore;

public class DisplayMessageFromCore implements TransferNumberToCore {

    @Override
    public void transferNumber(MessageToCore messageToCore) {
        System.out.println(messageToCore);
    }

}
