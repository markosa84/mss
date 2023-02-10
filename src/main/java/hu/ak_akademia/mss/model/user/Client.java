package hu.ak_akademia.mss.model.user;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Client extends MssUser {

    private String job;
    private String address;
    private int reservations;
    private int medicalRecords;
    private int bill;
    private int financialBalance;

    public Client() {
    }

    public Client(String job, String address, int reservations, int medicalRecords, int bill, int financialBalance) {
        this.job = job;
        this.address = address;
        this.reservations = reservations;
        this.medicalRecords = medicalRecords;
        this.bill = bill;
        this.financialBalance = financialBalance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "job='" + job + '\'' +
                ", address='" + address + '\'' +
                ", reservations=" + reservations +
                ", medicalRecords=" + medicalRecords +
                ", bill=" + bill +
                ", financialBalance=" + financialBalance +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return reservations == client.reservations && medicalRecords == client.medicalRecords && bill == client.bill && financialBalance == client.financialBalance && Objects.equals(job, client.job) && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), job, address, reservations, medicalRecords, bill, financialBalance);
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
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
