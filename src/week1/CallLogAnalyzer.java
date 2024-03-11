package out.production.src.week1;

import java.time.LocalDateTime;
import java.util.*;
import java.time.Duration;

public class CallLogAnalyzer {
    // Kiểm tra số điện thoại hợp lệ
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 10 && phoneNumber.matches("[0-9]+");
    }

    // Tính tổng thời gian cuộc gọi từ một số điện thoại cụ thể
    public static long countTimeCallsFrom(List<String[]> logData, String phoneNumber) {
        long totalTime = 0;
        for (String[] call : logData) {
            if (call[1].equals(phoneNumber)) {
                LocalDateTime startTime = LocalDateTime.parse(call[3] + "T" +  call[4]);
                LocalDateTime endTime = LocalDateTime.parse(call[3] + "T" +  call[5]);
                totalTime += Duration.between(startTime, endTime).getSeconds();
            }
        }
        return totalTime;
    }

    // Xử lý các truy vấn
    public static void processQueries(List<String[]> logData, List<String> queries) {
        for (String query : queries) {
            String[] queryParts = query.split(" ");
            if (queryParts[0].equals("?check_phone_number")) {
                boolean valid = logData.stream()
                        .allMatch(call -> isValidPhoneNumber(call[1]) && isValidPhoneNumber(call[2]));
                System.out.println(valid ? 1 : 0);
            } else if (queryParts[0].equals("?number_calls_from")) {
                String phoneNumber = queryParts[1];
                long numCalls = logData.stream().filter(call -> call[1].equals(phoneNumber)).count();
                System.out.println(numCalls);
            } else if (queryParts[0].equals("?number_total_calls")) {
                System.out.println(logData.size());
            } else if (queryParts[0].equals("?count_time_calls_from")) {
                String phoneNumber = queryParts[1];
                long totalTime = countTimeCallsFrom(logData, phoneNumber);
                System.out.println(totalTime);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String[]> logData = new ArrayList<>();
        List<String> queries = new ArrayList<>();

        // Nhập dữ liệu log từ bàn phím
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equals("#")) {
                break;
            }
            String[] parts = line.split(" ");
            logData.add(parts);
        }

        // Nhập các truy vấn từ bàn phím
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("#")) {
                break;
            }
            queries.add(line);
        }

        scanner.close();

        // Xử lý các truy vấn
        processQueries(logData, queries);
    }
}
