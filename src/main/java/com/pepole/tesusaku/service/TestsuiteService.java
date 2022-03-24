package com.pepole.tesusaku.service;

import java.util.List;

import com.pepole.tesusaku.model.Testsuite;

public interface TestsuiteService {

    /** テストスイート登録 */
    public void create(Testsuite suite, List<String> assignUsers);
    
    /** テストスイート一覧取得 */
    public List<Testsuite> getSuiteList(String userId);
    
    /** テストスイート取得 */
    public Testsuite getSuite(int suiteId, String userId);

    /** テストスイート更新 */
    public void update(Testsuite suite, List<String> assignUsers);

    /** テストスイート削除 */
    public int deleteSuite(int suiteId, String userId);
}
