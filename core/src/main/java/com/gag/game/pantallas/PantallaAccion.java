package com.gag.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gag.game.sprites.Arco;
import com.gag.game.sprites.Enemigo;
import com.gag.game.sprites.Flecha;

import java.awt.*;
import java.util.ArrayList;

public class PantallaAccion extends Pantalla{
    private Texture fondo;
    private Texture muro;
    private int rondas;
    private Arco arco;
    private Flecha flecha;
    private float tiempo;
    private float yInput;
    private float xInput;
    private int xHotSpot;
    private int yHotSpot;
    private float valorEnemigo;
    private OrthographicCamera camara;
    private FitViewport view;
    private float angle;
    private Rectangle areaMuro;
    private Vector2 posicionMuro;

    private Vector2 posicion;
    // private ArrayList<Enemigo> enemigos=new ArrayList<>();
    //Fisicas
    private World mundo;
    private Box2DDebugRenderer depurador;
    private OrthogonalTiledMapRenderer renderizador;
    private Enemigo enemigo=null;
    private Body cuerpoJugador=null;




    public PantallaAccion() {
        super();
        //camara y view
        camara=new OrthographicCamera();
        camara.position.x=juego.getAncho()/2;
        camara.position.y=juego.getAlto()/2;
        view=new FitViewport(juego.getAncho(), juego.getAlto(),camara);
        view.setScreenBounds(0,0, juego.getAncho(), juego.getAlto());

        posicion=new Vector2(0,0);
        rondas=1;
        valorEnemigo=rondas/10;
        fondo=am.get("Fondo.jpg");
        muro=am.get("devil_wall.jpg");
        arco=new Arco(2);
        //Area de colision para el muro
        posicionMuro=new Vector2(juego.getAncho()/7, juego.getAlto());
        areaMuro=new Rectangle((int)posicionMuro.x,(int)posicionMuro.y,muro.getWidth(),muro.getHeight());
        //Fisicas
        mundo=new World(new Vector2(0,0),true);
        depurador=new Box2DDebugRenderer();
        TextureAtlas atlas=am.get("FantasmaRun.atlas");
        enemigo=new Enemigo(mundo,atlas,0,0);
        cuerpoJugador=enemigo.getCuerpo();


        flecha = new Flecha(new Vector2(-20,450), new Vector2(0.01f,0));
        //renderizador=new OrthogonalTiledMapRenderer(mapa,1.0f/LADO_LOSA);

     //   enemigos.add(new Enemigo(valorEnemigo,valorEnemigo,valorEnemigo));
    }

    @Override
    public void leerEntrada(float delta) {
        if(Gdx.input.justTouched()){
           flecha.actualizar();
           sb.begin();
           flecha.draw(sb);
           sb.end();
        }
    }

    @Override
    public void actualizar(float delta) {
        enemigo.actualizar(delta);
        tiempo += delta;
        yInput = Gdx.input.getY();
        xInput = Gdx.input.getX();
        // arco.getPosicionArco().set(Gdx.input.getX() - posicion.x, yInput - posicion.y).nor();
        //position is a Vector2 update sprite coordinates
        posicion.x = arco.getPosicionArco().x ;
        posicion.y = arco.getPosicionArco().y ;

        //float yInput = (Gdx.graphics.getHeight() - Gdx.input.getY());
        Vector2 puntoClickeado=view.unproject(new Vector2(xInput,yInput));
        angle = MathUtils.radiansToDegrees * MathUtils.atan2(puntoClickeado.y - posicion.y, puntoClickeado.x - posicion.x);

        if(angle < 0){
            angle += 360;
        }

    }




    @Override
    public void dibujar(float delta) {
        view.apply();
        sb.setProjectionMatrix(camara.combined);
        sb.begin();
        sb.draw(fondo,0,0, juego.getAncho(), juego.getAlto());
        sb.draw(muro,0,0, juego.getAncho()/7, juego.getAlto());
        enemigo.draw(sb);
        arco.setRotation(angle);
        arco.draw(sb);
        sb.end();
        //------->Cursor Todo no funciona puntero
        Pixmap pm = new Pixmap(Gdx.files.internal("puntero1.png"));
        xHotSpot = pm.getWidth() / 2;
        yHotSpot = pm.getHeight() / 2;
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, xHotSpot, yHotSpot));
    }

    @Override
    public void show() {

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
}
