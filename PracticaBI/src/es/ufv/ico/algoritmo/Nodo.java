package es.ufv.ico.algoritmo;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class Nodo  implements Comparable<Nodo>{

	private double g;
	private double h;
	private double f;
	
	private Puzzle puzzle;
	private Puzzle objetivo;
	private List<Nodo> listaAbiertos = new ArrayList<>();
	private List<Nodo> listaCerrados = new ArrayList<>();
	private Nodo padre ;
	private int generados =0;
	private int explorados =0;
	private int operador;
	private ArrayList<Nodo> camino = new ArrayList<>();
	/*Cada nodo contiene el Puzzle ,  G=Coste , H=Heuristica y F*/
	public Nodo(Puzzle puzzle_actual,double g , double h , double f,ArrayList<Nodo> suc,Nodo padr,int operador)  {
		Nodo n = new Nodo();
		n.setPuzzle(puzzle_actual);
		n.setH(n.getPuzzle().Heuristico(puzzle_actual));
		n.setG(0);
		if(menu.getMetodo().equals("1")) {n.setF(n.getG()+n.getH());}
		else if(menu.getMetodo().equals("2")) {n.setF(n.getG());}
		else if(menu.getMetodo().equals("3")) {n.setF(n.getH());}
		
		n.setPadre(null);
		listaAbiertos.add(n);
		generados=1;
		while(!n.getPuzzle().comparaPuzzle(n.getPuzzle())) {
		System.out.println("Padre ");
		Puzzle.imprimePuzzle(n.getPuzzle().getPuzzleActual());
		System.out.println(n.getOperador());
		explorar_sucesores(n);
		n=escoger_nodo(menu.getMetodo(), n);
		System.out.println(n.getPadre());
			
}
		System.out.println(n.getOperador());

		System.out.println("Acabado , nodo ganador ");
		Puzzle.imprimePuzzle(n.getPuzzle().getPuzzleActual());
		listaCerrados.add(n);
		//System.out.print(n.getH() + " " +  n.getF());
		System.out.println("Nº Nodos generados = " + generados);
		System.out.println("Nº Nodos expandidos = " + explorados);
		camino.add(n);
		recorrerCamino(n);
		imprimePadres(camino);
		imprimeCamino(camino);
		//imprimeExplorados();
	}
	
	public Nodo() {
		super();
	}

	
	public boolean gestionarCola_Cerrados(Nodo hijo ) {
		boolean b = false;
		for(int i=0;i<listaCerrados.size();i++) {
		if(Puzzle.comparaPosiciones(listaCerrados.get(i).getPuzzle(),hijo.getPuzzle())){
			
				if(hijo.getF()<listaCerrados.get(i).getF()) {

//				System.out.println("esta en cerrados y h es menor");
				listaCerrados.remove(i);
				generados=generados+1;
				listaAbiertos.add(hijo);
				return true;
		
		}else {
			//esta en cerrados pero h es mayor
//			System.out.println("esta en cerrados y h es mayor");
				return true;
		}
				

	
		}else {
			//System.out.println("no esta en cerrados");
			b= false;
		}
		}
	return b;

	}
	public boolean gestionarColaAbiertos(Nodo  hijo ) {
		boolean b=false;
		for(int i=0;i<listaAbiertos.size();i++) {
		if(Puzzle.comparaPosiciones(listaAbiertos.get(i).getPuzzle(),hijo.getPuzzle())){
				
				if(hijo.getF()<listaAbiertos.get(i).getF()) {

				System.out.println("contiene en abiertos y h es menor");
				listaAbiertos.add(hijo);
				//generados=generados+1;
				listaAbiertos.remove(i);
				
				return true;
				}
				else {
					System.out.println("esta en abiertos y h es mayor");
					return false;
					
					}
				
				}
				else {
					b= false;
					
		}

		}
		if(b==false) {
			listaAbiertos.add(hijo);
			generados=generados+1;
		}
	
	return false;
	}
	
	
	/*Exploro cada nodo 
	 * Para cada posicion de la ficha 0 (si la puedo mover) 
	 * creo un nodo nuevo, lo meto en el array de hijos , y le asigno su padre
	 * 
	 * FALTA CREAR LA HEURISTICA PARA CADA NODO
	 * EL COSTE
	 * ORDENARLOS EN UN Array de "Abiertos" o "Cerrados" en funcion de su F total
	 * 
	 * */
	public void explorar_sucesores(Nodo padr){
		
		ArrayList<Nodo> sucesores= new ArrayList<>();
		
		Puzzle p;
		Nodo hijo1;
		
		//1.copio el puzzle actual 
		//2.determino la posicion de la ficha 0
		//3.creo los hijos en funcion de si se puede mover :IZQ,DERECHA,ARRIBA,ABAJO
		//para cada movimiento creo un Hijo
		//4.Comparo si es igual que el padre
		//5.si no son iguales calculo H y le agrego su padre
		//lo agrego a array Sucesores
		
		
		
		listaCerrados.add(padr);
		explorados=explorados+1;
		listaAbiertos.remove(padr);
		
		
		
				//TRASLADO DERECHA
				hijo1=new Nodo();
				p=padr.getPuzzle().transladar(padr.getPuzzle(),0,1);
				hijo1.setPuzzle(p);
				hijo1.setH(hijo1.getPuzzle().Heuristico(hijo1.getPuzzle()));
				hijo1.setG(padr.getG()+1);
				hijo1.setOperador(1);
				if(menu.getMetodo().equals("1")) {hijo1.setF(hijo1.getG()+hijo1.getH());
				}
				else if(menu.getMetodo().equals("2")) {hijo1.setF(hijo1.getG());}
				else if(menu.getMetodo().equals("3")) {hijo1.setF(hijo1.getH());}	
				hijo1.setPadre(padr);
				
				//comparo si el padre es igual que el hijo
				if(!Puzzle.comparaPosiciones(padr.getPuzzle(), hijo1.getPuzzle())) {
					
					
					if(!gestionarCola_Cerrados( hijo1)) {
						if(!gestionarColaAbiertos(hijo1));
					}
					
					
				}
				else {
//						System.out.println("Hijo igual que el padre");
					}
				
		
				
				
				
				//TRASLADO IZQUIERDA
				hijo1=new Nodo();
				p=padr.getPuzzle().transladar(padr.getPuzzle(),0,-1);
				hijo1.setPuzzle(p);
//				System.out.println("\n Sucesor" );
				hijo1.setH(hijo1.getPuzzle().Heuristico(hijo1.getPuzzle()));
				hijo1.setG(padr.getG()+1);
				hijo1.setOperador(2);
				hijo1.setPadre(padr);
				if(menu.getMetodo().equals("1")) {hijo1.setF(hijo1.getG()+hijo1.getH());
				}
						else if(menu.getMetodo().equals("2")) {hijo1.setF(hijo1.getG());}
						else if(menu.getMetodo().equals("3")) {hijo1.setF(hijo1.getH());}	
//					System.out.println(hijo1.getF());

				//comparo si el padre es igual que el hijo
				if(!Puzzle.comparaPosiciones(padr.getPuzzle(), hijo1.getPuzzle())) {
					
					//compruebo si esta en cerrados (1)
					if(!gestionarCola_Cerrados( hijo1)) {
						if(!gestionarColaAbiertos( hijo1));
					}
					
					
				}
				else {
//						System.out.println("Hijo igual que el padre");
					}
				
				
				
				
				
				
		
		//TRASLADO ARRIBA
	
		
		hijo1=new Nodo();
		p=padr.getPuzzle().transladar(padr.getPuzzle(),-1,0);
		hijo1.setPuzzle(p);
//		System.out.println("\n Sucesor" );
		hijo1.setH(hijo1.getPuzzle().Heuristico(hijo1.getPuzzle()));
		hijo1.setG(padr.getG()+1);
		hijo1.setPadre(padr);

		if(menu.getMetodo().equals("1")) {hijo1.setF(hijo1.getG()+hijo1.getH());
		}
		else if(menu.getMetodo().equals("2")) {hijo1.setF(hijo1.getG());}
		else if(menu.getMetodo().equals("3")) {hijo1.setF(hijo1.getH());}	
//		System.out.println(hijo1.getF());
		hijo1.setOperador(3);

		//comparo si el padre es igual que el hijo
		if(!Puzzle.comparaPosiciones(padr.getPuzzle(), hijo1.getPuzzle())) {
			
			//compruebo si esta en cerrados (1)
			
			if(!gestionarCola_Cerrados( hijo1)) {
				if(!gestionarColaAbiertos( hijo1));
					
			}
			
			
		}
		else {
//				System.out.println("Hijo igual que el padre");
			}
		
		//TRASLADO ABAJO
		hijo1=new Nodo();
		
		p=padr.getPuzzle().transladar(padr.getPuzzle(),1,0);
		hijo1.setPuzzle(p);
//		System.out.println("\n Sucesor" );
		hijo1.setH(hijo1.getPuzzle().Heuristico(hijo1.getPuzzle()));
		hijo1.setG(padr.getG()+1);
		if(menu.getMetodo().equals("1")) {hijo1.setF(hijo1.getG()+hijo1.getH());
		}
		else if(menu.getMetodo().equals("2")) {hijo1.setF(hijo1.getG());}
		else if(menu.getMetodo().equals("3")) {hijo1.setF(hijo1.getH());}	
		hijo1.setPadre(padr);
		hijo1.setOperador(4);

//		System.out.println(hijo1.getF());

		//comparo si el padre es igual que el hijo
		if(!Puzzle.comparaPosiciones(padr.getPuzzle(), hijo1.getPuzzle())) {
			
		
			if(!gestionarCola_Cerrados( hijo1)) {
				if(!gestionarColaAbiertos( hijo1));
			}
			
			
		}
		else {
//				System.out.println("Hijo igual que el padre");
			}
		
		
		
		
		
		
		
		
		
	
		
		
		
			Collections.sort(listaAbiertos);
//			System.out.println("a" + listaAbiertos.size());

//			System.out.println(" b "+ listaAbiertos.size());

//		return listaAbiertos;
		
	}	

	
	public Nodo escoger_nodo(String metodo,Nodo n) {
		
		
		if(metodo.equals("1")){
//			System.out.println("c " +listaAbiertos.size());
			n=listaAbiertos.get(0);
//			System.out.println(n.getH() + " " + n.getG() + " " + n.getF());
			
		}
		else if(metodo.equals("2")){
//			System.out.println("c " +listaAbiertos.size());
			n=listaAbiertos.get(0);
//			System.out.println(n.getH() + " " + n.getG() + " " + n.getF());
			
			
		}
		else if(metodo.equals("3")){
			
//			System.out.println("c " +listaAbiertos.size());
			n=listaAbiertos.get(0);
//			System.out.println(n.getH() + " " + n.getG() + " " + n.getF());
			
		}
		return n;
	
		
		
		
	}
	
	public int getOperador() {
		return operador;
	}

	public void setOperador(int operador) {
		this.operador = operador;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getF() {
		return f;
	}

	public void setF(double f) {
		this.f = f;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}

	public Puzzle getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Puzzle objetivo) {
		this.objetivo = objetivo;
	}

	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	

	public List<Nodo> getListaAbiertos() {
		return listaAbiertos;
	}

	public void setListaAbiertos(ArrayList<Nodo> listaAbiertos) {
		this.listaAbiertos = listaAbiertos;
	}

	public List<Nodo> getListaCerrados() {
		return listaCerrados;
	}

	public void setListaCerrados(ArrayList<Nodo> listaCerrados) {
		this.listaCerrados = listaCerrados;
	}


	public int compareTo(Nodo comparestu) {
		if(menu.getMetodo().equals("1")) {
        double compareage=((Nodo)comparestu).getF();
        /* For Ascending order*/
        return (int) (this.f-compareage);
		}
		else if(menu.getMetodo().equals("2")) {
			 double compareage=((Nodo)comparestu).getF();
		        /* For Ascending order*/
		        return (int) (this.f-compareage);
		}
		else if(menu.getMetodo().equals("3")){
			 double compareage=((Nodo)comparestu).getF();
		        /* For Ascending order*/
		        return (int) (this.f-compareage);
				}
       
		return 0;
    }
	
	
	public int recorrerCamino(Nodo hijo) {
		boolean b=true;
		while(b) {
//			System.out.println(hijo.getPadre());
			hijo=hijo.getPadre();
			camino.add(hijo);
			if(hijo.getPadre()==null) {
				b=false;
			}
		}
		return 0;
	}
	
	
	public void imprimePadres(List<Nodo> camino) {
//		for(int i=0;i<camino.size();i++) {
		System.out.println("--------CAMINO SOLUCION------------\n");

			for(int i = camino.size() - 1; i >= 0; i--){
			System.out.println("---Paso" + (camino.size()- i) + "---\n");

			Puzzle.imprimePuzzle(camino.get(i).getPuzzle().getPuzzleActual());
			System.out.println("F(n) = " + camino.get(i).getF());
			System.out.println("G(n) = " + camino.get(i).getG());
			System.out.println("H(n) = " + camino.get(i).getH());

			String op;
			switch (camino.get(i).getOperador()) {
			case 1:
				op="derecha";
				break;
			case 2:
				op="izquierda";
				break;
			case 3:
				op="arriba";
				break;
			case 4:
				op="abajo";
				break;
			default:
				op="Nodo Inicial";
			}
			System.out.println(op);
		}
		}

	
	public void imprimeCamino(List<Nodo> camino) {
		for(int i=0;i<camino.size();i++) {
			String op;
			switch (camino.get(i).getOperador()) {
			case 1:
				op="derecha";
				break;
			case 2:
				op="izquierda";
				break;
			case 3:
				op="arriba";
				break;
			case 4:
				op="abajo";
				break;
			default:
				op="Nodo Inicial";
			}
			System.out.println(op);
		}
	}
	
	
	public void imprimeExplorados() {
		System.out.println("--------NODOS EXPLORADOS------------\n");

		for(int i = listaCerrados.size() - 1; i >= 0; i--){
			System.out.println("--------PASOS" + (listaCerrados.size()- i) + "------------\n");

			Puzzle.imprimePuzzle(listaCerrados.get(i).getPuzzle().getPuzzleActual());
			System.out.println("F(n) = " + listaCerrados.get(i).getF());
			System.out.println("G(n) = " + listaCerrados.get(i).getG());
			System.out.println("H(n) = " + listaCerrados.get(i).getH());
			String op;
			switch (listaCerrados.get(i).getOperador()) {
			case 1:
				op="derecha";
				break;
			case 2:
				op="izquierda";
				break;
			case 3:
				op="arriba";
				break;
			case 4:
				op="abajo";
				break;
			default:
				op="Nodo Inicial";
			}
			System.out.println(op);
		}
		}

	
}
