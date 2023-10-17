const pathdeApp = "ssmcrud";

let totalRecord, totalPages, currentPage;

let searchName;

// ページの読み込み後、ajaxリクエストを送信してページ情報を取得する
$(document).ready(function() {
	toSelectedPg(1, searchName);
});

// クリックしたページ番号に転送する
function toSelectedPg(pageNum, searchName) {
	$.ajax({
		url: pathdeApp + '/city',
		data: {
			'pageNum': pageNum,
			'keyword': searchName
		},
		type: 'GET',
		success: function(result) {
			buildCityTable(result);
			buildPageInfos(result);
			buildPageNavi(result);
		}
	})
}

// データを分析して表示する
function buildCityTable(result) {
	// 元のテーブルを空にする
	$("#cityTableBody").empty();
	let index = result.extend.pageInfo.records;
	$.each(index, (index, item) => {
		let cityName = item.name;
		let nationName = item.nation;
		let districtName = item.district;
		let languageName = item.language;
		let idTd = $("<th scope='row' class='text-center' style='width:70px;vertical-align:bottom;'></th>").append(item.id);
		let nameTd;
		if (cityName.length >= 15) {
			nameTd = $("<td class='text-center' style='width:120px;font-size:10px;vertical-align:bottom;'></td>").append(cityName);
		} else {
			nameTd = $("<td class='text-center' style='width:120px;font-size:15px;vertical-align:bottom;'></td>").append(cityName);
		}
		let continentTd = $("<td class='text-center' style='width:100px;vertical-align:bottom;'></td>").append(item.continent);
		let nationTd;
		if (nationName.length > 12 && nationName.length <= 15) {
			nationTd = $("<td class='text-center' style='width:100px;font-size:12px;vertical-align:bottom;'></td>").append(nationName);
		} else if (nationName.length > 15) {
			nationTd = $("<td class='text-center' style='width:100px;font-size:10px;vertical-align:bottom;'></td>").append(nationName);
		} else {
			nationTd = $("<td class='text-center' style='width:100px;font-size:15px;vertical-align:bottom;'></td>").append(nationName);
		}
		let districtTd;
		if (districtName.length >= 15) {
			districtTd = $("<td class='text-center' style='width:120px;font-size:10px;vertical-align:bottom;'></td>").append(districtName);
		} else {
			districtTd = $("<td class='text-center' style='width:120px;font-size:15px;vertical-align:bottom'></td>").append(districtName);
		}
		let populationTd = $("<td class='text-center' style='width:70px;vertical-align:bottom;'></td>").append(item.population);
		let languageTd;
		if (languageName.length >= 15) {
			languageTd = $("<td class='text-center' style='width:80px;font-size:10px;vertical-align:bottom;'></td>").append(languageName);
		} else {
			languageTd = $("<td class='text-center' style='width:80px;font-size:15px;vertical-align:bottom;'></td>").append(languageName);
		}
		let editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
			.append($("<i class='bi bi-pencil-fill'></i>")).append("編集");
		editBtn.attr("editId", item.id);
		let deleteBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
			.append($("<i class='bi bi-trash'></i>")).append("削除");
		deleteBtn.attr("deleteId", item.id);
		let btnTd = $("<td class='text-center' style='width:120px;vertical-align:bottom;'></td>").append(editBtn).append(" ").append(deleteBtn);
		$("<tr></tr>").append(idTd).append(nameTd).append(continentTd).append(nationTd).append(districtTd).append(populationTd).append(languageTd)
			.append(btnTd).appendTo("#cityTableBody");
	});
}

// ページ情報を分析して表示する
function buildPageInfos(result) {
	let pageInfos = $("#pageInfos");
	pageInfos.empty();
	currentPage = result.extend.pageInfo.pageNum;
	totalPages = result.extend.pageInfo.totalPages;
	totalRecord = result.extend.pageInfo.totalRecords;
	pageInfos.append("The " + currentPage + " page in " + totalPages
		+ " pages, " + totalRecord + " records found.");
}

// ナビゲートされたページを分析して表示する
function buildPageNavi(result) {
	$("#pageNavi").empty();
	let ul = $("<ul></ul>").addClass("pagination");
	let firstPageLi = $("<li class='page-item'></li>").append(
		$("<a class='page-link'></a>").append("最初のページへ").attr("href", "#"));
	let previousPageLi = $("<li class='page-item'></li>").append(
		$("<a class='page-link'></a>").append("&laquo;").attr("href", "#"));
	if (!result.extend.pageInfo.hasPreviousPage) {
		firstPageLi.addClass("disabled");
		previousPageLi.addClass("disabled");
	} else {
		firstPageLi.click(function() {
			toSelectedPg(1, searchName);
		});
		previousPageLi.click(function() {
			toSelectedPg(currentPage - 1, searchName);
		});
	}
	let nextPageLi = $("<li class='page-item'></li>").append(
		$("<a class='page-link'></a>").append("&raquo;").attr("href", "#"));
	let lastPageLi = $("<li class='page-item'></li>").append(
		$("<a class='page-link'></a>").append("最後のページへ").attr("href", "#"));
	if (!result.extend.pageInfo.hasNextPage) {
		nextPageLi.addClass("disabled");
		lastPageLi.addClass("disabled");
	} else {
		lastPageLi.addClass("success");
		nextPageLi.click(function() {
			toSelectedPg(currentPage + 1, searchName);
		});
		lastPageLi.click(function() {
			toSelectedPg(totalPages, searchName);
		});
	}
	ul.append(firstPageLi).append(previousPageLi);
	$.each(result.extend.pageInfo.navigatePageNums, (index, item) => {
		let numsLi = $("<li class='page-item'></li>").append(
			$("<a class='page-link'></a>").append(item).attr("href", "#"));
		if (currentPage === item) {
			numsLi.attr("href", "#").addClass("active");
		}
		numsLi.click(function() {
			toSelectedPg(item, searchName);
		});
		ul.append(numsLi);
	});
	ul.append(nextPageLi).append(lastPageLi);
	let navElement = $("<nav></nav>").append(ul);
	navElement.appendTo("#pageNavi");
}

// 検索ボタンのクリック機能を追加する
$("#searchBtn").on('click', function() {
	searchName = $("#keywordInput").val().trim().toString();
	toSelectedPg(1, searchName);
});

// 入力モーダルにクリック機能を追加する
$("#cityAddModalBtn").on('click', function() {
	formReset("#cityAddModal form");
	getContinent("#continentInput");
	getNations($("#nationInput"), 'Africa');
	let addModal = new bootstrap.Modal($("#cityAddModal"), {
		backdrop: 'static'
	});
	addModal.show();
});


// 大陸選択にクリック機能を追加する
$("#continentInput").on('change', function() {
	let continentVal = $("#continentInput option:selected").val();
	getNations($("#nationInput"), continentVal);
})

// 大陸の名称を取得する
function getContinent(element) {
	$(element).empty();
	$.ajax({
		url: pathdeApp + '/continents',
		type: 'GET',
		success: function(result) {
			$.each(result.extend.continents, function() {
				let optionElement = $("<option></option>").append(this).attr(
					"value", this);
				optionElement.appendTo(element);
			});
		}
	});
}

// 国の名称を取得する
function getNations(element, continentVal) {
	$(element).empty();
	$.ajax({
		url: pathdeApp + '/countries',
		data: 'continentVal=' + continentVal,
		type: 'GET',
		success: function(result) {
			$.each(result.extend.nations, function() {
				let optionElement = $("<option></option>").append(this).attr("value", this);
				optionElement.appendTo(element);
			});
		}
	});
}

// 入力された名前が重複していないかを分析する
$("#nameInput").change(
	function() {
		let cityName = this.value;
		$.ajax({
			url: pathdeApp + '/checklist',
			data: 'cityName=' + cityName,
			type: 'GET',
			success: function(result) {
				if (result.code === 200) {
					showValidationMsg("#nameInput", "success", "");
					$("#cityInfoSaveBtn").attr("ajax-va", "success");
				} else {
					showValidationMsg("#nameInput", "error", result.extend.validatedMsg);
					$("#cityInfoSaveBtn").attr("ajax-va", "error");
				}
			}
		});
	});

// クリックして保存し、入力データ形式を確認する
$("#cityInfoSaveBtn").on('click', function() {
	let inputDistrict = $("#districtInput").val().trim();
	let inputPopulation = $("#populationInput").val().trim();
	let regularDistrict = /^[a-zA-Z-\s]{2,33}$/;
	let regularPopulation = /^\d{4,18}$/;
	if ($(this).attr("ajax-va") === "error") {
		return false;
	} else if (!regularDistrict.test(inputDistrict)
		&& !regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtInput", "error", "入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
		showValidationMsg("#populationInput", "error", "入力した人口数量が4桁から18桁までの数字にしなければなりません。");
		return false;
	} else if (!regularDistrict.test(inputDistrict)
		&& regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtInput", "error", "入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
		showValidationMsg("#populationInput", "success", "√");
		return false;
	} else if (regularDistrict.test(inputDistrict)
		&& !regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtInput", "success", "√");
		showValidationMsg("#populationInput", "error", "入力した人口数量が4桁から18桁までの数字にしなければなりません。");
		return false;
	} else {
		showValidationMsg("#districtInput", "success", "√");
		showValidationMsg("#populationInput", "success", "√");
		// ajaxリクエストを送信して保存オプションをコミットする
		$.ajax({
			url: pathdeApp + '/city',
			type: 'POST',
			contentType: 'application/json',
			dataType: 'json',
			data: JSON.stringify({
				'name': $("#nameInput").val().trim(),
				'continent': $("#continentInput option:selected").val(),
				'nation': $("#nationInput option:selected").val(),
				'district': inputDistrict,
				'population': inputPopulation
			}),
			success: function(result) {
				if (result.code === 200) {
					$("#cityAddModal").modal('hide');
					toSelectedPg(totalPages, searchName);
				} else if (undefined !== result.extend.errorFields.name) {
					showValidationMsg("#nameInput", "error", result.extend.errorFields.name);
				}
			}
		});
	}
});

// 編集モーダルにクリック機能を追加する
$(document).on('click', '.edit_btn', function() {
	let editId = $(this).attr("editId");
	formReset("#cityEditModal form");
	getCityInfo(editId);
	$("#cityInfoChangeBtn").attr("editId", editId);
	let editModal = new bootstrap.Modal($("#cityEditModal"), {
		backdrop: 'static'
	});
	editModal.show();
});

// 選択した都市情報を取得する
function getCityInfo(id) {
	$.ajax({
		url: pathdeApp + '/city/' + id,
		type: 'GET',
		dataType: 'json',
		success: function(result) {
			let cityData = result.extend.citySelected;
			$("#nameEdit").text(cityData.name);
			$("#continentEdit").text(cityData.continent);
			$("#languageEdit").text(cityData.language);
			$("#districtEdit").val(cityData.district);
			$("#populationEdit").val(cityData.population);
			getNationsById("#nationEdit", id);
		}
	});
}

// 都市IDによって国の名称を取得する
function getNationsById(element, id) {
	$(element).empty();
	$.ajax({
		url: pathdeApp + '/countries/' + id,
		type: 'GET',
		success: function(result) {
			$.each(result.extend.nationsByName, function() {
				let optionElement = $("<option></option>").append(this).attr("value", this);
				optionElement.appendTo(element);
			});
		}
	});
}

// 国のセレクターにクリック機能を追加する
$("#nationEdit").on('change', function() {
	let nationVal = $("#nationEdit option:selected").val();
	getLanguage($("#languageEdit"), nationVal);
})

// 言語の名称を取得する
function getLanguage(element, nationVal) {
	$(element).empty();
	$.ajax({
		url: pathdeApp + '/language',
		data: 'nationVal=' + nationVal,
		type: 'GET',
		success: function(result) {
			$("#languageEdit").text(result.extend.languages);
		}
	});
}

// 都市情報の変更を送信する
$("#cityInfoChangeBtn").on('click', function() {
	let inputDistrict = $("#districtEdit").val().trim();
	let inputPopulation = $("#populationEdit").val().trim();
	let regularDistrict = /^[a-zA-Z-\s]{2,33}$/;
	let regularPopulation = /^\d{4,18}$/;
	let editId = $(this).attr("editId");
	if (!regularDistrict.test(inputDistrict)
		&& !regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtEdit", "error", "入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
		showValidationMsg("#populationEdit", "error", "入力した人口数量が4桁から18桁までの数字にしなければなりません。");
		return false;
	}
	if (!regularDistrict.test(inputDistrict)
		&& regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtEdit", "error", "入力した地域名称が2桁から23桁までのローマ字にしなければなりません。");
		showValidationMsg("#populationEdit", "success", "√");
		return false;
	}
	if (regularDistrict.test(inputDistrict)
		&& !regularPopulation.test(inputPopulation)) {
		showValidationMsg("#districtEdit", "success", "√");
		showValidationMsg("#populationEdit", "error", "入力した人口数量が4桁から18桁までの数字にしなければなりません。");
		return false;
	}
	showValidationMsg("#districtEdit", "success", "√");
	showValidationMsg("#populationEdit", "success", "√");
	$.ajax({
		url: pathdeApp + '/city/' + editId,
		type: 'PUT',
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify({
			'id': editId,
			'name': $("#nameEdit").text(),
			'continent': $("#continentEdit").text(),
			'nation': $("#nationEdit option:selected").val(),
			'district': inputDistrict,
			'population': inputPopulation
		}),
		success: function() {
			$("#cityEditModal").modal('hide');
			toSelectedPg(currentPage, searchName);
		}
	});
});

// 削除ボタンにクリック機能を追加する
$(document).on('click', '.delete_btn', function() {
	let cityName = $(this).parents("tr").find("td:eq(0)").text().trim();
	let cityId = $(this).attr("deleteId");
	if (confirm("この" + cityName + "という都市の情報を削除する、よろしいでしょうか。")) {
		$.ajax({
			url: pathdeApp + "/city/" + cityId,
			type: "DELETE",
			success: function() {
				toSelectedPg(currentPage, searchName);
			}
		});
	}
});

// モーダルフォームをリセットする
function formReset(element) {
	$(element)[0].reset();
	$(element).find("*").removeClass("has-error has-success");
	$(element).find(".help-block").text("");
}

// モーダルフォームの入力行にステータスカラーを追加する
function showValidationMsg(element, status, msg) {
	$(element).removeClass("form-control");
	$(element).removeClass("form-control is-valid");
	$(element).removeClass("form-control is-invalid");
	$(element).next("span").removeClass("valid-feedback");
	$(element).next("span").removeClass("invalid-feedback");
	$(element).next("span").text("");
	if (status === "success") {
		$(element).addClass("form-control is-valid");
		$(element).next("span").addClass("valid-feedback");
	} else if (status === "error") {
		$(element).addClass("form-control is-invalid");
		$(element).next("span").addClass("invalid-feedback").text(msg);
	}
}