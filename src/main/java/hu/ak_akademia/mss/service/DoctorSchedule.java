package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.Appointment;
import hu.ak_akademia.mss.model.DoctorsWorkingHours;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class DoctorSchedule {

    private static List<Appointment> appointments2;
    private static final int INTERVAL = 15;
    private static final int DAYS = 7;
    static int[][] array;

    public static void main(String[] args) {
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = new Appointment();
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        appointment.setStartDate(LocalDateTime.of(2023, Month.OCTOBER, 25, 9, 30));
        appointment1.setStartDate(LocalDateTime.of(2023, Month.OCTOBER, 24, 10, 30));
        appointment2.setStartDate(LocalDateTime.of(2023, Month.OCTOBER, 26, 10, 30));
        appointments.add(appointment);
        appointments.add(appointment1);
        appointments.add(appointment2);
        appointments2 = appointments;
        DoctorsWorkingHours workingHours = new DoctorsWorkingHours();
        workingHours.setStartTime(LocalTime.of(9, 0, 0));
        workingHours.setEndTime(LocalTime.of(12, 0, 0));
        LocalTime startTime = workingHours.getStartTime();
        int frequent = 0;
        while (startTime.isBefore(workingHours.getEndTime())) {
            System.out.println(startTime);
            startTime = startTime.plusMinutes(INTERVAL);
            frequent++;
        }
        array = new int[DAYS][frequent];
//        new DoctorSchedule().fillTheWeekOut(workingHours, appointment);
//        new DoctorSchedule().fillTheWeekOut(workingHours, appointment1);
//        new DoctorSchedule().fillTheWeekOut(workingHours, appointment2);
        new DoctorSchedule().fillOutAllAppointment(workingHours);
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }

    void fillOutAllAppointment(DoctorsWorkingHours workingHours) {
        appointments2.forEach(a -> fillTheWeekOut(workingHours, a));
    }

    void fillTheWeekOut(DoctorsWorkingHours workingHours, Appointment appointment) {
        LocalDate startDate = appointment.getStartDate().getDayOfWeek().name().equalsIgnoreCase(java.time.DayOfWeek.MONDAY.name()) ?
                appointment.getStartDate().toLocalDate() :
                appointment.getStartDate().toLocalDate().with(TemporalAdjusters.previous(java.time.DayOfWeek.MONDAY));
        LocalDate endDate = startDate.plusDays(DAYS);
        LocalTime startTime = workingHours.getStartTime();
        for (int i = 0, x = 0; startDate.isBefore(endDate); startDate = startDate.plusDays(1L)) {
            selectAndFillTheDayOut(startDate, workingHours, appointment, startTime, i++, x);
        }

    }

    private void selectAndFillTheDayOut(LocalDate time, DoctorsWorkingHours workingHours, Appointment appointment, LocalTime startTime, int i, int x) {
        if (startTime.isBefore(workingHours.getEndTime())) {
            if (appointment.getStartDate().getDayOfMonth() == time.getDayOfMonth()) {
                blockedTheTime(i, x, appointment, startTime);
            }
            x++;
            startTime = startTime.plusMinutes(INTERVAL);
            selectAndFillTheDayOut(time, workingHours, appointment, startTime, i, x);
        }
    }

    private void blockedTheTime(int i, int x, Appointment appointment, LocalTime localTime) {
        if (localTime.getHour() != appointment.getStartDate().getHour()) {
            return;
        }
        if (localTime.getMinute() == appointment.getStartDate().getMinute()) {
            array[i][x] = 1;
        }
    }

}
