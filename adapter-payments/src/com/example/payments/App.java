package com.example.payments;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, PaymentGateway> gateways = new HashMap<>();

        // Register the adapters
        gateways.put("fastpay", new FastPayAdapter(new FastPayClient()));
        gateways.put("safecash", new SafeCashAdapter(new SafeCashClient()));

        OrderService svc = new OrderService(gateways);

        System.out.println("=== Processing Payments ===");
        String id1 = svc.charge("fastpay", "cust-1", 1299);
        String id2 = svc.charge("safecash", "cust-2", 1299);
        
        System.out.println("FastPay Result: " + id1);
        System.out.println("SafeCash Result: " + id2);
    }
}