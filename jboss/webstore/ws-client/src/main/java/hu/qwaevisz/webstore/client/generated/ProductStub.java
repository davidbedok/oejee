
package hu.qwaevisz.webstore.client.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productStub complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productStub">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="brand" type="{http://www.qwaevisz.hu/WebStore}brandStub" />
 *       &lt;attribute name="productName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="price" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productStub")
public class ProductStub {

    @XmlAttribute(name = "brand")
    protected BrandStub brand;
    @XmlAttribute(name = "productName")
    protected String productName;
    @XmlAttribute(name = "price", required = true)
    protected int price;

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

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(int value) {
        this.price = value;
    }

}
