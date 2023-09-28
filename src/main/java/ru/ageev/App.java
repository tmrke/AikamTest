package ru.ageev;



/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String uri = "src/main/resources/criteria/criteria1.json";

        JsonParser jsonParser = new JsonParser();

        jsonParser.parseToCriteria(uri);
    }
}


