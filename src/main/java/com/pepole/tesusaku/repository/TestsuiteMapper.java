package com.pepole.tesusaku.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pepole.tesusaku.model.Testsuite;

@Mapper
public interface TestsuiteMapper {

	// 登録
	public int insertOne(Testsuite suite);

	// 取得
	public List<Testsuite> selectAll();
}