package com.theta.poolman.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;


/**
 * <p>
 * General purpose utility methods related to processing a servlet request in
 * the Struts controller framework.
 * </p>
 * 
 * @version $Rev: 471754 $ $Date: 2006-11-06 08:55:09 -0600 (Mon, 06 Nov 2006) $
 */
public class RequestUtils {
	// ------------------------------------------------------- Static Variables

	/**
	 * <p>
	 * Commons Logging instance.
	 * </p>
	 */
	protected static Log log = LogFactory.getLog(RequestUtils.class);

	// --------------------------------------------------------- Public Methods
	/**
	 * <p>
	 * Populate the properties of the specified JavaBean from the specified HTTP
	 * request, based on matching each parameter name against the corresponding
	 * JavaBeans "property setter" methods in the bean's class. Suitable
	 * conversion is done for argument types as described under
	 * <code>convert()</code>.
	 * </p>
	 * 
	 * @param bean
	 *            The JavaBean whose properties are to be set
	 * @param request
	 *            The HTTP request whose parameters are to be used to populate
	 *            bean properties
	 * @throws ServletException
	 *             if an exception is thrown while setting property values
	 */
	public static void populate(Object bean, HttpServletRequest request)
			throws ServletException {
		populate(bean, null, null, request);
	}

	/**
	 * <p>
	 * Populate the properties of the specified JavaBean from the specified HTTP
	 * request, based on matching each parameter name (plus an optional prefix
	 * and/or suffix) against the corresponding JavaBeans "property setter"
	 * methods in the bean's class. Suitable conversion is done for argument
	 * types as described under <code>setProperties</code>.
	 * </p>
	 * 
	 * <p>
	 * If you specify a non-null <code>prefix</code> and a non-null
	 * <code>suffix</code>, the parameter name must match <strong>both</strong>
	 * conditions for its value(s) to be used in populating bean properties. If
	 * the request's content type is "multipart/form-data" and the method is
	 * "POST", the <code>HttpServletRequest</code> object will be wrapped in a
	 * <code>MultipartRequestWrapper</code object.</p>
	 *
	 * @param bean    The JavaBean whose properties are to be set
	 * @param prefix  The prefix (if any) to be prepend to bean property names
	 *                when looking for matching parameters
	 * @param suffix  The suffix (if any) to be appended to bean property
	 *                names when looking for matching parameters
	 * @param request The HTTP request whose parameters are to be used to
	 *                populate bean properties
	 * @throws ServletException if an exception is thrown while setting
	 *                          property values
	 */
	public static void populate(Object bean, String prefix, String suffix,
			HttpServletRequest request) throws ServletException {
		// Build a list of relevant request parameters from this request
		HashMap properties = new HashMap();
		// Iterator of parameter names
		Enumeration names = null;
		//String contentType = request.getContentType();
		String method = request.getMethod();
		boolean isMultipart = false;


		if (!isMultipart) {
			names = request.getParameterNames();

			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				String stripped = name;

				if (prefix != null) {
					if (!stripped.startsWith(prefix)) {
						continue;
					}

					stripped = stripped.substring(prefix.length());
				}

				if (suffix != null) {
					if (!stripped.endsWith(suffix)) {
						continue;
					}

					stripped = stripped.substring(0, stripped.length()
							- suffix.length());
				}

				Object parameterValue = null;

				parameterValue = request.getParameterValues(name);
				properties.put(stripped, parameterValue);
			}
		}
		// Set the corresponding properties of our bean
		try {
			BeanUtils.populate(bean, properties);
		} catch (Exception e) {
			throw new ServletException("BeanUtils.populate", e);
		}
	}
}
