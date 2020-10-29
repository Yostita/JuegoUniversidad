package escenarios;

import logica.Escenario;
import logica.Jugador;

public class Letras implements Escenario{

    public void generateEscenario(boolean enContra, Jugador jugador) {
        if(enContra) {
            jugador.setResistenciaExtra(-10);
            jugador.addResistencia();
            jugador.setVidaExtra(-100);
            jugador.addVida();
            jugador.setPoderExtra(-20);
            jugador.addPoder();
        }else {
            jugador.setResistenciaExtra(5);
            jugador.addResistencia();
            jugador.setVidaExtra(70);
            jugador.addVida();
            jugador.setPoderExtra(10);
            jugador.addPoder();
        }

    }


}
