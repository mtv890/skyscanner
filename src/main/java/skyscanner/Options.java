package skyscanner;


public class Options {
	//default values
	private Integer adults = 1;     // cantidad de viajeros de 12 años en adelante
	private Integer children = 0;   // cantidad de viajeros de 2 a 11 años
	private Integer adultsv2 = 1;   // cantidad de viajeros de 16 años o mas
	private String childrenv2 = ""; //edad del menor de ser mas de 1 se separa por %7c x ejemplo 5 y 3 childrenv2=5%7c3&, vacio si children e infant es 0
	private Integer infants = 0;    //cantdad de viajeros de 0 o 1 años de edad
	private String cabinclass = "economy"; // (economy, business, premiumeconomy, first)
	private Integer rtn = 1;        // ??
 	private Boolean preferdirects = false;       //si true busca solo vuelos sin escala
	private Boolean outboundaltsenabled = true; //buscar "aeropuertos cercanos" para ida
	private Boolean inboundaltsenabled = true;  //buscar "aeropuertos cercanos" para vuelta
	private String ref = "home";    // de q pagina que llegaste
	private String market = "AR";       // mercado 
	private String locale = "es-AR";	// idioma
	private String currency = "ARS";	// moneda
	
		
	public Options() {
	}	

	public Options setAdults(Integer adults) {
        adults = this.adults;
        return this;
	}	
	 
	public Options setChildren(Integer children) {
		children = this.children;
        return this;
	}	
	 
	public Options setAdultsv2(Integer adultsv2) {
		adultsv2 = this.adultsv2;
        return this;
	}	
	 
	public Options setChildrenv2(String childrenv2) {
		childrenv2 = this.childrenv2;
        return this;
	}	
	
	public Options setInfants(Integer infants) {
		infants = this.infants;
        return this;
	}	

	public Options setCabinclass(String cabinclass) {
		cabinclass = this.cabinclass;
        return this;
	}	

	public Options setRtn(Integer rtn) {
 		rtn = this.rtn;
        return this;
 	}	

	public Options setPreferdirects(Boolean preferdirects) {
	 	preferdirects = this.preferdirects;
        return this;
	}	

	public Options setOutboundaltsenabled(Boolean outboundaltsenabled) {
		outboundaltsenabled = this.outboundaltsenabled;
        return this;
	}	

	public Options setInboundaltsenabled(Boolean inboundaltsenabled) {
		inboundaltsenabled = this.inboundaltsenabled;
        return this;
	}	

	public Options setRef(String ref) {
		ref = this.ref;
        return this;
	}	

	public Options setMarket(String market) {
		market = this.market;
        return this;
	}	

	public Options setLocale(String locale) {
		locale = this.locale;
        return this;
	}	

	public Options setCurrency(String currency) {
		currency = this.currency;
        return this;
	}
	
    public String toString () {
    	return "?adults=" + adults
    			+ "&children=" + children
    			+ "&adultsv2=" + adultsv2
    			+ "&childrenv2=" + childrenv2
    			+ "&infants=" + infants
    			+ "&cabinclass=" + cabinclass
    			+ "&rtn=" + rtn
    			+ "&preferdirects=" + preferdirects
    			+ "&outboundaltsenabled=" + outboundaltsenabled
    			+ "&inboundaltsenabled=" + inboundaltsenabled
    			+ "&ref=" + ref
    			+ "#results";
    }
}