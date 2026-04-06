package com.fsad.model;

public class LoanApplication {
    private String id;
    private String borrowerId;
    private String borrowerName;
    private String lenderId;
    private double loanAmount;
    private double interestRate;
    private int tenure;
    private String status;
    private String purpose;
    private String appliedAt;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getBorrowerId() { return borrowerId; }
    public void setBorrowerId(String borrowerId) { this.borrowerId = borrowerId; }
    public String getBorrowerName() { return borrowerName; }
    public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }
    public String getLenderId() { return lenderId; }
    public void setLenderId(String lenderId) { this.lenderId = lenderId; }
    public double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }
    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
    public int getTenure() { return tenure; }
    public void setTenure(int tenure) { this.tenure = tenure; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public String getAppliedAt() { return appliedAt; }
    public void setAppliedAt(String appliedAt) { this.appliedAt = appliedAt; }
}
