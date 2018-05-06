package com.topweshare.mybatis.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by mongoding on 2017/4/20.
 */
public interface AccountMapper2 {
   int update(@Param("money") double money, @Param("id") int id);
}
