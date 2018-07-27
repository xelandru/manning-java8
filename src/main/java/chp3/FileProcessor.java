package chp3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {

    public static String processFile(BufferedReaderProcessor p) throws IOException {


        String fileName ="/home/adascalu/Desktop/jExer/manning/src/main/resources/chp3/file.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return p.process(br);
        }
    }


    public static void main(String[] args) throws IOException {

        String oneLine = processFile((BufferedReader br) -> br.readLine());
        System.out.println(oneLine);
        String twoLines = processFile((BufferedReader br) -> br.readLine() + "\n" + br.readLine());
        System.out.println(twoLines);
    }
}
