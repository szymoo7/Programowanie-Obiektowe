package com.example.project.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Signal extends Object
{
    //color == true (green)
    //color == false (red)
    private String direction;
    private boolean color;
    private ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/Pictures/stoplight-green.png")));

    public Signal(float x, float y, int id, String type) {
        super(x, y, id, type);
    }

    public void ChangeColor()
    {
        setColor(!color);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean getColor() {
        return color;
    }

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
