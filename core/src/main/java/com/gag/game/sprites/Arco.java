package com.gag.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Arco extends Sprite {

    private static int nivelArco=1;
    private static int nivelMuro=1;
    private static int dps=+300;
    private static int vida=1000;
    private static int costeArco=50,costeMuro=50;
    private Vector2 posicionArco;
    private Texture arcoTextura;

    public Arco(int dps) {
        super(new Texture("1.png"));
        posicionArco=new Vector2(-20,450);
        arcoTextura=new Texture("1.png");
        // arcoTextura=new TextureRegion(getTexture(),getRegionX(),getRegionY(),
        //        5,5);
        setTexture(arcoTextura);
        setRegion(new TextureRegion(arcoTextura,0,0,112,154));
        setPosition(-20,450);
        setOrigin(getWidth()/2,getHeight()/2);
    }

    public Vector2 getPosicionArco() {
        return posicionArco;}

    public void dispara(Vector2 posicionDisparo){
        //La imagen del arco mira hacia donde este la x y la y de donde pulse con el raton que es un vector2
        // que se le pasa por parametro y se usa asi,   posicionDisparo.x,posicionDisparo.y;

    }
    public static void mejoraArco(){
        int costeOro=nivelArco*50;

        if(Items.oro<costeOro){

            System.out.println("No tienes recursos");

        }else if (nivelArco==10 || nivelArco == 20 || nivelArco == 30){

            costeOro=nivelArco*150;
            Items.costeRecurso(costeOro,1);
            nivelArco++;
            dps+=50;
            costeArco=nivelArco*150;
        }
        else{
            Items.costeRecurso(costeOro,1);
            nivelArco++;
            dps+=10;
            costeArco=nivelArco*50;
        }
    }


    public static void mejoraMuro(){
        int costeOro=nivelMuro*50;

        if(Items.oro<costeOro){

            System.out.println("No tienes recursos");

        }else if(nivelMuro==10 || nivelMuro == 20 || nivelMuro == 30) {

            costeOro = nivelMuro * 150;
            Items.costeRecurso(costeOro,2);
            nivelMuro++;
            vida+=1000;
            costeMuro=nivelMuro*150;
        }
        else {
            Items.costeRecurso(costeOro,2);
            nivelMuro++;
            vida+=100;
            costeMuro=nivelMuro*50;
        }
    }

    public static int getNivelArco() {
        return nivelArco;
    }

    public static int getNivelMuro() {
        return nivelMuro;
    }

    public static int getDps() {
        return dps;
    }

    public static int getVida() {
        return vida;
    }

    public static int getCosteArco() {
        return costeArco;
    }

    public static int getCosteMuro() {
        return costeMuro;
    }
}


