package com.pepole.tesusaku.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AssignMapper {
	// 登録
	public int insertOne(String suiteId, String userId);
}
