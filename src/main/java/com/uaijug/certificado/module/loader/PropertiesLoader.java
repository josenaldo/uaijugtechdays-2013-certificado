package com.uaijug.certificado.module.loader;

import java.io.IOException;
import java.util.Properties;

public interface PropertiesLoader {
	Properties loadProperties(String path) throws IOException;
}
