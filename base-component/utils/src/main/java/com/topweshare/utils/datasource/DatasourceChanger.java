package com.topweshare.utils.datasource;


import org.aspectj.lang.JoinPoint;

/**
 * 数据源切换，根据指定数据源设置主从库
 * 
 * @author mongoding
 *
 */
public class DatasourceChanger {
	
	private String datasourceKey;
	
	private String findMethodPrefix;
	
	public DatasourceChanger(String datasourceKey, String findMethodPrefix) {
		super();
		this.datasourceKey = datasourceKey;
		this.findMethodPrefix = findMethodPrefix;
	}

	public void change(JoinPoint jp) {
		
		String methodName = jp.getSignature().getName();
			
		if (methodName.startsWith(findMethodPrefix)) {
			DatasourceHolder.setSlave(datasourceKey);
		} else {
			DatasourceHolder.setMaster(datasourceKey);
		}
	}

}
