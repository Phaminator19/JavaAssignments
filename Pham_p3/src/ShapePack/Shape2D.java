package ShapePack;

import ShapePack.Shape;

public abstract class Shape2D extends Shape {

    public Shape2D(String ShapeName) {
        super(ShapeName);
    }

    public abstract String getName();
    public abstract double getArea();
}
