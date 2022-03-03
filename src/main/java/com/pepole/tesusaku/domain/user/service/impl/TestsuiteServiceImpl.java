package com.pepole.tesusaku.domain.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pepole.tesusaku.domain.user.model.Testsuite;
import com.pepole.tesusaku.domain.user.service.TestsuiteService;
import com.pepole.tesusaku.repository.TestsuiteMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TestsuiteServiceImpl implements TestsuiteService {
	
	private final TestsuiteMapper mapper;

    /** テストスイート作成 */
    @Override
    public void create(Testsuite suite) {

        mapper.insertOne(suite);
    }
    
    /** テストスイート取得 */
    @Override
    public List<Testsuite> getSuites() {
        return mapper.selectAll();
    }


}
