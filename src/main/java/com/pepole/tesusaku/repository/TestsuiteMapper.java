package com.pepole.tesusaku.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pepole.tesusaku.model.Testsuite;

@Mapper
public interface TestsuiteMapper {

	// 登録
	public int insertOne(Testsuite suite);

	// 全取得
	public List<Testsuite> findAll();

	// 取得
	public List<Testsuite> findByUser(String userId);

	// 1件取得
	public Testsuite findBySuiteidAndUserid(int suiteId, String userId);

	// 更新
	public int updateBySuiteid(Testsuite suite);

	// 削除
	public int deleteBySuiteidAndUserid(int suiteId, String userId);
}
