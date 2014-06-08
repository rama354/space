text=document.getElementsByTagName("em").innerHTML;

function executeTextChange(){
	var count=0;
	while(true)
	{
		count++;
		if (count%2 == 1){
			
			document.getElementsByTagName("em").innerHTML="This is NOT a test jsp";
		}
		else{
			document.getElementsByTagName("em").innerHTML=text;
		}		
		
		if (count == 50)
			break;
	}
}

window.onload=executeTextChange;


function verifylogin(user,pwd){
	
	if (user == ""){
		alert("User has not been provided");
		return false;
	}
			
	if (pwd == "" ){
		alert("Password has not been provided");
		return false;
	}
	
}