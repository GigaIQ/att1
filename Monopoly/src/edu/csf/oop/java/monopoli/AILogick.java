package edu.csf.oop.java.monopoli;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class AILogick {

    public static Logger logger = LoggerFactory.getLogger(Main.class.getName());
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static boolean checkContains(List<String> list, String nowString) {
        for (String s : list) {
            if (s.equals(nowString)) {
                return true;
            }
        }
        return false;
    }

    private static void mapDrawing(String[][] moveArr) {
        System.out.println("Карта игры:");
        for (int i = 0; i < moveArr.length - 1; i++) {
            for (int j = 0; j < moveArr[i].length; j++) {
                System.out.println(moveArr[i][j] + " " + moveArr[i + 1][j]);
            }
        }
    }

    public static void play() {

        boolean checkNalog;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя первого игрока - ");
        Player player1 = new Player(scanner.nextLine(), 2500, 0, "", 0);
        Player player2 = new Player("ВОТ", 2500, 0, "", 0);
        String[][] moveArr = new String[][]{{"1) Старт", "2) Первая Парковая Ул.", "3) Ул. Огарева",
                "4) Нагатинская Ул.", "5) Ул. Поляка", "6) Рязанский Проспект", "7) Ул. Вавилова",
                "8) Ростовская Наб.", "9) Ул. Тверская", "10 Пушкинская Ул.", "11) Ул. Чайковского", "12) Смоленская Площадь",
                "13) Ул. Малая Бронная", "14) Гоголевский Бульвар", "15) Кутузовский Проспект", "16) Ул. Арбат"}, {"",
                "150", "200", "250", "300", "350", "400", "450", "500", "550", "600", "650", "700", "750", "800",
                "850"}
        };

        System.out.println("1 - начать игру");
        System.out.println("2 - выйти");
        int isStart = scanner.nextInt();
        if (isStart == 1) {

            mapDrawing(moveArr);

            System.out.println();

            while (player1.getCash() > 0 && player2.getCash() > 0) {
                System.out.println("Ход игрока " + player1.getName());
                System.out.println("1 - Бросить куб");
                System.out.println("2 - Обстановка на карте");
                System.out.println("3 - Ваши владения");
                int action = scanner.nextInt();
                if (action == 3) {
                    player1.getAllOwn();
                    continue;
                }
                if (action == 2) {
                    for (int i = 0; i < player1.getOwn().size(); i++) {
                        for (int j = 0; j < moveArr[0].length; j++) {
                            if (player1.getOwn().get(i).equals(moveArr[0][j])) {
                                System.out.println(ANSI_BLUE + moveArr[0][j] + ANSI_RESET);
                            }
                        }
                    }
                    for (int i = 0; i < player2.getOwn().size(); i++) {
                        for (int j = 0; j < moveArr[0].length; j++) {
                            if (player2.getOwn().get(i).equals(moveArr[0][j])) {
                                System.out.println(ANSI_RED + moveArr[0][j] + ANSI_RESET);
                            }
                        }
                    }
                    continue;
                }
                if (action == 1) {
                    checkNalog = false;
                    int move = (new Random().nextInt(6) + 1);
                    System.out.println("Вам выпало " + move);

                    player1.setPosition(move);
                    logger.info("Сделан ход игрока");
                    System.out.println("Вы пришли на " + moveArr[0][player1.getPosition()]);

                    if (moveArr[0][player1.getPosition()].equals(moveArr[0][0])) {
                        System.out.println("Вы пришли на старт и получили 200$");
                        player1.setCash(player1.getCash() + 200);
                        checkNalog = true;
                    }
                    if (checkContains(player1.getOwn(), moveArr[0][player1.getPosition()])) {
                        System.out.println("Вы пришли на свою территорию");
                        checkNalog = true;
                    }
                    if (checkContains(player2.getOwn(), moveArr[0][player1.getPosition()])) {
                        System.out.println("Вы остановились на чужой территории и с вас взяли ренту в размере " +
                                (Integer.parseInt(moveArr[1][player1.getPosition()]) / 5) + "$");
                        player1.setCash(player1.getCash() - (Integer.parseInt(moveArr[1][player1.getPosition()]) / 5));
                        player2.setCash(player2.getCash() + (Integer.parseInt(moveArr[1][player1.getPosition()]) / 5));
                        System.out.println("У вас осталось " + player1.getCash() + "$");
                        checkNalog = true;
                    }
                    if (!checkNalog) {
                        System.out.println("У вас есть " + player1.getCash() + "$");
                        System.out.println("Что делаем?");
                        System.out.println("1 - покупаю за " + moveArr[1][player1.getPosition()] + "$");
                        System.out.println("2 - оставляю");
                        action = scanner.nextInt();
                        if (action == 1) {
                            if (player1.getCash() >= Integer.parseInt(moveArr[1][player1.getPosition()])) {
                                System.out.println("Вы приобрели " + moveArr[0][player1.getPosition()]);
                                player1.addOwn(moveArr[0][player1.getPosition()]);
                                player1.setCash(player1.getCash() - Integer.parseInt(moveArr[1][player1.getPosition()]));
                                System.out.println("У вас осталось " + player1.getCash() + "$");
                                logger.info("Игрок совершил покупку");
                            } else {
                                System.out.println("У вас не достатачно $");
                            }
                        }
                        if (action == 2) {
                            System.out.println("Вы оставляете территорию");
                        }
                        if (action != 2 && action != 1) {
                            System.out.println("Вы нажали не туда и пропускаете ход");
                        }
                    }
                    System.out.println();
                }
                if (action != 1 & action != 2 && action != 3) {
                    System.out.println("Вы выбрали неправильное действие!!");
                    continue;
                }

                boolean checkNalog2 = false;
                int action2;
                System.out.println("Ход игрока " + player2.getName());

                int move2 = (new Random().nextInt(6) + 1);
                System.out.println("Боту выпало " + move2);

                player2.setPosition(move2);
                logger.info("Сделан ход бота");

                System.out.println("Бот пришёл на " + moveArr[0][player2.getPosition()]);
                if (moveArr[0][player2.getPosition()].equals(moveArr[0][0])) {
                    System.out.println("Бот пришёл на старт и получил 200$");
                    player1.setCash(player2.getCash() + 200);
                    checkNalog2 = true;
                }
                if (checkContains(player2.getOwn(), moveArr[0][player2.getPosition()])) {
                    System.out.println("Бот пришёл на свою территорию");
                    checkNalog2 = true;
                }
                if (checkContains(player1.getOwn(), moveArr[0][player2.getPosition()])) {
                    System.out.println("Бот остановился на вашей территории и отдал за ренту " +
                            (Integer.parseInt(moveArr[1][player2.getPosition()]) / 5) + "$");
                    player2.setCash(player2.getCash() - (Integer.parseInt(moveArr[1][player2.getPosition()]) / 5));
                    player1.setCash(player1.getCash() + (Integer.parseInt(moveArr[1][player2.getPosition()]) / 5));
                    System.out.println("У вас теперь " + player1.getCash() + "$");
                    checkNalog2 = true;
                }
                if (!checkNalog2) {
                    action2 = new Random().nextInt(2);
                    if (action2 == 1) {
                        if (player2.getCash() >= Integer.parseInt(moveArr[1][player2.getPosition()])) {
                            System.out.println("Бот приобрёл " + moveArr[0][player2.getPosition()]);
                            logger.info("Бот купил территорию");
                            player2.addOwn(moveArr[0][player2.getPosition()]);
                            player2.setCash(player2.getCash() - Integer.parseInt(moveArr[1][player2.getPosition()]));
                            System.out.println("У него осталось " + player2.getCash() + "$");
                        } else {
                            System.out.println("Бот хотел купить территорию, но у него " +
                                    "недостаточно $");
                        }
                    }
                    if (action2 == 0) {
                        System.out.println("Бот оставляет территорию");
                    }
                }
                System.out.println();

                if (player1.getCash() >= 5000 || player2.getCash() < 0) {
                    System.out.println("Вы победили");
                    break;
                }
                if (player2.getCash() >= 5000 || player1.getCash() < 0) {
                    System.out.println("Вы проиграли :с");
                    break;
                }
            }
        }
    }
}
