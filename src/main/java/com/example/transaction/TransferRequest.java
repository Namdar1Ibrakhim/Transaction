package com.example.transaction;

import java.math.BigDecimal;

public class TransferRequest {
    private long senderAccountId;
    private long receiverAccountId;
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setReceiverAccountId(long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public void setSenderAccountId(long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public long getReceiverAccountId() {
        return receiverAccountId;
    }

    public long getSenderAccountId() {
        return senderAccountId;
    }
}
