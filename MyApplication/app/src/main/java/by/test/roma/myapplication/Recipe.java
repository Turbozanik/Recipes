package by.test.roma.myapplication;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Лапа on 8/25/2015.
 */
public class Recipe {
    Bitmap img;
    String shortDescription;
    String description;
    String name;
    Recipe(String description,String shortDescription, String name, Bitmap photo){
        this.description = description;
        this.shortDescription = shortDescription;
        this.name = name;
        this.img = photo;
    }
}
