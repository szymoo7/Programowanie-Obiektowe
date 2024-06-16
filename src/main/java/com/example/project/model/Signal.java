package com.example.project.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Signal extends Object
{

    private String direction;
    private boolean color;
    private ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Pictures/stoplight-green.png")));
    /**
     * Konstruktor klasy Signal.
     *
     * @param x    pozycja x sygnalizatora
     * @param y    pozycja y sygnalizatora
     * @param id   identyfikator sygnalizatora
     * @param type typ sygnalizatora
     */
    public Signal(float x, float y, int id, String type) {
        super(x, y, id, type);
    }

    public void ChangeColor()
    {
        setColor(!color);
    }
    /**
     * Zwraca aktualny kolor sygnalizatora.
     *
     * @return true, jeśli sygnalizator jest zielony, false, jeśli jest czerwony
     */
    public boolean getColor() {
        return color;
    }
    /**
     * Ustawia kolor sygnalizatora.
     *
     * @param color true, aby ustawić kolor na zielony, false, aby ustawić kolor na czerwony
     */
    public void setColor(boolean color) {
        this.color = color;
        Image image;
        if(color) {
            image = new Image(getClass().getResourceAsStream("/Pictures/stoplight-green.png"));
        } else {
            image = new Image(getClass().getResourceAsStream("/Pictures/stoplight-red.png"));
        }
        imageView.setImage(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitHeight(40.0);
        imageView.setFitWidth(22.0);
        setImageView(imageView);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

}



