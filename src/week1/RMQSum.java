package out.production.src.week1;

import java.util.*;

public class RMQSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int[][] sparseTable = buildSparseTable(numbers);

        int m = scanner.nextInt();

        int result = 0;
        int[][] res = new int[n][n];

        for (int i = 0; i < m; i++) {
            int leftIndex = scanner.nextInt();
            int rightIndex = scanner.nextInt();
            if(res[leftIndex][rightIndex] == 0){
                res[leftIndex][rightIndex]  = querySparseTable(sparseTable, leftIndex, rightIndex);
            }
            result += res[leftIndex][rightIndex];
        }

        System.out.println(result);

        scanner.close();
    }

    // Hàm xây dựng Sparse Table
    public static int[][] buildSparseTable(int[] numbers) {
        int n = numbers.length;
        int logN = (int) (Math.log(n) / Math.log(2)) + 1;
        int[][] sparseTable = new int[n][logN];

        // Khởi tạo cột đầu tiên của sparse table
        for (int i = 0; i < n; i++) {
            sparseTable[i][0] = numbers[i];
        }

        // Xây dựng các giá trị trong sparse table
        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 0; i + (1 << j) - 1 < n; i++) {
                sparseTable[i][j] = Math.min(sparseTable[i][j - 1], sparseTable[i + (1 << (j - 1))][j - 1]);
            }
        }

        return sparseTable;
    }

    // Hàm truy vấn Sparse Table
    public static int querySparseTable(int[][] sparseTable, int leftIndex, int rightIndex) {
        int k = (int) (Math.log(rightIndex - leftIndex + 1) / Math.log(2));
        return Math.min(sparseTable[leftIndex][k], sparseTable[rightIndex - (1 << k) + 1][k]);
    }
}
