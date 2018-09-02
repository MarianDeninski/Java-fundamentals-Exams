import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.in;
import static java.lang.System.setOut;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        int size = Integer.parseInt(reader.readLine());

        String[] commands = reader.readLine().split(", ");

        String[][] matrix = new String[size][size];

        int food = 0;
        int lenght = 1;

        int row = 0;
        int col = 0;

        for (int i = 0; i < size; i++) {
            String[] str = reader.readLine().split(" ");
            matrix[i] = str;
            food+= Arrays.stream(str).filter(a->a.equals("f")).count();
        }

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                if(matrix[i][j].equals("s")){

                    row = i;
                    col = j;
                }

            }
        }

        for (String command : commands) {

            switch (command){

                case "up":

                    if(row-1<0){
                        row = matrix.length-1;
                    }else{
                        row = row-1;
                    }
                    break;

                case "right":
                    if(col+1>matrix[0].length-1){
                        col = 0;
                    }else{
                        col = col+1;
                    }
                    break;

                case "down":
                    if(row+1>matrix.length-1){
                        row = 0;
                    }else{
                        row = row+1;
                    }
                    break;

                case "left":
                    if(col-1<0){
                        col = matrix[0].length-1;
                    }else {
                        col = col-1;
                    }
                    break;
            }
            String str =  matrix[row][col];
            if(str.equals("f")){
                food--;
                lenght++;

            }

            if(str.equals("e")){

                System.out.println("You lose! Killed by an enemy!");
                return;
            }


            if(food==0){
                System.out.println("You win! Final snake length is "+lenght);
                return;
            }
        }
        System.out.println("You lose! There is still "+food+" food to be eaten.");
    }
}
