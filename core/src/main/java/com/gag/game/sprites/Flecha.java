package com.gag.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Flecha extends Sprite {
    public Vector2 localizacionFlecha = new Vector2(0, 0);
    private Vector2 velocidadFlecha = new Vector2(0, 0);
    private Texture aspecto;

    public Flecha(Vector2 cambiarLocalizacion, Vector2 cambiarVelocidad)
    {
        localizacionFlecha = new Vector2(cambiarLocalizacion.x, cambiarLocalizacion.y);
        velocidadFlecha = new Vector2(cambiarVelocidad.x, cambiarVelocidad.y);
        aspecto=new Texture("Flecha1.png");
        setRegion(aspecto);
        setRegion(new TextureRegion(aspecto,0,0,112,154));

    }


    public void actualizar()
    {
        localizacionFlecha.x += velocidadFlecha.x;
        localizacionFlecha.y += velocidadFlecha.y;
        setPosition(localizacionFlecha.x, localizacionFlecha.y );
    }
}

