package bank.ocr.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import bank.ocr.service.BankOCRConverter;
import bank.ocr.service.DisplayMessageFromCore;
import bank.ocr.service.TransferNumberToCore;

public class WatchDog {
    
    private String observerPath;
    private SendAccountNumbersToCore sendAccountNumberToCore;
    
    public WatchDog(String observerPath) {
        this.observerPath = observerPath;
        TransferNumberToCore transferNumberToCore = new DisplayMessageFromCore();
        sendAccountNumberToCore = new SendAccountNumbersToCore(new BankOCRConverter(), transferNumberToCore);
    }
    
    public void observe() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            try {
                Files.walk(Paths.get(observerPath)).forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        char[][] ocrNumber = readFromFileOCRSign(filePath.toUri());
                        sendAccountNumberToCore.sendAccountNumberToCore(ocrNumber);
                        try {
                            Files.delete(filePath);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static char[][] readFromFileOCRSign(URI pathName) {
        char[][] result = new char[3][];
        String line;
        try (
                InputStream resourceAsStream = new FileInputStream(new File(pathName));
                InputStreamReader isr = new InputStreamReader(resourceAsStream, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(isr);) {
            int row = 0;
            while ((line = br.readLine()) != null) {
                result[row] = line.toCharArray();
                row++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
