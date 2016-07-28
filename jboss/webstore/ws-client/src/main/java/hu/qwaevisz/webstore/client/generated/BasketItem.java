
package hu.qwaevisz.webstore.client.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for basketItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="basketItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.qwaevisz.hu/WebStore}Product" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="quantity" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="total" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basketItem", propOrder = {
    "product"
})
public class BasketItem {

    @XmlElement(name = "Product", namespace = "http://www.qwaevisz.hu/WebStore")
    protected ProductStub product;
    @XmlAttribute(name = "quantity", required = true)
    protected int quantity;
    @XmlAttribute(name = "total", required = true)
    protected int total;

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link ProductStub }
     *     
     */
    public ProductStub getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductStub }
     *     
     */
    public void setProduct(ProductStub value) {
        this.product = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the total property.
     * 
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(int value) {
        this.total = value;
    }

}
