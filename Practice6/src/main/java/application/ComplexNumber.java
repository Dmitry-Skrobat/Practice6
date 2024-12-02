package application;

import java.util.Objects;
import java.util.Scanner;

public class ComplexNumber extends Number<ComplexNumber>{
    private double real;
    private double imaginary;

    public static final ComplexNumber ZERO = new ComplexNumber(0, 0);

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber(){
        this.real = 0;
        this.imaginary = 0;
    }

    public ComplexNumber zero(){
        return ZERO;
    }

    public ComplexNumber one(){
        return new ComplexNumber(1,0);
    }

    public double getModule(){
        return Math.sqrt(real*real+imaginary*imaginary);
    }

    public double getReal(){
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public double getImaginary() {
        return imaginary;
    }

    public double getArgument() {
        return Math.atan2(imaginary, real);
    }

    public ComplexNumber numberSum(ComplexNumber value1, ComplexNumber value2){
        return new ComplexNumber(value1.getReal() + value2.getReal(), value1.getImaginary() + value2.getImaginary());
    }

    public ComplexNumber numberDiv(ComplexNumber value1,int value2){
        return new ComplexNumber(value1.getReal()/value2,value1.getImaginary()/value2);
    }

    public ComplexNumber numberDif(ComplexNumber value1,ComplexNumber value2){
        return new ComplexNumber(value1.getReal() - value2.getReal(),value1.getImaginary()-value2.getImaginary());
    }

    public ComplexNumber numberSquare(ComplexNumber value1){
        return new ComplexNumber(value1.getReal()*value1.getReal() + value1.getImaginary()*value1.getImaginary(),2*value1.getReal()*value1.getImaginary());
    }

    public ComplexNumber numberSqrt(ComplexNumber value1) {
        ComplexNumber result = new ComplexNumber();
        double module = value1.getModule();
        double fi = value1.getImaginary()/value1.getReal();
        result.setReal(Math.sqrt(module)*Math.cos(fi/2));
        result.setImaginary(Math.sqrt(module)*Math.sin(fi/2));
        return result;

    }

    public ComplexNumber numberMul(ComplexNumber value1, ComplexNumber value2){
        return new ComplexNumber(value1.getReal()*value2.getReal()-value1.getImaginary()*value2.getImaginary(),
                value1.getReal()*value2.getImaginary()+value1.getImaginary()*value2.getReal());
    }

    public ComplexNumber scannerNumber(){
        ComplexNumber number = new ComplexNumber();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.replaceAll(" ","");
        input = input.replace("i","");
        String[] parts = input.split("[+-]");

        int index = input.indexOf("+");
        int index1 = input.indexOf("-");
        if(index == -1) {
            int index2 = input.indexOf("-", index1+1);
            if (index1 == 0 && index2 != -1) {
                number.setReal(-1 * Double.parseDouble(parts[1]));
                number.setImaginary(-1 * Double.parseDouble(parts[2]));
            } else if (index1 !=-1 && index2==-1) {
                number.setReal(Double.parseDouble(parts[0]));
                if (!Objects.equals(parts[1], "0")) {
                    number.setImaginary(-1 * Double.parseDouble(parts[1]));
                }
                else {
                    number.setImaginary(-Double.parseDouble(parts[1]));
                }
            }
        }
        else {
            if(index1 == 0) {
                number.setReal(-1 * Double.parseDouble(parts[1]));
                number.setImaginary(Double.parseDouble(parts[2]));
            }
            else {
                number.setReal(Double.parseDouble(parts[0]));
                number.setImaginary(Double.parseDouble(parts[1]));
            }
        }
        return number;
    }

    public int comparisonNumber(ComplexNumber value1,ComplexNumber value2){
        if(value1.getModule() > value2.getModule()){
            return 1;
        }
        else if (value1.getModule()<value2.getModule()){
            return -1;
        }
        else {
            return Double.compare(value1.getArgument(),value2.getArgument());
        }
    }


    public ComplexNumber scannerNumber(String input){
        ComplexNumber number = new ComplexNumber();
        input = input.replaceAll(" ","");
        input = input.replace("i","");
        String[] parts = input.split("[+-]");

        int index = input.indexOf("+");
        int index1 = input.indexOf("-");
        if(index == -1) {
            int index2 = input.indexOf("-", index1+1);
            if (index1 == 0 && index2 != -1) {
                number.setReal(-1 * Double.parseDouble(parts[1]));
                number.setImaginary(-1 * Double.parseDouble(parts[2]));
            } else if (index1 !=-1 && index2==-1) {
                number.setReal(Double.parseDouble(parts[0]));
                if (!Objects.equals(parts[1], "0")) {
                    number.setImaginary(-1 * Double.parseDouble(parts[1]));
                }
                else {
                    number.setImaginary(-Double.parseDouble(parts[1]));
                }
            }

        }
        else {
            if(index1 == 0) {
                number.setReal(-1 * Double.parseDouble(parts[1]));
                number.setImaginary(Double.parseDouble(parts[2]));
            }
            else {
                number.setReal(Double.parseDouble(parts[0]));
                number.setImaginary(Double.parseDouble(parts[1]));
            }
        }
        return number;
    }

    @Override
    public String toString() {
        if (imaginary > 0 && real != 0) {
            return real + "+" + imaginary + "i";
        } else if (imaginary == 0 && real != 0) {
            return real + "";
        } else if (imaginary < 0 && real != 0) {
            return real + "-" + Math.abs(imaginary) + "i";
        } else if (imaginary == 0) {
            return real + "";
        } else {
            return imaginary + "i";
        }

    }
}
