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
		const deleteBtn = tesusaku.createDeleteBtn();
		const caseId = tesusaku.createInput('caseId', 'text');
		caseId.setAttribute("pattern", "[0-9]{1,3}");
		caseId.setAttribute("style", "ime-mode:disabled;");
		caseId.setAttribute("required", "true");
		const caseName = tesusaku.createTextarea('caseName');
		const condition = tesusaku.createTextarea('condition');
		const process = tesusaku.createTextarea('process');
		const expect = tesusaku.createTextarea('expect');
		const checkDate = tesusaku.createInput('checkDate', 'date');
		const checkVer = tesusaku.createInput('checkVer', 'text');
		const defectNo = tesusaku.createInput('defectNo', 'text');
		const tester = tesusaku.createInput('tester', 'text');
		const comment = tesusaku.createTextarea('comment');

		const RESULTS = ['', 'OK', 'NG', 'skip', 'pending', 'N/A'];
		const result = tesusaku.createSelect(RESULTS) ;

		caseId.classList.add('testcase-form__id');
		result.classList.add('testcase-form__result');
		checkDate.classList.add('testcase-form__input-text');
		checkVer.classList.add('testcase-form__input-text');
		defectNo.classList.add('testcase-form__input-text');
		tester.classList.add('testcase-form__input-text');

		const tr = document.createElement('tr');
		tr.appendChild(deleteBtn);
		tr.appendChild(tesusaku.createTd(caseId));
		tr.appendChild(tesusaku.createTd(caseName));
		tr.appendChild(tesusaku.createTd(condition));
		tr.appendChild(tesusaku.createTd(process));
		tr.appendChild(tesusaku.createTd(expect));
		tr.appendChild(tesusaku.createTd(result));
		tr.appendChild(tesusaku.createTd(checkDate));
		tr.appendChild(tesusaku.createTd(checkVer));
		tr.appendChild(tesusaku.createTd(defectNo));
		tr.appendChild(tesusaku.createTd(tester));
		tr.appendChild(tesusaku.createTd(comment));
		tesusaku.caseform.appendChild(tr);

		deleteBtn.addEventListener('click', function(e) {
			e.currentTarget.parentNode.remove();
		})

		Array.from(tr.children).forEach(function(e) {
			// 削除ボタンのセルは子要素なし
			if (e.childElementCount == 0) return;

			e.addEventListener('input', function () {
				tesusaku.adjustHeight(tr);			
			});
		});
	});

	// 1行削除
	tesusaku.eDelBtn = document.querySelectorAll('.e-delete-btn');
	tesusaku.eDelBtn.forEach(function(item) {
		item.addEventListener('click', function(e) {
			e.currentTarget.parentNode.remove();
		});
	});
	
	// textareaの高さ調整	
	tesusaku.adjustHeight = function (testcase) {
		let maxHeight = 0;

		Array.from(testcase.children).forEach(function (t) {
			// 削除ボタンのセルは子要素なし
			if (t.childElementCount == 0) return;

			// t -> textarea, input の入れ子になっている
			const childNode = t.children[0];

			// textarea本来の高さを得るため
			childNode.style.height = 'auto';

			if (childNode.nodeName == 'TEXTAREA' && childNode.scrollHeight > maxHeight) {
				maxHeight = childNode.scrollHeight;
			}
		})
		
		Array.from(testcase.children).forEach(function (t) {
			// 削除ボタンのセルは子要素なし
			if (t.childElementCount == 0) return;

			// t -> textarea, input の入れ子になっている
			const childNode = t.children[0];

			childNode.style.height = maxHeight + 'px';
		});
	}
	
	tesusaku.textAreas = document.querySelectorAll('.testcase-form__textarea');
	tesusaku.textAreas.forEach(function(e) {
		e.addEventListener('input', function () {
			// tr -> td -> textarea の入れ子になっている
			tesusaku.adjustHeight(e.parentElement.parentElement);			
		});
	});
	
	window.onload = function() { 
		tesusaku.textAreas.forEach(function(e) {
			// tr -> td -> textarea の入れ子になっている
			tesusaku.adjustHeight(e.parentElement.parentElement);
		});
	}
}(this));