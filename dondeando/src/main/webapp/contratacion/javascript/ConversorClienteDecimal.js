function ConversorClienteDecimal() {
  //debug
  this._class = "ConversorClienteDecimal";

}

function dameCadena(valor, etiqueta) {
	return valor;
}

/**
 * Comprueba que el valor pasado tenga formato de número decimal válido.
 * Permite que el separador decimal sea el punto o la coma. (si solo hay uno)
 * 
 * Si hay puntos y comas el separador debe ser la coma.
 */
function dameObjeto(valor, etiqueta) {
   
        if ((!valor) || (valor.length==0))
        	return null;
		
		var posicionComa = valor.indexOf(",");
		var posicionPunto= valor.indexOf(".");
		
		var puntosYcomas = (posicionComa >-1) && (posicionPunto >-1);
		
		var valueSinMiles = valor;
	
		if(puntosYcomas){
			 while (posicionPunto>-1){
			 	valueSinMiles = valueSinMiles.replace('.', '');
			 	var posicionPunto= valueSinMiles.indexOf(".");
			 }
		}
		
		var valorSinComas = cambiaComasAPuntos(valueSinMiles);
		
       	if (!isNaN(valorSinComas)) {
	        return valorSinComas;
        } else {
            //Se ha producido un error, se construye el mensaje de error:
        	var resumenError = "El dato introducido no tiene formato correcto.";
			var detalleError = "El valor \""+valor+"\" no tiene formato de cifra decimal.";
        
			var facesMessage = new TrFacesMessage(
			                    resumenError,
			                    detalleError,
			                    TrFacesMessage.SEVERITY_ERROR);
			throw new TrConverterException(facesMessage);
       }
       return null;
   
      return valor;
      
}

function cambiaComasAPuntos(cadena) {
	return cadena.replace(',', '.');
}

ConversorClienteDecimal.prototype = new TrConverter();
ConversorClienteDecimal.prototype.getAsString = dameCadena;
ConversorClienteDecimal.prototype.getAsObject = dameObjeto;