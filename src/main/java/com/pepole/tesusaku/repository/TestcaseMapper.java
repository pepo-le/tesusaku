package com.pepole.tesusaku.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pepole.tesusaku.model.Testcase;

@Mapper
public interface TestcaseMapper {
	
	// 登録
	public int insertOne(Testcase testcase);
	
	// 複数登録
	public int insertBulk(@Param("testcaseList")List<Testcase> testcaseList);

	// 複数取得
	public List<Testcase> selectBySuiteId(String path);
}
