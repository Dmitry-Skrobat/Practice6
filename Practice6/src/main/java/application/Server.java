package application;

import java.net.*;
import java.nio.charset.StandardCharsets;

import static application.Interface.Menu.polinom;


public class Server <T extends Number<T>> implements Runnable {

    private T zeroValue;

    @Override
    public void run(){
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            byte[] receiveData = new byte[2048];
            System.out.println("Server is listening on port 9876...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
                System.out.println("Запрос на сервер: " + clientMessage);

                String result = handlePolynomialOperation(clientMessage);
                System.out.println("Ответ сервера: " +result);
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(result.getBytes(), result.getBytes().length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    private String handlePolynomialOperation(String request) {
        String[] parts = request.split(" ");
        switch (parts[0]){
            case "create":
                polinom.checkType();
                zeroValue = zeroValue.zero();
                T fixedOdd = zeroValue.scannerNumber(parts[3]);
                Array<T> fixedOdds = new Array<T>(1);
                fixedOdds.insertElement(0, fixedOdd);
                String[] rootsScan = parts[2].split(",");
                Array<T> roots = new Array<T>(rootsScan.length);
                for (int i = 0; i < rootsScan.length; i++) {
                    T root = zeroValue.scannerNumber(rootsScan[i]);
                    roots.insertElement(i, root);
                }
                int degree = Integer.parseInt(parts[1]);
                polinom.createPolinom(roots, fixedOdds, degree);
                return "Полином успешно создан ";

            case "changeRoot":
                zeroValue = zeroValue.zero();
                T root = zeroValue.scannerNumber(parts[2]);
                int index = Integer.parseInt(parts[1]);
                polinom.changeRoot(index,root);
                return "Корень успешно изменен";
            case "changeMaxOdd":
                zeroValue = zeroValue.zero();
                T changeFixedOdd = zeroValue.scannerNumber(parts[1]);
                polinom.changeFixedOdds(changeFixedOdd);
                return "Наибольший коэффициент успешно изменен";
            case "calculate":
                zeroValue = zeroValue.zero();
                T number = zeroValue.scannerNumber(parts[1]);
                return polinom.calculate(number);
            case "showPolinomWithFixedOdds":
                return polinom.showWithFixedOdds();
            case "showPolinomWithRoots":
                return polinom.showWithRoots();
        }
        return "Unknown operation";
    }

}

