package hu.qwaevisz.school.webservice;

import javax.ejb.Stateless;

import hu.qwaevisz.school.ejbservice.domain.NumberStub;

@Stateless
public class RoutingViaHeaderServiceBean implements RoutingViaHeaderService {

	@Override
	public NumberStub getNumberTextContentTypeJsonAccept() {
		return new NumberStub(1);
	}

	@Override
	public NumberStub getNumberTextContentTypeXmlAccept() {
		return new NumberStub(4);
	}

	@Override
	public NumberStub getNumberJsonContentType() {
		return new NumberStub(2);
	}

	@Override
	public NumberStub getNumberXmlContentType() {
		return new NumberStub(3);
	}

}
