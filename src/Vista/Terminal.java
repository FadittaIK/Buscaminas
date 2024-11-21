package src.Vista;

import src.model.Casilla;

import java.util.Scanner;

public class Terminal implements Interface {
    Scanner sc;

    private char mina ;
    private char noClicada;
    public Terminal(){
        this.mina =  '*';
        this.noClicada = '.';
        this.sc = new Scanner(System.in);
    }

    public void pintarTablero(Casilla[][] casillas) {

        System.out.print("   ");
        for (int i = 0; i < casillas.length; i++) {//para pintar los numeros de columnas
            System.out.print("  " + i + " ");
        }
        System.out.println();
        for (int i = 0; i < casillas.length; i++) {
            System.out.print(i + "  ");//pintar los numeros de columnas
            for (int j = 0; j < casillas[0].length; j++) {
                casillas[i][j].setClicada(false);

                System.out.print(" |");
                if (casillas[i][j].isClicada()) {
                    System.out.print(casillas[i][j].getValor() + "|");
                }else {
                    System.out.print(casillas[i][j].getValor() + "|");
                }
            }
            System.out.println();
        }
    }
    public char getMina() {
        return mina;
    }

    public void pintarTableroDeMinas(Casilla casillas[][]) {
        System.out.print("   ");
        for (int i = 0; i < casillas.length; i++) {//para pintar los numeros de columnas
            System.out.print("  " + i + "  ");
        }
        System.out.println();
        for (int i = 0; i < casillas.length; i++) {
            System.out.print(i + "  ");//pintar los numeros de columnas

            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j].setFormaMina(mina);
                if (casillas[i][j].isTieneMina()) {
                    System.out.print(" |" + casillas[i][j].getFormaMina() + " |");
                } else if (casillas[i][j].isClicada()) {
                    System.out.print(" |" + casillas[i][j].getValor() + " |");
                }else {
                    System.out.print(" |"+casillas[i][j].getValor()+" |");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void introduceUnaFila() {
        System.out.println("Introduce una Fila: ");
    }

    @Override
    public void introduceUnaColumna() {
        System.out.println("Introduce una Columna: ");
    }
    public void posicionHaSidoClicada(){
        System.out.println("esta posición ya esta clicada elige otra");
        System.out.println("    --------------------");
    }
    @Override
    public int pedirColumna() {
        int columna = 0;
        boolean columnaValida = false;
        while (!columnaValida) {
            try {
                columna = sc.nextInt();
                    columnaValida = true;

            } catch (Exception e) {
                System.out.println("el valor introducido debe ser un entero[0-7]");
                System.out.println("    --------------------");
                System.out.println("Introduce otro valor:");
                sc.next();//para extraer los valores introducidos invalidos  hasta que se encuentra un salto de linea [y limpiar la linea]
            }
        }
        return columna;
    }
    public void invalidPosicion(){
        System.out.println("El numero introducido no es valido , elige una valor entre [0-7]");
        System.out.println("   -------------------- ");
        System.out.println("Introduce una Columna");
    }
    @Override
    public int pedirFila() {
        int fila = 0;
        boolean filaValida = false;
        while (!filaValida) {
            try {
                fila = sc.nextInt();
                    filaValida = true;
            } catch (Exception e) {
                System.out.println("el valor introducido debe ser un entero[0-7]");
                System.out.println("    --------------------");
                System.out.println("Introduce otro valor:");
                sc.next();//para extraer los valores introducidos invalidos  hasta que se encuentra un salto de linea [y limpiar la linea]
            }
        }
        return fila;
    }
    @Override
    public void encontrarUnaMina() {
        System.out.println("has perdido, has encontrado un mina");
    }

    @Override
    public void victoria(){
        System.out.println("   --------------------   ");
        System.out.println("HAS GANADO");
    }

}
