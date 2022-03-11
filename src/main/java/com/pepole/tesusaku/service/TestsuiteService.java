package com.pepole.tesusaku.service;

import java.util.List;

import com.pepole.tesusaku.model.Testsuite;

public interface TestsuiteService {

    /** テストスイート登録 */
    public void create(Testsuite suite);
    
    /** テストスイート一覧取得 */
    public List<Testsuite> getSuiteList(String userId);

    /** テストスイート(+テストケース)取得 */
    public List<Testsuite> getCaseList(String userId, Testsuite suite);
}
