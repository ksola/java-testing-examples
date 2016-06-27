package bank.ocr.service;

import java.util.HashMap;
import java.util.Map;

public class BankOCRConverter {
    
    private static final String ACCOUNT_NUMBER_SEPARATOR = " ";
    private static final Map<String, String> convereter = new HashMap<String, String>() {
        {
            put(" _ | ||_|", "0");
            put("     |  |", "1");
            put(" _  _||_ ", "2");
            put(" _  _| _|", "3");
            put("   |_|  |", "4");
            put(" _ |_  _|", "5");
            put(" _ |_ |_|", "6");
            put(" _   |  |", "7");
            put(" _ |_||_|", "8");
            put(" _ |_| _|", "9");
        }
    };
    
    public String converOCR(char[][] inputChars) {
        String result = "";
        for (int i = 0; i < 9; i++) {
            String number = convereter.get(readSignOnPoistion(inputChars, i));
            result += number != null ? number : "!";
        }
        return result;
    }
    
    public String converAndFormatOCR(char[][] inputChars) {
        String converOCR = converOCR(inputChars);
        if (isCheckSumCorrect(converOCR)) {
            return converOCR.substring(0, 3) + ACCOUNT_NUMBER_SEPARATOR + converOCR.substring(3, 6)
                    + ACCOUNT_NUMBER_SEPARATOR + converOCR.substring(6, 9) + "C";
        }
        return converOCR.substring(0, 3) + ACCOUNT_NUMBER_SEPARATOR + converOCR.substring(3, 6)
                + ACCOUNT_NUMBER_SEPARATOR + converOCR.substring(6, 9);
    }
    
    public boolean isCheckSumCorrect(String accountNumber) {
        if (accountNumber.contains("!")) {
            return false;
        }
        int sum = 0;
        for (int column = 0; column < accountNumber.length(); column++) {
            sum += (column + 1) * Integer.parseInt(String.valueOf(accountNumber.charAt(column)));
        }
        return sum % 11 == 0;
    }
    
    private String readSignOnPoistion(char[][] inputChars, int i) {
        int shift = i * 3;
        String string = Character.toString(inputChars[0][0 + shift]) + Character.toString(inputChars[0][1 + shift]) + Character.toString(inputChars[0][2 + shift]);
        string += Character.toString(inputChars[1][0 + shift]) + Character.toString(inputChars[1][1 + shift]) + Character.toString(inputChars[1][2 + shift]);
        string += Character.toString(inputChars[2][0 + shift]) + Character.toString(inputChars[2][1 + shift]) + Character.toString(inputChars[2][2 + shift]);
        return string;
    }
    
}
