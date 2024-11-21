package src.model;

public class Casilla {

    //una casilla forma parte de un tablero pues es una relacion part-of --> composition

    private int valor;
    private char formaMina;
    private  boolean clicada;
    private boolean tieneMina;

    private boolean esVacia;
    public  Casilla(){
        this.tieneMina = false;
        this.valor = 0;
        this.clicada = false;
        this.esVacia = false;
    }


    public boolean isEsVacia() {return esVacia;}
    public void setEsVacia(boolean esVacia) {this.esVacia = esVacia;}

    public char getFormaMina() {return formaMina;}

    public void setFormaMina(char formaMina) {this.formaMina = formaMina;}

    public boolean isClicada() {return clicada;}
    public boolean isTieneMina() {return tieneMina;}

    public void setClicada(boolean clicada) {
        this.clicada = clicada;
    }


    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {this.valor = valor;}
    public void setTieneMina(boolean tieneMina) {
        this.tieneMina = tieneMina;
    }


}
