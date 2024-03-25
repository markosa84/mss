package hu.ak_akademia.mss.model;

public enum AppointmentStatuses {
    COMPLETED(1), IN_PROGRESS(2), BOOKED(3), DID_NOT_COME(4), CANCELED(5);

    final int status;

    AppointmentStatuses(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
