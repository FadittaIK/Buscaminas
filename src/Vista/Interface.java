package src.Vista;

import src.model.Casilla;

public  interface Interface {
    void pintarTablero(Casilla casilla[][]);
    void introduceUnaFila();
    void introduceUnaColumna();
    int pedirFila();
    int pedirColumna();
    void encontrarUnaMina();
    void  victoria();

}
