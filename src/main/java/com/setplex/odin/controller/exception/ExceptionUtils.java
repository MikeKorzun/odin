package com.setplex.odin.controller.exception;

import static com.sun.deploy.uitoolkit.impl.fx.ui.resources.ResourceManager.getString;
import static java.lang.String.format;

/**
 * Utility class for exceptions with messages from message source.
 */
public final class ExceptionUtils {


    public static DataNotFoundException getDataNotFoundException(String messageCode, Object... params) {
        return new DataNotFoundException(getString(messageCode, params));
    }

    public static DataNotFoundException getDataNotFoundException(Class<?> entityClass, int entityId) {
        return new DataNotFoundException(format("%s with Id = %d is not found.", entityClass.getSimpleName(), entityId));
    }
}
