$('.minus-btn').on('click', function(e) {
    		e.preventDefault();
    		var $this = $(this);
    		
    		var $input = $this.closest('div').find('input#q');
    		var value = parseInt($input.val());
    		
    		var $id=$this.closest('div').find('input#idP');
			var idP=parseInt($id.val());
			var check=false;
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
 
$('.plus-btn').on('click', function(e) {
    		e.preventDefault();
    		var $this = $(this);
    		
    		var $input = $this.closest('div').find('input#q');
    		var value = parseInt($input.val());
    		
			var $id=$this.closest('div').find('input#idP');
			var idP=parseInt($id.val());
			
    		if (value < 100) {
      		value = value + 1;
    		} else {
    			value =100;
    		}
    		
    		$input.val(value);
    		document.location.href="../cart?action=updateC&id="+idP+"&quantita="+value;
    	});