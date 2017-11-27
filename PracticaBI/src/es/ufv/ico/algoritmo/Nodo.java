package es.ufv.ico.algoritmo;

import java.util.ArrayList;

public class Nodo  {

	private int g;
	private int h;
	private int f;
	
	private Puzzle puzzle;
	private Puzzle objetivo;
	private ArrayList<Nodo> hijos;
	
	private Nodo padre ;
	
	
	/*Cada nodo contiene el Puzzle ,  G=Coste , H=Heuristica y F*/
	public Nodo(Puzzle puzzle_actual,int g , int h , int f) {
		
		System.out.println("aa " + puzzle_actual.getPuzzleActual()[1][1]);
		ArrayList<Nodo> hijos= new ArrayList<>();
		Nodo n = new Nodo();
		n.setPuzzle(puzzle_actual);
		hijos=explorar_hijos(n);
		
		
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
	public ArrayList<Nodo> explorar_hijos(Nodo padre){
		
		
		Puzzle p;
		Nodo hijo;
		
		//copio el puzzle actual 
		//determino la posicion de la ficha 0
		//creo los hijos en funcion de si se puede mover :IZQ,DERECHA,ARRIBA,ABAJO
		//para cada movimiento creo un Hijo
		
		
		hijo=new Nodo();
		p=padre.getPuzzle().transladar(padre.getPuzzle(),1, 0);
		hijo.setPuzzle(p);
		hijo.getPuzzle().imprimePuzzle(hijo.getPuzzle().getPuzzleActual());
		hijo.setPadre(padre);
		hijos.add(hijo);

		
		hijo=new Nodo();
		p=padre.getPuzzle().transladar(padre.getPuzzle(),-1,0);
		hijo.setPuzzle(p);
		hijo.getPuzzle().imprimePuzzle(hijo.getPuzzle().getPuzzleActual());
		hijo.setPadre(padre);
		hijos.add(hijo);

		
		hijo=new Nodo();
		p=padre.getPuzzle().transladar(padre.getPuzzle(),0,-1);
		hijo.setPuzzle(p);
		hijo.getPuzzle().imprimePuzzle(hijo.getPuzzle().getPuzzleActual());
		hijo.setPadre(padre);
		hijos.add(hijo);

		
		hijo=new Nodo();
		p=padre.getPuzzle().transladar(padre.getPuzzle(),0,1);
		hijo.setPuzzle(p);
		hijo.getPuzzle().imprimePuzzle(hijo.getPuzzle().getPuzzleActual());
		hijo.setPadre(padre);
		hijos.add(hijo);


		
		hijos.add(hijo);
		
		return hijos;
		
	}
	
	
	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
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
