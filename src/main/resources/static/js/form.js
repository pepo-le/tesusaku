const form = document.getElementById('testcase-form-body');

function createInput(name, type) {
	const input = document.createElement('input')
	
	input.setAttribute('name', name);
	input.setAttribute('type', type);
	
	return input;
}

function createTextarea(name) {
	const textarea = document.createElement('textarea')
	
	textarea.setAttribute('name', name);
	
	return textarea;
}

function createTd(inner) {
	const td = document.createElement('td');
	td.appendChild(inner);

	return td;
}

function createSelect(results) {
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

function createDeleteBtn() {
	const td = document.createElement('td');
	
	td.setAttribute('class', 'delete-btn');
	td.textContent = '✕';
	
	return td;
}

const add_btn = document.getElementById('add-btn');
add_btn.addEventListener('click', function() {

	const delete_btn = createDeleteBtn();
	const case_id = createInput('caseId', 'text');
	const case_name = createTextarea('caseName');
	const condition = createTextarea('condition');
	const process = createTextarea('process');
	const expect = createTextarea('expect');
	const check_date = createInput('checkDate', 'date');
	const check_ver = createInput('checkVer', 'text');
	const defect_no = createInput('defectNo', 'text');
	const tester = createInput('tester', 'text');
	const comment = createTextarea('comment');

	const RESULTS = ['', 'OK', 'NG', 'skip', 'pending', 'N/A'];
	const result = createSelect(RESULTS) ;

	case_id.classList.add('testcase-form__id');
	result.classList.add('testcase-form__result');
	check_date.classList.add('testcase-form__input-text');
	check_ver.classList.add('testcase-form__input-text');
	defect_no.classList.add('testcase-form__input-text');
	tester.classList.add('testcase-form__input-text');
	
	const tr = document.createElement('tr');
	tr.appendChild(delete_btn);
	tr.appendChild(createTd(case_id));
	tr.appendChild(createTd(case_name));
	tr.appendChild(createTd(condition));
	tr.appendChild(createTd(process));
	tr.appendChild(createTd(expect));
	tr.appendChild(createTd(result));
	tr.appendChild(createTd(check_date));
	tr.appendChild(createTd(check_ver));
	tr.appendChild(createTd(defect_no));
	tr.appendChild(createTd(tester));
	tr.appendChild(createTd(comment));
	form.appendChild(tr);
	
	delete_btn.addEventListener('click', function(e) {
		e.currentTarget.parentNode.remove();
	})
});

const e_del_btn = document.querySelectorAll('.e-delete-btn');
e_del_btn.forEach(function(item) {
	item.addEventListener('click', function(e) {
		e.currentTarget.parentNode.remove();
	});
});