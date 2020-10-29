package logica;

public class Personaje extends Armas{

	String nombre;
	String tipo;
	String descripcion;
	int vida;
	int resistencia;
	int poder;
	
	//SETTERS Y GETTERS
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;	
    }

	public String getDescripcion() {
        return this.descripcion;	
    }
	
    public void setVida(int vida) {
		this.vida=vida;
	}
	
	public int getVida() {
		return this.vida;
	}
	
	public void setResistencia(int resistencia) {
		this.resistencia=resistencia;
		
		if(resistencia <= 0) {
			this.resistencia=1;
		}
	}
	
	public int getResistencia() {
		return this.resistencia;
	}
	
	public void setPoder(int poder) {
		this.poder=poder;
		
		if(poder <= 0) {
			this.poder=10;
		}
	}
	
	public int getPoder() {
		return this.poder;
	}
	
	//Calcula el poder del Personaje en base a estos parametros
	public int calcularPoder(int minIq, int maxIq, int minInvestigacion ,int maxInvestigacion, int minCreatividad, int maxCreatividad, int minCarisma, int maxCarisma) {
		int iq = (int)(Math.random()*(maxIq-minIq+1) + minIq);
		int investigacion = (int)(Math.random()*(maxInvestigacion - minInvestigacion + 1) + minInvestigacion);
		int creatividad = (int)(Math.random()*(maxCreatividad - minCreatividad + 1) + minCreatividad);
		int carisma= (int)(Math.random()*(maxCarisma - minCarisma + 1) + minCarisma);
		int resultado = ((iq + investigacion + creatividad + carisma)/4);
		
		//MAXIMO PODER 122
		//MINIMO PODER 45
		//Solo para comprobar que funcionan las limitaciones
		//System.out.println("IQ: " + iq);
		//System.out.println("Investigacion: " + investigacion);
		//System.out.println("Creatividad: " + creatividad);
		//System.out.println("Carisma: " + carisma);
		
		return resultado;
	}
	
	public void imprimirEstadisticas() {
		System.out.println("   _   \t \t \t Nombre: " + this.nombre);
		System.out.println(" _(\")_ \t \t \t Clase: " + this.tipo);
		System.out.println("(_ . _)\t \t \t Vida: " + this.vida);
		System.out.println(" / : \\ \t \t \t Resistencia: " + this.resistencia);
		System.out.println("(_/ \\_)\t \t \t Poder: " + this.poder + "\n");
	}
	
	public void imprimirMuerte() {
		System.out.println("                       ______");
		System.out.println("                    .-\"      \"-.");
		System.out.println("                   /            \\");
		System.out.println("       _          |              |          _");
		System.out.println("      ( \\         |,  .-.  .-.  ,|         / )");
		System.out.println("       > \"=._     | )(__/  \\__)( |     _.=\" <");
		System.out.println("      (_/\"=._\"=._ |/     /\\     \\| _.=\"_.=\"\\_)");
		System.out.println("             \"=._ (_     ^^     _)\"_.=\"");
		System.out.println("                 \"=\\__|IIIIII|__/=\"");
		System.out.println("                _.=\"| \\IIIIII/ |\"=._");
		System.out.println("      _     _.=\"_.=\"\\          /\"=._\"=._     _");
		System.out.println("     ( \\_.=\"_.=\"     `--------`     \"=._\"=._/ )");
		System.out.println("      > _.=\"                            \"=._ <");
		System.out.println("     (_/                                    \\_)");
	}
}
