package com.topweshare.commons;

/**
 * Created by mongoding on 2016/11/23.
 */
public interface EnumInterface<E> {
	/**
	 * 从枚举获取要存进数据库的业务码
	 * @return 业务码
	 */
     int getCode();
   

}
