package edu.csf.oop.java.monopoli;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        while (true) {
            try {
                logger.info("Запуск программы");

                Scanner scanner = new Scanner(System.in);
                System.out.println("Игра монополия. Вы должны покупать территории и зарабатывать деньги, сда" +
                        "вая их в ренту. Ваши владения обозначаються " + ANSI_BLUE + "синим" +
                        ANSI_RESET + ", а территории " + "бота" + ANSI_RED + " красным. " + ANSI_RESET + "Чтобы " +
                        "игра работала максимально корректно, следуйте инструкциям управления во время игры. " +
                        "Для победы нужно обонкротить соперника. Приятной игры!!!");
                System.out.println("1 - начать игру с ботом");
                System.out.println("2 - выйти из игры");

                int playerAnswer = scanner.nextInt();

                switch (playerAnswer) {
                    case 1 -> AILogick.play();
                    case 2 -> System.exit(1);
                }
                logger.info("Плановое завершение программы");
            }catch (InputMismatchException e){
                logger.error("Ошибка ");
                System.out.println("Введёные данные некорректны");
            }
        }

    }
}
