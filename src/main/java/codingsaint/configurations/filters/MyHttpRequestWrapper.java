package codingsaint.configurations.filters;

 
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

public class MyHttpRequestWrapper extends HttpServletRequestWrapper {
	private Map<String, String[]> escapedParametersValuesMap = new HashMap<String, String[]>();

	public MyHttpRequestWrapper(HttpServletRequest req) {
		super(req);
	}

	@Override
	public String getParameter(String name) {
		String[] escapedParameterValues = escapedParametersValuesMap.get(name);
		String escapedParameterValue = null;
		if (escapedParameterValues != null) {
			escapedParameterValue = escapedParameterValues[0];
		} else {
			String parameterValue = super.getParameter(name);

			// HTML transformation characters
			escapedParameterValue = org.springframework.web.util.HtmlUtils
					.htmlEscape(parameterValue);

			// SQL injection characters
			escapedParameterValue = StringEscapeUtils
					.escapeSql(escapedParameterValue);

			escapedParametersValuesMap.put(name,
					new String[] { escapedParameterValue });
		}// end-else

		return escapedParameterValue;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] escapedParameterValues = escapedParametersValuesMap.get(name);
		if (escapedParameterValues == null) {
			String[] parametersValues = super.getParameterValues(name);
			if (parametersValues != null) {
				escapedParameterValues = new String[parametersValues.length];

				//
				for (int i = 0; i < parametersValues.length; i++) {
					String parameterValue = parametersValues[i];
					String escapedParameterValue = parameterValue;

					// HTML transformation characters
					escapedParameterValue = org.springframework.web.util.HtmlUtils
							.htmlEscape(parameterValue);

					// SQL injection characters
					escapedParameterValue = StringEscapeUtils
							.escapeSql(escapedParameterValue);

					escapedParameterValues[i] = escapedParameterValue;
				}// end-for

				escapedParametersValuesMap.put(name, escapedParameterValues);
			}// end-else
		}

		return escapedParameterValues;
	}
}
