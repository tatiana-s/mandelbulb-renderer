package util;

// basic immutable 3D vector class storing doubles
public class Vector {

    private double x1;
    private double x2;
    private double x3;

    // initialise zero vector
    public Vector() {
        x1 = 0;
        x2 = 0;
        x3 = 0;
    }

    // initialise vector with given values
    public Vector(double x1, double x2, double x3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
    }

    // initialise by creating new copy of given vector
    public Vector(Vector v) {
        this.x1 = v.x1;
        this.x2 = v.x2;
        this.x3 = v.x3;
    }

    // getters
    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getX3() {
        return x3;
    }

    // addition
    public Vector add(Vector v) {
        return new Vector(x1 + v.x1, x2 + v.x2, x3 + v.x3);
    }

    // subtraction
    public Vector subtract(Vector v) {
        return new Vector(x1 - v.x1, x2 - v.x2, x3 - v.x3);
    }

    // vector from one point to another
    public static Vector distanceVector(Vector v1, Vector v2) {
        return v2.subtract(v1);
    }

    // normalise
    public Vector normalise() {
        double m = magnitude();
        return new Vector(x1 / m, x2 / m, x3 / m);
    }

    // length
    public double magnitude() {
        return Math.sqrt(Math.pow(x1, 2) + Math.pow(x2, 2) + Math.pow(x3, 2));
    }

    // scalar multiplication
    public Vector scale(double factor) {
        return new Vector(factor * x1, factor * x2, factor * x3);
    }

    // for printing
    @Override
    public String toString() {
        return "Vector{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                '}';
    }
}
