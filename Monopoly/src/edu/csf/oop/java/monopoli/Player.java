package edu.csf.oop.java.monopoli;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private int cash;
    private int position;
    private int ownCount;
    private final List<String> allOwn = new ArrayList<>();

    public Player(String name, int cash, int position, String own, int ownCount) {
        this.name = name;
        this.cash = cash;
        this.position = position;
        this.ownCount = ownCount;
    }

    public String getName() {
        return name;
    }

    public int getCash() {
        return cash;
    }

    public int getPosition() {
        return position;
    }

    public List<String> getOwn() {
        return allOwn;
    }

    public int getOwnCount() {
        return ownCount;
    }

    public void getAllOwn() {
        System.out.print("Ваши владения: ");
        if (ownCount !=0) {
            for (int i = 0; i < ownCount; i++) {
                System.out.print(allOwn.get(i) + "; ");
            }
        }else {
            System.out.println("У вас ничего нету :c");
        }
        System.out.println();
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setPosition(int position) {
        if (this.position + position < 16) {
            this.position += position;
        }else {
            this.position += position - 16;
        }
    }

    public void addOwn(String nowOwn) {
        allOwn.add(nowOwn);
        ownCount++;
    }
}
