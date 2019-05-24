package model;

import annotation.Scheduled;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

@AllArgsConstructor
public class Lotto {
    private String nameLotto;
    private int[] numbers;
    private int numberDraw;

    public Lotto() {
        numbers = new int[3];
        numberDraw = 1000;
    }

    @Scheduled(period = 1000 * 30)
    public void makeDraw() {
        nameLotto = "Lotto nr. "+numberDraw;
        Random rand = new Random();
        IntStream.range(0,3)
                .forEach(i -> numbers[i] = rand.nextInt(50)+1);

        showResult();
        numberDraw++;
    }

    public void showResult() {
        System.out.println("Wyniki "+nameLotto+":");
        Arrays.stream(numbers)
                .forEach(System.out::println);
    }
}
