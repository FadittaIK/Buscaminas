    package src.model;

    import src.Vista.Terminal;

    public class Juego {

        private  final int FILAS = 8;
        private  final int COLUMNAS = 8;
        private  final int MINAS = 10;
        private int[] guardarElValorInicial;

        private Terminal terminal;
        private Tablero tablero;

        public Juego() {
            this.guardarElValorInicial = new int[2];
            this.terminal = new Terminal();
            this.tablero = new Tablero(FILAS,COLUMNAS,MINAS);

        }


        public void inicioDelJuego() {


            boolean primeraCasillaElegida = true;

            terminal.pintarTablero(tablero.casillas);
            boolean salir = false;

            while (!salir) {


                terminal.introduceUnaFila();
                int fila = terminal.pedirFila();
                terminal.introduceUnaColumna();
                int columna = terminal.pedirColumna();

                if (tablero.existeEnElTablero(fila, columna)) {

                    if (primeraCasillaElegida) {//-->la primera casilla que elige el usuario siempre tendrá el valor de 0
                        guardarLasCoordenasDeLaPrimeraCasilla(fila, columna);
                        mostrarElTablero(fila, columna);
                        primeraCasillaElegida = false;//solo guarda las coordenadas de la primera casilla elegida
                    } else{
                        if (realizarJuego(fila,columna)){
                            salir = true;
                        }
                        if (haGanado()){
                            terminal.victoria();
                            terminal.pintarTableroDeMinas(tablero.casillas);
                            salir = true;
                        }
                    }
                }
            }
        }
        public boolean realizarJuego(int fila,int columna){
            if (tieneMina(fila, columna)) {
                terminal.encontrarUnaMina();
                terminal.pintarTableroDeMinas(tablero.casillas);
                return true;
            } else if (esClicada(fila, columna)) {
                terminal.posicionHaSidoClicada();
            } else {
                mostrarElTablero(fila, columna);
            }
            return false;
        }


        private void guardarLasCoordenasDeLaPrimeraCasilla(int fila, int columna) {
            setElValorInicial(fila,columna);
            asignarNumerosALasCasillas();
        }
        private void setElValorInicial(int fila, int columna) {
            guardarElValorInicial[0] = fila;
            guardarElValorInicial[1] = columna;
        }


        private void mostrarElTablero(int fila, int columna) {
            tablero.casillas[fila][columna].setClicada(true);
            destaparLasCasillas(fila, columna);
            terminal.pintarTablero(tablero.casillas);
        }

        private void ponerMinasAleatoriamente() {
            final int TOTAL_MINAS = 10;
            int contador = 0;
            while (contador < TOTAL_MINAS) {
                int numero1 = (int) (Math.random() * (tablero.casillas.length - 1));
                int numero2 = (int) (Math.random() * (tablero.casillas.length - 1));
                if (!tieneMina(numero1, numero2)  && !posicionCercaDelValorInicial(numero1, numero2)) {
                    tablero.casillas[numero1][numero2].setFormaMina(terminal.getMina());
                    tablero.casillas[numero1][numero2].setTieneMina(true);
                    contador++;
                }
            }
        }



        private boolean posicionCercaDelValorInicial(int i, int j) {//verifica las casillas vecinas de la casilla inicial
            for (int f = -1; f <= 1; f++) {
                for (int l = -1; l <= 1; l++) {
                    int valorFila = i + f;
                    int valorColumna = j + l;
                    if (tablero.existeEnElTablero(valorFila, valorColumna)) {
                        if (guardarElValorInicial[0] == valorFila && guardarElValorInicial[1] == valorColumna) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private Casilla[][] asignarNumerosALasCasillas() {

            ponerMinasAleatoriamente();

            for (int i = 0; i < tablero.casillas.length; i++) {
                for (int j = 0; j < tablero.casillas[0].length; j++) {
                    int contadorDeMinas = 0;//para contar cada casilla sola
                    if (!tieneMina(i,j)) {
                        for (int k = -1; k <= 1; k++) {
                            for (int l = -1; l <= 1; l++) {
                                int valorFila = i + k;
                                int valorColumna = j + l;
                                if (tablero.existeEnElTablero(valorFila, valorColumna)) {
                                    //cuantas minas estan alrededor de la casilla[i][j]
                                    if (tieneMina(valorFila, valorColumna)) {
                                        contadorDeMinas++;
                                    }
                                }
                            }
                        }
                        tablero.casillas[i][j].setValor(contadorDeMinas);
                        if (contadorDeMinas ==  0) {
                            tablero.casillas[i][j].setEsVacia(true);
                        }
                    }
                }
            }
            return tablero.casillas;
        }

        private boolean esClicada(int fila, int columna) {
            return tablero.casillas[fila][columna].isClicada();
        }

        private boolean tieneMina(int fila, int columna) {
            return tablero.casillas[fila][columna].isTieneMina();
        }


        private void destaparLasCasillas(int filaInicio, int columnaInicio) {

            if (tablero.existeEnElTablero(filaInicio, columnaInicio)) {
                boolean esPosicionVacia = tablero.casillas[filaInicio][columnaInicio].isEsVacia();
                if (esPosicionVacia && esClicada(filaInicio, columnaInicio)) {//si el valor de la casilla es 0
                    for (int f = -1; f <= 1; f++) {
                        for (int l = -1; l <= 1; l++) {
                            int valorFila = filaInicio + f;
                            int valorColumna = columnaInicio + l;
                            if (tablero.existeEnElTablero(valorFila, valorColumna)) {
                                if (!tieneMina(valorFila, valorColumna) && !esClicada(valorFila, valorColumna)) {
                                    tablero.casillas[valorFila][valorColumna].setClicada(true);
                                        destaparLasCasillas(valorFila, valorColumna);
                                }
                            }
                        }
                    }
                }
            }
        }



        private boolean haGanado() {

            int contadorDeCasillas = 0;
            int totalCasillasSinMinas = (tablero.casillas.length * tablero.casillas[0].length) - MINAS;
            for (int i = 0; i < tablero.casillas.length; i++) {
                for (int j = 0; j < tablero.casillas.length; j++) {
                    if (esClicada(i, j) && !tieneMina(i, j)) {
                        contadorDeCasillas++;
                    }
                }
            }
            return contadorDeCasillas == totalCasillasSinMinas;
        }

    }
