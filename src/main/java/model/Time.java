package model;

import annotation.Scheduled;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Time {
    private int hour;
    private int minute;
    private int second;

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
        System.out.println(String.format("%d:%d:%d", this.hour, this.minute, this.second));
    }


}
