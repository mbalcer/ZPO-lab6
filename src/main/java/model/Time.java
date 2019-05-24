package model;

import annotation.Scheduled;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time() {
        LocalTime localTime = LocalTime.now();
        this.hour = localTime.getHour();
        this.minute = localTime.getMinute();
        this.second = localTime.getSecond();
    }

    @Scheduled
    public void moveClockBySecond() {
        this.second++;
        changeMinute();
        changeHour();
    }

    private void changeHour() {
        if (this.minute>=60) {
            this.hour++;
            this.minute = 0;
        }
    }

    private void changeMinute() {
        if (this.second>=60) {
            this.minute++;
            this.second = 0;
        }
    }

    @Scheduled
    public void showTime() {
        System.out.println(String.format("%02d:%02d:%02d", this.hour, this.minute, this.second));
    }


}
