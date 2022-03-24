package com.pepole.tesusaku.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pepole.tesusaku.model.Testsuite;
import com.pepole.tesusaku.repository.AssignMapper;
import com.pepole.tesusaku.repository.TestsuiteMapper;
import com.pepole.tesusaku.service.TestsuiteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TestsuiteServiceImpl implements TestsuiteService {
	
	private final TestsuiteMapper testsuiteMapperr;
	
	private final AssignMapper assignMapper;

    /** テストスイート作成 */
	@Transactional
    @Override
    public void create(Testsuite suite, List<String> assignUsers) {
        testsuiteMapperr.insertOne(suite);

        assignUsers.add(suite.getAdminId());
        assignMapper.insertBulk(suite.getSuiteId(), assignUsers);
    }
    
    /** テストスイート一覧取得 */
    @Override
    public List<Testsuite> getSuiteList(String userId) {
        return testsuiteMapperr.findByUser(userId);
    }

    /** テストスイート取得 */
    @Override
    public Testsuite getSuite(int suiteId, String userId) {
        return testsuiteMapperr.findBySuiteidAndUserid(suiteId, userId);

    }

    /** テストスイート更新 */
	@Transactional
    @Override
    public void update(Testsuite suite, List<String> assignUsers) {
        testsuiteMapperr.updateBySuiteid(suite);

        assignUsers.add(suite.getAdminId());
        
        assignMapper.deleteBySuiteid(suite.getSuiteId());
        assignMapper.insertBulk(suite.getSuiteId(), assignUsers);
    }

    /** テストスイート削除 */
    public int deleteSuite(int suiteId, String userId) {
    	return testsuiteMapperr.deleteBySuiteidAndUserid(suiteId, userId);
    }
}
