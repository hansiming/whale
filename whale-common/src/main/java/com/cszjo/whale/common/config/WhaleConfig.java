package com.cszjo.whale.common.config;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.cszjo.whale.common.exception.ExceptionCode;
import com.cszjo.whale.common.exception.WhaleException;

public class WhaleConfig extends PropertiesConfiguration {

    private static final String WHALE_CONFIGURATION_FILE_NAME =
            "whale.properties";

    private static final String WHALE_SERVER_PORT = "whale.server.port";
    private static final int DEFAULT_WHALE_SERVER_PORT = 8077;



    // forbid to instance out of class
    private WhaleConfig() {

    }

    private static class WhaleConfigHolder {
        private static final WhaleConfig whaleConfig = init();

        private static WhaleConfig init() {

            Parameters parameters = new Parameters();
            FileBasedConfigurationBuilder builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>
                            (WhaleConfig.class).configure(
                                    parameters.properties()
                                            .setFileName(WHALE_CONFIGURATION_FILE_NAME));
            try {
                return (WhaleConfig) builder.getConfiguration();
            } catch (ConfigurationException e) {
                throw new WhaleException(ExceptionCode.START_FAILED,
                                         "load properties failed!");
            }
        }
    }

    public static WhaleConfig getInstance() {
        return WhaleConfigHolder.whaleConfig;
    }

    public int getWhaleServerProt() {
        return getInt(WHALE_SERVER_PORT, DEFAULT_WHALE_SERVER_PORT);
    }
}
