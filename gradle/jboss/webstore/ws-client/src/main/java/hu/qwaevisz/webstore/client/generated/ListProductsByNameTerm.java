
package hu.qwaevisz.webstore.client.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListProductsByNameTerm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListProductsByNameTerm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NameTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListProductsByNameTerm", propOrder = {
    "nameTerm"
})
public class ListProductsByNameTerm {

    @XmlElement(name = "NameTerm")
    protected String nameTerm;

    /**
     * Gets the value of the nameTerm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameTerm() {
        return nameTerm;
    }

    /**
     * Sets the value of the nameTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameTerm(String value) {
        this.nameTerm = value;
    }

}
