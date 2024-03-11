package out.production.src.week1;

import java.util.*;

public class MaximalRectangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Đọc kích thước của ma trận
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Đọc ma trận
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Tính chiều rộng của hình chữ nhật tại mỗi ô
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = (j == 0) ? 1 : dp[i][j - 1] + 1;
                }
            }
        }

        // Tìm diện tích lớn nhất của hình chữ nhật
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int width = dp[i][j];
                for (int k = i; k >= 0; k--) {
                    width = Math.min(width, dp[k][j]);
                    maxArea = Math.max(maxArea, width * (i - k + 1));
                }
            }
        }

        System.out.println(maxArea);
        scanner.close();
    }
}

