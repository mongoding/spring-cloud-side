package com.topweshare.utils.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.topweshare.commons.EnumInterface;

public class EnumTypeHandlerForCode <E extends EnumInterface<E>> extends BaseTypeHandler<E> {

    private Class<E> type;
    private final E[] enums;

    public EnumTypeHandlerForCode(Class<E> type) {
        if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {

        ps.setInt(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            try {
            return	this.getEnumFromCode(i);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", ex);
            }
        }
       
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            try {
            	return	this.getEnumFromCode(i);
               
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", ex);
            }
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            try {
            	return	this.getEnumFromCode(i);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", ex);
            }
        }
    }
    private E getEnumFromCode(int code){
    	 for (int j=0; j<enums.length; j++){
             if (enums[j].getCode() == code){
                 return enums[j];
             }
         }
		return null;
    }

}
