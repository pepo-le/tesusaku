package com.pepole.tesusaku.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AssignMapper {
	// 登録
	public int insertOne(int suiteId, String userId);
	
	// 複数登録
	public int insertBulk(int suiteId, @Param("assignUsers") List<String> assignUsers);
	
	// 削除
	public int deleteBySuiteid(int suiteId);

	// 取得
	public List<String> findUserBySuiteid(int suiteId);
}
