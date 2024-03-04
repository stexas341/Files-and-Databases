/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.tables;

import java.util.Random;

/**
 *
 * @author lympe
 */
public class RandomLicencePlate {

    private static final Random random = new Random();

    public static String generateLicencePlate() {
        // Generate 3 random uppercase letters
        String letters = random.ints(3, 65, 91) // ASCII range for uppercase letters
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        // Generate 4 random digits
        String numbers = String.format("%04d", random.nextInt(10000)); // Ensures 4 digits

        return letters + numbers;
    }

}
