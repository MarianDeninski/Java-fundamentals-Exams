import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static java.lang.System.in;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));


        Map<String, Double> missions = new LinkedHashMap<>();

        Map<String, Agents> map = new LinkedHashMap<>();

        String input = reader.readLine();

        while (!"registration".equals(input)){

            
            if(input.startsWith("#")){

               
                String[] output = input.split(":");
                if(Double.parseDouble(output[1])>0) {

                    if (!missions.containsKey(output[0])) {

                        missions.put(output[0], Double.parseDouble(output[1]));

                    }
                }
            }else {
                char[] chars = input.toCharArray();

                if (chars[chars.length - 3] == '0') {

                    if(!map.containsKey(input)){
                        map.put(input,new Agents(input));

                    }

                }
            }
                input = reader.readLine();
        }

        input = reader.readLine();


        while (!"operate".equals(input)){

            //assign->Delta008->#BombField

            String[] output = input.split("->");

            switch (output[0]){

                case "assign":

                    if(missions.containsKey(output[2])) {

                        if(map.containsKey(output[1])) {

                            if (!map.get(output[1]).getMissions().containsKey(output[2])) {

                                map.get(output[1]).setMissions(output[2], missions.get(output[2]));

                            }
                        }
                    }
                    break;

                case "abort":
                    //abort->#Spying
                    for (Agents agents : map.values()) {

                        if(agents.getMissions().containsKey(output[1])){
                            agents.getMissions().remove(output[1]);
                        }
                    }
                    break;

                case "change":

                    //change->Bond007->K001
                    Map<String,Double> mission1 = map.get(output[1]).getMissions().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e2, LinkedHashMap::new));
                    Map<String,Double> mission2 = map.get(output[2]).getMissions();

                    map.get(output[1]).change(mission2);
                    map.get(output[2]).change(mission1);
            }
            input = reader.readLine();
        }

        Map<String,Agents> finale = map.entrySet().stream().filter(a->a.getValue().getMissions().size()!=0).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e2, LinkedHashMap::new));

        Map<String,Agents> ready = finale.entrySet().stream().sorted((a,b)-> Double.compare(b.getValue().totalRating(),a.getValue().totalRating())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e2, LinkedHashMap::new));

        for (Map.Entry<String,Agents> stringAgentsEntry : ready.entrySet()) {


            System.out.println(String.format("Agent: %s - Total Rating: %.2f",stringAgentsEntry.getKey(),stringAgentsEntry.getValue().totalRating()));

            for (Map.Entry<String,Double> stringDoubleEntry : stringAgentsEntry.getValue().sorted().entrySet()) {


                System.out.println(String.format(" - %s -> %.2f",stringDoubleEntry.getKey(),stringDoubleEntry.getValue()));

            }
        }
    }

    public static class Agents{


        String name;
        private Map<String,Double> missions;


        public Agents(String name){

            this.missions = new LinkedHashMap<>();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Map<String, Double> getMissions() {
            return missions;
        }

        public void setMissions(String missionName, double rating) {
            this.missions.put(missionName,rating);
        }

        public void change(Map<String, Double> map1){
            this.missions.clear();
            for (Map.Entry<String,Double> stringAgentsEntry : map1.entrySet()) {

                this.missions.put(stringAgentsEntry.getKey(),stringAgentsEntry.getValue());
            }
        }

        public double totalRating(){

            double result = 0D;
            for (double s : this.missions.values()) {
                result+=s;

            }

            return result;
        }
        public Map<String, Double> sorted(){

            Map<String, Double> newMap = this.missions.entrySet().stream().sorted((a,b)-> Double.compare(b.getValue(),a.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (e1, e2) -> e2, LinkedHashMap::new));


            return newMap;
        }
    }
}