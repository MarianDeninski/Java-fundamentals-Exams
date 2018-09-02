import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.in;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        int problemCount = Integer.parseInt(reader.readLine());
        int namesCount = Integer.parseInt(reader.readLine());

        Queue<String> problems = new ArrayDeque<>();
        Queue<String> names = new ArrayDeque<>();


        for (int i = 0; i < problemCount; i++) {
            String prob = reader.readLine();

            ((ArrayDeque<String>) problems).push(prob);
        }
        String regex = "^[A-Z][a-z]* [A-Z][a-z]*$";


        for (int i = 0; i < namesCount; i++) {

            String input = reader.readLine();
            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(input);

            if (match.find()) {
                names.add(input);
            }
        }

        while (true) {
            if (names.size() == 1) {
                System.out.println(((ArrayDeque<String>) names).getFirst() + " gets the job!");
                return;
            }

            if(problems.size()==0){
                break;
            }
            if(names.size()==0){
                break;
            }

                    int sumNamae = ((ArrayDeque<String>) names).getFirst().chars().sum();
                    int sumProblem = ((ArrayDeque<String>) problems).getFirst().chars().sum();
                    String prName = ((ArrayDeque<String>) problems).getFirst();

                    if (sumNamae > sumProblem) {
                        System.out.println(((ArrayDeque<String>) names).getFirst() + " solved " + prName + ".");
                        ((ArrayDeque<String>) problems).pop();
                        if(problems.size()==0){
                            break;
                        }
                        String here = ((ArrayDeque<String>) names).pop();
                        names.add(here);


                    } else {

                        System.out.println(((ArrayDeque<String>) names).getFirst() + " failed " + prName + ".");
                        ((ArrayDeque<String>) names).pop();
                        String here = ((ArrayDeque<String>) problems).pop();
                        problems.add(here);

                    }
        }
        System.out.println(String.join(", ", names));
    }
}
