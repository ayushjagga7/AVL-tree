package GradedLab3;

public class Value {
    int N;
    int height = 0;
    Value parent = null, left = null, right = null;

    public Value(int n, int h, Value P) {
        N = n;
        height = h;
        parent = P;
    }
}