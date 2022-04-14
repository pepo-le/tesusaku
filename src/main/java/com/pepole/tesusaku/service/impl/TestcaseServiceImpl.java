package com.pepole.tesusaku.service.impl;

import java.sql.Date;
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
	
	@Transactional
	@Override
    public void updateCases(TestcaseForm testcaseForm, int suiteId) {
    	/**
    	 * フォームデータの形式
    	 * 各項目がそれぞれ配列で格納されているのでテストケースに直でマッピングできない
    	 */

		if (testcaseForm.getCaseId().length == 1) {
			// テストケースを削除
			mapper.deleteBySuiteId(suiteId);

			// 登録
			Testcase testcase = new Testcase();
			testcase.setSuiteId(suiteId);
			testcase.setCaseId(Integer.parseInt(testcaseForm.getCaseId()[0]));
			testcase.setCaseName(testcaseForm.getCaseName().length == 1 ? testcaseForm.getCaseName()[0] : "");
			testcase.setCondition(testcaseForm.getCondition().length == 1 ? testcaseForm.getCondition()[0] : "");
			testcase.setProcess(testcaseForm.getProcess().length == 1 ? testcaseForm.getProcess()[0] : "");
			testcase.setExpect(testcaseForm.getExpect().length == 1 ? testcaseForm.getExpect()[0] : "");
			testcase.setResult(testcaseForm.getResult().length == 1 ? Integer.parseInt(testcaseForm.getResult()[0]) : 0);
			testcase.setCheckDate(testcaseForm.getCheckDate().length == 1 ? Date.valueOf(testcaseForm.getCheckDate()[0]) : Date.valueOf("9999-12-31"));
			testcase.setCheckVer(testcaseForm.getCheckVer().length == 1 ? testcaseForm.getCheckVer()[0] : "");
			testcase.setDefectNo(testcaseForm.getDefectNo().length == 1 ? testcaseForm.getDefectNo()[0] : "");
			testcase.setTester(testcaseForm.getTester().length == 1 ? testcaseForm.getTester()[0] : "");
			testcase.setComment(testcaseForm.getComment().length == 1 ? testcaseForm.getComment()[0] : "");

			mapper.upsertOne(testcase);
		} else {
			// フォーム上にないテストケースを削除
			List<Testcase> existingList = mapper.findCaseidBySuiteId(suiteId);
			List<Testcase> deleteList = new ArrayList<>();
			List<Integer> formIdList = new ArrayList<>(); 

			for (var caseId : testcaseForm.getCaseId()) {
				formIdList.add(Integer.parseInt(caseId));
			}
			
			existingList.forEach(c -> {
				if (! formIdList.contains(c.getCaseId())) deleteList.add(c);
			});

			if (deleteList.size() > 0) mapper.deleteById(deleteList);
		
			// 登録（更新）
			List<Testcase> testcaseList = new ArrayList<>();
			for (int i = 0; i < testcaseForm.getCaseId().length; i++) {
				Testcase testcase = new Testcase();

				testcase.setSuiteId(suiteId);
				testcase.setCaseId(Integer.parseInt(testcaseForm.getCaseId()[i]));
				testcase.setCaseName(testcaseForm.getCaseName()[i]);
				testcase.setCondition(testcaseForm.getCondition()[i]);
				testcase.setProcess(testcaseForm.getProcess()[i]);
				testcase.setExpect(testcaseForm.getExpect()[i]);
				testcase.setResult(Integer.parseInt(testcaseForm.getResult()[i]));
				if (testcaseForm.getCheckDate()[i] == "") {
					testcase.setCheckDate(Date.valueOf("9999-12-31"));
				} else {
					testcase.setCheckDate(Date.valueOf(testcaseForm.getCheckDate()[i]));
				}
				testcase.setCheckVer(testcaseForm.getCheckVer()[i]);
				testcase.setDefectNo(testcaseForm.getDefectNo()[i]);
				testcase.setTester(testcaseForm.getTester()[i]);
				testcase.setComment(testcaseForm.getComment()[i]);

				testcaseList.add(testcase);
			}

			mapper.upsertBulk(testcaseList);
		}
	}
	
	@Override
    public void updateResult(TestcaseForm testcaseForm, int suiteId) {
    	/**
    	 * フォームデータの形式
    	 * 各項目がそれぞれ配列で格納されているのでテストケースに直でマッピングできない
    	 */

		if (testcaseForm.getCaseId().length == 1) {
			Testcase testcase = new Testcase();
			
			testcase.setSuiteId(suiteId);
			testcase.setCaseId(Integer.parseInt(testcaseForm.getCaseId()[0]));
			testcase.setResult(testcaseForm.getResult().length == 1 ? Integer.parseInt(testcaseForm.getResult()[0]) : 0);
			testcase.setCheckDate(testcaseForm.getCheckDate().length == 1 ? Date.valueOf(testcaseForm.getCheckDate()[0]) : Date.valueOf("9999-12-31"));
			testcase.setCheckVer(testcaseForm.getCheckVer().length == 1 ? testcaseForm.getCheckVer()[0] : "");
			testcase.setDefectNo(testcaseForm.getDefectNo().length == 1 ? testcaseForm.getDefectNo()[0] : "");
			testcase.setTester(testcaseForm.getTester().length == 1 ? testcaseForm.getTester()[0] : "");
			testcase.setComment(testcaseForm.getComment().length == 1 ? testcaseForm.getComment()[0] : "");

			mapper.updateResultOne(testcase);
		} else {
			List<Testcase> testcaseList = new ArrayList<>();
			for (int i = 0; i < testcaseForm.getCaseId().length; i++) {
				Testcase testcase = new Testcase();

				testcase.setSuiteId(suiteId);
				testcase.setCaseId(Integer.parseInt(testcaseForm.getCaseId()[i]));
				testcase.setResult(Integer.parseInt(testcaseForm.getResult()[i]));
				if (testcaseForm.getCheckDate()[i] == "") {
					testcase.setCheckDate(Date.valueOf("9999-12-31"));
				} else {
					testcase.setCheckDate(Date.valueOf(testcaseForm.getCheckDate()[i]));
				}
				testcase.setCheckVer(testcaseForm.getCheckVer()[i]);
				testcase.setDefectNo(testcaseForm.getDefectNo()[i]);
				testcase.setTester(testcaseForm.getTester()[i]);
				testcase.setComment(testcaseForm.getComment()[i]);

				testcaseList.add(testcase);
			}

			mapper.updateResultBulk(testcaseList, suiteId);
		}
	}

    /** テストケース取得 */
    @Override
    public List<Testcase> getBySuiteId(int suiteId) {
    	return mapper.findBySuiteId(suiteId);
    }

    /** テストケースID取得 */
    @Override
    public List<Testcase> getCaseidBySuiteId(int suiteId) {
    	return mapper.findCaseidBySuiteId(suiteId);
    }
}