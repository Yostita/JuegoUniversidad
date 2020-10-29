package logica;

public class Filosofo extends Jugador{
	Filosofo(String nombre){
		setNombre(nombre);
		setVida(1000);
		int resistencia = (int)  ((Math.random()*(50-11+1)+11));
		setResistencia(resistencia);
		//Cada clase tiene sus parametros minimos y máximos de cada habilidad
		setPoder(calcularPoder(40,80,0,50,60,150,80,200));
	}
}
