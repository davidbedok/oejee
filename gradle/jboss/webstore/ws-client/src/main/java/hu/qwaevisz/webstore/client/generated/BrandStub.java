
package hu.qwaevisz.webstore.client.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for brandStub.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="brandStub">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PHILIPS"/>
 *     &lt;enumeration value="SONY"/>
 *     &lt;enumeration value="PANASONIC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "brandStub")
@XmlEnum
public enum BrandStub {

    PHILIPS,
    SONY,
    PANASONIC;

    public String value() {
        return name();
    }

    public static BrandStub fromValue(String v) {
        return valueOf(v);
    }

}
