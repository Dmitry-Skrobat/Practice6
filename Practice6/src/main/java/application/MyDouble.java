package application;


import java.util.Scanner;

public class MyDouble extends Number<MyDouble>{
    private double number;
    public static final MyDouble ZERO = new MyDouble(0);

    public MyDouble(double number) {
        this.number = number;
    }
    public MyDouble(){
        this.number = 0;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public MyDouble zero(){
        return ZERO;
    }

    public MyDouble one(){
        return new MyDouble(1);
    }



    public double getNumber() {
        return number;
    }

    public MyDouble numberSum(MyDouble value1, MyDouble value2){
        return new MyDouble(value1.getNumber()+value2.getNumber());
    }

    public MyDouble numberDiv(MyDouble value1, int value2){
        return new MyDouble(value1.getNumber()/value2);
    }

    public MyDouble numberDif(MyDouble value1, MyDouble value2){
        return new MyDouble(value1.getNumber() - value2.getNumber());
    }

    public MyDouble numberSquare(MyDouble value1){
        return new MyDouble(value1.getNumber()*value1.getNumber());
    }

    public MyDouble numberSqrt(MyDouble value1) {
        return new MyDouble(Math.sqrt(value1.getNumber()));

    }

    public MyDouble numberMul(MyDouble value1, MyDouble value2){
        return new MyDouble(value1.getNumber()*value2.getNumber());
    }

    public MyDouble scannerNumber(){
        Scanner scanner = new Scanner(System.in);
        return new MyDouble(scanner.nextDouble());
    }

    public int comparisonNumber(MyDouble value1, MyDouble value2){
        return Double.compare(value1.getNumber(),value2.getNumber());
    }


    public MyDouble scannerNumber(String input){
        return new MyDouble(Double.parseDouble(input));
    }

    @Override
    public String toString() {
        return number + "";
    }
}
