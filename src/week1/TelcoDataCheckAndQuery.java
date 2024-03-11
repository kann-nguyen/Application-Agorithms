package out.production.src.week1;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class TelcoDataCheckAndQuery {

    static List<String[]> CallLogs = new ArrayList<>();

    static Map<String, Long>  countTimes = new HashMap<>();

    public static boolean isPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() != 10) {
            return false;
        }

        return phoneNumber.matches("[0-9]+");
    }

    public static long countTimeCallsFrom(String phoneNumber) {
        long result = 0;

        for(String[] call : CallLogs){
            if(phoneNumber.equals(call[1])){
                LocalTime startTime = LocalTime.parse(call[4]);
                LocalTime endTime = LocalTime.parse(call[5]);

                result += Duration.between(startTime, endTime).toSeconds();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String log = scanner.nextLine();
            if(log.equals("#")) {
                break;
            }

            CallLogs.add(log.split(" "));
        }

        while(true) {
            String log = scanner.nextLine();
            if(log.equals("#")) {
                break;
            }

            String[] query = log.split(" ");
            if(query[0].equals("?count_time_calls_from")) {
                if(countTimes.containsKey(query[1])){
                    System.out.println(countTimes.get(query[1]));
                }
                else {
                    long times = countTimeCallsFrom(query[1]);
                    countTimes.put(query[1], times);
                    System.out.println(times);
                }
            }
            else if(query[0].equals("?number_calls_from")) {
                String numberCallFrom = query[1];
                long val = CallLogs.stream().filter(call -> call[1].equals(numberCallFrom)).count();
                System.out.println(val);
            }
            else if(query[0].equals("?number_total_calls")) {
                System.out.println(CallLogs.size());
            }
            else {
                boolean res = CallLogs.stream().allMatch(call -> isPhoneNumber(call[1]) && isPhoneNumber(call[2]));
                long val = res ? 1 : 0;
                System.out.println(val);


            }
        }
    }
}
