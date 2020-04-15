package com.nure;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

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

    public void task3(double[] x, int[] n, double alpha) {
        for (int i = 0; i < x.length; i++) {
            double v = x[i];
            if (i == 0) {
                System.out.printf("Xi  %8s |", v);
            } else {
                System.out.printf(" %8s |", v);
            }
        }
        System.out.println();
        for (int i = 0; i < n.length; i++) {
            double v = n[i];
            if (i == 0) {
                System.out.printf("Ni  %8s |", v);
            } else {
                System.out.printf(" %8s |", v);
            }
        }
        System.out.println();
        int N = IntStream.of(n).sum();
        int K = x.length;
        double[] p = new double[x.length];
        Arrays.fill(p, 1.0/K);
        for (int i = 0; i < p.length; i++) {
            double v = p[i];
            if (i == 0) {
                System.out.printf("Pi` %8s |", v);
            } else {
                System.out.printf(" %8s |", v);
            }
        }
        System.out.println();
        double[] nsh = new double[x.length];
        Arrays.fill(nsh, (double)N/K);
        for (int i = 0; i < nsh.length; i++) {
            double v = nsh[i];
            if (i == 0) {
                System.out.printf("Ni` %8s |", v);
            } else {
                System.out.printf(" %8s |", v);
            }
        }
        System.out.println();

        double[] answer = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            answer[i] = Math.pow(n[i]-nsh[i], 2)/nsh[i];
            System.out.printf("(%d - %.3f)^2 / %.3f = %.2f\n", n[i], nsh[i], nsh[i], answer[i]);
        }
        double kNabl = DoubleStream.of(answer).sum();
        System.out.printf("Посмотрите на таблицу распределения ХиКвадрат. Число степеней свободы равно %d. \n" +
                "Уровень значимости равен %.2f.\nВведите число на пересечении, РАЗДЕЛИТЕЛЬ - ЗАПЯТАЯ \n", K-1, alpha);
        double kKr = scanner.nextDouble();
        System.out.printf("%.3f %s %.3f\n", kNabl, kNabl > kKr ? ">" : "<", kKr);
        if (kNabl < kKr) System.out.println("Нулевая гипотеза принимается (распределение равномерное)");
        else System.out.println("Нулевая гипотеза отвергается (распределение неравномерное)");
    }
}
