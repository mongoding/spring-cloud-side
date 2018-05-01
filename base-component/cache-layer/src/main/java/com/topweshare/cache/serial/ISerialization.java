package com.topweshare.cache.serial;

import java.io.IOException;

/**
 * 序列化行为定义
 * 
 * @author mongoding
 *
 */
public interface ISerialization {

	byte[] serialize(Object obj) throws IOException;

	Object deserialize(byte[] by) throws IOException;

}