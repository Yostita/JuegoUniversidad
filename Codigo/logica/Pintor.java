package logica;

public class Pintor extends Jugador{
	Pintor(String nombre){
		setNombre(nombre);
		setVida(1000);
		int resistencia = (int)  ((Math.random()*(50-11+1)+11));
		setResistencia(resistencia);
		//Cada clase tiene sus parametros minimos y máximos de cada habilidad
		setPoder(calcularPoder(60,150,0,50,80,200,40,80));
	}

}
