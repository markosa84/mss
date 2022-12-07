package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ClientRegistration {
    public static void main(String[] args) {

        Scanner sc  = new Scanner(System.in);


        Client client1 = new Client();

        client1.setClientId(1);
        client1.setActive(true);
        client1.setRegistrationDate(LocalDate.from(LocalDateTime.now()));

        print("Adja meg az e-mail címét:");
        client1.setEmail(sc.next());
        sc.nextLine();

        print("Adja meg a jelszavát:");
        client1.setPassword(sc.next());
        sc.nextLine();

        print("Adja meg a keresztnevét:");
        client1.setFirstName(sc.next());
        sc.nextLine();

        print("Adja meg a vezetéknevét:");
        client1.setLastName(sc.next());
        sc.nextLine();

        print("Adja meg a születési idejét:");
        client1.setBirthday(sc.nextInt());
        sc.nextLine();

        print("Adja meg a születési helyét:");
        client1.setBirthPlace(sc.next());
        sc.nextLine();

        print("Adja meg az anyja leánykori nevét:");
        client1.setMotherName(sc.next());
        sc.nextLine();

        print("Adja meg a tajszámát:");
        client1.setTAJNumber(sc.nextInt());
        sc.nextLine();

        print("Adja meg a nemét:");
        client1.setGender(sc.next());
        sc.nextLine();

        print("Adja meg az állampolgárságát:");
        client1.setNationality(sc.next());
        sc.nextLine();

        print("Adja meg az anyanyelvét:");
        client1.setMotherLanguage(sc.next());
        sc.nextLine();

        print("Adja meg a foglalkozását:");
        client1.setJob(sc.next());
        sc.nextLine();

        print("Adja meg a címét:");
        client1.setAddress(sc.next());
        sc.nextLine();

        print("Adja meg a telefonszámát:");
        client1.setPhoneNumber(sc.next());
        sc.nextLine();

        client1.setReservations(0);
        client1.setMedicalRecords(0);
        client1.setBill(0);
        client1.setFinancialBalance(0);



         print(client1.toString());

    }

    public static void print(String s){
        System.out.print(s);
    }
}
