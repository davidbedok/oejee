
package hu.qwaevisz.webstore.client.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetBasketSizeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetBasketSizeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BasketSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetBasketSizeResponse", propOrder = {
    "basketSize"
})
public class GetBasketSizeResponse {

    @XmlElement(name = "BasketSize")
    protected int basketSize;

    /**
     * Gets the value of the basketSize property.
     * 
     */
    public int getBasketSize() {
        return basketSize;
    }

    /**
     * Sets the value of the basketSize property.
     * 
     */
    public void setBasketSize(int value) {
        this.basketSize = value;
    }

}
