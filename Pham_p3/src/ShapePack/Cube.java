package ShapePack;

public class Cube extends Shape3D {
    private double edge;

    public Cube (double edge) {
        super("cube");
        this.edge = edge;
    }

    public void setEdge (double edge) {
        this.edge = edge;
    }

    public double getAreaCube() {
        return 6*Math.pow(edge, 2);
    }

    public double getVolumeCube() {
        return Math.pow(edge, 3);
    }

    @Override
    public String getName() {
        return ShapeName;
    }
    @Override
    public double getArea() {
        return getAreaCube();
    }
    @Override
    public double getVolume() {
        return getVolumeCube();
    }
}
