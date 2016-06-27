package bank.ocr.service;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import bank.ocr.batch.MessageToCore;

public class MessageToCoreAssert extends AbstractAssert<MessageToCoreAssert, MessageToCore> {
    
    protected MessageToCoreAssert(MessageToCore actual) {
        super(actual, MessageToCoreAssert.class);
    }
    
    public static MessageToCoreAssert assertThat(MessageToCore actual) {
        return new MessageToCoreAssert(actual);
    }
    
    public MessageToCoreAssert hasBankName(String bankName) {
        Assertions.assertThat(actual.getBankName()).isEqualTo(bankName);
        return this;
    }
    
    public MessageToCoreAssert hasBranchName(String banchName) {
        Assertions.assertThat(actual.getBankBranchName()).isEqualTo(banchName);
        return this;
    }
    
    public MessageToCoreAssert hasNoBranchName() {
        Assertions.assertThat(actual.getBankBranchName()).isNull();
        return this;
    }
    
    public MessageToCoreAssert hasNumber(String accountNumber) {
        Assertions.assertThat(actual.getNumber()).isEqualTo(accountNumber);
        return this;
    }
    
    public MessageToCoreAssert isConfirmed() {
        Assertions.assertThat(actual.isConfirmed()).isTrue();
        return this;
    }
    
    public MessageToCoreAssert isNotConfirmed() {
        Assertions.assertThat(actual.isConfirmed()).isFalse();
        return this;
    }

}
