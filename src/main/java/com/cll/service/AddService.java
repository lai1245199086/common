package com.cll.service;

import java.util.Set;

public interface AddService {
	public String setName(String key, String value);

	public String getName(String key);

	public long del(String key);

	public String incr(String key, String value);

	public Set<String> getAllKeys();

}
