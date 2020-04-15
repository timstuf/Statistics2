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
        System.out.printf("H0: m = %.1f\n", generalExpectation);
        System.out.printf("H1: m < %.1f\n\n", generalExpectation);
        System.out.printf("          (%.3f - %.3f) * sqrt(%d)\n", sampleExpectation, generalExpectation, n);
        System.out.printf("K nabl = ------------------------------    ==   %f\n", kNabl);
        System.out.printf("               sqrt(%.3f)    \n\n", generalDispersion);
        double laplas = (1 - 2 * alpha) / 2;
        System.out.printf("Ф(u кр) = (1 - 2 * alpha) / 2 = (1 - 2 * %.2f) / 2 = %.3f\n", alpha, laplas);
        System.out.printf("Посмотрите на таблицу Лапласа. Найдите ячейку, значение которой равно %.2f. \n" +
                "Сложите строку и колонку, которой соответствует это значение, и введите это число. \n(Например, если находится на пересечении 1.6 и 0.05 введите 1,65,\n РАЗДЕЛИТЕЛЬ - ЗАПЯТАЯ)\n", laplas);
        double uKr = scanner.nextDouble();
        System.out.printf("%.3f %s %.3f\n", kNabl, kNabl > -uKr ? ">" : "<", -uKr);
        if (kNabl > -uKr) System.out.printf("Нулевая гипотеза H0: m = %.1f принимается\n", generalExpectation);
        else System.out.printf("Нулевая гипотеза отвергается, так как не выполняется %.3f > %.3f\n", kNabl, -uKr);
    }

    public void task1part2(int n, double sampleExpectation, double generalExpectation, double dispersion, double alpha) {
        System.out.println(" _____________________________________________");
        System.out.println(" ________________TASK 1 PART 2________________");
        System.out.println(" _____________________________________________");
        System.out.println();
        double kNabl = (sampleExpectation - generalExpectation) * Math.sqrt(n) / Math.sqrt(dispersion);
        System.out.printf("H0: m = %.1f\n", generalExpectation);
        System.out.printf("H1: m не равно %.1f\n\n", generalExpectation);
        System.out.printf("          (%.3f - %.3f) * sqrt(%d)\n", sampleExpectation, generalExpectation, n);
        System.out.printf("K nabl = ------------------------------    ==   %f\n", kNabl);
        System.out.printf("               sqrt(%.3f)    \n", dispersion);
        System.out.printf("Посмотрите на таблицу распределения Стьюдента. Число степеней свободы равно %d. \n" +
                "Уровень значимости равен %.2f.\nВведите число на пересечении, РАЗДЕЛИТЕЛЬ - ЗАПЯТАЯ \n", n - 1, alpha);
        double t = scanner.nextDouble();
        System.out.printf("|%.3f| %s %.3f\n", kNabl, Math.abs(kNabl) > t ? ">" : "<", t);
        if (Math.abs(kNabl) < t) System.out.printf("Нулевая гипотеза H0: m = %.1f принимается\n", generalExpectation);
        else System.out.printf("Нулевая гипотеза отвергается, так как не выполняется |%.3f| < %.3f", kNabl, t);
    }

    public void task3(double[] x, int[] n, double alpha) {
        System.out.println(" _____________________________________________");
        System.out.println(" ________________TASK 3_______________________");
        System.out.println(" _____________________________________________");
        System.out.println();
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
        System.out.printf("\npi' = 1 / %d = %.2f\n", K, 1/(double)K);
        System.out.printf("ni' = %.2f * %d = %.2f\n\n", 1 / (double)K, N, 1 / (double)K * N);
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
        System.out.println("\n");

        double[] answer = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            answer[i] = Math.pow(n[i]-nsh[i], 2)/nsh[i];
            System.out.printf("(%d - %.3f)^2 / %.3f = %.2f\n", n[i], nsh[i], nsh[i], answer[i]);
        }
        double kNabl = DoubleStream.of(answer).sum();
        System.out.printf("K набл = %.3f\n\n", kNabl);
        System.out.printf("Посмотрите на таблицу распределения ХиКвадрат. Число степеней свободы равно %d. \n" +
                "Уровень значимости равен %.2f.\nВведите число на пересечении, РАЗДЕЛИТЕЛЬ - ЗАПЯТАЯ \n", K-1, alpha);
        double kKr = scanner.nextDouble();
        System.out.printf("%.3f %s %.3f\n", kNabl, kNabl > kKr ? ">" : "<", kKr);
        if (kNabl < kKr) System.out.println("Нулевая гипотеза принимается (распределение равномерное)");
        else System.out.println("Нулевая гипотеза отвергается (распределение неравномерное)");
    }

    public void task2(int n,double dispersionCounted, double dispersionExpected,  double alpha)
    {
        System.out.println(" _____________________________________________");
        System.out.println(" ___________________TASK 2____________________");
        System.out.println(" _____________________________________________");
        System.out.println();
        System.out.printf("H0: D = %.1f\n", dispersionExpected);
        System.out.printf("H1: D не равно %.1f\n\n", dispersionExpected);
        double KN=((n-1)*dispersionCounted)/dispersionExpected;
        System.out.printf("          (%d - 1) * sqrt(%.3f)\n", n, dispersionCounted);
        System.out.printf("K nabl = ------------------------------ = %.3f\n", KN);
        System.out.printf("               sqrt(%.3f)    \n\n", dispersionExpected);
        System.out.printf("Посмотрите на таблицу Хи-распределения, найдите уровень значимости 1-(аlpha/2) = %.3f и степеней свободы (n-1) = %d.(Для левого края)\n" +
                "(РАЗДЕЛИТЕЛЬ - ЗАПЯТАЯ)\n", (1-alpha/2), n-1);
        double XL = scanner.nextDouble();
        System.out.printf("Кр л = %.3f\n\n", XL);
        System.out.printf("Посмотрите на таблицу Хи-распределения, найдите уровень значимости (аlpha/2) = %.3f и степеней свободы (n-1) = %d.(Для правого края)\n" +
                "(РАЗДЕЛИТЕЛЬ - ЗАПЯТАЯ)\n", (alpha/2), n-1);
        double XR = scanner.nextDouble();
        System.out.printf("Кр п = %.3f\n\n", XR);

        System.out.printf("Введенные данные: для левого края (Кл): %.2f, для правого края (Кп) %.2f\n", XL, XR);
        System.out.printf("Чекнем неравнество: Кл < Кн < Кп\n" +
                "В нашем случае %.2f < %.2f < %.2f\n" +
                " _____________________________________________\n" +
                "Если оба неравенства выполняются, то наше предположение\n" +
                " о том, что дисперсия ген. совокупности\n" +
                "равна %.2f согласуется с єксперемент. данными, \n" +
                "тоесть стат. гипотеза H0: D = %.2f о равенстве принимается\n" +
                " _____________________________________________\n" +
                "Логично предположить, что если хотя бы одно из неравенств\n" +
                "не выполняется, то стат. гипотеза H0: D = %.2f о равенстве не принимается\n", XL,KN,XR, (float)dispersionExpected, dispersionExpected, dispersionExpected);
    }

}
