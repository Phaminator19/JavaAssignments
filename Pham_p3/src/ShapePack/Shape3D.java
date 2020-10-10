package ShapePack;

public abstract class Shape3D extends Shape  {
    public Shape3D(String ShapeName) {
        super(ShapeName);
    }

    public abstract String getName();
    public abstract double getArea();
    public abstract double getVolume();
}
