package es.ufv.ico.algoritmo;

import java.io.IOException;
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
	public Nodo(Puzzle puzzle_actual,double g , double h , double f,ArrayList<Nodo> suc,Nodo padr,int operador) throws IOException  {
		Nodo n = new Nodo();
		n.setPuzzle(puzzle_actual);
		n.setH(n.getPuzzle().Heuristico(puzzle_actual));
		n.setG(0);
		
		if(menu.getMetodo().equals("1")) {n.setF(n.getG()+n.getH());}
		else if(menu.getMetodo().equals("2")) {n.setF(n.getG());}
		else if(menu.getMetodo().equals("3")) {n.setF(n.getH());}
		
		n.setPadre(null);
		listaAbiertos.add(n);
		generados=generados+1;
		while(!n.getPuzzle().comparaPuzzle(n.getPuzzle())) {
		System.out.println("Padre ");
		Puzzle.imprimePuzzle(n.getPuzzle().getPuzzleActual());
		System.out.println(n.getOperador());
		explorar_sucesores(n);
		n=escoger_nodo(menu.getMetodo(), n);
		System.out.println(n.getPadre());
			
}	
		System.out.println(n.getOperador() +"\n");
		
		System.out.println("Acabado , nodo ganador ");
		Puzzle.imprimePuzzle(n.getPuzzle().getPuzzleActual());
		listaCerrados.add(n);
		System.out.println("Nº Nodos generados = " + generados);
		System.out.println("Nº Nodos expandidos = " + explorados);
		camino.add(n);
		
		recorrerCamino(n);
		imprimeExplorados(listaCerrados);
		imprimePadres(camino);
		imprimeCamino(camino);
		int ultimo=camino.size()-1;
		System.out.println(camino.get(0).getH());
		Puzzle.bw.write(Puzzle.newLine+"HEURISTICA  ESTADO INICIAL " + camino.get(0).getH() + "   NUMERO DE MOVIMIENTOS" + Integer.toString(explorados)+ "    NODOS GENERADOS " + Integer.toString(generados) + Puzzle.newLine + Puzzle.newLine + Puzzle.newLine + Puzzle.newLine);

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
				listaAbiertos.add(hijo);

				listaCerrados.remove(i);
				generados=generados+1;
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
				generados=generados+1;
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
						if(menu.getMetodo().equals("1")) {
							if(!gestionarColaAbiertos( hijo1));
							}
							else{
								listaAbiertos.add(hijo1);
								generados=generados+1;
							}
					
					
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
						if(menu.getMetodo().equals("1")) {
							if(!gestionarColaAbiertos( hijo1));
							}
							else{
								generados=generados+1;
								listaAbiertos.add(hijo1);
							}
					
					
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
				if(menu.getMetodo().equals("1")) {
					if(!gestionarColaAbiertos( hijo1));
					}
					else{
						listaAbiertos.add(hijo1);
						generados=generados+1;
					}
			
			
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
				if(menu.getMetodo().equals("1")) {
				if(!gestionarColaAbiertos( hijo1));
				}
				else{
					listaAbiertos.add(hijo1);
					generados=generados+1;
				}
			}
			
			
		}
		else {
//				System.out.println("Hijo igual que el padre");
			}
		
		
		
		
		
		
		
		
		
	
		
		
		
			Collections.sort(listaAbiertos);

		
	}	

	
	public Nodo escoger_nodo(String metodo,Nodo n) {
		
		
		if(metodo.equals("1")){
			n=listaAbiertos.get(0);
			
		}
		else if(metodo.equals("2")){
			n=listaAbiertos.get(0);
			
			
		}
		else if(metodo.equals("3")){
			
			n=listaAbiertos.get(0);
			
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


	public int compareTo(Nodo nodo) {
		if(menu.getMetodo().equals("1")) {
        double comparador=((Nodo)nodo).getF();
        /* For Ascending order*/
        return (int) (this.f-comparador);
		}
		else if(menu.getMetodo().equals("2")) {
			 double comparador=((Nodo)nodo).getF();
		        /* For Ascending order*/
			 	if((this.f-comparador)==0) {
			 		int c=((Nodo)nodo).getOperador();
			 		return (int) this.operador-c;
			 	}else {
		        return (int) (this.f-comparador);
			 	}
			 	}
		else if(menu.getMetodo().equals("3")){
			 double compareage=((Nodo)nodo).getF();
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
	
	/*CAMINO EN PUZZLES HASTA LA SOLUCION */
	
	public void imprimePadres(List<Nodo> camin) throws IOException {
		Collections.reverse(camin);
		//		for(int i=0;i<camino.size();i++) {
		System.out.println("--------CAMINO SOLUCION------------\n" );
		Puzzle.bw.write("--------CAMINO SOLUCION------------" + Puzzle.newLine +Puzzle.newLine +Puzzle.newLine);
			for(int i = camin.size() ; i > 0; i--){
			System.out.println("*******PASO " + (i-1) + "*******");
			Puzzle.bw.write("---Paso" + Integer.toString(camin.size()-i) + "---\n");
			Puzzle.imprimePuzzle(camin.get((camin.size()-i)).getPuzzle().getPuzzleActual());
			Puzzle.escribePuzzle(camin.get((camin.size()-i)).getPuzzle().getPuzzleActual());

			System.out.println("F(n) = " + camin.get((camin.size()-i)).getF() + " ");
			System.out.println("G(n) = " + camin.get((camin.size()-i)).getG()+ " ");
			System.out.println("H(n) = " + camin.get((camin.size()-i)).getH());
			
			
			Puzzle.bw.write("F(n) = " + camin.get((camin.size()-i)).getF()+ " ");
			Puzzle.bw.write("G(n) = " + camin.get((camin.size()-i)).getG()+ " ");
			Puzzle.bw.write("H(n) = " + camin.get((camin.size()-i)).getH()+ " ");
			
			String op;
			switch (camin.get((camin.size()-i)).getOperador()) {
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
			System.out.println(op+ " ");
			Puzzle.bw.write(op+ " ");
			Puzzle.bw.write(Puzzle.newLine +Puzzle.newLine);

		}
			
			System.out.println("--------Nº PASOS DEL CAMINO " + (camin.size()-1) + "------------\n");
			Puzzle.bw.write("--------Nº PASOS DEL CAMINO " + Integer.toString((camin.size()-1)) + "------------" + Puzzle.newLine);
		}

	/*CAMINO EN PASOS HASTA LA SOLUCION*/
	public void imprimeCamino(List<Nodo> camino) throws IOException {
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

			Puzzle.bw.write(op + " ->" );
			System.out.println(op);
		}
	}
	
	
	/*PASOS EN LA BUSQUEDA DEL CAMINO = NUMERO DE NODOS EXPLORADOS*/
	public void imprimeExplorados(List<Nodo> listaCerrado) {
		Collections.reverse(listaCerrado);
		System.out.println("--------NODOS EXPLORADOS------------\n");
		for(int i=0;i< listaCerrado.size();i++) {
	//	for(int i = listaCerrado.size()-1 ; i >=0; i--){
			System.out.println("---Paso" + (listaCerrado.size() - i) + "---\n");

			Puzzle.imprimePuzzle(listaCerrado.get(i).getPuzzle().getPuzzleActual());
			System.out.println("F(n) = " + listaCerrado.get(i).getF());
			System.out.println("G(n) = " + listaCerrado.get(i).getG());
			System.out.println("H(n) = " + listaCerrado.get(i).getH());

			String op;
			switch (listaCerrado.get(i).getOperador()) {
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
			
			System.out.println("--------Nº NODOS EXPLORADOS " + (listaCerrado.size()) + "------------\n\n\n\n\n\n \n");

		}


	
}
