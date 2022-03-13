package com.pepole.tesusaku.service;

import java.util.List;

import com.pepole.tesusaku.form.TestcaseForm;
import com.pepole.tesusaku.model.Testcase;

public interface TestcaseService {
    /** テストケース複数件更新 */
    public void updateCases(TestcaseForm testcaseForm, String suiteId);

    /** テストケース複数件更新 */
    public void updateResult(TestcaseForm testcaseForm, String suiteId);

    /** テストケース複数件取得 */
    public List<Testcase> getBySuiteId(String suiteId);

    /** テストケース複数件取得 */
    public List<Testcase> getCaseidBySuiteId(String suiteId);
}
