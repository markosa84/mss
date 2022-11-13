package hu.ak_akademia.mss.model;

public class Date {  // TODO: really need this class?

    private int dateId;
    private byte year;
    private byte month;
    private byte day;
    private byte hour;
    private byte minute;

    public Date() {
    }

    public Date(int dateId, byte year, byte month, byte day, byte hour, byte minute) {
        this.dateId = dateId;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public byte getYear() {
        return year;
    }

    public void setYear(byte year) {
        this.year = year;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public byte getHour() {
        return hour;
    }

    public void setHour(byte hour) {
        this.hour = hour;
    }

    public byte getMinute() {
        return minute;
    }

    public void setMinute(byte minute) {
        this.minute = minute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date date = (Date) o;

        if (dateId != date.dateId) return false;
        if (year != date.year) return false;
        if (month != date.month) return false;
        if (day != date.day) return false;
        if (hour != date.hour) return false;
        return minute == date.minute;
    }

    @Override
    public int hashCode() {
        int result = dateId;
        result = 31 * result + (int) year;
        result = 31 * result + (int) month;
        result = 31 * result + (int) day;
        result = 31 * result + (int) hour;
        result = 31 * result + (int) minute;
        return result;
    }

    @Override
    public String toString() {
        return "Date{" +
                "dateId=" + dateId +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                '}';
    }
}
