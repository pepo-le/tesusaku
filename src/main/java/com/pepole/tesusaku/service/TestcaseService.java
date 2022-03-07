package com.pepole.tesusaku.service;

import java.util.List;

import com.pepole.tesusaku.form.TestcaseForm;
import com.pepole.tesusaku.model.Testcase;

public interface TestcaseService {
    /** テストケース登録 */
    public void create(TestcaseForm testcaseForm, String path);

    /** テストケース複数件登録 */
    public void createBulk(TestcaseForm testcaseForm, String path);

    /** テストケース複数件取得 */
    public List<Testcase> selectBySuiteId(String path);
}
