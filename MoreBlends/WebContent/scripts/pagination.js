const paginationNumbers = document.getElementById("pagination-numbers");
const paginatedList = document.getElementById("list");
const listItems = paginatedList.querySelectorAll("li");
const nextButton = document.getElementById("next-button");
const prevButton = document.getElementById("prev-button");
const l= $(window).width();

let paginationLimit = 21;
if (l <= 600){
	paginationLimit = 4;
	$("ul#list").css("grid-template-columns", "repeat(1, 165px)");
}
else if (l >= 750 && l <= 800){
	paginationLimit = 8;
	$("ul#list").css("grid-template-columns", "repeat(2, 165px)");
}
else if(l >= 800 && l <= 1249)
{
	paginationLimit = 15;
	$("ul#list").css("grid-template-columns", "repeat(3, 165px)");
}
else if(l >= 1250 && l <= 1440)
{
	paginationLimit = 16;
	$("ul#list").css("grid-template-columns", "repeat(4, 165px)");
}else if(l >= 1500 && l <= 1999)
{
	paginationLimit = 15;
	$("ul#list").css("grid-template-columns", "repeat(5, 165px)");
}else if(l >= 2000)
{
	paginationLimit = 21;
	$("ul.list").css("grid-template-columns", "repeat(7, 165px)")
}


let pageCount = Math.ceil(listItems.length / paginationLimit);
let currentPage = 1;

const disableButton = (button) => {
	button.classList.add("disabled");
	button.setAttribute("disabled", true);
};

const enableButton = (button) => {
	button.classList.remove("disabled");
	button.removeAttribute("disabled");
};

const handlePageButtonsStatus = () => {
	if (currentPage === 1) {
		disableButton(prevButton);
	} else {
		enableButton(prevButton);
	}

	if (pageCount === currentPage) {
		disableButton(nextButton);
	} else {
		enableButton(nextButton);
	}
};

const handleActivePageNumber = () => {
	document.querySelectorAll(".pagination-number").forEach((button) => {
		button.classList.remove("active");
		const pageIndex = Number(button.getAttribute("page-index"));
		if (pageIndex == currentPage) {
			button.classList.add("active");
		}
	});
};

const appendPageNumber = (index) => {
	const pageNumber = document.createElement("button");
	pageNumber.className = "pagination-number";
	pageNumber.innerHTML = index;
	pageNumber.setAttribute("page-index", index);
	pageNumber.setAttribute("aria-label", "Page " + index);

	paginationNumbers.appendChild(pageNumber);
};

const getPaginationNumbers = () => {
	for (let i = 1; i <= pageCount; i++) {
		appendPageNumber(i);
	}
};

const setCurrentPage = (pageNum) => {
	currentPage = pageNum;

	handleActivePageNumber();
	handlePageButtonsStatus();

	const prevRange = (pageNum - 1) * paginationLimit;
	const currRange = pageNum * paginationLimit;

	listItems.forEach((item, index) => {
		item.classList.add("hidden");
		if (index >= prevRange && index < currRange) {
			item.classList.remove("hidden");
		}
	});
};

window.addEventListener("load", () => {
	getPaginationNumbers();
	setCurrentPage(1);

	prevButton.addEventListener("click", () => {
		setCurrentPage(currentPage - 1);
	});

	nextButton.addEventListener("click", () => {
		setCurrentPage(currentPage + 1);
	});

	document.querySelectorAll(".pagination-number").forEach((button) => {
		const pageIndex = Number(button.getAttribute("page-index"));

		if (pageIndex) {
			button.addEventListener("click", () => {
				setCurrentPage(pageIndex);
			});
		}
	});
});

window.addEventListener("resize", () => {
	let width = $(window).width();
	if (width <= 600) {
		paginationLimit = 4;
		pageCount = Math.ceil(listItems.length / paginationLimit);
		$("ul#list").css("grid-template-columns", "repeat(1, 165px)");

		document.querySelectorAll(".pagination-number").forEach((button) => {
			button.remove();
		});

		getPaginationNumbers();
		setCurrentPage(1);

		document.querySelectorAll(".pagination-number").forEach((button) => {
			const pageIndex = Number(button.getAttribute("page-index"));
			if (pageIndex) {
				button.addEventListener("click", () => {
					setCurrentPage(pageIndex);
				});
			}
		});
	}
	else if (width >= 750 && width <= 800) {
		paginationLimit = 8;
		pageCount = Math.ceil(listItems.length / paginationLimit);
		$("ul#list").css("grid-template-columns", "repeat(2, 165px)");

		document.querySelectorAll(".pagination-number").forEach((button) => {
			button.remove();

		});
		getPaginationNumbers();
		setCurrentPage(1);

		document.querySelectorAll(".pagination-number").forEach((button) => {
			const pageIndex = Number(button.getAttribute("page-index"));
			if (pageIndex) {
				button.addEventListener("click", () => {
					setCurrentPage(pageIndex);
				});
			}
		});
	}
	else if (width >= 800 && width <= 1249) {
		paginationLimit = 15;
		pageCount = Math.ceil(listItems.length / paginationLimit);
		$("ul#list").css("grid-template-columns", "repeat(3, 165px)");

		document.querySelectorAll(".pagination-number").forEach((button) => {
			button.remove();

		});
		getPaginationNumbers();
		setCurrentPage(1);

		document.querySelectorAll(".pagination-number").forEach((button) => {
			const pageIndex = Number(button.getAttribute("page-index"));
			if (pageIndex) {
				button.addEventListener("click", () => {
					setCurrentPage(pageIndex);
				});
			}
		});
	}
	else if (width >= 1250 && width <= 1499) {
		paginationLimit = 16;
		pageCount = Math.ceil(listItems.length / paginationLimit);
		$("ul#list").css("grid-template-columns", "repeat(4, 165px)");

		document.querySelectorAll(".pagination-number").forEach((button) => {
			button.remove();

		});
		getPaginationNumbers();
		setCurrentPage(1);

		document.querySelectorAll(".pagination-number").forEach((button) => {
			const pageIndex = Number(button.getAttribute("page-index"));
			if (pageIndex) {
				button.addEventListener("click", () => {
					setCurrentPage(pageIndex);
				});
			}
		});
	}else if (width >= 1500 && width <= 1999) {
		paginationLimit = 15;
		pageCount = Math.ceil(listItems.length / paginationLimit);
		$("ul#list").css("grid-template-columns", "repeat(5, 165px)");

		document.querySelectorAll(".pagination-number").forEach((button) => {
			button.remove();

		});
		getPaginationNumbers();
		setCurrentPage(1);

		document.querySelectorAll(".pagination-number").forEach((button) => {
			const pageIndex = Number(button.getAttribute("page-index"));
			if (pageIndex) {
				button.addEventListener("click", () => {
					setCurrentPage(pageIndex);
				});
			}
		});
	}else if (width >= 2000) {
		paginationLimit = 21;
		pageCount = Math.ceil(listItems.length / paginationLimit);
		$("ul#list").css("grid-template-columns", "repeat(7, 165px)");

		document.querySelectorAll(".pagination-number").forEach((button) => {
			button.remove();

		});
		
		getPaginationNumbers();
		setCurrentPage(1);

		document.querySelectorAll(".pagination-number").forEach((button) => {
			const pageIndex = Number(button.getAttribute("page-index"));
			if (pageIndex) {
				button.addEventListener("click", () => {
					setCurrentPage(pageIndex);
				});
			}
		});
	}
}); 