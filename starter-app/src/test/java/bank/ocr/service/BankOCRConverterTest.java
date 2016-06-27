package bank.ocr.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BankOCRConverterTest {
    
    @Test
    public void shouldReadOnlyZeros() throws Exception {
        // given
        BankOCRConverter converter = new BankOCRConverter();
        OCRCreator ocrCreator = new OCRCreator();
        char[][] readFromFileOCRSign = ocrCreator.readFromFileOCRSign("nine_zeros.txt");
        
        // when
        String converOCR = converter.converOCR(readFromFileOCRSign);
        
        // then
        Assertions.assertThat(converOCR).isEqualTo("000000000");
    }
    
    @Test
    public void shouldReadOnlyOnes() throws Exception {
        // given
        BankOCRConverter converter = new BankOCRConverter();
        OCRCreator ocrCreator = new OCRCreator();
        char[][] readFromFileOCRSign = ocrCreator.readFromFileOCRSign("nine_ones.txt");
        
        // when
        String converOCR = converter.converOCR(readFromFileOCRSign);
        
        // then
        Assertions.assertThat(converOCR).isEqualTo("111111111");
    }
    
    @Test
    public void shouldReadOnlyTwos() throws Exception {
        // given
        BankOCRConverter converter = new BankOCRConverter();
        OCRCreator ocrCreator = new OCRCreator();
        char[][] readFromFileOCRSign = ocrCreator.readFromFileOCRSign("nine_twos.txt");
        
        // when
        String converOCR = converter.converOCR(readFromFileOCRSign);
        
        // then
        Assertions.assertThat(converOCR).isEqualTo("222222222");
    }
    
    @Test
    public void shouldReadOnlyThree() throws Exception {
        // given
        BankOCRConverter converter = new BankOCRConverter();
        OCRCreator ocrCreator = new OCRCreator();
        char[][] readFromFileOCRSign = ocrCreator.readFromFileOCRSign("nine_three.txt");
        
        // when
        String converOCR = converter.converOCR(readFromFileOCRSign);
        
        // then
        Assertions.assertThat(converOCR).isEqualTo("333333333");
    }
    
    @Test
    public void shouldReadAllNumbers() throws Exception {
        // given
        BankOCRConverter converter = new BankOCRConverter();
        OCRCreator ocrCreator = new OCRCreator();
        char[][] readFromFileOCRSign = ocrCreator.readFromFileOCRSign("all_numbers.txt");
        
        // when
        String converOCR = converter.converOCR(readFromFileOCRSign);
        
        // then
        Assertions.assertThat(converOCR).isEqualTo("123456789");
    }
    
    @Test
    public void shouldCheckSumBeCorrectFor888857100() throws Exception {
        // given
        BankOCRConverter converter = new BankOCRConverter();
        
        // when
        boolean result = converter.isCheckSumCorrect("888857100");
        
        // then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldCheckSumBeNotCorrectFor888888888() throws Exception {
        // given
        BankOCRConverter converter = new BankOCRConverter();
        
        // when
        boolean result = converter.isCheckSumCorrect("888888888");
        
        // then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldFormatAccountNumber888886888() throws Exception {
        // given
        BankOCRConverter converter = new BankOCRConverter();
        OCRCreator ocrCreator = new OCRCreator();
        char[][] readFromFileOCRSign = ocrCreator.readFromFileOCRSign("888886888.txt");
        
        // when
        String accountNumber = converter.converAndFormatOCR(readFromFileOCRSign);
        
        // then
        Assertions.assertThat(accountNumber).isEqualTo("888 886 888");
    }
    
}
