package out.production.src.week1;

import java.util.*;

public class MazeEscape {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int escapeMaze(int[][] maze, int startRow, int startCol) {
        int n = maze.length;
        int m = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});
        maze[startRow][startCol] = 1;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            // Kiểm tra xem có đi ra được biên không
            if (row == 0 || row == n - 1 || col == 0 || col == m - 1) {
                return maze[row][col];
            }

            // Duyệt qua các ô kề
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Kiểm tra xem ô kề có hợp lệ không
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && maze[newRow][newCol] == 0) {
                    maze[newRow][newCol] = maze[row][col] + 1; // Gán giá trị bước đi
                    queue.offer(new int[]{newRow, newCol}); // Thêm vào hàng đợi
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int r = scanner.nextInt();
        int c = scanner.nextInt();

        int[][] maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maze[i][j] = scanner.nextInt();
            }
        }


        int steps = escapeMaze(maze, r - 1, c - 1);
        System.out.println(steps);

        scanner.close();
    }
}

