package bank.ocr.service;

import static bank.ocr.service.MessageToCoreAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import bank.ocr.batch.MessageToCore;
import bank.ocr.batch.SendAccountNumbersToCore;

public class SendAccountNumbrsToCoreTest {
    
    private SendAccountNumbersToCore sendAccountNumbersToCore;
    private OCRCreator ocrCreator = new OCRCreator();
    private TransferNumberToCore transferNumberToCore;
    
    @Before
    public void before() {
        transferNumberToCore = Mockito.mock(TransferNumberToCore.class);
        sendAccountNumbersToCore = new SendAccountNumbersToCore(new BankOCRConverter(), transferNumberToCore);
    }
    
    @Test
    public void shouldSendAccountNumberToCoreFor888857100ForBZWBK() throws Exception {
        // given
        char[][] OCRSigns = ocrCreator.readFromFileOCRSign("888857100.txt");
        
        // when
        sendAccountNumbersToCore.sendAccountNumberToCore(OCRSigns);
        
        ArgumentCaptor<MessageToCore> argumentCaptor = ArgumentCaptor.forClass(MessageToCore.class);
        Mockito.verify(transferNumberToCore).transferNumber(argumentCaptor.capture());
        MessageToCore value = argumentCaptor.getValue();
        assertThat(value).hasBankName("BZ WBK")
                .hasBranchName("Wroclaw branch")
                .hasNumber("888 857 100C")
                .isConfirmed();
    }
    
    @Test
    public void shouldNotSendAccountNumberToCoreFor888888888ForBZWBK() throws Exception {
        // given
        char[][] OCRSigns = ocrCreator.readFromFileOCRSign("888888888.txt");
        
        // when
        sendAccountNumbersToCore.sendAccountNumberToCore(OCRSigns);
        
        ArgumentCaptor<MessageToCore> argumentCaptor = ArgumentCaptor.forClass(MessageToCore.class);
        Mockito.verify(transferNumberToCore).transferNumber(argumentCaptor.capture());
        MessageToCore value = argumentCaptor.getValue();
        assertThat(value).hasBankName("BZ WBK")
                .hasNoBranchName()
                .hasNumber("888 888 888")
                .isNotConfirmed();
    }
    
    @Test
    public void shouldNotSendAccountNumberToCoreFor111111111ForDeutscheBank() throws Exception {
        // given
        char[][] OCRSigns = ocrCreator.readFromFileOCRSign("111111111.txt");
        
        // when
        sendAccountNumbersToCore.sendAccountNumberToCore(OCRSigns);
        
        ArgumentCaptor<MessageToCore> argumentCaptor = ArgumentCaptor.forClass(MessageToCore.class);
        Mockito.verify(transferNumberToCore).transferNumber(argumentCaptor.capture());
        MessageToCore value = argumentCaptor.getValue();
        assertThat(value).hasBankName("Deutsche Bank")
                .hasBranchName("Wroclaw branch")
                .hasNumber("111 111 111")
                .isNotConfirmed();
    }
    
    @Test
    public void shouldSendAccountNumberToCoreFor111334211ForDeutscheBank() throws Exception {
        // given
        char[][] OCRSigns = ocrCreator.readFromFileOCRSign("111334211.txt");
        
        // when
        sendAccountNumbersToCore.sendAccountNumberToCore(OCRSigns);
        
        ArgumentCaptor<MessageToCore> argumentCaptor = ArgumentCaptor.forClass(MessageToCore.class);
        Mockito.verify(transferNumberToCore).transferNumber(argumentCaptor.capture());
        MessageToCore value = argumentCaptor.getValue();
        assertThat(value).hasBankName("Deutsche Bank")
                .hasNoBranchName()
                .hasNumber("111 334 211C")
                .isConfirmed();
    }
    
    @Test
    public void shouldSendAccountNumberToCoreFor222757200ForMilleniumBank() throws Exception {
        // given
        char[][] OCRSigns = ocrCreator.readFromFileOCRSign("222757200.txt");
        
        // when
        sendAccountNumbersToCore.sendAccountNumberToCore(OCRSigns);
        
        ArgumentCaptor<MessageToCore> argumentCaptor = ArgumentCaptor.forClass(MessageToCore.class);
        Mockito.verify(transferNumberToCore).transferNumber(argumentCaptor.capture());
        MessageToCore value = argumentCaptor.getValue();
        assertThat(value).hasBankName("Millenium")
                .hasBranchName("Warszawa branch")
                .hasNumber("222 757 200C")
                .isConfirmed();
    }
    
    @Test
    public void shouldSendAccountNumberToCoreFor222787300ForMilleniumBankAndFindBranchPoznan() throws Exception {
        // given
        char[][] OCRSigns = ocrCreator.readFromFileOCRSign("222787300.txt");
        
        // when
        sendAccountNumbersToCore.sendAccountNumberToCore(OCRSigns);
        
        ArgumentCaptor<MessageToCore> argumentCaptor = ArgumentCaptor.forClass(MessageToCore.class);
        Mockito.verify(transferNumberToCore).transferNumber(argumentCaptor.capture());
        MessageToCore value = argumentCaptor.getValue();
        assertThat(value).hasBankName("Millenium")
            .hasBranchName("Poznan branch")
            .hasNumber("222 787 300C")
            .isConfirmed();
    }
}
