package hu.ak_akademia.mss.model.user;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Client extends MssUser {

    private String job;
    private int reservations;
    private int medicalRecords;
    private int bill;
    private int financialBalance;

    public Client() {
        super.setUserTypeId("Client");
    }

    public Client(String job, int reservations, int medicalRecords, int bill, int financialBalance) {
        super.setUserTypeId("Client");
        this.job = job;
        this.reservations = reservations;
        this.medicalRecords = medicalRecords;
        this.bill = bill;
        this.financialBalance = financialBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return reservations == client.reservations && medicalRecords == client.medicalRecords && bill == client.bill && financialBalance == client.financialBalance && Objects.equals(job, client.job);
    }

    @Override
    public String toString() {
        return "Client{" +
                "job='" + job + '\'' +
                ", reservations=" + reservations +
                ", medicalRecords=" + medicalRecords +
                ", bill=" + bill +
                ", financialBalance=" + financialBalance +
                "} " + super.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), job, reservations, medicalRecords, bill, financialBalance);
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    public int getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(int medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getFinancialBalance() {
        return financialBalance;
    }

    public void setFinancialBalance(int financialBalance) {
        this.financialBalance = financialBalance;
    }
}
