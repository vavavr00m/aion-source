package org.openaion.commons.scripting.metadata;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Method marked as {@link OnClassLoad} will be called when class was loaded by script.<br>
 * It's more useful alternative for
 * 
 * <pre>
 * static {
 * 	...
 * }
 * </pre>
 * 
 * block.<br>
 * <br>
 * Only static methods with no arguments can be marked with this annotation.<br>
 * 
 * This is only used if {@link org.openaion.commons.scripting.ScriptContext#getClassListener()} returns instance of
 * {@link org.openaion.commons.scripting.classlistener.DefaultClassListener} subclass.
 * 
 * @author SoulKeeper
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClassLoad
{
}
