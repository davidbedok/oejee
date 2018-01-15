package hu.qwaevisz.lottery.ejbservice.management;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

public class AnnotatedStandardMBean extends StandardMBean {

	private static final Map<String, Class<?>> PRIMITIVE_CLASSES = new HashMap<String, Class<?>>();
	private static final String ACCESSOR_PREFIX = "get";

	static {
		final Class<?>[] primitives = { byte.class, short.class, int.class, long.class, float.class, double.class, char.class, boolean.class, };
		for (final Class<?> clazz : primitives) {
			PRIMITIVE_CLASSES.put(clazz.getName(), clazz);
		}
	}

	public <T> AnnotatedStandardMBean(final T impl, final Class<T> mbeanInterface) throws NotCompliantMBeanException {
		super(impl, mbeanInterface);
	}

	protected AnnotatedStandardMBean(final Class<?> mbeanInterface) throws NotCompliantMBeanException {
		super(mbeanInterface);
	}

	private static Class<?> classForName(final String name, final ClassLoader loader) throws ClassNotFoundException {
		Class<?> c = PRIMITIVE_CLASSES.get(name);
		if (c == null) {
			c = Class.forName(name, false, loader);
		}
		return c;
	}

	@Override
	protected String getDescription(MBeanInfo info) {
		String result = info.getDescription();
		final Description description = this.getMBeanInterface().getAnnotation(Description.class);
		if (description != null) {
			result = description.value();
		}
		return result;
	}

	@Override
	protected String getDescription(final MBeanOperationInfo operation) {
		String result = operation.getDescription();
		final Method method = methodFor(this.getMBeanInterface(), operation);
		if (method != null) {
			final Description description = method.getAnnotation(Description.class);
			if (description != null) {
				result = description.value();
			}
		}
		return result;
	}

	private static Method methodFor(final Class<?> mbeanInterface, final MBeanOperationInfo operation) {
		final MBeanParameterInfo[] parameters = operation.getSignature();
		final String[] types = new String[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			types[i] = parameters[i].getType();
		}
		return findMethod(mbeanInterface, operation.getName(), types);
	}

	private static Method findMethod(final Class<?> mbeanInterface, final String name, final String... paramTypes) {
		try {
			final ClassLoader loader = mbeanInterface.getClassLoader();
			final Class<?>[] paramClasses = new Class<?>[paramTypes.length];
			for (int i = 0; i < paramTypes.length; i++) {
				paramClasses[i] = classForName(paramTypes[i], loader);
			}
			return mbeanInterface.getMethod(name, paramClasses);
		} catch (final RuntimeException e) {
			throw e;
		} catch (final Exception e) {
			return null;
		}
	}

	@Override
	protected String getParameterName(final MBeanOperationInfo operation, final MBeanParameterInfo parameter, final int paramIndex) {
		String name = parameter.getName();
		final Method method = methodFor(this.getMBeanInterface(), operation);
		if (method != null) {
			final PName pname = getParameterAnnotation(method, paramIndex, PName.class);
			if (pname != null) {
				name = pname.value();
			}
		}
		return name;
	}

	private static <A extends Annotation> A getParameterAnnotation(final Method method, final int paramIndex, final Class<A> annotation) {
		for (final Annotation a : method.getParameterAnnotations()[paramIndex]) {
			if (annotation.isInstance(a)) {
				return annotation.cast(a);
			}
		}
		return null;
	}

	@Override
	protected String getDescription(MBeanAttributeInfo attribute) {
		String result = attribute.getDescription();
		final Method method = methodFor(this.getMBeanInterface(), attribute);
		if (method != null) {
			final Description description = method.getAnnotation(Description.class);
			if (description != null) {
				result = description.value();
			}
		}
		return result;
	}

	private static Method methodFor(final Class<?> mbeanInterface, final MBeanAttributeInfo attribute) {
		String attributeName = attribute.getName();
		String accessorName = ACCESSOR_PREFIX + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
		return findMethod(mbeanInterface, accessorName);
	}

}
