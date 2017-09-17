
package hu.qwaevisz.webstore.client.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListProductsByBrand complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListProductsByBrand">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Brand" type="{http://www.qwaevisz.hu/WebStore}brandStub" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListProductsByBrand", propOrder = {
    "brand"
})
public class ListProductsByBrand {

    @XmlElement(name = "Brand")
    @XmlSchemaType(name = "string")
    protected BrandStub brand;

    /**
     * Gets the value of the brand property.
     * 
     * @return
     *     possible object is
     *     {@link BrandStub }
     *     
     */
    public BrandStub getBrand() {
        return brand;
    }

    /**
     * Sets the value of the brand property.
     * 
     * @param value
     *     allowed object is
     *     {@link BrandStub }
     *     
     */
    public void setBrand(BrandStub value) {
        this.brand = value;
    }

}
