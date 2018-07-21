package io.ascending.training.api.v1;

import com.fasterxml.jackson.databind.MapperFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public static final ThreadLocal<Class<?>> jsonViewClassLocal = new ThreadLocal<>();

    public static final ThreadLocal<MapperFeature> jsonViewMapperFeatureLocal = new ThreadLocal<>();


    protected void setJsonViewClass(final Class<?> jsonViewClass) {
        jsonViewClassLocal.set(jsonViewClass);
    }

    protected void disableMapperFeature_DEFAULT_VIEW_INCLUSION() {
        jsonViewMapperFeatureLocal.set(MapperFeature.DEFAULT_VIEW_INCLUSION);
    }
}
