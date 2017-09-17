package hu.qwaevisz.maven;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplicationTest {

	@Test
	public void addNumbers() {
		final Application app = new Application();
		Assert.assertEquals(app.add(2, 3), 5);
	}

}
