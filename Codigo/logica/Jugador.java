package logica;

public class Jugador extends Personaje{
	int vidaExtra;
	int resistenciaExtra;
	int poderExtra;
	
	//Modifica la vida
	public void addVida() {
		Jugador.super.setVida(Jugador.super.getVida() + vidaExtra);
	}
	
	//Modifica la resistencia
	public void addResistencia() {
		//Para evitar que la resistencia sea 0
		if(Jugador.super.getResistencia() + resistenciaExtra <= 0) {
			Jugador.super.setResistencia(1);
		}else {
			Jugador.super.setResistencia(Jugador.super.getResistencia() + resistenciaExtra);
		}
		
	}
	
	//Modifica el poder
	public void addPoder() {
		Jugador.super.setPoder(Jugador.super.getPoder() + poderExtra);
	}
	
	//Setter vidaExtra
	public void setVidaExtra(int vidaExtra) {
		this.vidaExtra = vidaExtra;
	}
	
	//Setter resistenciaExtra
	public void setResistenciaExtra(int resistenciaExtra) {
		this.resistenciaExtra = resistenciaExtra;
	}

	//Setter poderExtra
	public void setPoderExtra(int poderExtra) {
		this.poderExtra = poderExtra;
	}
}
