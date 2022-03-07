var pepole = pepole || {};

(function (global) {
	pepole.form = document.getElementById('testcase-form-body'),

	pepole.createInput = function (name, type) {
		const input = document.createElement('input')
		
		input.setAttribute('name', name);
		input.setAttribute('type', type);
		
		return input;
	}

	pepole.createTextarea = function (name) {
		const textarea = document.createElement('textarea')
		
		textarea.setAttribute('name', name);
		
		return textarea;
	}

	pepole.createTd = function (inner) {
		const td = document.createElement('td');
		td.appendChild(inner);

		return td;
	}

	pepole.createSelect = function (results) {
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

	pepole.createDeleteBtn = function () {
		const td = document.createElement('td');
		
		td.setAttribute('class', 'delete-btn');
		td.textContent = '－';
		
		return td;
	}

	pepole.add_btn = document.getElementById('add-btn');

	pepole.add_btn.addEventListener('click', function() {

		const delete_btn = pepole.createDeleteBtn();
		const case_id = pepole.createInput('caseId', 'text');
		const case_name = pepole.createTextarea('caseName');
		const condition = pepole.createTextarea('condition');
		const process = pepole.createTextarea('process');
		const expect = pepole.createTextarea('expect');
		const check_date = pepole.createInput('checkDate', 'date');
		const check_ver = pepole.createInput('checkVer', 'text');
		const defect_no = pepole.createInput('defectNo', 'text');
		const tester = pepole.createInput('tester', 'text');
		const comment = pepole.createTextarea('comment');

		const RESULTS = ['', 'OK', 'NG', 'skip', 'pending', 'N/A'];
		const result = pepole.createSelect(RESULTS) ;

		case_id.classList.add('testcase-form__id');
		result.classList.add('testcase-form__result');
		check_date.classList.add('testcase-form__input-text');
		check_ver.classList.add('testcase-form__input-text');
		defect_no.classList.add('testcase-form__input-text');
		tester.classList.add('testcase-form__input-text');
		
		const tr = document.createElement('tr');
		tr.appendChild(delete_btn);
		tr.appendChild(pepole.createTd(case_id));
		tr.appendChild(pepole.createTd(case_name));
		tr.appendChild(pepole.createTd(condition));
		tr.appendChild(pepole.createTd(process));
		tr.appendChild(pepole.createTd(expect));
		tr.appendChild(pepole.createTd(result));
		tr.appendChild(pepole.createTd(check_date));
		tr.appendChild(pepole.createTd(check_ver));
		tr.appendChild(pepole.createTd(defect_no));
		tr.appendChild(pepole.createTd(tester));
		tr.appendChild(pepole.createTd(comment));
		pepole.form.appendChild(tr);
		
		delete_btn.addEventListener('click', function(e) {
			e.currentTarget.parentNode.remove();
		})
	});

	pepole.e_del_btn = document.querySelectorAll('.e-delete-btn');

	pepole.e_del_btn.forEach(function(item) {
		item.addEventListener('click', function(e) {
			e.currentTarget.parentNode.remove();
		});
	});
	
}(this));
