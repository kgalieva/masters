package serialization;

class Point3d extends Point2d {
    private int z;

    public Point3d(int px, int py, int pz) {
        super(px, py);
        z = pz;
    }

    public Point3d() {
        this(0, 0, 0);
    }

    public int getZ() {
        return z;
    }

    public double distanceFrom(Point3d pt) {
        double xyDistance = super.distanceFrom(pt);
        int dz = Math.abs(z - pt.getZ());

        return Math.sqrt((xyDistance * xyDistance) + (dz * dz));
    }

    public double distanceFromOrigin() {
        return distanceFrom(new Point3d());
    }

    public String toStringForZ() {
        return ", " + z;
    }

    public String toString() {
        return toStringForXY() + toStringForZ() + ")";
    }
}