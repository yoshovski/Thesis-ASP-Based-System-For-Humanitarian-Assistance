package dataRandomizer;

import java.io.*;
import java.util.*;
import dataRandomizer.Randomizer;

//main
class Main{
    public static void main(String[] args) throws IOException {

       Randomizer.getInstance().generateData(100);

    }
}