package net.mindview.chapter14;

import net.mindview.chapter14.Part.Part;

public class RegisteredFactories {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(Part.create());
        }
    }
}
