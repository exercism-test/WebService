
package calculator;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 *
 */
@WebServiceClient (name = "CalculatorService", targetNamespace = "http://calculator/", wsdlLocation = "http://127.0.0.1:8080/services/Calculator?wsdl")
public class CalculatorService
        extends Service {

    private final static URL CALCULATORSERVICE_WSDL_LOCATION;
    private final static WebServiceException CALCULATORSERVICE_EXCEPTION;
    private final static QName CALCULATORSERVICE_QNAME = new QName("http://calculator/", "CalculatorService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
			url = new URL("http://127.0.0.1:8080/services/Calculator?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CALCULATORSERVICE_WSDL_LOCATION = url;
        CALCULATORSERVICE_EXCEPTION = e;
    }

    public CalculatorService () {
        super(__getWsdlLocation(), CALCULATORSERVICE_QNAME);
    }

    public CalculatorService (WebServiceFeature... features) {
        super(__getWsdlLocation(), CALCULATORSERVICE_QNAME, features);
    }

    public CalculatorService (URL wsdlLocation) {
        super(wsdlLocation, CALCULATORSERVICE_QNAME);
    }

    public CalculatorService (URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CALCULATORSERVICE_QNAME, features);
    }

    public CalculatorService (URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CalculatorService (URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation () {
        if (CALCULATORSERVICE_EXCEPTION != null) {
            throw CALCULATORSERVICE_EXCEPTION;
        }
        return CALCULATORSERVICE_WSDL_LOCATION;
    }

    /**
     * @return returns Calculator
     */
    @WebEndpoint (name = "CalculatorPort")
    public Calculator getCalculatorPort () {
        return super.getPort(new QName("http://calculator/", "CalculatorPort"), Calculator.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns Calculator
     */
    @WebEndpoint (name = "CalculatorPort")
    public Calculator getCalculatorPort (WebServiceFeature... features) {
        return super.getPort(new QName("http://calculator/", "CalculatorPort"), Calculator.class, features);
    }

}
