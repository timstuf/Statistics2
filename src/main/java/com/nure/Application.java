package com.nure;

public class Application {
    public static void main(String[] args) {

        Solution solution = new Solution();
        solution.task1part1(25, 5.5, 6.6, 16, 0.1); //n, m*, m0, D, alpha
        solution.task1part2(25, 5.5, 6.6, 15.5, 0.02);
        solution.task2(25,15.5, 16,0.1); //n, D, D0, alpha
        solution.task3(new double[]{22, 26, 30, 34, 38, 42, 46, 50, 54, 58}, new int[]{0, 1, 3, 4, 4, 5, 4, 2, 1, 1}, 0.05);
    }
}
