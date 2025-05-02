package org.example.web4.services;

public interface AttemptStatsMBean {
    void recordAttempt(boolean hit);
    int getTotalSupplied();
    int getTotalIn();
    int getTotalMisses();
}
