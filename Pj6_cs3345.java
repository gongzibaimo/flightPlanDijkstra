package pj6_cs3345;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.StringTokenizer;

public class Pj6_cs3345 {

    public static void main(String[] args) {
          // read file
        Scanner fData = null;
        Scanner fQuery = null;
        // create a out put file
        File fOut = new File(args[2]);
        // print things into the output file
        PrintWriter writer = null;

        String dataSplitBar[] = new String[4];
        String querySplitBar[] = new String[3];
        String flightTime[][] = new String [2][4];
        String thePath[][] = new String[3][3];
        List<String> myList = new ArrayList<String>();
        String name = null;
        String dataFile, queryFile;
        dataFile = fData.nextLine();
        queryFile = fQuery.nextLine();
        dataSplitBar = dataFile.split("|");
        querySplitBar = queryFile.split("|");

        try {
            fData = new Scanner(new File(args[0]));
            fQuery = new Scanner(new File(args[1]));
            writer = new PrintWriter(fOut);
            int i = 0;
            int j = 0;


            while (fData.hasNextLine()) {
                if (!myList.contains(dataSplitBar[0])) {
                    myList.add(dataSplitBar[0]);
                }
                if (!myList.contains(dataSplitBar[1])) {
                    myList.add(dataSplitBar[1]);
                }
                dataFile = fData.nextLine();
                dataSplitBar = dataFile.split("|");
            }

            while (fData.hasNextLine()) {
                j = 0;
                int k = 1;
                // strData..
                StringTokenizer strData = new StringTokenizer(dataFile, "|");
                // 改成forloop试试？
                while (k <= 2) {
                    flightTime[i][j++] = name;
                    k++;
                }
                while (strData.hasMoreTokens()) {
                    flightTime[i][j++] = strData.nextToken();
                }
                i++;
                dataFile = fData.nextLine();
            }
            i = 0;
            while (fQuery.hasNextLine()) {
                j = 0;
                // strreq...
                StringTokenizer strreq = new StringTokenizer(queryFile, "|");
                while (strreq.hasMoreTokens()) {
                    thePath[i][j++] = strreq.nextToken();
                }
                i++;
                queryFile = fQuery.nextLine();
            }

            i = 1;
            // req....
            for (String req[] : thePath) {
                if (!myList.contains(thePath[0]) && myList.contains(req[1])) {
                    //....
                    writer.println("NO FLIGHT AVAILABLE FOR THE REQUEST");
                    continue;
                }
                //...
                String type, other;
                if (req[2].equals("T")) {
                    type = "Time";
                    other = "Cost";
                } else {
                    type = "Cost";
                    other = "Time";
                }
                DijkstraAlgorithm obj = new DijkstraAlgorithm(myList, flightTime);
                obj.(flightTime, req);

                for (int o = 0; o < myList.size(); o++) {
                    if (!myList.get(o).equals(dij.getDestination())) {
                        continue;
                    }
                    //...
                    List<String> myPath = getpath(dij.getVisitedNode(), dij.getDestination());
                    writer.println(i + "|" + dij.getSource() + "|" + dij.getDestination() + "|" + dij.getOptimalDistancs().get(myList.get(o))
                            + "|" + dij.getDistances().get(myList.get(o)));
                }
            }


        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        }
//            catch (Exception e){
//                System.out.println("Exception: " + e.toString());
//            }

        writer.close();
        fData.close();
        fQuery.close();

    }
    
    private static List<String> getpath(List<Path> list, String des){
        List<String> path = new ArrayList<String>();
        for(int i = 0; i < list.size(); i++){
            if(!list.get(i).isEmpty(des)){
                continue;
            }
            path = getpath(list, list.get(i).getNewNode());
            path.add(des);
            return path;
        }
        path.add(des);
        return path;
    }
    
}
