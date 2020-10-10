package ShapePack;

public class Sphere extends Shape3D {
    private double radius;

    public Sphere (double radius) {
        super("sphere");
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getAreaSphere() {
        return 4*3.14159*Math.pow(radius,2);
    }

    public double getVolumeSphere() {
        return (4*3.14159*Math.pow(radius, 3))/3;
    }

    @Override
    public String getName() {
        return ShapeName;
    }
    @Override
    public double getArea() {
        return getAreaSphere();
    }
    @Override
    public double getVolume() {
        return getVolumeSphere();
    }
}
