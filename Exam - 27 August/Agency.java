import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.setOut;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String[] input = reader.readLine().split("\\s+");
        String[] inputAgents = reader.readLine().split("\\s+");


        Queue<String> idies = new ArrayDeque<>();
        List<String> list = new ArrayList<>();


        Queue<String> agents = new ArrayDeque<>();


        for (String s : input) {

            ((ArrayDeque<String>) idies).push(s);

        }

        for (String inputAgent : inputAgents) {

            agents.add(inputAgent);

        }

        String here = reader.readLine();

        while (!"stop".equals(here)) {

            String[] output = here.split("\\s+");


            switch (output[0]) {

                case "add-ID":

                    ((ArrayDeque<String>) idies).push((output[1]));

                    break;

                case "add-agent":


                    agents.add(output[1]);
                    break;

                case "remove-ID":
                    //{ID Number} has been removed.
                    String removed = " ";

                    removed = ((ArrayDeque<String>) idies).pop();


                    System.out.println(removed + " has been removed.");
                    break;

                case "remove-agent":

                    String name = ((ArrayDeque<String>) agents).removeLast();
                    System.out.println(name + " has left the queue.");
                    break;

                case "sort-ID":

                    for (String idy : idies) {

                        list.add(idy);

                    }

                    list = list.stream().sorted((a, b) -> Integer.parseInt(b) - Integer.parseInt(a)).collect(Collectors.toList());
                    idies = new ArrayDeque<>();
                    for (String s : list) {

                        idies.add(s);
                    }
            }
            here = reader.readLine();
        }


        while (agents.size() > 0 && idies.size() > 0) {

                System.out.println(((ArrayDeque<String>) agents).getFirst() + " takes ID number: " + ((ArrayDeque<String>) idies).getFirst());
                ((ArrayDeque<String>) idies).pop();
                ((ArrayDeque<String>) agents).pop();

        }

        if(idies.size()>0 && agents.size()==0) {
            System.out.println("No more agents left.");
            for (String idy : idies) {
                System.out.println("ID number: " + idy);
            }
            
        }

        if(agents.size()>0 && idies.size()==0){
            for (String agent : agents) {

                System.out.println(agent +" does not have an ID.");
            }
        }

    }


}

