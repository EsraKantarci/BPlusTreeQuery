import java.io.*;
import java.util.*;

public class generator {

    public static void main(String[] args) {
        // Create an empty hash map
        HashMap<Integer, Double> map = new HashMap<>();
        caseRandom(map);


        HashMap<Integer, Double> mapOrdered = new HashMap<>();
        caseIncreasing(mapOrdered);



        // Print size and content
        System.out.println("Size of map is: "
                + map.size());

                String[] query = new String[100];

        for(int i = 0; i<100; i++) {
             query[i]= randomSearchQuery();
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("inputgeneratedRandom.txt"), "utf-8"))) {


            ((BufferedWriter) writer).newLine();

            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry me = (Map.Entry) iterator.next();
                String insertOrder = inserter((int)me.getKey(), (Double)me.getValue());
                writer.write(insertOrder);
                ((BufferedWriter) writer).newLine();

            }
            for(int i = 0; i<100; i++){
               String search = query[i];
               writer.write(search);
               ((BufferedWriter) writer).newLine();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("inputgeneratedOrdered.txt"), "utf-8"))) {


            ((BufferedWriter) writer).newLine();

            Iterator iterator = mapOrdered.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry me = (Map.Entry) iterator.next();
                String insertOrder = inserter((int)me.getKey(), (Double)me.getValue());
                writer.write(insertOrder);
                ((BufferedWriter) writer).newLine();

            }
            for(int i = 0; i<100; i++){
                String search = query[i];
                writer.write(search);
                ((BufferedWriter) writer).newLine();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void caseRandom(HashMap<Integer, Double> map) {
        // Add elements to the map
        for (int i = 0; i < 500000; i++) {
            Random rand = new Random();
            double randomValue = -1000 + (2000) * rand.nextDouble();
            map.put(i, randomValue);
        }

    }

    public static void caseIncreasing(HashMap<Integer, Double> map) {
        // Add elements to the map
        int j=0;
        double[] randomNumbers = new double[500000];
        double delta = 2000.0 / (float)500000;
        Random random = new Random();
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = (double)((i*delta + random.nextDouble() * delta)-1000);
        }
        for (int i = 0; i < 500000; i++) {
            map.put(i, randomNumbers[j]);
            j++;

        }
    }

    public static String randomSearchQuery(){
            Random rand = new Random();
            int key = rand.nextInt(500000);
            return "Search (" + key + ")"  ;

    }

    public static String initializer(int m) {
        return "Initialize(" + m +")" ;

    }

    public static String inserter(int key, Double value){
        return "Insert("+key+", "+value+")";
    }


}
