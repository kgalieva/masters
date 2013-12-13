package serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

class Point4d extends Point3d{
    private transient int q;

    public Point4d(int px, int py, int pz, int pq) {
        super(px, py, pz);
        q = pq;
    }

    public Point4d() {
        this(0, 0, 0, 0);
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }


    public String toStringForQ() {
        return ", " + q;
    }

    public String toString() {
        return toStringForXY() + toStringForZ() + toStringForQ() + ")";
    }

    private void readObject(
            ObjectInputStream aInputStream
    ) throws ClassNotFoundException, IOException {
        //always perform the default de-serialization first
        aInputStream.defaultReadObject();
        q = 105;
    }

    /**
     * This is the default implementation of writeObject.
     * Customise if necessary.
     */
    private void writeObject(
            ObjectOutputStream aOutputStream
    ) throws IOException {
        //perform the default serialization for all non-transient, non-static fields
        aOutputStream.defaultWriteObject();
    }
}