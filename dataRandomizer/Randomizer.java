package dataRandomizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Randomizer {

    private static Randomizer instance = null;

    private Randomizer() {
    }

    public static Randomizer getInstance() {
        if (instance == null) {
            instance = new Randomizer();
        }
        return instance;
    }

    public String generateRandomName() {
        String[] names = { "Ivan", "Petro", "Mykola", "Oleksandr", "Andriy", "Vasyl", "Yaroslav", "Dmytro", "Oleh",
                "Yevhen", "Roman", "Volodymy", "Vladyslav", "Taras", "Yuriy", "Maksym", "Oleksiy", "Ihor", "Serhiy",
                "Viktor", "Bohdan", "Mykhailo", "Stepan", "Marian", "Yehor", "Danylo", "Vitaliy", "Kyrylo", "Mikolaj",
                "Orest", "Iryna", "Oksana", "Anastasiya", "Yuliya", "Olena", "Nataliya", "Tetyana", "Iryna",
                "Yevheniya", "Viktoria", "Iuliia", "Yana", "Yaroslava", "Yevheniia", "Oleksandra", "Iryna", "Yuliia",
                "Oksana", "Anastasiia", "Tetyana", "Iryna", "Yevheniia", "Viktoria", "Iuliia", "Yana", "Yaroslava",
                "Yevheniia", "Oleksandra", "Iryna", "Yuliia", "Oksana", "Anastasiia", "Tetyana", "Iryna", "Yevheniia",
                "Viktoria", "Iuliia", "Yana", "Yaroslava", "Yevheniia", "Oleksandra", "Iryna", "Yuliia", "Oksana",
                "Anastasiia", "Tetyana", "Iryna", "Yevheniia", "Viktoria", "Iuliia", "Yana", "Yaroslava", "Yevheniia",
                "Oleksandra", "Iryna", "Yuliia", "Oksana", "Anastasiia", "Tetyana", "Iryna", "Yevheniia", "Viktoria",
                "Iuliia", "Yana", "Yaroslava", "Yevheniia", "Oleksandra", "Iryna", "Yuliia", "Oksana", "Anastasiia",
                "Tetyana", "Iryna", "Yevheniia", "Viktoria", "Iuliia" };
        String[] surnames = { "Ivanov", "Petrov", "Mykolaev", "Oleksandrov", "Andriev", "Vasilev", "Yaroslavov",
                "Dmytrov", "Olehov", "Yevhenov", "Romanov", "Volodymov", "Vladyslavov", "Tarasov", "Yuriyev",
                "Maksymov", "Oleksiyev", "Ihorov", "Serhiyev", "Viktorov", "Bohdanov", "Mykhailov", "Stepanov",
                "Marianov", "Yehorov", "Danylov", "Vitaliyev", "Kyrylov", "Mikolajov", "Orestov", "Irynova" };

        int namesDim = names.length;
        int surnamesDim = surnames.length;

        String name = names[(int) (Math.random() * namesDim)];
        String surname = surnames[(int) (Math.random() * surnamesDim)];
        return name + " " + surname;
    }

    public String gender(int i) {
        switch (i) {
            case 0:
                return "M";
            case 1:
                return "F";
            case 2:
                return "O";
            default:
                return "M";
        }
    }

    private int generateRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public String gender() {
        return gender(generateRandomNumber(0, 2));
    }

    public String generateRandomLocation() {
        int i = generateRandomNumber(0, 1);
        // if (i == 0)
        // return "Podkarpackie";
        // else
        // return "Lubelskie";

        if (i == 0)
            return "Krakow";
        else
            return "Warszawa";
    }

    public int generateRandomAge(int lower, int upper) {
        return generateRandomNumber(lower, upper);
    }

    /**
     * 
     * @param age
     * @param gender
     * @param location
     * @return e.g "John Smith",9,"F","Krakow"
     */
    private String compone(int age, String gender, String location) {
        String name = generateRandomName();
        return "\"" + name + "\"," + age + "," + "\"" + gender + "\"," + "\"" + location + "\"";

    }

    public ArrayList<String> randomData(int lowerAge, int upperAge, double femalesSubsetPerc, double malesSubsetPerc,
            double totalSubsetPerc, double totalPeople) {
        ArrayList<String> data = new ArrayList<String>();
        int totalFemales = (int) (femalesSubsetPerc * totalSubsetPerc * (totalPeople / 10000));
        int totalMales = (int) (malesSubsetPerc * totalSubsetPerc * (totalPeople / 10000));

        System.out.println(totalFemales);
        System.out.println(totalMales);

        for (int i = 0; i < totalFemales; i++) {
            int age = generateRandomAge(lowerAge, upperAge);
            String location = generateRandomLocation();
            data.add(compone(age, "F", location));
        }

        for (int i = 0; i < totalMales; i++) {
            int age = generateRandomAge(lowerAge, upperAge);
            String location = generateRandomLocation();
            data.add(compone(age, "M", location));
        }

        return data;
    }

    public void generateData(int people) throws IOException {

        // percentages
        int disabilities = 13;
        int medical = 10;
        int protectionNeed = 9;
        int genderM = 5;
        int genderF = 94;
        int genderO = 1;

        ArrayList<String> data = new ArrayList<String>();
        data.addAll(randomData(0, 4, 49.2, 51, 8, people));
        data.addAll(randomData(5, 11, 49.6, 50, 19, people));
        data.addAll(randomData(12, 17, 51.1, 49, 15, people));
        data.addAll(randomData(18, 59, 87.1, 13, 51, people));
        data.addAll(randomData(60, 100, 76.5, 23, 8, people));

        // mix data
        Collections.shuffle(data);

        personToTxt(data);

        generateRelatives(78, 2, data);

    }

    public void personToTxt(ArrayList<String> data) throws IOException {
        String pathname = "dataRandomizer/receptionCenters.txt";
        File file = new File(pathname);
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < data.size(); i++) {
            fileWriter.write("person(" + i + "," + data.get(i) + ").");
            fileWriter.write("\r\n");
        }
        fileWriter.close();
    }

    private void generateRelatives(int percentage, int maxRelatives, ArrayList<String> data) throws IOException {
        int total = data.size();
        int totalRelatives = (int) (total * percentage / 100);

        ArrayList<String> relatives = new ArrayList<String>();

        int countRelatives = 0;

        for (int i = 0; i < total - maxRelatives; i++) {
            int relativesNumber = generateRandomNumber(0, maxRelatives);
            for (int j = 1; j <= relativesNumber; j++) {
                relatives.add(componeRelative(i, i + j));
                countRelatives++;
            }

            i+= relativesNumber;

            if (countRelatives >= totalRelatives)
                break;
        }

        relativesToTxt(relatives);

    }

    private void relativesToTxt(ArrayList<String> data) throws IOException {
        String pathname = "dataRandomizer/relatives.txt";
        File file = new File(pathname);
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < data.size(); i++) {
            fileWriter.write(data.get(i));
            fileWriter.write("\r\n");
        }
        fileWriter.close();
    }

    private String componeRelative(int personId, int relativeId) {
        return "relative(" + personId + "," + relativeId + ").";
    }

    private ArrayList<String> randomizeList(ArrayList<String> data){
        Collections.shuffle(data);
        return data;
    }

    private String componeAtom(String name, String body){
        return name + "(" + body + ").";
    }

    private void toTxt(ArrayList<String> data, String filename) throws IOException {
        File file = new File("dataRandomizer/"+filename+".txt");
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < data.size(); i++) {
            fileWriter.write(data.get(i));
            fileWriter.write("\r\n");
        }
        fileWriter.close();
    }

    /*
    * return a string of a random polish city
    */
    private String randomCity(){
        String[] cities = {"Wroclaw","Torun","Bydgoszcz","Gorzow","Kielce","Olsztyn","Poznan","Szczecin","Lodz","Krakow","Warszawa","Opole","Gdansk","Katowice","Chelm","Tomaszow Lubelski","Przemysl"};
        return cities[generateRandomNumber(0, cities.length-1)];
    }


}
