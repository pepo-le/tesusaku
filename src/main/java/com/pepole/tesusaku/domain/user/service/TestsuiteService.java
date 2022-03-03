package com.pepole.tesusaku.domain.user.service;

import java.util.List;

import com.pepole.tesusaku.domain.user.model.Testsuite;

public interface TestsuiteService {

    /** テストスイート登録 */
    public void create(Testsuite suite);
    
    /** テストスイート取得 */
    public List<Testsuite> getSuites();

}
