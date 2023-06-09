const nameOrLastnameErrorMessage = "Questo campo deve contenere solo lettere";
const emailErrorMessage = "Il campo Email deve avere questo formato username@domain.ext";
const phoneErrorMessage = "Il campo Telefono deve avere questo formato ###-#######";
const emptyFieldErrorMessage = "Questo campo non pu&ograve; essere vuoto";
const passwordErrorMessage ="Il campo deve contenere almeno 8 caratteri, un carattere deve essere maiuscolo, uno minuscolo e un numero";
const viaErrorMessage="Il campo Via deve inizia con delle lettere";
const civicoErrorMessage="Il campo Civico deve avere il seguente formato 44 o 4d";
const capErrorMessage="Il campo CAP deve essere composta da 5 numeri";
const cittaErrorMessage="il campo Citt&agrave;-Paese puoi contenere solo lettere";
const spErrorMessage="Il campo Sigla Provincia deve essere composta da due lettere maiuscole";


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


function validateReg() {
	let valid = true;	
	let form = document.getElementById("regForm");
	
	let spanName = document.getElementById("errorName");
	if(!validateFormElem(form.firstname, spanName, nameOrLastnameErrorMessage)){
		valid = false;
	} 
	let spanLastname = document.getElementById("errorLastname");
	if (!validateFormElem(form.lastname, spanLastname, nameOrLastnameErrorMessage)){
		valid = false;
	}
	let spanEmail = document.getElementById("errorEmail");
	if (!validateFormElem(form.email, spanEmail, emailErrorMessage)){
		valid = false;
	}
	let spanPassword = document.getElementById("errorPassword");
	if (!validateFormElem(form.password, spanPassword, passwordErrorMessage)){
		valid = false;
	}
	let spanPhone = document.getElementById("errorPhone");
	if (!validateFormElem(document.getElementById("phone"), spanPhone, phoneErrorMessage)){
		valid = false;
	}
	let spanVia = document.getElementById("errorVia");
	if (!validateFormElem(form.via, spanVia, viaErrorMessage)){
		valid = false;
	}
	let spanCivico = document.getElementById("errorCivico");
	if (!validateFormElem(form.civico, spanCivico, civicoErrorMessage)){
		valid = false;
	}
	let spanCAP = document.getElementById("errorCAP");
	if (!validateFormElem(form.CAP, spanCAP, capErrorMessage)){
		valid = false;
	}	
	let spanCitta = document.getElementById("errorCitta");
	if (!validateFormElem(form.localita, spanCitta, cittaErrorMessage)){
		valid = false;
	}
	let spanSP = document.getElementById("errorSP");
	if (!validateFormElem(form.sp, spanSP, spErrorMessage)){
		valid = false;
	}
	return valid;
}

function validateLog() {
	let valid = true;	
	let form = document.getElementById("logForm");
	
	let spanEmail = document.getElementById("errorEmail");
	if (!validateFormElem(form.email, spanEmail, emailErrorMessage)){
		valid = false;
	}
	let spanPassword = document.getElementById("errorPassword");
	if (!validateFormElem(form.password, spanPassword, passwordErrorMessage)){
		valid = false;
	}
	
	return valid;
}

function validatePhone() {
	let valid = true;	
	let form = document.getElementById("phoneForm");
	
	let spanPhone = document.getElementById("errorPhone");
	if (!validateFormElem(form.phone, spanPhone, phoneErrorMessage)){
		valid = false;
	}
	return valid;
}

function validateInd()
{
	let valid = true;	
	let form = document.getElementById("indForm");
	
	let spanVia = document.getElementById("errorVia");
	if (!validateFormElem(form.via, spanVia, viaErrorMessage)){
		valid = false;
	}
	let spanCivico = document.getElementById("errorCivico");
	if (!validateFormElem(form.civico, spanCivico, civicoErrorMessage)){
		valid = false;
	}
	let spanCAP = document.getElementById("errorCAP");
	if (!validateFormElem(form.CAP, spanCAP, capErrorMessage)){
		valid = false;
	}	
	let spanCitta = document.getElementById("errorCitta");
	if (!validateFormElem(form.localita, spanCitta, cittaErrorMessage)){
		valid = false;
	}
	let spanSP = document.getElementById("errorSP");
	if (!validateFormElem(form.sp, spanSP, spErrorMessage)){
		valid = false;
	}
	return valid;
}