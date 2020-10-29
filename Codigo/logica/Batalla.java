package logica;
import java.util.*;
import escenarios.Ciencias;
import escenarios.Letras;

public class Batalla{
	private static Batalla inicioUnico;
	Personaje jugador;
	Personaje npc;

	//Constructor por parametros
	Batalla(Personaje jugador, int ronda, int nivel){
		this.jugador = jugador;
		Npc enemigo = new Npc(ronda,nivel);
		this.npc = enemigo;
		crearEscenario();
	}
	
	//Crea el escenario de la batalla
	public void crearEscenario() {
		if(jugador.getTipo() == "Cientifico" || jugador.getTipo() == "Informatico") {
			if(npc.getTipo().contains("Informatico") || npc.getTipo().contains("Cientifico")) {
				Ciencias ciencia =  new Ciencias();
				ciencia.generateEscenario(false,(Jugador) jugador);
			}else {
				Letras letras =  new Letras();
				letras.generateEscenario(true,(Jugador) jugador);
			}
		}else {
			if(npc.getTipo().contains("Informatico") || npc.getTipo().contains("Cientifico")) {
				Ciencias ciencia =  new Ciencias();
				ciencia.generateEscenario(true,(Jugador) jugador);
			}else {
				Letras letras =  new Letras();
				letras.generateEscenario(false,(Jugador) jugador);
			}
			
		}
	}
	
	//Inicializa la batalla
	public void iniciarBatalla(int contador[]) {
        int accionYo = 0;
        int accionAnterior = 0;
        int accionNpc =  0;
        boolean muerto =  true;
        System.out.println(jugador.getNombre() + "(" + jugador.getTipo() + ")" 
                + " -VS- " + npc.getNombre() + "(" + npc.getDescripcion() + ")" );
        do {
        	System.out.println();
            imprimir();
            System.out.println();
            accionNpc =  setAccionNPC(contador,accionYo);
            accionYo = setAccionJugador();
            
            //Para asegurar que la accion es valida
            while (accionYo == 0 || accionYo == 6) {
            	if(accionYo == 0) {
            		System.out.println("Esa accion no es válida");
            	}
            	if(accionYo == 6) {
            		jugador.imprimirEstadisticas();
            	}
                accionYo = setAccionJugador();
            }
            
            System.out.println();
            actualizarContador(accionYo,accionAnterior,contador);
            mostrarAcciones(accionYo,accionNpc);
            resolverPelea(accionYo, accionNpc);
            accionAnterior = accionYo;
            muerto = Estado.getEstado(jugador,npc);
            
            if(jugador.getVida() <= 0) {
                if(npc.getNombre().contentEquals("Mark Zuckerberg")) {
                    System.out.println("Mark Zuckerberg te ha robado los datos y los ha vendido");
                }
                if(npc.getNombre().contentEquals("Steve Jobs")) {
                    System.out.println("Steve Jobs te ha cambiado tu Windows por un MAC");
                }
                if(npc.getNombre().contentEquals("Bill Gates")) {
                    System.out.println("Bill Gates te ha cambiado tu MAC por un Windows");
                }
                if(npc.getNombre().contentEquals("Elon Musk")) {
                    System.out.println("Elon Musk se ha quedado con tu cuenta de Paypal");
                }
                if(npc.getNombre().contentEquals("Nietzsche")) {
                    System.out.println("Nietzsche: Evidentemente, tu no eres Dios.");
                }
            }
            if(npc.getVida() <= 0) {
                if(npc.getNombre().contentEquals("Karl Marx")) {
                    System.out.println("Los alumnos se sublevaron antes la clase superior (el profesor) en este caso, Karl Marx");
                }
            }
        }while(muerto);
        
        if(npc.getVida() <= 0) {
        	System.out.println();
        	System.out.println("       _      _             _       ");
        	System.out.println("      (_)    | |           (_)      ");
        	System.out.println("__   ___  ___| |_ ___  _ __ _  __ _ ");
        	System.out.println("\\ \\ / / |/ __| __/ _ \\| '__| |/ _` |");
        	System.out.println(" \\ V /| | (__| || (_) | |  | | (_| |");
        	System.out.println("  \\_/ |_|\\___|\\__\\___/|_|  |_|\\__,_|");
        }
        System.out.println();
	}
	
	//Muestra las acciones que se han tomado en el turno
	public void mostrarAcciones(int accionYo,int accionNpc){
		switch(accionYo) {
		case 1:
			System.out.print(jugador.getNombre() + "--> Atacar");
			break;
		case 2:
			System.out.print(jugador.getNombre() + "--> No hacer nada");
			break;
		case 3:
			System.out.print(jugador.getNombre() + "--> Defender");
			break;
		case 4:
			System.out.print(jugador.getNombre() + "--> Atacar rapido");
			break;
		case 5:
			System.out.print(jugador.getNombre() + "--> Cegar");
			break;
		}
		
		System.out.print(" vs ");
		
		switch(accionNpc) {
		case 1:
			System.out.println("Atacar <--" + npc.getNombre());
			break;
		case 2:
			System.out.println("No hacer nada <--" + npc.getNombre());
			break;
		case 3:
			System.out.println("Defender <--" + npc.getNombre());
			break;
		case 4:
			System.out.println("Atacar rapido <--" + npc.getNombre());
			break;
		case 5:
			System.out.println("Cegar <--" + npc.getNombre());
			break;
		}
	}
	
	//La IA decide que accion tomar en base a su contador
	public int setAccionNPC(int contador[], int accionYo) {
		int accion=0;
		boolean encontrado=false;
		
		switch(accionYo){
			case 0:
				accion = (int)(Math.random()*(5-1+1) + 1);
				break;
			case 1:
				if(contador[0]>contador[1] && contador[0] > contador[2] && contador[0]>contador[3] && contador[0] > contador[4]) {	//Seguramente ataque
					accion = 3;
					encontrado = true;
				}
				if(contador[1]>contador[0] && contador[1] > contador[2] && contador[1]>contador[3] && contador[1] > contador[4]) {    //Seguramente no haga nada
					accion = 1;		
					encontrado = true;
				}
				if(contador[2]>contador[0] && contador[2] > contador[1] && contador[2]>contador[3] && contador[2] > contador[4]) {    //Seguramente se defienda
					accion = 2;		
					encontrado = true;
				}
				if(contador[3]>contador[0] && contador[3] > contador[1] && contador[3]>contador[2] && contador[3] > contador[4]) {   //Seguramente haga un ataque rapido
					accion = 3; //O atacas	
					encontrado = true;
				}
				if(contador[4]>contador[0] && contador[4] > contador[1] && contador[4]>contador[2] && contador[4] > contador[3]) { 	//Seguramente intente cegar
					accion = 4; 
					encontrado = true;
				}
				//En caso de que los contadores aun no tengan valores suficientes para decidir una accion
				if(!encontrado) {
					accion = (int)(Math.random()*(5-1+1) + 1);
				}
				break;
			case 2:
				if(contador[1]>contador[5] && contador[1] > contador[6] && contador[1]>contador[7] && contador[1] > contador[8]) {	//Seguramente ataque
					accion = 3;
					encontrado = true;
				}
				if(contador[5]>contador[1] && contador[5] > contador[6] && contador[5]>contador[7] && contador[5] > contador[8]) {    //Seguramente no haga nada
					accion = 1;	
					encontrado = true;
				}
				if(contador[6]>contador[1] && contador[6] > contador[5] && contador[6]>contador[7] && contador[6] > contador[8]) {    //Seguramente se defienda
					accion = 2;	
					encontrado = true;
				}	
				if(contador[7]>contador[1] && contador[7] > contador[5] && contador[7]>contador[6] && contador[7] > contador[8]) {    //Seguramente haga un ataque rapido
					accion = 3; //O atacas	
					encontrado = true;
				}
				if(contador[8]>contador[1] && contador[8] > contador[5] && contador[8]>contador[6] && contador[8] > contador[7]) { //Seguramente intente cegar
					accion = 4; 
					encontrado = true;
				}
				//En caso de que los contadores aun no tengan valores suficientes para decidir una accion
				if(!encontrado) {
					accion = (int)(Math.random()*(5-1+1) + 1);
				}
				break;
			case 3:
				if(contador[2]>contador[6] && contador[2] > contador[9] && contador[2]>contador[10] && contador[2] > contador[11]) {	//Seguramente ataque
					accion = 3;
					encontrado =true;
				}
				if(contador[6]>contador[2] && contador[6] > contador[9] && contador[6]>contador[10] && contador[6] > contador[11]) {    //Seguramente no haga nada
					accion = 1;		
					encontrado =true;
				}
				if(contador[9]>contador[2] && contador[9] > contador[6] && contador[9]>contador[10] && contador[9] > contador[11]) {    //Seguramente se defienda
					accion = 2;		
					encontrado =true;
				}
				if(contador[10]>contador[2] && contador[10] > contador[6] && contador[10]>contador[9] && contador[10] > contador[11]) {    //Seguramente haga un ataque rapido
					accion = 3; //O atacas		
					encontrado =true;
				}
				if(contador[11]>contador[2] && contador[11] > contador[6] && contador[11]>contador[9] && contador[11] > contador[10]) {  //Seguramente intente cegar
					accion = 4; 
					encontrado =true;
				}
				//En caso de que los contadores aun no tengan valores suficientes para decidir una accion
				if(!encontrado) {
					accion = (int)(Math.random()*(5-1+1) + 1);
				}
				break;
			case 4:
				if(contador[3]>contador[7] && contador[3] > contador[10] && contador[3]>contador[12] && contador[3] > contador[13]) {	//Seguramente ataque
					accion = 3;
					encontrado =true;
				}
				if(contador[7]>contador[3] && contador[7] > contador[10] && contador[7]>contador[12] && contador[7] > contador[13]) {    //Seguramente no haga nada
					accion = 1;	
					encontrado =true;
				}
				if(contador[10]>contador[3] && contador[10] > contador[7] && contador[10]>contador[12] && contador[10] > contador[13]) {    //Seguramente se defienda
					accion = 2;	
					encontrado =true;
				}
				if(contador[12]>contador[3] && contador[12] > contador[7] && contador[12]>contador[10] && contador[12] > contador[13]) {    //Seguramente haga un ataque rapido
					accion = 3; //O atacas	
					encontrado =true;
				}
				if(contador[13]>contador[3] && contador[13] > contador[7] && contador[13]>contador[10] && contador[13] > contador[12]) {  //Seguramente intente cegar
					accion = 4; 
					encontrado =true;
				}
				//En caso de que los contadores aun no tengan valores suficientes para decidir una accion
				if(!encontrado) {
					accion = (int)(Math.random()*(5-1+1) + 1);
				}
				break;
			case 5:
				if(contador[4]>contador[8] && contador[4] > contador[11] && contador[4]>contador[13] && contador[4] > contador[14]) {	//Seguramente ataque
					accion = 3;
					encontrado =true;
				}
				if(contador[8]>contador[4] && contador[8] > contador[11] && contador[8]>contador[13] && contador[8] > contador[14]) {   //Seguramente no haga nada
					accion = 1;	
					encontrado =true;
				}
				if(contador[11]>contador[4] && contador[11] > contador[8] && contador[11]>contador[13] && contador[11] > contador[14]) {    //Seguramente se defienda
					accion = 2;	
					encontrado =true;
				}
				if(contador[13]>contador[4] && contador[13] > contador[8] && contador[13]>contador[11] && contador[13] > contador[14]) {   //Seguramente haga un ataque rapido
					accion = 3; //O atacas	
					encontrado =true;
				}
				if(contador[14]>contador[4] && contador[14] > contador[8] && contador[14]>contador[11] && contador[14] > contador[13]) {  //Seguramente intente cegar
					accion = 4; 
					encontrado =true;
				}
				//En caso de que los contadores aun no tengan valores suficientes para decidir una accion
				if(!encontrado) {
					accion = (int)(Math.random()*(5-1+1) + 1);
				}
				break;
		}
		return accion;
	}
	
	//Actualiza el contador de la IA
	public void actualizarContador(int accionYo, int accionAnterior,int contador[]) {
        if(accionYo == accionAnterior && accionYo==1) {contador[0]++; }

        if(accionYo == 1 && accionAnterior == 2) { contador[1]++; }

        if(accionYo == 1 && accionAnterior == 3) { contador[2]++; }

        if(accionYo == 1 && accionAnterior == 4) { contador[3]++; }

        if(accionYo == 1 && accionAnterior == 5) { contador[4]++; }

        if(accionYo == accionAnterior && accionYo==2) { contador[5]++; }

        if(accionYo == 2 && accionAnterior == 3) { contador[6]++; }

        if(accionYo == 2 && accionAnterior == 4) { contador[7]++; }

        if(accionYo == 2 && accionAnterior == 5) { contador[8]++; }

        if(accionYo == accionAnterior && accionYo==3) { contador[9]++; }

        if(accionYo == 3 && accionAnterior == 4) { contador[10]++; }

        if(accionYo == 3 && accionAnterior == 5) { contador[11]++; }

        if(accionYo == accionAnterior && accionYo==4) { contador[12]++; }

        if(accionYo == 4 && accionAnterior == 5) { contador[13]++; }

        if(accionYo == accionAnterior && accionYo==5) { contador[14]++; }
    }

	//Imprime el marcador
	public void imprimir() {
		System.out.println("-------------------------------READY TO RUMBLEEEEEEEEEEEEEE-------------------------------");
		System.out.println("|                                                                                         |");
		System.out.println("|                                                                                         |");
		System.out.println("|                                                                                         |");
		System.out.println("\t\t   " + jugador.getVida() + "                                  " + "\t      " + npc.getVida() + "      ");
		System.out.println("\t\t" + jugador.getNombre() + "-" + jugador.getPoder() + "\t\t" + "   VS    " + "\t\t" + npc.getPoder() + "-" + npc.getNombre());
		System.out.println("|                                                                                         |");
		System.out.println("|                                                                                         |");
		System.out.println("|                                                                                         |");
		System.out.println("|                                                                                         |");
		System.out.println("------------------------------------------------------------------------------------------");
	}
	
	//Devuelve la accion que decide el jugador
	public int setAccionJugador() {
		Scanner scan = new Scanner(System.in);
		System.out.println("---ACCION QUE QUIERES REALIZAR---");
		System.out.println("1.Atacar - Levantar la mano");
		//System.out.println("2.No hacer nada - No se la respuesta profe");
		System.out.println("2.No hacer nada - Jugar al pc en clase");
		System.out.println("3.Defender - Presentar proyecto");
		System.out.println("4.Ataque rapido - Mirar apuntes en el examen");
		System.out.println("5.Cegar - Dormirse");
		System.out.println("6.Ver stats");
		int accion = scan.nextInt();
		
		switch(accion) {
			case 1:
				//Atacar
				return 1;
			case 2:
				//No hacer nada
				return 2;
			case 3:
				//Defenderse
				return 3;
			case 4:
				//Ataque rapido
				return 4;
			case 5:
				//Cegar
				return 5;
			case 6:
				//Ver estadisticas
				return 6;
		}
		
		return 0;
	}
	
	//Comprueba quien ha ganado la batalla
	public void resolverPelea(int accionYo, int accionNpc) {
		switch(accionYo) {
			//El jugador ataca
			case 1:
				if(accionNpc == 1) {System.out.println("Cri Cri");}
				if(accionNpc == 2) {calcularDmg(true,0,false); System.out.println(npc.getNombre() + ": Muy buena respuesta");}
				if(accionNpc == 3) {calcularDmg(false,1,false); System.out.println(npc.getNombre() + ": Respuesta Equivocada");}
				if(accionNpc == 4) {calcularDmg(true,2,false); System.out.println(npc.getNombre() + ": No es del todo correcta la respuesta");}
				if(accionNpc == 5) {calcularDmg(false,3,false); System.out.println(npc.getNombre() + ": ¿Has escuchado lo que he dicho?");}
				break;
			//El jugador no hace nada
			case 2:
				if(accionNpc == 1) {calcularDmg(false,0,false); System.out.println(npc.getNombre() + ": ¿Que demonios haces jugando en clase?");}
				if(accionNpc == 2) {System.out.println("Cri Cri");}
				if(accionNpc == 3) {calcularDmg(true,1,false); System.out.println(npc.getNombre() + " (No se da cuenta de que estas jugando)");}
				if(accionNpc == 4) {calcularDmg(false,0,false); System.out.println(npc.getNombre() + ": Fuera de clase, aqui no se juega");}
				if(accionNpc == 5) {calcularDmg(true,1,false); System.out.println(npc.getNombre() + " (Se piensa que estas buscando informacion sobre la asignatura)");}
				break;
			//El jugador se defiende
			case 3:
				if(accionNpc == 1) {calcularDmg(true,1,false); System.out.println(npc.getNombre() + ": Increible presentacion");}
				if(accionNpc == 2) {calcularDmg(false,1,false); System.out.println(npc.getNombre() + ": No he visto una presentacion peor en mi vida");}
				if(accionNpc == 3) {System.out.println("Cri Cri");}
				if(accionNpc == 4) {calcularDmg(true,1,false); System.out.println(npc.getNombre() + ": No me esperaba esto de ti, un 10");}
				if(accionNpc == 5) {calcularDmg(false,1,false); System.out.println(npc.getNombre() + ": No has presentado el tema que te tocaba");}
				break;
			//El jugador hace un ataque rapido
			case 4:
				if(accionNpc == 1) {calcularDmg(false,2,false); System.out.println(npc.getNombre() + " (Sospecha que has copiado y te baja la nota)");}
				if(accionNpc == 2) {calcularDmg(true,0,true); System.out.println(npc.getNombre() + " (No sospecha que has copiado)");}
				if(accionNpc == 3) {calcularDmg(false,1,false); System.out.println(npc.getNombre() + ": Vaya, vaya asi que copiando, suspenso");}
				if(accionNpc == 4) {System.out.println("Cri Cri");}
				if(accionNpc == 5) {calcularDmg(true,0,true); System.out.println(npc.getNombre() + " (No nota que has estado mirando el movil en mitad del examen)");}
				break;
			//El jugador decide cegar
			case 5:
				if(accionNpc == 1) {calcularDmg(true,3,false); System.out.println(npc.getNombre() + " (Estas en la ultima fila y no ve que te has dormido)");}
				if(accionNpc == 2) {calcularDmg(false,1,false); System.out.println(npc.getNombre() + ": ¿Tanto te aburre mi clase? Pues fuera de esta");}
				if(accionNpc == 3) {calcularDmg(true,1,false); System.out.println(npc.getNombre() + " (No haces sonidos eres como un ninja durmiendo)");}
				if(accionNpc == 4) {calcularDmg(false,0,false); System.out.println(npc.getNombre() + " (Aplaude para que te despiertes)");}
				if(accionNpc == 5) {System.out.println("Cri Cri");}
				break;
		}
	}
	
	//Calcula el daño que se le realiza al derrotado
	public void calcularDmg(boolean victorioso, int tipoDmg, boolean AtqRapido) {
		int dmg;
		double porcentajeDmg;
		double dmgAtaqueRapido;
		double porcentajeDmg2;
		double dmgFinal;

		switch(tipoDmg) {
		//Hacer da�o al derrotado
		case 0:
			if(victorioso) {
				if(AtqRapido) {
					dmgAtaqueRapido = ((Math.random()*(75-25+1)+25)/100);
					dmg = (int) (jugador.getPoder() * dmgAtaqueRapido);
				}else {
					dmg = jugador.getPoder();
				}
				//CALCULO DE LA RESISTENCIA DMGFINAL
				porcentajeDmg = 100 - npc.getResistencia();
				porcentajeDmg = porcentajeDmg/100;
				dmgFinal = dmg * porcentajeDmg;
				this.npc.setVida(this.npc.getVida()- (int) dmgFinal);
			}else {
				if(AtqRapido) {
					dmgAtaqueRapido = ((Math.random()*(75-25+1)+25)/100);
					dmg = (int) (npc.getPoder() * dmgAtaqueRapido);
				}else {
					dmg = npc.getPoder();
				}
				//CALCULO DE LA RESISTENCIA DMGFINAL
				porcentajeDmg2 = 100 - jugador.getResistencia();

				dmgFinal = dmg * (porcentajeDmg2/100);
				this.jugador.setVida(this.jugador.getVida()- (int) dmgFinal);
			}
			break;
		//Bajar resistencia al derrotado
		case 1:
			if(victorioso) {
				this.npc.setResistencia(this.npc.getResistencia() - 5);
			}else {
				this.jugador.setResistencia(this.jugador.getResistencia() - 5);
			}
			break;
		//Hacer da�o a los 2 pero el derrotado sufre mas 
		case 2:
			dmgAtaqueRapido = ((Math.random()*(75-25+1)+25)/100);
			if(victorioso) {
				dmg = (int) (npc.getPoder() * dmgAtaqueRapido);
				int dmg2 = jugador.getPoder();
				//CALCULO DE LA RESISTENCIA
				porcentajeDmg = 100 - jugador.getResistencia();
				porcentajeDmg = porcentajeDmg/100;
				dmgFinal = dmg * porcentajeDmg;
				porcentajeDmg2 = 100 - npc.getResistencia();
				porcentajeDmg2 = porcentajeDmg2/100;
				double dmgFinal2 = dmg2 * porcentajeDmg2;
				this.jugador.setVida(this.jugador.getVida() - (int) dmgFinal);
				this.npc.setVida(this.npc.getVida() - (int) dmgFinal2);
			}else {
				dmg = (int) (jugador.getPoder() * dmgAtaqueRapido);
				int dmg2 = npc.getPoder();
				//CALCULO DE LA RESISTENCIA
				porcentajeDmg = 100 - npc.getResistencia();
				porcentajeDmg = porcentajeDmg/100;
				dmgFinal = dmg * porcentajeDmg;
				porcentajeDmg2 = 100 - jugador.getResistencia();
				porcentajeDmg2 = porcentajeDmg2 /100;
				double dmgFinal2 = dmg2 * porcentajeDmg2;
				this.npc.setVida(this.npc.getVida() - (int) dmgFinal);
				this.jugador.setVida(this.jugador.getVida() -(int) dmgFinal2);
			}
			break;
		//Perjudica al ataque
		case 3:
			if(victorioso) {
				this.npc.setPoder(this.npc.getPoder() - 10);
			}else {
				this.jugador.setPoder(this.jugador.getPoder() - 10);
			}
			break;
		}
	}
	
	//Crea una estancia unica de batalla
	public static Batalla getInstance(Personaje jugador, int ronda, int nivel){ 
        if (inicioUnico == null) {
            inicioUnico =  new Batalla (jugador,ronda,nivel);
        }else {
            System.out.println("No se puede crear una batalla, ya existe una");
        }
        return inicioUnico;
	}
}
