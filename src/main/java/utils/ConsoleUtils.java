package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUtils {
    public BufferedReader teclado =
            new BufferedReader(new InputStreamReader(System.in));

    public void print(String x){
        System.out.println(x);
    }

    public String readLine(String x) {
        print(x);

        try {
            return teclado.readLine();
        } catch (IOException e) {
            print("There was an error trying to read a string line.");
            System.exit(1);
            return null;
        }
    }

    public int readInt(String x) {
        return Integer.parseInt(readLine(x));
    }

    public int readInt() {
        return readInt("");
    }
}
