package logica;


public class Armas {
	Jugador jugador;
	//Constructor
	public Armas() {
		super();
	}
	
	//Constructor por parametros
	public Armas(int num, Jugador yo) {
		this.jugador =  yo;
		switch(num){
		case 1:
			buffPoder(20);
			nerfResistencia(5);
			break;
		case 2:
			buffResistencia(5);
			buffPoder(15);
			nerfVida(30);
			break;
		case 3:
			buffPoder(5);
			buffVida(10);
			break;
		case 4:
			buffPoder(40);
			nerfVida(80);
			nerfResistencia(5);
			break;
		case 5:
			nerfPoder(15);
			buffResistencia(2);
			buffVida(20);
			break;
		case 6:
			buffPoder(70);
			nerfResistencia(15);
			break;
		}
	}
	
	//Incrementa la resistencia
	public void buffResistencia(int buff) {
		jugador.setResistenciaExtra(buff);
		jugador.addResistencia();
	}
	
	//Incrementa la vida
	public void buffVida(int buff) {
		jugador.setVidaExtra(buff);
		jugador.addVida();
	}

	//Incrementa el poder
	public void buffPoder(int buff) {
		jugador.setPoderExtra(buff);
		jugador.addPoder();
	}
	
	//Reduce la resistencia
	public void nerfResistencia(int nerf) {
		jugador.setResistenciaExtra(-nerf);
		jugador.addResistencia();
	}
	
	//Reduce la vida
	public void nerfVida(int nerf) {
		jugador.setVidaExtra(-nerf);
		jugador.addVida();
	}
	
	//Reduce el poder
	public void nerfPoder(int nerf) {
		jugador.setPoderExtra(-nerf);
		jugador.addPoder();
	}
}
