package logica;

import java.util.*;
public class Npc extends Personaje{
	String tipo;//0-7 inf 8-15 cientifico/investigador/creador 16-23 filosofo/divulgador 24-31 pintor/escultores(cuando se genere el math random)
				//32-43 hibridos (inf-cien 32,33)(inf-filosofo 34,35)(inf-pintor 36,37)(cien-filosofo 38,39)(cien-pintor 40,41)(filosofo-pintor 42,43)	
	static String[] nombreRivales ={
			//INFORMATICOS
			 "Shawn Fanning_Creador Napster primera web de pirateria",
			 "Jimmy Wales_Fundador Wikipedia",
			 "Chad Hurley_Uno de los creadores de YT",
			 "Janus Friis_Uno de los creadores de Skype",
			 "Kim Dotcom_Creador de Megaupload", 
			 "Alan Turing_Padre teorico del ordenador y precursor de IA",
			 "Tim Berners-Lee_Padre de internet creador de la WWW",
			 "Richard Stallman_Fundador del movimiento Software libre y creador del S.O. GNU",
			//CIENTIFICOS/INVESTIGADORES/CREADORES
			 "Elon Musk_Creador de Tesla,SpaceX,Paypal...",
			 "Albert Einstein_Cientifico mas importante que se ha conocido",
			 "Katie Bouman_Hizo la primera foto de un Agujero Negro",
			 "Stanislav Grof_Investigador de varias enfermedades mentales",
			 "Nikola Tesla_Hizo numerosas invenciones en el campo del electromagnetismo",
			 "Marie Curie_Pionera en el campo de la radioactividad",
			 "Niels Bohr_Publico el modelo atomico actual",
			 "James Clerk Maxwell_formulo de la teoría clásica de la radiación",
			//FILOSOFOS/DIVULGADORES
			 "Isaac Asimov_Divulgador Cientifico",
			 "Carl Sagan_Divulgador Cientifico",
			 "Socrates_Es reconocido como el pensador que transformo la dirección de la filosofia europea y el mas sabio de los filosofos griegos",
			 "Platon_Creador de la teoria de las ideas",
			 "Karl Marx_Desarrollo el comunismo moderno con la teoria de lucha de clases y la concepcion materialista",
			 "Nietzsche_Critico de la religion, los ideales y la folosofia occidental",
			 "Eduardo Punset_Divulgador cientifico",
			 "Neil deGrasse_Divulgador cientifico",
			//PINTORES
			 "Pablo Picasso_Pintor y escultor",
			 "Vincent van Gogh_Principal pintor del postimpresionismo",
			 "Salvador Dalí_Pintor del surrealismo",
			 "Velazquez_Uno de los maximos exponentes de la pintura española",
			 "Caravaggio_Pintor Italiano",
			 "Goya_Pintor español",
			 "El Greco_Pintor del posbizantino",
			 "Rembrandt_Pintor mas importante de los paises bajos",
			//INFORMATICOS-CREADORES
			 "Jeff Bezos_Creador de Amazon",
			 "Mark Zuckerberg_Creador de Facebook",
			//INFORMATICOS-DIVULGADORES
			 "Steve Jobs_Creador de Apple y divulgador",
			 "Bill Gates_Creador de Microsoft y divulgador",
			//INFORMATICOS-PINTORES
			 "Bobby Chiu_Programadora de arte digital, creadora de varios personajes de videojuegos como el WoW",
			 "Nico Di Mattia_Estrella de la animacion y el arte digital",
			//CIENTIFICOS-DIVULGADORES
			 "Stephen Hawking_(aplico un nuevo y complejo modelo matemático creado a Teoria de la relatividad de Einstein)Investigador cientifico y divulgador",
			 "Isaac Newton_Descubrio la ley de la gravedad y establecio las bases de la mecanica y divulgador",
			//CIENTIFICOS-PINTORES
			 "Paul Friedlander_Fisico, Matematico y creador de esculturas cineticas luminosas",
			 "Leonardo da Vinci_Pintor, cientifico, ingeniero, musico, arquitecto...",
			//FILOSOFOS-PINTOR
			 "Claude Monet_Pintor creador del impresionismo y divulgadore de arte",
			 "Tiziano_Pintor y divulgador del arte en la escuela de Venecia",
		};

	//Constructor por parametros
	Npc(int escenario, int nivel){
		if(nivel <= 0) {
			nivel = 1;
		}
		Random random = new Random();
		int idRival = -1;
		int poderBasico = 0;
		int poderTotal = 0;
		int vida = 0;
		int resistencia = 0;
		String data_npc , nombre_npc, descripcion_npc;
		//Cada 4 escenarios se aumenta en 10 el poder
		//De los escenarios 4 al 7 y del 12 al 15 pueden salir npcs hibridos
		switch(escenario) {
		case 0:
		case 1:
		case 2:
		case 3:
			idRival = random.nextInt(32);
			
			//Dependiendo del idRival se le asigna una clase diferente
			if (0 <= idRival && idRival < 7) {
				tipo="Informatico";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(80, 120, 50, 80, 40, 60, 20, 40);
			}else if(8 <= idRival && idRival < 15) {
				tipo="Cientifico";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(50, 80, 80, 120, 20, 40, 40, 60);
			}else if(16 <= idRival && idRival < 23) {
				tipo="Filosofo";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(40, 60, 20, 40, 50, 80, 80, 120);
			}else {
				tipo="Pintor";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(50, 80, 20, 40, 80, 120, 40, 60);
			}
			
			//Asigna variables
			data_npc = nombreRivales[idRival];
            nombre_npc = data_npc.split("_")[0];
            descripcion_npc = data_npc.split("_")[1];
            Npc.super.setNombre(nombre_npc);
            Npc.super.setDescripcion(descripcion_npc);
			poderTotal = nivel * poderBasico;
			Npc.super.setPoder(poderTotal);
			Npc.super.setResistencia(15);
			vida = (int)(Math.random()*(400-300+1) + 300);
			Npc.super.setVida(vida);
			break;
		case 4:
		case 5:
		case 6:
		case 7:
			idRival = random.nextInt(44);
			Npc.super.setNombre(nombreRivales[idRival]);
			resistencia = 20;
			if (0 <= idRival && idRival < 7) {
				tipo="Informatico";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(90, 130, 60, 90, 50, 70, 30, 50);
			}else if(8 <= idRival && idRival < 15) {
				tipo="Cientifico";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(60, 90, 90, 130, 30, 50, 50, 70);
			}else if(16 <= idRival && idRival < 23) {
				tipo="Filosofo";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(50, 70, 30, 50, 60, 90, 90, 130);
			}else if(24 <= idRival && idRival < 31){
				tipo="Pintor";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(60, 90, 30, 50, 90, 130, 50, 70);
			}else if(32 <= idRival && idRival < 33){
				resistencia = 25;
				tipo="Informatico-Cientifico";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(90, 130, 90, 130, 50, 70, 50, 70);
			}else if(34 <= idRival && idRival < 35){
				resistencia = 25;
				tipo="Informatico-Filosofo";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(90, 130, 60, 90, 60, 90, 90, 130);
			}else if(36 <= idRival && idRival < 37){
				resistencia = 25;
				tipo="Informatico-Pintor";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(90, 130, 60, 90, 90, 130, 50, 70);
			}else if(36 <= idRival && idRival < 37){
				resistencia = 25;
				tipo="Cientifico-Filosofo";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(60, 90, 90, 130, 60, 90, 90, 130);
			}else if(36 <= idRival && idRival < 37){
				resistencia = 25;
				tipo="Cientifico-Pintor";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(60, 90, 90, 130, 90, 130, 50, 70);
			}else {
				resistencia = 25;
				tipo="Filosofo-Pintor";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(60, 90, 30, 50, 90, 130, 90, 130);
			}
			data_npc = nombreRivales[idRival];
            nombre_npc = data_npc.split("_")[0];
            descripcion_npc = data_npc.split("_")[1];
            Npc.super.setNombre(nombre_npc);
            Npc.super.setDescripcion(descripcion_npc);
			poderTotal = nivel * poderBasico;
			Npc.super.setPoder(poderTotal);
			Npc.super.setResistencia(resistencia);
			vida = (int)(Math.random()*(500-400+1) + 400);
			Npc.super.setVida(vida);
			break;
		case 8:
		case 9:
		case 10:
		case 11:
			idRival = random.nextInt(32);
			if (0 <= idRival && idRival < 7) {
				tipo="Informatico";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(100, 140, 70, 100, 60, 80, 40, 60);
			}else if(8 <= idRival && idRival < 15) {
				tipo="Cientifico";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(70, 100, 100, 140, 40, 60, 60, 80);
			}else if(16 <= idRival && idRival < 23) {
				tipo="Filosofo";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(60, 80, 40, 60, 70, 90, 100, 140);
			}else {
				tipo="Pintor";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(70, 100, 40, 60, 100, 140, 60, 80);
			}

			data_npc = nombreRivales[idRival];
            nombre_npc = data_npc.split("_")[0];
            descripcion_npc = data_npc.split("_")[1];
            Npc.super.setNombre(nombre_npc);
            Npc.super.setDescripcion(descripcion_npc);
			poderTotal = nivel * poderBasico;
			Npc.super.setPoder(poderTotal);
			Npc.super.setResistencia(30);
			vida = (int)(Math.random()*(600-500+1) + 500);
			Npc.super.setVida(vida);	
			break;
		case 12:
		case 13:
		case 14:
		case 15:
			idRival = random.nextInt(44);
			Npc.super.setNombre(nombreRivales[idRival]);
			resistencia = 35;
			if (0 <= idRival && idRival < 7) {
				tipo="Informatico";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(110, 150, 80, 110, 70, 90, 50, 30);
			}else if(8 <= idRival && idRival < 15) {
				tipo="Cientifico";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(80, 110, 110, 150, 50, 70, 70, 90);
			}else if(16 <= idRival && idRival < 23) {
				tipo="Filosofo";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(70, 90, 50, 70, 80, 110, 110, 150);
			}else if(24 <= idRival && idRival < 31){
				tipo="Pintor";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(80, 110, 50, 70, 110, 150, 70, 90);
			}else if(32 <= idRival && idRival < 33){
				resistencia = 40;
				tipo="Informatico-Cientifico";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(110, 150, 110, 150, 70, 90, 70, 90);
			}else if(34 <= idRival && idRival < 35){
				resistencia = 40;
				tipo="Informatico-Filosofo";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(110, 150, 80, 110, 80, 110, 110, 150);
			}else if(36 <= idRival && idRival < 37){
				resistencia = 40;
				tipo="Informatico-Pintor";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(110, 150, 80, 110, 110, 150, 70, 90);
			}else if(36 <= idRival && idRival < 37){
				resistencia = 40;
				tipo="Cientifico-Filosofo";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(80, 110, 110, 150, 80, 110, 110, 150);
			}else if(36 <= idRival && idRival < 37){
				resistencia = 40;
				tipo="Cientifico-Pintor";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(80, 110, 110, 150, 110, 150, 70, 90);
			}else {
				resistencia = 40;
				tipo="Filosofo-Pintor";
				Npc.super.setTipo(tipo);
				poderBasico =  calcularPoder(80, 110, 50, 70, 110, 150, 110, 150);
			}
			data_npc = nombreRivales[idRival];
            nombre_npc = data_npc.split("_")[0];
            descripcion_npc = data_npc.split("_")[1];
            Npc.super.setNombre(nombre_npc);
            Npc.super.setDescripcion(descripcion_npc);
			poderTotal = nivel * poderBasico;
			Npc.super.setPoder(poderTotal);
			Npc.super.setResistencia(resistencia);
			vida = (int)(Math.random()*(700-600+1) + 600);
			Npc.super.setVida(vida);
			break;
		}
	}
}
