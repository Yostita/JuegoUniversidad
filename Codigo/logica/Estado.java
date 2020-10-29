package logica;

public abstract class Estado extends Batalla{
	Estado(Personaje jugador, int ronda, int nivel) {
		super(jugador, ronda, nivel);
	}
	
	static public boolean getEstado(Personaje jugador, Personaje npc) {
        if(jugador.getVida() <= 0 || npc.getVida() <= 0) {
        	return false;
        }
        return true;
	}
}
