package com.ssm.service;

import java.util.List;

public interface TXService {
	public void tx();
	
	public void insertBatch(List<?> members, String sqlId);
}
