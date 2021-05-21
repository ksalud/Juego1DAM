package com.gag.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gag.game.sprites.Arco;
import com.gag.game.sprites.Items;
import com.gag.game.sprites.Tipoflecha;

import java.awt.*;

public class PantallaMenu extends Pantalla{

    private Stage stage;

    private int dmg;

    private Texture fondo,fondoTienda,titulo,arco1,oro,madera,piedra,sello,muro,flecha1,flecha2,flecha3,separador,iluminacion,flechaEquipada;
    private Texture botonJugar;

    private Vector2 posicionJugar,posicionArco,posicionMuro,posicionFlecha1,posicionFlecha2,posicionFlecha3;

    private Rectangle areaJugar;

    private ImageButton mejorar1,mejorar2,mejorar3,mejorarArco,mejorarMuro;
    private ImageButton equipar1,equipar2,equipar3;
    private ImageButton tienda;

    private Label oroLabel,maderaLabel,piedraLabel,selloLabel,niveles,statsActual,precio;
    BitmapFont fuente=am.get("fuente.fnt");
    Label.LabelStyle estilo=new Label.LabelStyle(fuente, Color.WHITE);

    private boolean able=false,tiendalist=false;

    public PantallaMenu() {
        super();
        Music musica= am.get("pp2.mp3");
        musica.setVolume(0.25f);
        musica.setLooping(true);
        musica.play();

        //stage

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Label

        oroLabel= new Label(String.valueOf(Items.oro),estilo);
        maderaLabel= new Label(String.valueOf(Items.madera),estilo);
        piedraLabel= new Label(String.valueOf(Items.piedra),estilo);
        selloLabel= new Label(String.valueOf(Items.sello),estilo);
        niveles= new Label("Nivel de mejoras"+"\n"+
                "Arco: "+Arco.getNivelArco()+"\n"+
                "Muro: "+Arco.getNivelMuro()+"\n"+
                "Flecha normal: "+Tipoflecha.FLECHANORMAL.getNivel()+"\n"+
                "Flecha de piedra: "+Tipoflecha.FLECHAPIEDRA.getNivel()+"\n"+
                "Flecha de fuego: "+Tipoflecha.FLECHAFUEGO.getNivel()+"\n"
                ,estilo);
        statsActual= new Label("Dmg-> "+dmg+" || Dps-> "+Arco.getDps()+" || Vida-> "+Arco.getVida(),estilo);
        precio= new Label("",estilo);

        //Aplicar texturas

        fondo=am.get("Fondo.jpg");
        fondoTienda=am.get("FondoTienda.jpg");
        titulo=am.get("Titulo.png");
        oro=am.get("gold.png");
        madera=am.get("madera.png");
        piedra=am.get("piedra.png");
        sello=am.get("sello.png");
        arco1=am.get("Arco.png");
        muro=am.get("MejorarMuro.png");
        flecha1=am.get("Flecha1.png");
        flecha2=am.get("Flecha2.png");
        flecha3=am.get("Flecha3.png");

        flechaEquipada=Tipoflecha.FLECHANORMAL.getImagen();
        dmg=Tipoflecha.FLECHANORMAL.getDmg();

        iluminacion=am.get("iluminacion.png");
        separador=am.get("separador.png");
        botonJugar=am.get("BotonPlay.png");


        //Botones

        equipar1 = new ImageButton(new TextureRegionDrawable((Texture)am.get("equip.png")),new TextureRegionDrawable((Texture)am.get("equip2.png")));
        equipar2 = new ImageButton(new TextureRegionDrawable((Texture)am.get("equip.png")),new TextureRegionDrawable((Texture)am.get("equip2.png")));
        equipar3 = new ImageButton(new TextureRegionDrawable((Texture)am.get("equip.png")),new TextureRegionDrawable((Texture)am.get("equip2.png")));

        mejorar1 = new ImageButton(new TextureRegionDrawable((Texture)am.get("upgrade.png")),new TextureRegionDrawable((Texture)am.get("upgrade2.png")));
        mejorar2 = new ImageButton(new TextureRegionDrawable((Texture)am.get("upgrade.png")),new TextureRegionDrawable((Texture)am.get("upgrade2.png")));
        mejorar3 = new ImageButton(new TextureRegionDrawable((Texture)am.get("upgrade.png")),new TextureRegionDrawable((Texture)am.get("upgrade2.png")));
        mejorarArco = new ImageButton(new TextureRegionDrawable((Texture)am.get("upgrade.png")),new TextureRegionDrawable((Texture)am.get("upgrade2.png")));
        mejorarMuro = new ImageButton(new TextureRegionDrawable((Texture)am.get("upgrade.png")),new TextureRegionDrawable((Texture)am.get("upgrade2.png")));

        tienda = new ImageButton(new TextureRegionDrawable((Texture)am.get("shop.png")));

        //Listener

        tienda.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(tiendalist==false){
                    able=false;
                }else able=true;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(able==true){
                    tiendalist=false;
                }else tiendalist=true;
                return true;
            }
        });
        equipar1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                flechaEquipada=Tipoflecha.FLECHANORMAL.getImagen();
                dmg=Tipoflecha.FLECHANORMAL.getDmg();
                //flecha=new Flecha(Tipoflecha.FLECHANORMAL);
                return true;
            }
        });
        equipar2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                flechaEquipada=Tipoflecha.FLECHAPIEDRA.getImagen();
                dmg=Tipoflecha.FLECHAPIEDRA.getDmg();
                //flecha=new Flecha(Tipoflecha.FLECHAPIEDRA);
                return true;
            }
        });
        equipar3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                flechaEquipada=Tipoflecha.FLECHAFUEGO.getImagen();
                dmg=Tipoflecha.FLECHAFUEGO.getDmg();
                //flecha=new Flecha(Tipoflecha.FLECHAFUEGO);
                return true;
            }
        });
        mejorarArco.addListener(new InputListener(){
            @Override
            public boolean mouseMoved (InputEvent event, float x, float y) {
                precio.setText("Coste: -"+Arco.getCosteArco()+" oro");
                precio.setPosition(mejorarArco.getX(),mejorarArco.getY()-10);
                return false;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Arco.mejoraArco();
                oroLabel.setText(String.valueOf(Items.oro));
                precio.setText("Coste: -"+Arco.getCosteArco()+" oro");
                precio.setPosition(mejorarArco.getX(),mejorarArco.getY()-10);
                return true;
            }
        });
        mejorarMuro.addListener(new InputListener(){
            @Override
            public boolean mouseMoved (InputEvent event, float x, float y) {
                precio.setText("Coste: -"+Arco.getCosteMuro()+" oro");
                precio.setPosition(mejorarMuro.getX(),mejorarMuro.getY()-10);
                return true;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Arco.mejoraMuro();
                oroLabel.setText(String.valueOf(Items.oro));
                precio.setText("Coste: -"+Arco.getCosteMuro()+" oro");
                precio.setPosition(mejorarMuro.getX(),mejorarMuro.getY()-10);
                return true;
            }
        });
        mejorar1.addListener(new InputListener(){

            @Override
            public boolean mouseMoved (InputEvent event, float x, float y) {
                precio.setText("Coste: -"+Tipoflecha.FLECHANORMAL.getCoste()+" Madera");
                precio.setPosition(mejorar1.getX(),mejorar1.getY()-60);
                return true;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Tipoflecha.mejoraFlechanormal();
                dmg=Tipoflecha.FLECHANORMAL.getDmg();
                maderaLabel.setText(String.valueOf(Items.madera));
                precio.setText("Coste: -"+Tipoflecha.FLECHANORMAL.getCoste()+" Madera");
                precio.setPosition(mejorar1.getX(),mejorar1.getY()-60);
                return true;
            }
        });
        mejorar2.addListener(new InputListener(){
            @Override
            public boolean mouseMoved (InputEvent event, float x, float y) {
                precio.setText("Coste: -"+Tipoflecha.FLECHAPIEDRA.getCoste()+" Piedra");
                precio.setPosition(mejorar2.getX(),mejorar2.getY()-60);
                return true;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Tipoflecha.mejoraFlechapiedra();
                dmg=Tipoflecha.FLECHAPIEDRA.getDmg();
                piedraLabel.setText(String.valueOf(Items.piedra));
                precio.setText("Coste: -"+Tipoflecha.FLECHAPIEDRA.getCoste()+" Piedra");
                precio.setPosition(mejorar2.getX(),mejorar2.getY()-60);
                return true;
            }
        });
        mejorar3.addListener(new InputListener(){
            @Override
            public boolean mouseMoved (InputEvent event, float x, float y) {
                precio.setText("Coste: -"+Tipoflecha.FLECHAFUEGO.getCoste()+" Sellos");
                precio.setPosition(mejorar3.getX(),mejorar3.getY()-60);
                return true;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Tipoflecha.mejoraFlechafuego();
                dmg=Tipoflecha.FLECHAFUEGO.getDmg();
                selloLabel.setText(String.valueOf(Items.sello));
                precio.setText("Coste: -"+Tipoflecha.FLECHAFUEGO.getCoste()+" Sellos");
                precio.setPosition(mejorar3.getX(),mejorar3.getY()-60);
                return true;
            }
        });

        //Actors

        stage.addActor(tienda);
        stage.addActor(equipar1);
        stage.addActor(equipar2);
        stage.addActor(equipar3);
        stage.addActor(mejorar1);
        stage.addActor(mejorar2);
        stage.addActor(mejorar3);
        stage.addActor(mejorarArco);
        stage.addActor(mejorarMuro);
        stage.addActor(oroLabel);
        stage.addActor(maderaLabel);
        stage.addActor(piedraLabel);
        stage.addActor(selloLabel);
        stage.addActor(niveles);
        stage.addActor(statsActual);
        stage.addActor(precio);

        //Ubicaciones

        posicionJugar=new Vector2(150-botonJugar.getWidth()/2,(juego.getAlto()-150)-botonJugar.getHeight()/2);
        posicionArco=new Vector2(500,700);
        posicionMuro=new Vector2(posicionArco.x+380,posicionArco.y-20);
        posicionFlecha1=new Vector2(posicionArco.x,posicionArco.y-300);
        posicionFlecha2=new Vector2(posicionFlecha1.x+450,posicionFlecha1.y);
        posicionFlecha3=new Vector2(posicionFlecha2.x+450,posicionFlecha1.y);

        oroLabel.setPosition(100,370);
        maderaLabel.setPosition(100,270);
        piedraLabel.setPosition(100,170);
        selloLabel.setPosition(100,70);

        tienda.setPosition(120,(juego.getAlto()-270));

        //Para definir "área de click"

        areaJugar=new Rectangle((int)posicionJugar.x,(int)posicionJugar.y,botonJugar.getWidth(),botonJugar.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void leerEntrada(float delta) {
        if(Gdx.input.justTouched()){
            //Debo hacer el unproject porque la vista puede estar escalada!!
            Vector2 puntoClickado=juego.getVista().unproject(new Vector2(Gdx.input.getX(),Gdx.input.getY()));
            if(areaJugar.contains(puntoClickado.x, puntoClickado.y)){
                juego.cambiarPantalla(this,new PantallaAccion());
            }
        }
    }

    @Override
    public void actualizar(float delta) {

    }

    @Override
    public void dibujar(float delta) {

        sb.begin();
        sb.draw(fondo,0,0, juego.getAncho(), juego.getAlto());
        sb.draw(titulo,juego.getAncho()/2-400,100);
        sb.draw(botonJugar, posicionJugar.x,posicionJugar.y);
        sb.draw(separador,-110,juego.getAlto()-280);
        sb.draw(separador,-110,posicionJugar.y-450);
        sb.draw(oro,20 ,350);
        sb.draw(madera,20 ,250);
        sb.draw(piedra,20 ,150);
        sb.draw(sello,20 ,50);
        sb.draw(iluminacion,posicionJugar.x-50,posicionJugar.y-440);
        sb.draw(arco1,posicionJugar.x,posicionJugar.y-380);
        sb.draw(flechaEquipada,posicionJugar.x,posicionJugar.y-170);
        statsActual.setPosition(posicionJugar.x-70,posicionJugar.y-130);

        if(able==true){
                sb.draw(fondoTienda,430,100);
                sb.draw(arco1,500,700);
                sb.draw(muro,posicionMuro.x,posicionMuro.y);
                sb.draw(flecha1,posicionFlecha1.x,posicionFlecha1.y);
                sb.draw(flecha2,posicionFlecha2.x,posicionFlecha2.y);
                sb.draw(flecha3,posicionFlecha3.x,posicionFlecha3.y);
                //Botones
                equipar1.setPosition(posicionFlecha1.x-5,posicionFlecha1.y-120);
                mejorar1.setPosition(posicionFlecha1.x-5,posicionFlecha1.y-70);
                equipar2.setPosition(posicionFlecha2.x-5,posicionFlecha2.y-120);
                mejorar2.setPosition(posicionFlecha2.x-5,posicionFlecha2.y-70);
                equipar3.setPosition(posicionFlecha3.x-5,posicionFlecha3.y-120);
                mejorar3.setPosition(posicionFlecha3.x-5,posicionFlecha3.y-70);
                mejorarArco.setPosition(posicionArco.x,posicionArco.y-70);
                mejorarMuro.setPosition(posicionMuro.x+65,posicionMuro.y-50);
                niveles.setPosition(posicionMuro.x+450,posicionMuro.y);

        }else{
            equipar1.setPosition(-200,-200);
            mejorar1.setPosition(-200,-200);
            equipar2.setPosition(-200,-200);
            mejorar2.setPosition(-200,-200);
            equipar3.setPosition(-200,-200);
            mejorar3.setPosition(-200,-200);
            mejorarArco.setPosition(-200,-200);
            mejorarMuro.setPosition(-200,-200);
            niveles.setPosition(-200,-200);
            precio.setPosition(-200,-200);
        }
        sb.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act();
        stage.draw();

        niveles.setText("Nivel de mejoras"+"\n"+
                "Arco: "+Arco.getNivelArco()+"\n"+
                "Muro: "+Arco.getNivelMuro()+"\n"+
                "Flecha normal: "+Tipoflecha.FLECHANORMAL.getNivel()+"\n"+
                "Flecha de piedra: "+Tipoflecha.FLECHAPIEDRA.getNivel()+"\n"+
                "Flecha de fuego: "+Tipoflecha.FLECHAFUEGO.getNivel()+"\n"
                );

        statsActual.setText("Dmg-> "+dmg+" || Dps-> "+Arco.getDps()+" || Vida-> "+Arco.getVida());

    }
}
