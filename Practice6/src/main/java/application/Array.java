package application;

public class Array <T extends Number<T>>{
    private T[] elements;
    private int size;
    private int capacity;


    @SuppressWarnings("unchecked")
    public Array(int initialCapacity) {
        this.size = 0;
        this.capacity = initialCapacity;
        this.elements = (T[]) new Number[capacity];
    }

    @SuppressWarnings("unchecked")
    public Array() {
        this(5);
    }

    @SuppressWarnings("unchecked")
    public void resize(int newSize){
        T[] numbers =  (T[]) new Number[newSize];
        for (int i = 0; i < size; i++) {
            numbers[i] = elements[i];
        }
        elements = numbers;
        capacity = newSize;
    }

    public int getSize() {
        return size;
    }


    public T getElement(int index){
        if(index<0 || index>=capacity){
            System.out.println("Ошибка индекс вне диапазона");
            return null;
        }
         return elements[index];
    }


    public void insertElement(int index, T number){
        if(index<0 || index>=capacity){
            System.out.println("Ошибка индекс вне диапазона");
            return;
        }
        elements[index] = number;
    }

}
