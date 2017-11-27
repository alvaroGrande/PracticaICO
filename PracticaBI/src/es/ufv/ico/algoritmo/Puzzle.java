package es.ufv.ico.algoritmo;
import es.ufv.ico.algoritmo.menu;
import java.util.Scanner;

public class Puzzle {

	private static int puzzleInicial[][] = new int[3][3];
	private int[][] puzzleActual;
	
	private  int x0; //coordenada x ficha 0
	private  int y0; //coordenada y ficha 0
	
	
	static Nodo n;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		menu m = new menu();
		int [][]puzzleActual;
		puzzleInicial=m.getMatriz();
		puzzleActual=puzzleInicial;

		Puzzle p = new Puzzle();
		p.setPuzzleActual(puzzleActual);
		p.calcula0(puzzleActual);
		
		
		Nodo n = new Nodo(p,0,0,0);

	}
	
	

	



	public Puzzle( int [][] puzzle,int x0,int y0){
		super();
		this.puzzleActual=puzzle;
		this.x0=x0;
		this.y0=y0;
		calcula0(puzzleActual);
	}
	
	
	public Puzzle() {
		super();
	}
	
	
	








	public void calcula0(int[][] puzzle) {
		
	int[][] p= new int[3][3];
	for(int i=0;i<3;i++) {
		for(int j=0;j<3;j++) {
			p[i][j]=puzzleActual[i][j];
			
			if(p[i][j]==0) {
				x0=i;
				y0=j;
			}
		}
	}
}

	
	
	public Puzzle transladar(Puzzle padre , int x , int y) {
		int [][]posicionesNuevo = new int[3][3];
		
		Puzzle puzzleNuevo = new Puzzle();
		puzzleNuevo.setPuzzleActual(copiaPuzzle(padre.getPuzzleActual(), posicionesNuevo));
		puzzleNuevo.setX0(x0);
		puzzleNuevo.setY0(y0);

		

		boolean b=false;
		
		//transladar horizontalmente el 0
		if(x!=0) {
			
			//derecha ->
			if(x>0 && x0<2) {
				b=true;
				if(b) {
				
				puzzleNuevo.getPuzzleActual()[x0][y0]=padre.getPuzzleActual()[x0+1][y0];
				puzzleNuevo.getPuzzleActual()[x0+1][y0]=0;
				puzzleNuevo.setX0(x0+1);
				return puzzleNuevo;
				}
			}
			//izquierda <-
			else if(x<0 && x0>0) {
				b=true;
					if(b) {
				
					puzzleNuevo.getPuzzleActual()[x0][y0]=padre.getPuzzleActual()[x0-1][y0];
					puzzleNuevo.getPuzzleActual()[x0-1][y0]=0;
					puzzleNuevo.setX0(x0-1);
					return puzzleNuevo;
				}
			}
			
		}
		//translado vertical del 0
		else if(y!=0) {
			if(y<0 && y0>0)
				b=true;

				if(b) {

					puzzleNuevo.getPuzzleActual()[x0][y0]=padre.getPuzzleActual()[x0][y0-1];
					puzzleNuevo.getPuzzleActual()[x0][y0-1]=0;
					puzzleNuevo.setY0(y0-1);
				
				
				return puzzleNuevo;
				}

			
				else if(y>0 && y0<2) {
				b=true;
					if(b) {
				
					puzzleNuevo.getPuzzleActual()[x0][y0]=padre.getPuzzleActual()[x0][y0+1];
					puzzleNuevo.getPuzzleActual()[x0][y0+1]=0;
					puzzleNuevo.setY0(y0+1);
					
				
				return puzzleNuevo;
				}
			}
		}
		return puzzleNuevo;
		
	}
	
	public int[][] copiaPuzzle(int[][]puzzleActual,int[][] puzzleCopia) {
		
		for(int i=0;i<puzzleActual.length;i++) {
			for(int j=0;j<puzzleActual[i].length;j++) {
				puzzleCopia[i][j]=puzzleActual[i][j];
			}
		}
		
		return puzzleCopia;
		
	}
	public void imprimePuzzle(int[][] puzzle) {
		System.out.println("/------------------/");
		for(int i= 0; i<3; i++) {
			for(int j= 0; j<3; j++) {
				System.out.print(" | ");
				System.out.print(puzzle[i][j]);
				System.out.print(" | ");
			}
			System.out.println("");
		}
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

}
