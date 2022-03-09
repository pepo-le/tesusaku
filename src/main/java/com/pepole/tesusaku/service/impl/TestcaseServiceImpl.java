package com.pepole.tesusaku.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pepole.tesusaku.form.TestcaseForm;
import com.pepole.tesusaku.model.Testcase;
import com.pepole.tesusaku.repository.TestcaseMapper;
import com.pepole.tesusaku.service.TestcaseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TestcaseServiceImpl implements TestcaseService {
	
	private final TestcaseMapper mapper;

    /** テストケース1件作成 */
    @Override
    public void create(TestcaseForm testcaseForm, String path) {
		Testcase testcase = new Testcase();
    	
		testcase.setCaseId(testcaseForm.getCaseId()[0]);
		testcase.setCaseName(testcaseForm.getCaseName().length == 1 ? testcaseForm.getCaseName()[0] : "");
		testcase.setCondition(testcaseForm.getCondition().length == 1 ? testcaseForm.getCondition()[0] : "");
		testcase.setProcess(testcaseForm.getProcess().length == 1 ? testcaseForm.getProcess()[0] : "");
		testcase.setExpect(testcaseForm.getExpect().length == 1 ? testcaseForm.getExpect()[0] : "");
		testcase.setResult(testcaseForm.getResult().length == 1 ? testcaseForm.getResult()[0] : "");
		testcase.setCheckDate(testcaseForm.getCheckDate().length == 1 ? testcaseForm.getCheckDate()[0] : "9999-12-31");
		testcase.setCheckVer(testcaseForm.getCheckVer().length == 1 ? testcaseForm.getCheckVer()[0] : "");
		testcase.setDefectNo(testcaseForm.getDefectNo().length == 1 ? testcaseForm.getDefectNo()[0] : "");
		testcase.setTester(testcaseForm.getTester().length == 1 ? testcaseForm.getTester()[0] : "");
		testcase.setComment(testcaseForm.getComment().length == 1 ? testcaseForm.getComment()[0] : "");
		testcase.setTestsuiteId(path);


        mapper.insertOne(testcase);
    }

    /** テストケース複数件作成 */
    @Override
    public void createBulk(TestcaseForm testcaseForm, String path) {
		List<Testcase> testcaseList = new ArrayList<>();
		
		for (int i = 0; i < testcaseForm.getCaseId().length; i++) {
			Testcase testcase = new Testcase();

			testcase.setCaseId(testcaseForm.getCaseId()[i]);
			testcase.setCaseName(testcaseForm.getCaseName()[i]);
			testcase.setCondition(testcaseForm.getCondition()[i]);
			testcase.setProcess(testcaseForm.getProcess()[i]);
			testcase.setExpect(testcaseForm.getExpect()[i]);
			testcase.setResult(testcaseForm.getResult()[i]);
			if (testcaseForm.getCheckDate()[i] == "") {
				testcase.setCheckDate("9999-12-31");
			} else {
				testcase.setCheckDate(testcaseForm.getCheckDate()[i]);
			}
			testcase.setCheckVer(testcaseForm.getCheckVer()[i]);
			testcase.setDefectNo(testcaseForm.getDefectNo()[i]);
			testcase.setTester(testcaseForm.getTester()[i]);
			testcase.setComment(testcaseForm.getComment()[i]);
			testcase.setTestsuiteId(path);

			testcaseList.add(testcase);
		}

        mapper.insertBulk(testcaseList);
    }

    /** テストケース複数件編集 */
    @Transactional
    @Override
    public void editBulk(TestcaseForm testcaseForm, String path) {

    	/**
    	 * フォームデータの形式
    	 * 各項目がそれぞれ配列で格納されているのでテストケースに直でマッピングできない
    	 */
    	
    	// フォーム上にないテストケースを削除
    	List<Testcase> existingList = mapper.selectIdBySuiteId(path);
    	List<Testcase> deleteList = new ArrayList<Testcase>();
    	existingList.forEach(c -> { deleteList.add(c); });
    	if (deleteList.size() > 0) mapper.deleteAll(deleteList);
    
    	// 登録（更新）
		List<Testcase> testcaseList = new ArrayList<>();
		for (int i = 0; i < testcaseForm.getCaseId().length; i++) {
			Testcase testcase = new Testcase();

			testcase.setCaseId(testcaseForm.getCaseId()[i]);
			testcase.setCaseName(testcaseForm.getCaseName()[i]);
			testcase.setCondition(testcaseForm.getCondition()[i]);
			testcase.setProcess(testcaseForm.getProcess()[i]);
			testcase.setExpect(testcaseForm.getExpect()[i]);
			testcase.setResult(testcaseForm.getResult()[i]);
			if (testcaseForm.getCheckDate()[i] == "") {
				testcase.setCheckDate("9999-12-31");
			} else {
				testcase.setCheckDate(testcaseForm.getCheckDate()[i]);
			}
			testcase.setCheckVer(testcaseForm.getCheckVer()[i]);
			testcase.setDefectNo(testcaseForm.getDefectNo()[i]);
			testcase.setTester(testcaseForm.getTester()[i]);
			testcase.setComment(testcaseForm.getComment()[i]);
			testcase.setTestsuiteId(path);

			testcaseList.add(testcase);
		}

        mapper.insertBulk(testcaseList);
    }

    /** テストケース取得 */
    @Override
    public List<Testcase> selectBySuiteId(String path) {
    	return mapper.selectBySuiteId(path);
    }

    /** テストケースID取得 */
    @Override
    public List<Testcase> selectIdBySuiteId(String path) {
    	return mapper.selectIdBySuiteId(path);
    }
    
}