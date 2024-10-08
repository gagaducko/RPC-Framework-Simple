package rpc.gagaduck.test;

import rpc.gagaduck.client.RpcClient;

public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int number1 = (int)(Math.random() * 100) + 1;
        int number2 = (int)(Math.random() * 100) + 1;
        int number3 = (int)(Math.random() * 100) + 1;
        int number4 = (int)(Math.random() * 100) + 1;
        int add = service.add(number1, number2);
        int minus = service.minus(number3, number4);

        System.out.println("number1 is: " + number1 + " and number2 is: " + number2);
        System.out.println("add number1 and number2 get: " + add);
        System.out.println("number3 is: " + number3 + " and number4 is: " + number4);
        System.out.println("minus number3 by number4 get: " + minus);
    }
}
