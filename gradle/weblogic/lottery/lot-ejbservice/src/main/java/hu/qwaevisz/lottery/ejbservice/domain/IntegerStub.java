package hu.qwaevisz.lottery.ejbservice.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IntegerStub {

    @XmlElement
    private int value;

    public IntegerStub() {
        this(0);
    }

    public IntegerStub( int value ) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
