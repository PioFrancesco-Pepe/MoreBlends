const errorNameProd="Questo campo pu&ograve; contenere solo caratteri";
const errorDesc="Il campo non pu&ograve; essere vuoto.";
const errorDescA="Il campo non pu&ograve; essere vuoto.";
const errorCosto="Il campo pu&ograve; contenere solo numeri decimali. Si utilizza il punto al posto della virgola ES: 12.05";
const errorPrezzoVendita="Il campo pu&ograve; contenere solo numeri decimali. Si utilizza il punto al posto della virgola ES: 12.05";
const errorQuantita="Il campo pu&ograve; contenere solo numeri naturali.";
const emptyFieldErrorMessage = "Questo campo non pu&ograve; essere vuoto";
const namemErrorMessage ="Questo campo non ammette lo spazio e i numeri.";
const descmErrorMessage = "Questo campo non ammette i numeri";
const namesErrorMessage ="Questo campo non ammette i numeri.";


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
	let form = document.getElementById("newProd");
	
	let spanName = document.getElementById("errorNameProd");
	if(!validateFormElem(form.NomeProdotto, spanName, errorNameProd)){
		valid = false;
	}
	let spanDesc = document.getElementById("errorDesc");
	if(!validateFormElem(form.desc, spanDesc, errorDesc)){
		valid = false;
	}
	let spanDescA = document.getElementById("errorDescA");
	if(!validateFormElem(form.descA, spanDescA, errorDescA)){
		valid = false;
	}   
	let spanCosto = document.getElementById("errorCosto");
	if(!validateFormElem(form.costo, spanCosto, errorCosto)){
		valid = false;
	}  
	let spanPV = document.getElementById("errorPrezzoVendita");
	if(!validateFormElem(form.pv, spanPV, errorPrezzoVendita)){
		valid = false;
	}
	let spanQ = document.getElementById("errorQuantita");
	if(!validateFormElem(form.q, spanQ, errorQuantita)){
		valid = false;
	}
	return valid;	
}

function validateM() {
	let valid = true;
	let form = document.getElementById("newMarca");

	let spanMarca = document.getElementById("errorNameM");
	if (!validateFormElem(form.nameM, spanMarca, namemErrorMessage)) {
		valid = false;
	}
	let spanDescrizione = document.getElementById("errorDescM");
	if (!validateFormElem(form.descM, spanDescrizione, descmErrorMessage)) {
		valid = false;
	}
	return valid;
}

function validateS() {
	let valid = true;
	let form = document.getElementById("newSistema");

	let spanSistema = document.getElementById("errorNameS");
	if (!validateFormElem(form.nameS, spanSistema, namesErrorMessage)) {
		valid = false;
	}
	return valid;
}