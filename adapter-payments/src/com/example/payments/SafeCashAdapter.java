package com.example.payments;

public class SafeCashAdapter implements PaymentGateway {
    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = client;
    }

    @Override
    public String charge(String customerId, int amountCents) {
        // Bridge the multi-step SDK logic into a single call
        SafeCashPayment payment = client.createPayment(amountCents, customerId);
        return payment.confirm();
    }
}