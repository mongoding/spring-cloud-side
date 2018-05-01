package com.topweshare.utils.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 数据源路由，实现数据源的动态切换
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return com.topweshare.utils.datasource.DatasourceHolder.getDatasourceKey();
	}

}
