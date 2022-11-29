package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ClientRegistration {

    public static void main(String[] args) {

        Client client1 = new Client(1, // auto
                true, // auto
                null, // auto
                null, // must be validated
                null, // must be validated
                null, // must be validated
                null, // must be validated
                0, // must be validated
                null, // must be validated
                null, // must be validated
                0, // must be validated
                null, // must be validated
                null, // must be validated
                null, // must be validated
                null, // must be validated
                null, // must be validated
                0, // auto
                0, // auto
                0, // auto
                0, // auto
                null // must be validated
        );
        client1.setClientId(1);
        client1.setActive(true);
        client1.setRegistrationDate(LocalDate.from(LocalDateTime.now()));

        Scanner sc = new Scanner(System.in);
        System.out.print("Adja meg a keresztnevét:");
        client1.setFirstName(sc.next());
        sc.nextLine();

        System.out.print("Adja meg a vezetéknevét:");
        client1.setLastName(sc.next());

        System.out.print(client1.toString());

    }
}
