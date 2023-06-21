const annoErrorMessage="Il campo pu&ograve; contenere solo numeri e il formato &egrave; il seguente: 2023";
const emptyFieldErrorMessage = "Questo campo non pu&ograve; essere vuoto";
const giornoErrorMessage="Errore";

function validateFormElem(formElem, span, errorMessage) {
	if(formElem.checkValidity()){
		formElem.classList.remove("error");
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	}
	formElem.classList.add("error");
	span.style.color = "red";
	if (formElem.validity.valueMissing){
		span.innerHTML = emptyFieldErrorMessage;
	} else {
		span.innerHTML = errorMessage;
	}
	return false;
}


function validate() {
	let valid = true;	
	let form = document.getElementById("r");
	
	let spanAnno = document.getElementById("errorAnno");
	if (!validateFormElem(form.anno, spanAnno, annoErrorMessage)){
		valid = false;
	}
	let spanGiorno = document.getElementById("errorGiorno");
	if (!validateFormElem(form.dateG, spanGiorno, giornoErrorMessage)){
		valid = false;
	}
	
	return valid;
}
