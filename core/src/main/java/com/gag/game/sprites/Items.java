package com.gag.game.sprites;

public class Items {
    public static int oro=500;
    public static int madera=10;
    public static int piedra=0;
    public static int sello=0;

    public static void costeRecurso(int valor,int tipo){

        //El tipo sera para saber que valor aplicar
        //1=arco , 2=muro , 3=flecha1 , 4=flecha2 , 5=flecha3

        if (tipo==1) {
            oro -= valor;
        }
        if (tipo==2) {
            oro -= valor;
        }
        if (tipo==3) {
            madera -= valor;
        }
        if (tipo==4) {
            piedra -= valor;
        }
        if (tipo==5) {
            sello -= valor;
        }
    }
}
