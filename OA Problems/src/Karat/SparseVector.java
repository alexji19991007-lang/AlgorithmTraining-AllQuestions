package Karat;

import java.util.Arrays;

public class SparseVector {
    double[] vector;
    int size;

    public SparseVector(int size) {
        this.vector = new double[size];
        this.size = size;
    }

    public void set(int index, double val) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index Out of Range");
        }
        vector[index] = val;
    }

    public double get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index Out of Range");
        }
        return vector[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(vector);
    }

    public String add(SparseVector other) {
        if (this.size != other.size) {
            throw new IllegalArgumentException("Vector Lengths Don't Match");
        }
        double[] ans = new double[this.size];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = this.vector[i] + other.vector[i];
        }
        return Arrays.toString(ans);
    }

    public double dot(SparseVector other) {
        if (this.size != other.size) {
            throw new IllegalArgumentException("Vector Lengths Don't Match");
        }
        double ans = 0;
        for (int i = 0; i < this.size; ++i) {
            ans += this.vector[i] * other.vector[i];
        }
        return ans;
    }

    public double cos(SparseVector other) {
        if (this.size != other.size) {
            throw new IllegalArgumentException("Vector Lengths Don't Match");
        }
//        return this.dot(other) / (norm(this) * norm(other));
        double res = this.dot(other) / (norm(this) * norm(other));
        return Math.round(res * 1000.0) / 1000.0;
    }

    private double norm(SparseVector v) {
        int squareSum = 0;
        for (double d : v.vector) {
            squareSum += d * d;
        }
        return Math.sqrt(squareSum);
    }

    public static void main(String[] args) {
        SparseVector v = new SparseVector(100); //size constructor; size is 100.
        v.set(0, 1.0);
        v.set(3, 2.0);
        v.set(80, -4.5);

        System.out.println(v.get(80)); //should print -4.5
        System.out.println(v.get(50)); //should print 0.0

        try {
            System.out.println(v.get(101)); //error -- index out of range
            throw new IllegalStateException("We should not get here, an exception should have been thrown");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.toString());
        }
        System.out.println(v.toString()); //should print something like [1.0, 0.0, 0.0, 2.0, 0.0, ...]

        SparseVector v1 = new SparseVector(5);
        v1.set(0, 4.0);
        v1.set(1, 5.0);
        SparseVector v2 = new SparseVector(5);
        v2.set(1, 2.0);
        v2.set(3, 3.0);
        SparseVector v3 = new SparseVector(2);

        System.out.println(v1.add(v2));
        try {
            System.out.println(v1.add(v3));
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }

        System.out.println(v1.dot(v2));
        try {
            System.out.println(v1.dot(v3));
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }

        System.out.println(v1.cos(v2));
        try {
            System.out.println(v1.cos(v3));
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }
    }
}
