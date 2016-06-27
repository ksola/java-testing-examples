package bank.ocr.service;

import bank.ocr.batch.WatchDog;

public class MainBankOCRConverter {

    public static void main(String[] args) {
        if (args.length == 1) {
            
            WatchDog watchDog = new WatchDog(args[0]);
            watchDog.observe();
        }
        else {
            System.out.println("no file given");
        }
    }
    
}
