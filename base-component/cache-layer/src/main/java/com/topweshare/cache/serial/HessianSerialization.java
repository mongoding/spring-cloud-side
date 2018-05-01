package com.topweshare.cache.serial;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.google.common.base.Preconditions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Hessian序列化
 * 
 * @author mongoding
 *
 */
public class HessianSerialization implements ISerialization {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dangdang.cart.outer.service.cache.ISerialization#serialize(java.lang
	 * .Object)
	 */
	@Override
	public byte[] serialize(Object obj) throws IOException {
		Preconditions.checkNotNull(obj);
		byte[] results = null;

		HessianOutput ho = null;
		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
			ho = new HessianOutput(os);
			ho.writeObject(obj);
			results = os.toByteArray();
		} finally {
			if (ho != null)
				ho.close();
		}
		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dangdang.cart.outer.service.cache.ISerialization#deserialize(byte[])
	 */
	@Override
	public Object deserialize(byte[] by) throws IOException {
		Preconditions.checkNotNull(by);
		Object result = null;

		HessianInput hi = null;
		try (ByteArrayInputStream is = new ByteArrayInputStream(by)) {
			hi = new HessianInput(is);
			result = hi.readObject();
		} finally {
			if (hi != null)
				hi.close();
		}
		return result;
	}

}
