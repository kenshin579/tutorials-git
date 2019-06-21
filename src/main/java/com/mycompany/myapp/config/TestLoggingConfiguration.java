package com.mycompany.myapp.config;

import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jhipster.config.JHipsterProperties;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static io.github.jhipster.config.logging.LoggingUtils.*;

/*
 * Configures the console and Logstash log appenders from the app properties
 */
@Configuration
public class LoggingConfiguration {

	public LoggingConfiguration(
			@Value("${spring.application.name}") String appName,
			@Value("${server.port}") String serverPort,
			JHipsterProperties jHipsterProperties,
			ObjectMapper mapper) throws JsonProcessingException {

		Map<String, String> map = new HashMap<>();
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

		map.put("app_name", appName);
		String customFields2 = mapper.writeValueAsString(map);
		public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

		JHipsterProperties.Logging loggingProperties = jHipsterProperties.getLogging();
		JHipsterProperties.Logging.Logstash logstashProperties = loggingProperties.getLogstash();

		if (loggingProperties.isUseJsonFormat()) {
			addJsonConsoleAppender(context, customFields2);
		}
		if (logstashProperties.isEnabled()) {
			addLogstashTcpSocketAppender(context, customFields2, logstashProperties);
		}
		if (jHipsterProperties.getMetrics().getLogs().isEnabled()) {
			setMetricsMarkerLogbackFilter(context, loggingProperties.isUseJsonFormat());
		}
	}
}
