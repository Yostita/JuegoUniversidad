package logica;

public class Informatico extends Jugador{
	Informatico(String nombre){
		setNombre(nombre);
		setVida(1000);
		int resistencia = (int)  ((Math.random()*(50-11+1)+11));
		setResistencia(resistencia);
		//Cada clase tiene sus parametros minimos y máximos de cada habilidad
		setPoder(calcularPoder(80,200,60,150,40,80,0,50));
		
		if(nombre.contentEquals("Yosta")||nombre.contentEquals("Jgr")) {
			setVida(1000000000);
			setResistencia(99);
			setPoder(calcularPoder(2000,2000,2000,2000,2000,2000,2000,2000));
		}
	}
}
