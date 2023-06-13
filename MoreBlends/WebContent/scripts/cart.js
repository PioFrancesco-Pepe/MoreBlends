$('.minus-btn').on('click', function() {
    		let $this = $(this);
    		
    		let $input = $this.closest('div').find('input#q');
    		let value = parseInt($input.val());
    		
    		let $id=$this.closest('div').find('input#idP');
			let idP=parseInt($id.val());
			let check=false;
    		
    		if (value > 1) {
    			value--;
    		} else {
				check=true;
    			document.location.href="../cart?action=deleteC&id="+idP+"&quantita="+value;
    		}
        $input.val(value);
        
        if(!check)
			document.location.href="../cart?action=updateC&id="+idP+"&quantita="+value;
    	});
 
$('.plus-btn').on('click', function() {
    		let $this = $(this);
    		
    		let $input = $this.closest('div').find('input#q');
    		let value = parseInt($input.val());
    		
			let $id=$this.closest('div').find('input#idP');
			let idP=parseInt($id.val());
			
			let max=parseInt($this.closest('div').find('input#max').val());
			
    		if (value < max) {
      			value ++;
      		
    		}
    		$input.val(value);
   			if(value==max)
   				disableButtonPlus($this);
			else
				document.location.href="../cart?action=updateC&id="+idP+"&quantita="+value;
    	});
    	
function disableButtonPlus($this)
{
	let $currentB=$this.closest('div').find('button.plus-btn');
	let $currentImg=$this.closest('div').find('img.plus');
	$currentB.css("visibility","hidden");
	$currentImg.css("visibility","hidden");
}