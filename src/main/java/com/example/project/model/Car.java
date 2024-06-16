package com.example.project.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Car extends Transport
{

    private ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Pictures/car_blue.png")));
    /**
     * Konstruktor klasy Car.
     *
     * @param x             pozycja x pojazdu
     * @param y             pozycja y pojazdu
     * @param id            identyfikator pojazdu
     * @param type          typ pojazdu
     * @param exit          punkt wyjścia pojazdu
     * @param enter         punkt wejścia pojazdu
     * @param vehicle       rodzaj pojazdu
     * @param vmax          maksymalna prędkość pojazdu
     * @param reactionTime  czas reakcji kierowcy
     * @param acceleration  przyspieszenie pojazdu
     * @param speed         aktualna prędkość pojazdu
     * @param vehicleGap    odstęp między pojazdami
     * @param color         kolor pojazdu
     * @param onPoint       punkt na mapie, w którym znajduje się pojazd
     */
    public Car(float x, float y, int id, String type, String exit, String enter, String vehicle, int vmax, float reactionTime, float acceleration, float speed, float vehicleGap, String color, Point onPoint) {
        super(x, y, id, type, exit, enter, vehicle, vmax, reactionTime, acceleration, speed, vehicleGap, color);
        Image image;
        if(color.equals("Red")) {
            image = new Image(getClass().getResourceAsStream("/Pictures/car_red.png"));
        } else if(color.equals("Blue")) {
            image = new Image(getClass().getResourceAsStream("/Pictures/car_blue.png"));
        } else {
            image = new Image(getClass().getResourceAsStream("/Pictures/car_green.png"));
        }
        imageView.setImage(image);
        imageView.setFitHeight(50.0);
        imageView.setFitWidth(45.0);
        centeringXY();
        imageView.setX(this.x);
        imageView.setY(this.y);
        setImageView(imageView);
        imageView.setVisible(false);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
    /**
     * Ustawia współrzędne obiektu ImageView.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public void setImageViewXY(float x, float y) {
        this.x = x;
        this.y = y;
        centeringXY();
        imageView.setX(this.x);
        imageView.setY(this.y);
        setImageView(imageView);

    }
    /**
     * Centruje współrzędne x i y obiektu ImageView na podstawie jego szerokości i wysokości.
     */
    public void centeringXY() {
        float width = (float) imageView.getFitWidth();
        float height = (float) imageView.getFitHeight();
        this.x = this.x - width / 2;
        this.y = this.y - height / 2;
    }

}
