package src.model;

import src.Vista.Terminal;

public class Tablero {
    Terminal terminal = new Terminal();
    private int fila;
    private int columna;
    private int numeroDeMinas;

    public   Casilla casillas[][];


    public Tablero(int fila, int columna, int numeroDeMinas) {
        this.fila = fila;
        this.columna = columna;
        this.numeroDeMinas = numeroDeMinas;
        this.casillas = new Casilla[fila][columna];
        inicializarElTablero();
    }

    public void inicializarElTablero() {
        for (int i = 0; i < this.casillas.length; i++) {
            for (int j = 0; j < this.casillas[0].length; j++) {
                this.casillas[i][j] = new Casilla();
            }
        }
    }



    public boolean existeEnElTablero(int fila, int columna) {
        return (!(fila < 0 || columna < 0 || fila > casillas.length - 1 || columna > casillas.length - 1));
    }


}
