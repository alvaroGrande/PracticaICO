package es.ufv.ico.algoritmo;

import java.util.ArrayList;

public class Nodo  {

	private double g;
	private double h;
	private double f;
	
	private Puzzle puzzle;
	private Puzzle objetivo;

	
	private Nodo padre ;
	
	
	/*Cada nodo contiene el Puzzle ,  G=Coste , H=Heuristica y F*/
	public Nodo(Puzzle puzzle_actual,int g , int h , int f) {
		
		ArrayList<Nodo> sucesores= new ArrayList<>();
		Nodo n = new Nodo();
		n.setPuzzle(puzzle_actual);
		n.setH(n.getPuzzle().Heuristico(puzzle_actual));
		n.setG(0);
		double sumaG=n.getF()+n.getH();
		n.setF( sumaG);
		System.out.println(n.getH());

		sucesores=explorar_sucesores(n);
		
	}
	
	public Nodo() {
		super();
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
	public ArrayList<Nodo> explorar_sucesores(Nodo padre){
		
		ArrayList<Nodo> sucesores= new ArrayList<>();
		
		Puzzle p;
		Nodo hijo;
		
		//1.copio el puzzle actual 
		//2.determino la posicion de la ficha 0
		//3.creo los hijos en funcion de si se puede mover :IZQ,DERECHA,ARRIBA,ABAJO
		//para cada movimiento creo un Hijo
		//4.Comparo si es igual que el padre
		//5.si no son iguales calculo H y le agrego su padre
		//lo agrego a array Sucesores
		
		hijo=new Nodo();
		p=padre.getPuzzle().transladar(padre.getPuzzle(),1, 0);
		hijo.setPuzzle(p);
		if(p.comparaPosiciones(padre.getPuzzle(), hijo.getPuzzle())) {
			hijo.getPuzzle().imprimePuzzle(hijo.getPuzzle().getPuzzleActual());
			hijo.setH(hijo.getPuzzle().Heuristico(hijo.getPuzzle()));
			
			hijo.setPadre(padre);
			sucesores.add(hijo);
			System.out.println(hijo.getH());

		}
		else {
			System.out.println("Hijo es igual que el padre");
		}
		
		
		
		
		
		
		
		
		hijo=new Nodo();
		p=padre.getPuzzle().transladar(padre.getPuzzle(),-1,0);
		hijo.setPuzzle(p);
		if(p.comparaPosiciones(padre.getPuzzle(), hijo.getPuzzle())) {
			hijo.getPuzzle().imprimePuzzle(hijo.getPuzzle().getPuzzleActual());
			hijo.setH(hijo.getPuzzle().Heuristico(hijo.getPuzzle()));
			
			hijo.setPadre(padre);
			sucesores.add(hijo);
			System.out.println(hijo.getH());

		}
		else {
			System.out.println("Hijo es igual que el padre");
		}
		
		
		
		
		hijo=new Nodo();
		p=padre.getPuzzle().transladar(padre.getPuzzle(),0,-1);
		hijo.setPuzzle(p);
		if(p.comparaPosiciones(padre.getPuzzle(), hijo.getPuzzle())) {
		hijo.getPuzzle().imprimePuzzle(hijo.getPuzzle().getPuzzleActual());
		hijo.setH(hijo.getPuzzle().Heuristico(hijo.getPuzzle()));
			
		hijo.setPadre(padre);
		sucesores.add(hijo);
		System.out.println(hijo.getH());

		}
		else {
			System.out.println("Hijo es igual que el padre");
		}
		
		

		
		
		
		
		
		hijo=new Nodo();
		p=padre.getPuzzle().transladar(padre.getPuzzle(),0,1);
		hijo.setPuzzle(p);

		if(p.comparaPosiciones(padre.getPuzzle(), hijo.getPuzzle())) {
			hijo.getPuzzle().imprimePuzzle(hijo.getPuzzle().getPuzzleActual());
			hijo.setH(hijo.getPuzzle().Heuristico(hijo.getPuzzle()));
			
			hijo.setPadre(padre);
			sucesores.add(hijo);
			System.out.println(hijo.getH());

		}
		else {
			System.out.println("Hijo es igual que el padre");
		}
		
		
		


		
		
		return sucesores;
		
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
	
	
	
	
	
	
}
