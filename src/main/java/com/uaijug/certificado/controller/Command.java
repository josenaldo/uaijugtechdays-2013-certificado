package com.uaijug.certificado.controller;

import java.util.Map;

public interface Command {
	public void execute(Map<String, Object> params) throws Exception;
}
