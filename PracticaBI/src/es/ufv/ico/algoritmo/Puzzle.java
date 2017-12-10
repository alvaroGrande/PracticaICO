package es.ufv.ico.algoritmo;

import es.ufv.ico.algoritmo.menu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Puzzle {

	private static int puzzleInicial[][] = new int[3][3];
	private int[][] puzzleActual;
	private static int[][] puzzleSolucion = new int[3][3];;
	public static final String ruta_fichero = "C:/Users/Jorge Gonzalez.JORGEGONZALEZ/Desktop/fichero.txt";
	
	public static final File archivo = new File(ruta_fichero);
	private int x0; // coordenada x ficha 0
	private int y0; // coordenada y ficha 0

	static Nodo n;
	public static   String newLine = System.getProperty("line.separator");
	public static FileWriter fw;
	public static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		fw = new FileWriter(archivo);
		bw= new BufferedWriter(fw);
		
		menu m = new menu();
		int[][] puzzleActual;
//		SimpleDateFormat fecha1= new SimpleDateFormat("dd-MM-yyyy");
		DateFormat fecha1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		bw.write(newLine);
		Date fecha =new Date();
		bw.write("Jorge Gonzalez Pizarro" +" , Grupo Predeterminado ,"+" Fecha de ejecución = "+ (fecha1.format(fecha)) + newLine + newLine);
		
//		puzzleInicial = m.getMatriz();
//		puzzleActual = puzzleInicial;
//		rellenaPuzzleSol();
//		
//		Puzzle p = new Puzzle();
//		System.out.println("Puzzle Solucion : "  );
//		imprimePuzzle(puzzleSolucion);
//		
//		p.setPuzzleActual(puzzleActual);
//		p.calcula0(puzzleActual);
//		escribePuzzle(puzzleActual);
		
			Puzzle p = new Puzzle();
			
		if(menu.getMetodo().equals("1") || menu.getMetodo().equals("3")) {
			menu.setHeuristica("1");
			for(int i=0;i<2;i++) {
				puzzleInicial = m.getMatriz();
				puzzleActual = puzzleInicial;
				rellenaPuzzleSol();
				
				
				System.out.println("Puzzle Solucion : "  );
				imprimePuzzle(puzzleSolucion);
				
				p.setPuzzleActual(puzzleActual);
				p.calcula0(puzzleActual);
				bw.write("PUZZLE INICIAL  :" + newLine );
				escribePuzzle(puzzleActual);

				bw.write("Algoritmo Elegido : " + menu.getMetodo() + newLine );
				
				bw.write("Heuristica  : H" + menu.getHeuristica() + newLine +newLine +newLine  );

				Nodo n = new Nodo(p, 0, 0, 0, new ArrayList<Nodo>() , null,0);
			
			menu.setHeuristica("2");
			//bw.close();
		}
		}
			else {
				puzzleInicial = m.getMatriz();
				puzzleActual = puzzleInicial;
				rellenaPuzzleSol();
				
				
				System.out.println("Puzzle Solucion : "  );
				imprimePuzzle(puzzleSolucion);
				
				p.setPuzzleActual(puzzleActual);
				p.calcula0(puzzleActual);
				bw.write("PUZZLE INICIAL  :" + newLine );
				escribePuzzle(puzzleActual);

				bw.write("Algoritmo Elegido : " + menu.getMetodo() + newLine );
				

			
				Nodo n = new Nodo(p, 0, 0, 0, new ArrayList<Nodo>() , null,0);

			}
		
		bw.close();
		
	}

	/*
	 * El puzzle contiene psiciones y la posicion de la ficha 0
	 */

	public Puzzle(int[][] puzzle, int x0, int y0) {
		super();
		this.puzzleActual = puzzle;
		this.x0 = x0;
		this.y0 = y0;
		calcula0(puzzleActual);
	}

	public Puzzle() {
		super();
	}

	public void calcula0(int[][] puzzle) {

		int[][] p = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				p[i][j] = puzzleActual[i][j];

				if (p[i][j] == 0) {
					x0 = i;
					y0 = j;
				}
			}
		}
	}

	public Puzzle transladar(Puzzle padre, int x, int y) {
		int[][] posicionesNuevo = new int[3][3];

		Puzzle puzzleNuevo = new Puzzle();
		puzzleNuevo.setPuzzleActual(copiaPuzzle(padre.getPuzzleActual(), posicionesNuevo));
		puzzleNuevo.setX0(x0);
		puzzleNuevo.setY0(y0);

		boolean b = false;

		// transladar horizontalmente el 0
		if (x != 0) {

			// derecha ->
			if (x > 0 && x0 < 2) {
				b = true;
				if (b) {

					puzzleNuevo.getPuzzleActual()[x0][y0] = padre.getPuzzleActual()[x0 + 1][y0];
					puzzleNuevo.getPuzzleActual()[x0 + 1][y0] = 0;
					puzzleNuevo.setX0(x0 + 1);
					return puzzleNuevo;
				}
			}
			// izquierda <-
			else if (x < 0 && x0 > 0) {
				b = true;
				if (b) {

					puzzleNuevo.getPuzzleActual()[x0][y0] = padre.getPuzzleActual()[x0 - 1][y0];
					puzzleNuevo.getPuzzleActual()[x0 - 1][y0] = 0;
					puzzleNuevo.setX0(x0 - 1);
					return puzzleNuevo;
				}
			}

		}
		// translado vertical del 0
		else if (y != 0) {
			if (y < 0 && y0 > 0)
				b = true;

			if (b) {

				puzzleNuevo.getPuzzleActual()[x0][y0] = padre.getPuzzleActual()[x0][y0 - 1];
				puzzleNuevo.getPuzzleActual()[x0][y0 - 1] = 0;
				puzzleNuevo.setY0(y0 - 1);

				return puzzleNuevo;
			}

			else if (y > 0 && y0 < 2) {
				b = true;
				if (b) {

					puzzleNuevo.getPuzzleActual()[x0][y0] = padre.getPuzzleActual()[x0][y0 + 1];
					puzzleNuevo.getPuzzleActual()[x0][y0 + 1] = 0;
					puzzleNuevo.setY0(y0 + 1);

					return puzzleNuevo;
				}
			}
		}
		return puzzleNuevo;

	}

	public int[][] copiaPuzzle(int[][] puzzleActual, int[][] puzzleCopia) {

		for (int i = 0; i < puzzleActual.length; i++) {
			for (int j = 0; j < puzzleActual[i].length; j++) {
				puzzleCopia[i][j] = puzzleActual[i][j];
			}
		}

		return puzzleCopia;

	}

	public static void rellenaPuzzleSol() {
		
		puzzleSolucion[0][0]=1;
		puzzleSolucion[0][1]=2;
		puzzleSolucion[0][2]=3;
		puzzleSolucion[1][0]=8;
		puzzleSolucion[1][1]=0;
		puzzleSolucion[1][2]=4;
		puzzleSolucion[2][0]=7;
		puzzleSolucion[2][1]=6;
		puzzleSolucion[2][2]=5;
		

		
		
		
	}

	public static void imprimePuzzle(int[][] puzzle) {
		System.out.println("/------------------/");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(" | ");
				System.out.print(puzzle[i][j]);
				System.out.print(" | ");
			}
			System.out.println("");
		}
	}
	public static void escribePuzzle(int[][] puzzle) throws IOException {
		
			bw.write(newLine);

		 bw.write("/------------------/");
			bw.write(newLine);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				bw.write(" | ");
				bw.write(Integer.toString(puzzle[i][j]));
				bw.write(" | ");
			}
			bw.write(newLine);
		}
	}
	
	public boolean comparaPuzzle(Puzzle puzzle) {
		boolean b=true;
		
		for (int i = 0; i < puzzle.getPuzzleActual().length; i++) {
			for (int j = 0; j < puzzle.getPuzzleActual()[i].length; j++) {
				if(puzzleSolucion[i][j]!=puzzle.getPuzzleActual()[i][j]) {
					b=false;
				}
				
			}
		}
		
		return b;
		
		
	}
	
	public double Heuristico(Puzzle puzzle){
		 // Algoritmo que calcula la suma de las distancias manhattan
		 // hasta la solucion
		double suma = 0.0000;
		if(menu.getHeuristica()!=null) {
		if(menu.getHeuristica().equals("2")) {
		for (int i = 0; i < puzzle.getPuzzleActual().length; ++i) {
			for (int j = 0; j < puzzle.getPuzzleActual()[i].length; ++j) {
				if (puzzleSolucion[i][j] != puzzle.getPuzzleActual()[i][j]) {
					// Buscamos la posición correcta para la ficha en puzzle[i][j]
					int i2 = 0, j2 = 0;
					while (i2 < puzzleSolucion.length && (puzzleSolucion[i2][j2] != puzzle.getPuzzleActual()[i][j])) {
						j2++;
						if (j2 >= puzzleSolucion[i2].length) {
							i2++;
							j2 = 0;
						}
					}
					// En [i2][j2] está la posición correcta
					suma += Math.abs(i2 - i) + Math.abs(j2 - j);
					
				}
			}
		}
		}
		else if(menu.getHeuristica().equals("1")) {
			       
			        int numeroPuzzle = 0;
			        int numeroSolucion = 0;
			        for (int i = 0; i < puzzle.getPuzzleActual().length; i++) {
			        	for(int j=0;j<puzzle.getPuzzleActual()[i].length;j++) {
			            numeroPuzzle = puzzle.getPuzzleActual()[i][j];
			            numeroSolucion = puzzleSolucion[i][j];
			            if (numeroPuzzle != numeroSolucion) {
			                suma++;
			            }
			        }
			    
		}
		}
		}
		else {
			return 0;
		}
		return suma;
	}
	
	
	public static double Calcular_H2(Puzzle puzzle) { 
        double h2 = 0;
        int x = 0;
        int y = 0;
        int calculo_pos = 0;

        for (int i = 0; i < puzzleSolucion.length; i++) {
            for (int j = 0; j < puzzleSolucion[i].length; j++) {
                if (puzzleSolucion[i][j] == puzzle.getPuzzleActual()[i][j]) {
                    x = i ;
                    y = j;
                    if (x < 0) {
                        x = x * -1;
                    }
                    if (y < 0) {
                        y = y * -1;
                    }
                    calculo_pos = x + y;
                    h2 = h2 + calculo_pos;
                }
            }
        }
        return h2;
    }
	
	
	public static boolean comparaPosiciones( Puzzle padre,Puzzle sucesor ){
		boolean b=true;
			for (int i = 0; i < padre.getPuzzleActual().length; ++i) {
				for (int j = 0; j < padre.getPuzzleActual()[i].length; ++j) {
					if (padre.getPuzzleActual()[i][j] != sucesor.getPuzzleActual()[i][j]) {
						b=false;
					}
				}
			}
			return b;
		}
	
	

	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public int[][] getPuzzleActual() {
		return puzzleActual;
	}

	public void setPuzzleActual(int[][] puzzleActual) {
		this.puzzleActual = puzzleActual;
	}

	public  int[][] getPuzzleSolucion() {
		return puzzleSolucion;
	}

	public void setPuzzleSolucion(int[][] puzzleSolucion) {
		this.puzzleSolucion = puzzleSolucion;
	}

}
