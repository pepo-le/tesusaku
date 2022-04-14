package com.pepole.tesusaku.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pepole.tesusaku.model.Testcase;

@Mapper
public interface TestcaseMapper {
	
	// 登録&更新
	public int upsertOne(Testcase testcase);
	
	// 複数登録&更新
	public int upsertBulk(@Param("testcaseList")List<Testcase> testcaseList);

	// 複数取得
	public List<Testcase> findBySuiteId(int suiteId);

	// 複数件ID取得
	public List<Testcase> findCaseidBySuiteId(int suiteId);

	// 複数件削除
	public int deleteBySuiteId(int suiteId);

	// 複数件削除
	public int deleteById(@Param("deleteList")List<Testcase> deleteList);

	// 実行結果更新
	public int updateResultOne(Testcase testcase);
	
	// 複数実行結果更新
	public int updateResultBulk(@Param("testcaseList")List<Testcase> testcaseList, int suiteId);
}