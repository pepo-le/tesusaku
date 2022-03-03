package com.pepole.tesusaku.domain.user.service;

import java.util.List;

import com.pepole.tesusaku.domain.user.model.Testcase;
import com.pepole.tesusaku.form.TestcaseForm;

public interface TestcaseService {
    /** テストケース登録 */
//    public void create(Testcase testcase);
    public void create(TestcaseForm testcaseForm, String path);

    /** テストケース複数件登録 */
    public void createBulk(TestcaseForm testcaseForm, String path);

    /** テストケース複数件取得 */
    public List<Testcase> selectBySuiteId(String path);
}
