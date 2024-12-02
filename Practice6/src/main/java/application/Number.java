package application;


public abstract class Number<T extends Number<T>> {

    public abstract T numberSum(T one, T two);
    public abstract T numberDiv(T one, int value);
    public abstract T numberDif(T one, T two);
    public abstract T numberSquare(T other);
    public abstract T numberSqrt(T other);
    public abstract T numberMul(T one, T two);
    public abstract T scannerNumber();
    public abstract int comparisonNumber(T one, T two);
    public abstract T scannerNumber(String s);
    public abstract T zero();
    public abstract T one();
    public abstract String toString();





}

