package org.k11techlab.framework.selenium.webuitestengine.dataproviderhelper;

import org.k11techlab.framework.selenium.webuitestengine.annotations.K11DataProvider;
import org.json.simple.JSONObject;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.k11techlab.framework.selenium.webuitestengine.configManager.ConfigurationManager.getBundle;

public class DataProviderUtil {

	public static void setDataProvider(ITestAnnotation testAnnotation, Method method) {
		if ((null != method) && null != method.getParameterTypes() && (method.getParameterTypes().length > 0)) {
			String dataProvider = testAnnotation.getDataProvider();
			boolean hasDataProvider = isNotBlank(dataProvider);

			// other than k11 data provider
			if (hasDataProvider && !dataProvider.startsWith(K11DataProvider.NAME)) {
				// keep actual data-provider details with description
				Map<String, String> desc = new HashMap<String, String>();
				desc.put("description", testAnnotation.getDescription());
				desc.put("dataProvider", testAnnotation.getDataProvider());
				Class<?> dpClass = testAnnotation.getDataProviderClass();
				if (null != dpClass) {
					desc.put("dataProviderClass", dpClass.getName());
				}
				testAnnotation.setDescription(new JSONObject(desc).toString());
			}

			boolean globalParallelSetting = getBundle().getBoolean("global.datadriven.parallel");
			boolean parallel = getBundle().getBoolean(method.getName() + ".parallel");
			dataProvider = parallel ? K11DataProvider.NAME_PARALLEL : K11DataProvider.NAME;

			testAnnotation.setDataProvider(dataProvider);
			testAnnotation.setDataProviderClass(K11DataProvider.params.valueOf("DATAPROVIDERCLASS").getClass());
		}
	}
}