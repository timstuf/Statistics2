package com.nure;

import java.util.Scanner;

public class Solution {
    private Scanner scanner = new Scanner(System.in);

    public void task1part1(int n, double sampleExpectation, double generalExpectation, double generalDispersion, double alpha) {
        System.out.println(" _____________________________________________");
        System.out.println(" ________________TASK 1 PART 1________________");
        System.out.println(" _____________________________________________");
        System.out.println();
        double kNabl = (sampleExpectation - generalExpectation) * Math.sqrt(n) / Math.sqrt(generalDispersion);
        System.out.printf("          (%.3f - %.3f) * sqrt(%d)\n", sampleExpectation, generalExpectation, n);
        System.out.printf("K nabl = ------------------------------    ==   %f\n", kNabl);
        System.out.printf("               sqrt(%.3f)    \n", generalDispersion);
        double laplas = (1 - 2 * alpha) / 2;
        System.out.printf("Ф(u кр) = (1 - 2 * alpha) / 2 = %.3f\n", laplas);
        System.out.printf("Посмотрите на таблицу Лапласа. Найдите ячейку, значение которой равно %.2f. \n" +
                "Сложите строку и колонку, которой соответствует это значение, и введите это число. \n(Например, если находится на пересечении 1.6 и 0.05 введите 1,65,\n РАЗДЕЛИТЕЛЬ - ЗАПЯТАЯ)\n", laplas);
        double uKr = scanner.nextDouble();
        System.out.printf("%.3f %s %.3f\n", kNabl, kNabl > -uKr ? ">" : "<", -uKr);
        if (kNabl > -uKr) System.out.println("Нулевая гипотеза принимается");
        else System.out.println("Нулевая гипотеза отвергается");
    }

    public void task1part2(int n, double sampleExpectation, double generalExpectation, double dispersion, double alpha) {
        System.out.println(" _____________________________________________");
        System.out.println(" ________________TASK 1 PART 2________________");
        System.out.println(" _____________________________________________");
        System.out.println();
        double kNabl = (sampleExpectation - generalExpectation) * Math.sqrt(n) / Math.sqrt(dispersion);
        System.out.printf("          (%.3f - %.3f) * sqrt(%d)\n", sampleExpectation, generalExpectation, n);
        System.out.printf("K nabl = ------------------------------    ==   %f\n", kNabl);
        System.out.printf("               sqrt(%.3f)    \n", dispersion);
        System.out.printf("Посмотрите на таблицу распределения Стьюдента. Число степеней свободы равно %d. \n" +
                "Уровень значимости равен %.2f.\nВведите число на пересечении, РАЗДЕЛИТЕЛЬ - ЗАПЯТАЯ \n", n - 1, alpha);
        double t = scanner.nextDouble();
        System.out.printf("%.3f %s %.3f\n", kNabl, kNabl > t ? ">" : "<", t);
        if (kNabl < t) System.out.println("Нулевая гипотеза принимается");
        else System.out.println("Нулевая гипотеза отвергается");
    }
}
