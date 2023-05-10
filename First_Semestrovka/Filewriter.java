package AISD.Semestrovka;


//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Filewriter {
//    public static void main(String[] args) {
//        try{
//            File file = new File("Test.txt");
//            file.createNewFile();
//            PrintWriter pw = new PrintWriter(file);
//            for (int i = 0; i < 70; i++) {
//                List<Integer> list = new ArrayList<>();
//                for (int j = 0; j < (int)(Math.random()* 10000)+100; j++) {
//                    list.add(Double.valueOf(Math.random()*10000).intValue());
//                }
//                for (int j = 0; j < list.size(); j++) {
//                    pw.print(list.get(j));
//                    pw.print(" ");
//                }
//                pw.println();
//            }
//        }catch (IOException e){
//            System.out.println(e);
//        }
//    }
//}




import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class Filewriter {
    public static void fileWriter() throws IOException {
        File file = new File("input.txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter("input.txt");
        int value = (int) Math.random()*100;
        for (int i = 0; i < Math.random() * 50 + 50; i++) {
            fileWriter.write(value + " ("  +(int) (Math.random() * 100)
                    + "," + (int) (Math.random() * 100)
                    + "," + Math.abs((int) (Math.random() *100))+") ");
            for (int j = 0; j < Math.random() * 9900 + 99; j++) {
                fileWriter.write(";\n");
                fileWriter.write((int) (Math.random() * 1000) + "  (" + (int) (Math.random()*100)
                        + "," + (int) (Math.random() * 100)
                        + "," + Math.abs((int) (Math.random() * 100))+") ");
                for (int k = 0; k < (Math.random() * 100+100) ; k++) {
                    fileWriter.write( "  (" + (int) (Math.random()*100)
                            + "," + (int) (Math.random()*100)
                            + "," + Math.abs((int) (Math.random() * 100))+") ");
                }
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException {
        fileWriter();
    }
}

