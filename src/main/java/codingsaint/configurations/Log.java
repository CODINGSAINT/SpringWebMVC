package codingsaint.configurations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <code>Log</code> : This Annotation has been created for Lazy Developers
 * Rather than writing everywhere <br>
 * <code>
 * private static Logger Logger=LoggerFactory(Clazz.class)
 * </code> <br>
 * We are going to replace it with<br>
 * <code>@Log<br>
 * private static Logger LOGGER
 * </code>
 * 
 * @author Kumar Pallav
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Log {

}
