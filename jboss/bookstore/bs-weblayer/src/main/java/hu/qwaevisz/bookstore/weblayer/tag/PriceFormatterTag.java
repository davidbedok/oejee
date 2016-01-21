package hu.qwaevisz.bookstore.weblayer.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.swing.text.NumberFormatter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PriceFormatterTag extends SimpleTagSupport {

    private static final String CURRENCY_SIGN = "Ft";

    private Double value;

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        NumberFormat formatter = new DecimalFormat("#");
        this.getJspContext().getOut().write(formatter.format(value) + " " + CURRENCY_SIGN);
    }

}
