package com.topweshare.utils.datasource;

/**
 * 配合RoutingDataSource做数据源的动态切换
 * datasourceKey：存放当前要使用的数据源的key
 */
public class DatasourceHolder {
	private static final ThreadLocal<String> datasourceKey = new ThreadLocal<String>();

	public static void setDatasourceKey(String key) {
		datasourceKey.set(key);
	}

	public static void setMaster(String datasource) {
		datasourceKey.set(datasource + "-master");
	}

	public static void setSlave(String datasource) {
		datasourceKey.set(datasource + "-slave1");
	}

	public static String getDatasourceKey() {
		return datasourceKey.get();
	}
}
