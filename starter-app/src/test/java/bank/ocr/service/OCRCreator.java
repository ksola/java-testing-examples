package bank.ocr.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class OCRCreator {
    
    public char[][] readFromFileOCRSign(String fileName) {
        char[][] result = new char[3][];
        String line;
        try (
                InputStream resourceAsStream = OCRCreator.class.getResourceAsStream(fileName);
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
