package com.pepole.tesusaku.service;

import java.util.List;

import com.pepole.tesusaku.model.Testsuite;

public interface TestsuiteService {

    /** テストスイート登録 */
    public void create(Testsuite suite);
    
    /** テストスイート取得 */
    public List<Testsuite> getSuites();

}
