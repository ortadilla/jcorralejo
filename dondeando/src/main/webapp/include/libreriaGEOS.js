/**
 * Función para bloquear el uso de la pantalla cuando se haya pulsado un botón.
 * Mostrará un gif con un reloj y hará visible el div de bloqueo de pantalla
 * que viene con ADF.
 */
var listenerLlamado = false;
var permitirBloquearPantalla = true;

function noBloquearPantalla() {
    permitirBloquearPantalla = false;
}

function bloquearPantalla(form) {
	//Antes de bloquear la pantalla, comprobamos si se han validado correctamente
	//los campos (si no, se quedaría bloqueada)
	if(_validateForm(form, {source:''})) {
	    if(permitirBloquearPantalla) {
		    TrPage.getInstance().getRequestQueue().addStateChangeListener(_bloquearPantalla);

		    if(!listenerLlamado) {
				var e = document.getElementById("indicador::busy");
				_mostrarDivsBloqueo(e);
				listenerLlamado = false;
		    }
	    } //Si no se puede bloquear la pantalla, no lo hacemos y limpiamos la bandera para la próxima vez.
	    else
	    	permitirBloquearPantalla = true;
    }
}



function _bloquearPantalla(estado) {

	//Miramos si se ha añadido el statusIndicator
	var e = document.getElementById("indicador::busy");

	if(estado==TrRequestQueue.STATE_BUSY) {
		_mostrarDivsBloqueo(e);
		listenerLlamado = true;
	}
	else if (estado==TrRequestQueue.STATE_READY){
		TrPage.getInstance().getRequestQueue().removeStateChangeListener(_bloquearPantalla);
		_refrescarSelectMultiples();
		listenerLlamado = false;
	}
	else {
		_mostrarDivsBloqueo(e);
	}

}

function _mostrarDivsBloqueo(e) {
	//Si no se ha añadido, lo hacemos como antes, con el div de Trinidad.
	if(e!=null)
		e.style.display="block";
	else {
		document.getElementById("tr_pprBlockingDiv").style.display="block";
		//Para Gecko
		document.getElementsByTagName("body")[0].style.overflow="hidden";
		//Para explorer
		document.body.scroll='no';
	 	document.getElementById("tr_pprBlockingDiv").style.width="100%";
	 	document.getElementById("tr_pprBlockingDiv").style.height="100%";
	 	document.getElementById("tr_pprBlockingDiv").style.margin="0px";
	 	document.getElementById("tr_pprBlockingDiv").style.padding="0px;";
	 	document.getElementById("tr_pprBlockingDiv").style.filter="alpha(opacity=60)";
	 	document.getElementById("tr_pprBlockingDiv").style.opacity=".6";
		document.getElementById("tr_pprBlockingDiv").style.background="black url('../imagenes/cargando.gif') center center no-repeat";
	 	document.getElementById("tr_pprBlockingDiv").style.color="white";
	}
}

//Cuando se quita la pantalla de bloqueo, habrá que situar el scroll de todos los selectManyListBox en el primer elemento (IE lo coloca al principio)
//para ello cuando se crearon los componentes, almacenamos sus ids en un campo hidden llamado "componentesMultiplesPantalla"
function _refrescarSelectMultiples(){
	var elemento = document.getElementById("componentesMultiplesPantalla");
	if(elemento!=null && elemento.getAttribute("value")!=null){
		trozos = elemento.getAttribute("value").split (",");
		for(var i=0;i<trozos.length-1;i++){
			var select = document.getElementById(trozos[i]);
			if(select.selectedIndex){                                
				var hfila = select.scrollHeight/select.options.length;
				var posScroll = (hfila*(select.selectedIndex+1))-select.clientHeight;
          		if(posScroll>select.clientHeight)
                	select.scrollTop=posScroll;
			}
		}
	}
}