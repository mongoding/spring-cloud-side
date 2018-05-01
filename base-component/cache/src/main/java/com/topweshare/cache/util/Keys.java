package com.topweshare.cache.util;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import java.util.Arrays;

public class Keys {

	private Object[] keys;

	private static final Joiner JOINER = Joiner.on('-');

	private Keys(Object... keys) {
		super();
		this.keys = Preconditions.checkNotNull(keys);
	}

	public static Keys of(Object... keys) {
		return new Keys(keys);
	}

	public String getJoinedKey() {
		return JOINER.join(keys);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(keys);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Keys other = (Keys) obj;
		if (!Arrays.equals(keys, other.keys))
			return false;
		return true;
	}

	public Object get(int i) {
		return keys[i];
	}

	public int getAsInt(int i) {
		return (int) get(i);
	}

	public long getAsLong(int i) {
		return (long) get(i);
	}

	public String getAsString(int i) {
		return (String) get(i);
	}

}
