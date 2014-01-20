package com.uaijug.certificado;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.uaijug.certificado.module.ControllerModule;
import com.uaijug.certificado.module.RepositoryModule;
import com.uaijug.certificado.test.AbstractIntegrationTest;
import com.uaijug.certificado.test.annotation.type.IntegrationTest;
import com.uaijug.certificado.test.module.RealTestPropertiesConfigModule;

@Category(IntegrationTest.class)
@RunWith(JukitoRunner.class)
@UseModules(value = { RealTestPropertiesConfigModule.class,
		RepositoryModule.class })
public class GeneratorTest extends AbstractIntegrationTest {

	@Test
	public void testExecute() throws Exception {
		Generator generator = new Generator();

		generator.init(new RealTestPropertiesConfigModule(),
				new RepositoryModule(), new ControllerModule());

		generator.execute();

	}
}
