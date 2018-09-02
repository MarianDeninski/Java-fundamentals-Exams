import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static java.lang.System.in;
import static java.lang.System.setOut;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Map<String, String> contests = new LinkedHashMap<>();
        Map<String,Users> users = new LinkedHashMap<>();

        String input = reader.readLine();
        String[] output = null;

        while (!"end of contests".equals(input)){
            output = input.split(":");

            contests.put(output[0],output[1]);

            input = reader.readLine();
        }

        output = null;

        input = reader.readLine();

        while (!"end of submissions".equals(input)){

            output = input.split("=>");

            //{contest}:{password for contest}
            //{contest}=>{password}=>{username}=>{points}
            if(contests.containsKey(output[0])){

                String passCont = contests.get(output[0]);

                if(passCont.equals(output[1])){

                   if(users.containsKey(output[2])){

                       if(users.get(output[2]).getContests().containsKey(output[0])){
                           users.get(output[2]).check(output[0],Integer.parseInt(output[3]));
                       }else{
                           users.get(output[2]).getContests().put(output[0],Integer.parseInt(output[3]));
                       }



                   }else{
                       Users users1 = new Users(output[0],output[1],output[2],Integer.parseInt(output[3]));
                       users.put(output[2],users1);
                   }
                }
            }

            input = reader.readLine();
        }
         users.entrySet().stream().sorted((a,b)-> (int) (b.getValue().totalpoints() - a.getValue().totalpoints())).limit(1)
                 .forEach(a-> System.out.println("Best candidate is "+a.getValue().getUsername()+" with total "+a.getValue().totalpoints()+" points."));


        users =  users.entrySet().stream().sorted((a,b)->a.getKey().compareTo(b.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e2, LinkedHashMap::new));


        System.out.println("Ranking: ");
        for (Map.Entry<String, Users> s : users.entrySet()) {

            System.out.println(s.getKey());


            for (Map.Entry<String, Integer> s1 : s.getValue().sort().entrySet()) {

                System.out.println("#  "+s1.getKey()+" -> "+s1.getValue());




            }
        }
    }

    public static class Users{

        private Map<String, Integer> contests;
        private String password;
        private String username;


        public Users(String contests, String password, String username, Integer points) {


            this.contests = new LinkedHashMap<>();
            this.contests.put(contests,points);
            this.password = password;
            this.username = username;

        }

        public void check(String contest, Integer points){

            if(this.contests.containsKey(contest)){

                if(this.contests.get(contest)<points){

                    this.contests.put(contest,points);
                }

            }


        }

        public Map<String, Integer> getContests() {
            return contests;
        }

        public String getPassword() {
            return password;
        }

        public String getUsername() {
            return username;
        }

        public long totalpoints(){

            long sum = 0;
            for (Integer integer : this.contests.values()) {

                sum+=integer;
            }

            return sum;
        }
        public Map<String, Integer> sort(){

            Map<String, Integer> here = this.contests.entrySet().stream().sorted((a, b)->b.getValue()-a.getValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (e1, e2) -> e2, LinkedHashMap::new));
            return here;

        }
    }
}
