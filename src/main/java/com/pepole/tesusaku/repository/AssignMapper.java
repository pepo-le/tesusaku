package com.pepole.tesusaku.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AssignMapper {
	// 登録
	public int insertOne(String suiteId, String userId);
	
	// 複数登録
	public int insertBulk(String suiteId, @Param("assignUsers") List<String> assignUsers);
	
	// 取得
	public List<String> findUserBySuiteid(String suiteId);
}
