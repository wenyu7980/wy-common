package com.wenyu7980.data.jpa.generator;

import org.hibernate.jpa.spi.IdentifierGeneratorStrategyProvider;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wenyu
 */
public class IdGeneratorStrategyProvider implements IdentifierGeneratorStrategyProvider {
    @Override
    public Map<String, Class<?>> getStrategies() {
        Map<String, Class<?>> strategies = new HashMap<>(1);
        strategies.put("uuid32", UUIDGenerator.class);
        return strategies;
    }

}