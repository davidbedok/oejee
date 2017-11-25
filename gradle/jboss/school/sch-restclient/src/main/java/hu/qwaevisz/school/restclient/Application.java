package hu.qwaevisz.school.restclient;

import java.util.List;

import hu.qwaevisz.school.restclient.domain.MarkConditions;
import hu.qwaevisz.school.restclient.domain.MarkStub;

public class Application {

	private static final String HOST = "localhost";
	private static final int PORT = 8080;

	public static void main(String[] args) {
		final SchoolRestClient client = new SchoolRestClient(HOST, PORT);
		final List<MarkStub> marks = client.process("WI53085", new MarkConditions("Programming", 1, 3));
		for (MarkStub mark : marks) {
			System.out.println(mark.toString());
		}
	}

}
