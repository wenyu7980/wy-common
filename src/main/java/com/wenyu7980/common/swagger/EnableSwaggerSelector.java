package com.wenyu7980.common.swagger;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 *
 * @author wenyu
 */
public class EnableSwaggerSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[] { SwaggerConfig.class.getName() };
    }
}
