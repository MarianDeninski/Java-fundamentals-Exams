import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static java.lang.System.in;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String regex = "[A-Z]";
        String regexSmall = "[a-z]";
        String digit = "[0-9]";
        String missionReg = "^M.*I.*S.*S.*I.*O.*N.*$";

        Map<String, Integer> completed = new LinkedHashMap<>();
        Map<String, Integer> faild = new LinkedHashMap<>();


        String input = reader.readLine();


        while (!"Decrypt".equals(input)) {

            String mission = "";
            String name = "";
            int result = 0;

            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(input);

            while (match.find()) {

                mission += match.group(0);
            }

            Pattern pattern3 = Pattern.compile(missionReg);
            Matcher match3 = pattern3.matcher(mission);


            if (match3.find()) {

                Pattern pattern1 = Pattern.compile(regexSmall);
                Matcher match1 = pattern1.matcher(input);

                while (match1.find()) {

                    name += match1.group(0);

                }

                Pattern pattern2 = Pattern.compile(digit);
                Matcher match2 = pattern2.matcher(input);

                while (match2.find()) {

                    result += Integer.parseInt(match2.group(0));

                }
                if (input.contains("C")) {

                    completed.put(name,result);

                } else if (input.contains("X")) {
                    faild.put(name,result);

                }
            }
            input = reader.readLine();
        }

        completed.entrySet().stream().sorted((a,b)->b.getValue()-a.getValue()).limit(1).forEach(a->
                System.out.println("Completed mission "+a.getKey()+" with rating: "+a.getValue()));

        faild.entrySet().stream().sorted((a,b)->b.getValue()-a.getValue()).limit(1).forEach(a->
                System.out.println("Failed Mission "+a.getKey()+" with rating: "+a.getValue()));
    }
}