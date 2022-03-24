var tesusaku = tesusaku || {};

(function (global) {
	tesusaku.caseform = document.getElementById('testcase-form-body');

	tesusaku.createInput = function (name, type) {
		const input = document.createElement('input')

		input.setAttribute('name', name);
		input.setAttribute('type', type);

		return input;
	}

	tesusaku.createTextarea = function (name) {
		const textarea = document.createElement('textarea')

		textarea.setAttribute('name', name);

		return textarea;
	}

	tesusaku.createTd = function (inner) {
		const td = document.createElement('td');
		td.appendChild(inner);

		return td;
	}

	tesusaku.createSelect = function (results) {
		const select = document.createElement('select');
		select.setAttribute('name', 'result');

		results.forEach(function(value, index) {
			const option = document.createElement('option');

			option.setAttribute('value', index);
			option.textContent = value;

			select.appendChild(option);
		});

		return select;
	}

	tesusaku.createDeleteBtn = function () {
		const td = document.createElement('td');

		td.setAttribute('class', 'delete-btn');
		td.textContent = '－';

		return td;
	}

	tesusaku.addBtn = document.getElementById('add-btn');
	tesusaku.addBtn.addEventListener('click', function() {
		const delete_btn = tesusaku.createDeleteBtn();
		const case_id = tesusaku.createInput('caseId', 'text');
		case_id.setAttribute("pattern", "[0-9]{1,3}");
		case_id.setAttribute("style", "ime-mode:disabled;");
		case_id.setAttribute("required", "true");
		const case_name = tesusaku.createTextarea('caseName');
		const condition = tesusaku.createTextarea('condition');
		const process = tesusaku.createTextarea('process');
		const expect = tesusaku.createTextarea('expect');
		const check_date = tesusaku.createInput('checkDate', 'date');
		const check_ver = tesusaku.createInput('checkVer', 'text');
		const defect_no = tesusaku.createInput('defectNo', 'text');
		const tester = tesusaku.createInput('tester', 'text');
		const comment = tesusaku.createTextarea('comment');

		const RESULTS = ['', 'OK', 'NG', 'skip', 'pending', 'N/A'];
		const result = tesusaku.createSelect(RESULTS) ;

		case_id.classList.add('testcase-form__id');
		result.classList.add('testcase-form__result');
		check_date.classList.add('testcase-form__input-text');
		check_ver.classList.add('testcase-form__input-text');
		defect_no.classList.add('testcase-form__input-text');
		tester.classList.add('testcase-form__input-text');

		const tr = document.createElement('tr');
		tr.appendChild(delete_btn);
		tr.appendChild(tesusaku.createTd(case_id));
		tr.appendChild(tesusaku.createTd(case_name));
		tr.appendChild(tesusaku.createTd(condition));
		tr.appendChild(tesusaku.createTd(process));
		tr.appendChild(tesusaku.createTd(expect));
		tr.appendChild(tesusaku.createTd(result));
		tr.appendChild(tesusaku.createTd(check_date));
		tr.appendChild(tesusaku.createTd(check_ver));
		tr.appendChild(tesusaku.createTd(defect_no));
		tr.appendChild(tesusaku.createTd(tester));
		tr.appendChild(tesusaku.createTd(comment));
		tesusaku.caseform.appendChild(tr);

		delete_btn.addEventListener('click', function(e) {
			e.currentTarget.parentNode.remove();
		})
	});

	tesusaku.eDelBtn = document.querySelectorAll('.e-delete-btn');
	tesusaku.eDelBtn.forEach(function(item) {
		item.addEventListener('click', function(e) {
			e.currentTarget.parentNode.remove();
		});
	});
}(this));