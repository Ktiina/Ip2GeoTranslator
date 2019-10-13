window.onload = customize;

function customize(){
	window.document.getElementById('div3').style.display = 'none';
}

function getIpGeo(inputType)
{
    window.document.getElementById('div3').style.display = 'none';
    var q_str = null;
    
    if (inputType === "current") {
    	q_str = 'type=current&ip='+document.getElementById('t2').value;
    }
    else if (inputType === "custom") {
    	q_str = 'type=custom&ip='+document.getElementById('t3').value;
    }
    else {
    	return;
    }
	
	doAjax('IpGeoServlet',q_str,'getIpGeo_back','post',0);
}
function getIpGeo_back(result)
{
	if (result.substring(0,5)=='error') {
		 window.document.getElementById('div3').style.display = 'block';
		 window.document.getElementById('div3').innerHTML="<p style='color:red;'><b>"+result.substring(6)+"</b></p>";
	} 
	else {
		var resultArr = result.split("#");
		
		if (resultArr[0] === "current") {
			window.document.getElementById('td11').innerHTML = ""+resultArr[1];
			window.document.getElementById('td21').innerHTML = ""+resultArr[2];
			window.document.getElementById('td31').innerHTML = ""+resultArr[3];
			window.document.getElementById('td41').innerHTML = ""+resultArr[4];
			window.document.getElementById('td51').innerHTML = ""+resultArr[5];
			window.document.getElementById('td61').innerHTML = ""+resultArr[6];
			window.document.getElementById('td71').innerHTML = ""+resultArr[7];
			window.document.getElementById('td81').innerHTML = ""+resultArr[8];
			window.document.getElementById('td91').innerHTML = ""+resultArr[9];
			window.document.getElementById('td101').innerHTML = ""+resultArr[10];
			window.document.getElementById('td111').innerHTML = ""+resultArr[11];
		}
		else if (resultArr[0] === "custom") {
			window.document.getElementById('td12').innerHTML = ""+resultArr[1];
			window.document.getElementById('td22').innerHTML = ""+resultArr[2];
			window.document.getElementById('td32').innerHTML = ""+resultArr[3];
			window.document.getElementById('td42').innerHTML = ""+resultArr[4];
			window.document.getElementById('td52').innerHTML = ""+resultArr[5];
			window.document.getElementById('td62').innerHTML = ""+resultArr[6];
			window.document.getElementById('td72').innerHTML = ""+resultArr[7];
			window.document.getElementById('td82').innerHTML = ""+resultArr[8];
			window.document.getElementById('td92').innerHTML = ""+resultArr[9];
			window.document.getElementById('td102').innerHTML = ""+resultArr[10];
			window.document.getElementById('td112').innerHTML = ""+resultArr[11];
		}
	}
}
