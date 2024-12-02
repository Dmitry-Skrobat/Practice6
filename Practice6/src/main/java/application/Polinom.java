package application;


public class Polinom<T extends Number<T>>{
    private String typeName;
    private Array<T> roots;
    private Array<T> fixedOdds;
    private int polynomialDegree;


    public Polinom() {
        this.typeName = "Unknown";
    }
    @SuppressWarnings("unchecked")
    public void createPolinom(Array<T> roots, Array<T> fixedOdds, int polynomialDegree) {
        if(polynomialDegree<0) {
            System.out.println("Ошибка степень полинома не может быть отрицательной");

        }
        else {
            this.roots = roots;
            this.fixedOdds = fixedOdds;
            this.polynomialDegree = polynomialDegree;
            calculateFixedOdds();
        }
    }

    public Polinom(Class<T> type,String string) {
        this.typeName = string;
    }

    public String showWithFixedOdds(){
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = fixedOdds.getSize()-1;i>=0;i--) {
            if (polynomialDegree - count == 0) {
                stringBuilder.append("(").append(fixedOdds.getElement(i)).append(")");

            } else {
                stringBuilder.append("(").append(fixedOdds.getElement(i)).append(")").append("*X^").append(polynomialDegree - count).append(" + ");
            }
            count++;
        }
        return String.valueOf(stringBuilder);

    }

    public String showWithRoots(){
        StringBuilder stringBuilder = new StringBuilder();
        if(roots.getSize()==0){
            stringBuilder.append("(").append(fixedOdds.getElement(0)).append(")");
        }
        else {
            for (int i = 0; i < roots.getSize(); i++) {
                if (roots.getSize() == 1) {
                    stringBuilder.append("(").append(fixedOdds.getElement(i)).append(")").append(" * (X - (").append(roots.getElement(i)).append("))");
                    return String.valueOf(stringBuilder);
                } else if (i == 0) {
                    stringBuilder.append("(").append(fixedOdds.getElement(fixedOdds.getSize() - 1 - i)).append(")").append(" * (X - (").append(roots.getElement(i)).append(")) *");
                } else if (polynomialDegree - i != 1) {
                    stringBuilder.append(" (X - (").append(roots.getElement(i)).append(")) *");
                } else {
                    stringBuilder.append(" (X - (").append(roots.getElement(i)).append("))");
                }
            }
        }
        return String.valueOf(stringBuilder);
    }
    @SuppressWarnings("unchecked")
    private void calculateFixedOdds(){
        fixedOdds.resize(polynomialDegree+1);

        for(int i = 0;i<polynomialDegree;i++){
            T number = (T) roots.getElement(i);

            Array<T> newCoefficients = new Array<T>(polynomialDegree+1);

            for(int j = 0;j<polynomialDegree+1;j++){
                T num1 = (T) fixedOdds.getElement(j);
                T res = num1.numberMul(num1,number);
                T newNumber = (T) newCoefficients.getElement(j);
                res = res.numberDif(newNumber,res);
                newCoefficients.insertElement(j,res);
                if(j+1<polynomialDegree+1){
                    T sum =  num1.zero();
                    newCoefficients.insertElement(j+1, (T) sum.numberSum((T) newCoefficients.getElement(j+1), (T) fixedOdds.getElement(j)));
                }
            }
            fixedOdds = newCoefficients;
        }

    }
    @SuppressWarnings("unchecked")
    public void changeRoot(int index, T number){
        roots.insertElement(index,number);
        T temp = (T) fixedOdds.getElement(fixedOdds.getSize()-1);
        for (int i = 0; i < fixedOdds.getSize(); i++) {
            fixedOdds.insertElement(i,number.zero());
        }
        fixedOdds.insertElement(0,temp);
        calculateFixedOdds();
    }

    @SuppressWarnings("unchecked")
    public void changeFixedOdds(T number){
        for (int i = 0; i < fixedOdds.getSize(); i++) {
            fixedOdds.insertElement(i, (T) fixedOdds.getElement(0).zero());
        }
        fixedOdds.insertElement(0,number);
        calculateFixedOdds();
    }

    public int getPolynomialDegree() {
        return polynomialDegree;
    }

    public String calculate(T number){

        T result = (T) roots.getElement(0).one();
        Array<T> newArray = new Array<T>(roots.getSize());
        for(int i = 0;i<roots.getSize();i++){
            T res = (T) roots.getElement(0).zero();
            res = res.numberDif(number, (T) roots.getElement(i));
            newArray.insertElement(i, (T) res);
        }

        for(int i = 0;i<newArray.getSize();i++){
            result = result.numberMul(result, (T) newArray.getElement(i));
        }


        return String.valueOf(result.numberMul(result, (T) fixedOdds.getElement(fixedOdds.getSize()-1)));
    }


    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void checkType() {
        System.out.println("Тип полинома: " + getTypeName());
    }

}

