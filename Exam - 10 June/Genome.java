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


        String input = reader.readLine();

        String regex = "^([a-z!@#$?]*)=(\\d*)--(\\d*)(<<[a-z]*)$";
        String reg = "[a-z]";


        Map<String, Integer>map = new LinkedHashMap<>();

        while (!"Stop!".equals(input)){

            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(input);

            if(match.find()){

               int size = 0;

                for (Character s : match.group(1).toCharArray()) {


                    Pattern pattern1 = Pattern.compile(reg);
                    Matcher match1 = pattern1.matcher(s.toString());
                    if(match1.find()){

                        size++;

                    }
                }
               if(size==Integer.parseInt(match.group(2))){

                   if(map.containsKey(match.group(4).substring(2,match.group(4).length()))){


                      int res =  map.get(match.group(4).substring(2,match.group(4).length()));
                       map.put(match.group(4).substring(2,match.group(4).length()),Integer.parseInt(match.group(3))+res);
                   }else {

                       map.put(match.group(4).substring(2,match.group(4).length()), Integer.parseInt(match.group(3)));
                   }
               }

            }
            input = reader.readLine();
        }

        map.entrySet().stream().sorted((a,b)->b.getValue() - a.getValue()).forEach(a-> System.out.println(a.getKey()+" has genome size of "+a.getValue()));

    }
}
