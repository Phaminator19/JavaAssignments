package ShapePack;

public abstract class Shape{
    public String ShapeName;
    public Shape (String ShapeName) {
        this.ShapeName=ShapeName;
    }
    public abstract String getName();
    public abstract double getArea();
}
