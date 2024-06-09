package com.example.project.model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Car extends Transport
{
    private ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Pictures/car_blue.png")));

    public Car(float x, float y, int id, String type, String exit, String enter, String vehicle, int vmax, float reactionTime, float acceleration, float speed, float vehicleGap, String color, Point onPoint) {
        super(x, y, id, type, exit, enter, vehicle, vmax, reactionTime, acceleration, speed, vehicleGap, color, onPoint);
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
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setImageViewXY(float x, float y) {
        this.x = x;
        this.y = y;
        centeringXY();
        imageView.setX(this.x);
        imageView.setY(this.y);
        setImageView(imageView);

    }

    public void centeringXY() {
        float width = (float) imageView.getFitWidth();
        float height = (float) imageView.getFitHeight();
        this.x = this.x - width / 2;
        this.y = this.y - height / 2;
    }


}
