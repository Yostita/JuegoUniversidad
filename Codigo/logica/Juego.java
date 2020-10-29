package logica;
import java.util.*;

import escenarios.Ciencias;
import escenarios.Letras;

public class Juego {	
	
	public static void main(String[] args) {
		int[] contador = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};			//Contador de IA
		int tipoEscenario;   	//Hay diferentes tipos de escenarios del 0 al 15
		int ronda=0; 			//Contador de rondas
		int carreras=0; 		//Contador de cursos
		
		Scanner scan = new Scanner(System.in);
		
		//Selecciona la clase del personaje
		System.out.print("Nombre del jugador: ");
		String nombre = scan.next();
		System.out.println("Elige una clase: ");	
		System.out.println("1.Informático ");	
		System.out.println("2.Cientifico ");
		System.out.println("3.Filosofo ");
		System.out.println("4.Pintor " + "\n");
		int clase = scan.nextInt();
		System.out.println();
		
		//Crea la instancia Jugador
		Jugador yo = new Jugador();
				
        switch(clase) {
            case 1:
        		System.out.println("Ha seleccionado la clase INFORMATICO.");
                yo= new Informatico(nombre);
                yo.setTipo("Informatico");
                break;
            case 2:
        		System.out.println("Ha seleccionado la clase CIENTIFICO.");
                yo = new Cientifico(nombre);
                yo.setTipo("Cientifico");
                break;
            case 3:
        		System.out.println("Ha seleccionado la clase FILOSOFO.");
                yo= new Filosofo(nombre);
                yo.setTipo("Filosofo");
                break;
            case 4:
        		System.out.println("Ha seleccionado la clase PINTOR.");
                yo = new Pintor(nombre);
                yo.setTipo("Pintor");
                break;
        }
		System.out.println("\n" + "Personaje creado." + "\n");	
		yo.imprimirEstadisticas();
		
		do {
			//Selecciona el tipo de carrera
			System.out.println("Carreras terminadas : " + carreras);
			System.out.println("Elige una carrera: ");	
			System.out.println("1.Informática ");	
			System.out.println("2.Fisica o Quimica ");
			System.out.println("3.Filosofia ");
			System.out.println("4.Arte " + "\n");
			tipoEscenario = scan.nextInt();
			System.out.println();
			
			int poderInicialCarrera = yo.getPoder();
			int resistenciaIncialCarrera = yo.getResistencia();
			int vidaInicialCarrera = yo.getVida();
			crearEscenario(tipoEscenario, yo);
			
			//Cuatrimestres
			for(int i=0; i<4 || yo.getVida()<=0 ;i++) {
				//Rondas x Cuatrimestre
				System.out.println("Empezando el "+ (i+1) + "º año..." );	
				int vidaInicial = yo.getVida();
				ronda = 0;
				
				for(int j=0; j<4 || yo.getVida()<= 0;j++) {
					System.out.println("Empezando Batalla...");	
					int poderInicial = yo.getPoder();
					int resistenciaIncial = yo.getResistencia();
					
					//Selecciona el arma
					System.out.println();
					System.out.println("Elige una arma: ");	
					System.out.println("1.Calculadora (+20 poder -5 resistencia)");
					System.out.println("2.Apuntes (+5 resistencia +15 poder -30 vida)");
					System.out.println("3.Boli (+5 poder +10 vida)");
					System.out.println("4.Chuleta (+40 poder -80 vida -5 resistencia)");
					System.out.println("5.Papel (-15 poder +2 resistencia +20 vida)");
					System.out.println("6.Nokia3310 (+70 poder -15 resistencia)");
					System.out.println();
					int armaElegida = scan.nextInt();
					System.out.println();
					
					//Inicia la batalla
					Armas arma = new Armas(armaElegida, yo);
					Batalla batalla = new Batalla(yo,ronda,carreras);
					batalla.iniciarBatalla(contador);
					
					//En caso de que la vida del jugador sea menor que 0
					if(yo.getVida() <= 0) {
						System.out.println("Batalla terminada.");	
						scan.close();
						yo.imprimirMuerte();
						gameOver(i,j,carreras);
					}
					System.out.println("Vida: " + yo.getVida());
					System.out.println("Batalla terminada.");	
					
					//Restablece el poder y la resistencia
					yo.setPoder(poderInicial);
					yo.setResistencia(resistenciaIncial);
					ronda++;
				}
				System.out.println("                                  )___(");
				System.out.println("                           _______/__/_");
				System.out.println("                  ___     /===========|   ___");
				System.out.println(" ____       __   [\\\\\\]___/____________|__[///]   __");
				System.out.println(" \\   \\_____[\\\\]__/___________________________\\__[//]___");
				System.out.println("  \\40A                                                 |");
				System.out.println("   \\                                                  /");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Te vas de vacaciones y recuperas tu vida");
				
				//Restablece la vida despues de un año de carrera
				yo.setVida(vidaInicial);
				System.out.println((i+1) + "º año terminado.");	
			}
			yo.setPoder(poderInicialCarrera);
			yo.setResistencia(resistenciaIncialCarrera);
			yo.setVida(vidaInicialCarrera);
			carreras++;
		}while(true);
		
	}
	
	//Termina el juego
	public static void gameOver(int i, int j, int carreras) {
		System.out.println("No has podido con la presion...");
		System.out.println("Has dejado la carrera...");
		System.out.println("Has aguantado " + j + "/4 de "+ (i+1) + "º de carrera");
		System.out.println("Has terminado con exito " + carreras + " carreras");
		System.exit(0);
	}
	
	//Crea un escenario
	public static void crearEscenario(int tipoEscenario, Jugador yo) {
		if(yo.getTipo() == "Cientifico" || yo.getTipo() == "Informatico") {
			if(tipoEscenario == 1 || tipoEscenario == 2) {
				Ciencias ciencia =  new Ciencias();
				ciencia.generateEscenario(false,(Jugador) yo);
			}else {
				Letras letras =  new Letras();
				letras.generateEscenario(true,(Jugador) yo);
			}
		}else {
			if(tipoEscenario == 1 || tipoEscenario == 2) {
				Ciencias ciencia =  new Ciencias();
				ciencia.generateEscenario(true,(Jugador) yo);
			}else {
				Letras letras =  new Letras();
				letras.generateEscenario(false,(Jugador) yo);
			}
		}
	}	
}
