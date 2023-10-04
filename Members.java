package com.example.chessapp;

import java.io.*;
import java.util.ArrayList;

public class Members {
    public static ArrayList<String> namesList = new ArrayList<>();
    public static ArrayList<Integer> gradeList = new ArrayList<>();
    private static ArrayList<Integer> ratingList = new ArrayList<>();
    public static ArrayList<Game> gamesList = new ArrayList<>();

    public static void addRating(int rating) {
        ratingList.add(rating);
    }

    public static int getRating(int index) {
        return ratingList.get(index);
    }

    public static void updateRating(int index, Boolean isWinner, int opponentRating) {
        int oldRating = ratingList.get(index);
        int newRating;
        int ratingDiff = opponentRating - oldRating;
        if(ratingDiff > 90){
            ratingDiff = 90;
        }
        if(ratingDiff < -90){
            ratingDiff = -90;
        }
        if (isWinner) {
            newRating = oldRating + (int) ((0.1*ratingDiff) + 10);
        } else {
            newRating = oldRating + (int) ((0.1*ratingDiff) - 10);
        }
        ratingList.set(index, newRating);
    }
    public static void updateRating(int index, int opponentRating) {
        int oldRating = ratingList.get(index);
        int newRating;
        int ratingDiff = opponentRating - oldRating;
        if(ratingDiff > 90){
            ratingDiff = 90;
        }
        if(ratingDiff < -90){
            ratingDiff = -90;
        }
        newRating = oldRating + (int)(0.1*ratingDiff);
        ratingList.set(index, newRating);
    }

    public static ArrayList<Member> createMembers() {
        ArrayList<Member> tempList = new ArrayList<>();
        for (int i = 0; i < namesList.size(); i++) {
            tempList.add(new Member(namesList.get(i), i + 1, gradeList.get(i)));
        }
        return tempList;
    }

    public static void sortLists() {
        for (int j = 0; j < namesList.size(); j++) {
            for (int i = namesList.size() - 1; i > 0; i--) {
                if (ratingList.get(i) > ratingList.get(i - 1)) {
                    int tempR = ratingList.get(i - 1);
                    ratingList.set(i - 1, ratingList.get(i));
                    ratingList.set(i, tempR);
                    String tempN = namesList.get(i - 1);
                    namesList.set(i - 1, namesList.get(i));
                    namesList.set(i, tempN);
                    int tempG = gradeList.get(i - 1);
                    gradeList.set(i - 1, gradeList.get(i));
                    gradeList.set(i, tempG);
                }
            }
        }
    }
    public static void edit(String member, String newName, String newRank, Integer newGrade) {
        if (!newRank.equals(null) && !newRank.equals("")) {
            Integer rank = Integer.parseInt(newRank);
            if (rank <= ratingList.size() && rank > 0) {
                Integer newRating;
                if (ratingList.get(namesList.indexOf(member)) > ratingList.get(rank - 1)) {
                    newRating = ratingList.get(rank - 1) - 1;
                } else {
                    newRating = ratingList.get(rank - 1) + 1;
                }
                ratingList.set(namesList.indexOf(member), newRating);
            }
        }
        if (newGrade != null) {
            gradeList.set(namesList.indexOf(member), newGrade);
        }
        if (!newName.equals(null) && !newName.equals("")) {
            namesList.set(namesList.indexOf(member), newName);
            for (int i = 0; i <= gamesList.size() - 1; i++) {
                if (gamesList.get(i).getWinner().equals(member)) {
                    gamesList.get(i).setWinner(newName);
                }
                else if (gamesList.get(i).getLoser().equals(member)) {
                    gamesList.get(i).setLoser(newName);
                }
                if(gamesList.get(i).getWinner().contains(member + "|")){
                    String opponent = gamesList.get(i).getWinner().substring(member.length() +1);
                    gamesList.get(i).setWinner(newName + "|" + opponent);
                    gamesList.get(i).setLoser(opponent + "|" + newName);
                }
                else if(gamesList.get(i).getWinner().contains("|" + member)){
                    String opponent = gamesList.get(i).getLoser().substring(member.length() +1);
                    gamesList.get(i).setLoser(newName + "|" + opponent);
                    gamesList.get(i).setWinner(opponent + "|" + newName);
                }
            }
        }
    }
    static void delete (String member){
        int index = namesList.indexOf(member);
        namesList.remove(index);
        ratingList.remove(index);
        gradeList.remove(index);
    }
    static void saveNames () throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("names.txt"));
        for (String name : namesList) {
            writer.write(name + "\n");
        }
        writer.close();
        System.out.println("Wrote names successfully!");
    }
    static void readNames () throws IOException {
        File file = new File("names.txt");
        if (!file.exists()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("names.txt"));
        }
        BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            namesList.add(line);
        }
        reader.close();
        System.out.println("Read names successfully");
        System.out.println(namesList);
    }
    static void saveGrades () throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("gradeLevels.txt"));
        for (Integer grade : gradeList) {
            writer.write(grade + "\n");
        }
        writer.close();
        System.out.println("Wrote grades successfully!");
    }
    static void readGrades () throws IOException {
        File file = new File("gradeLevels.txt");if (!file.exists()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("gradeLevels.txt"));
        }
        BufferedReader reader = new BufferedReader(new FileReader("gradeLevels.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            gradeList.add(Integer.parseInt(line));
        }
        reader.close();
        System.out.println("Read grades successfully!");
        System.out.println(gradeList);
    }
    static void saveRatings () throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("ratings.txt"));
        for (Integer rating : ratingList) {
            writer.write(rating + "\n");
        }
        writer.close();
        System.out.println("Wrote ratings successfully!");
    }
    static void readRatings () throws IOException {
        File file = new File("ratings.txt");
        if (!file.exists()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ratings.txt"));
        }
        BufferedReader reader = new BufferedReader(new FileReader("ratings.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            ratingList.add(Integer.parseInt(line));
        }
        reader.close();
        System.out.println("Read ratings successfully!");
        System.out.println(ratingList);
    }
    static void saveGames () throws IOException {
        BufferedWriter wWriter = new BufferedWriter(new FileWriter("winners.txt"));
        BufferedWriter lWriter = new BufferedWriter(new FileWriter("losers.txt"));
        for (int i = 0; i < gamesList.size(); i++) {
            wWriter.write(gamesList.get(i).getWinner() + "\n");
            lWriter.write(gamesList.get(i).getLoser() + "\n");
        }
        lWriter.close();
        wWriter.close();
        System.out.println("Wrote games successfully!");
    }
    static void readGames () throws IOException {
        File wFile = new File("winners.txt");
        if (!wFile.exists()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("winners.txt"));
        }
        File lFile = new File("losers.txt");
        if (!lFile.exists()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("losers.txt"));
        }
        BufferedReader wReader = new BufferedReader(new FileReader("winners.txt"));
        BufferedReader lReader = new BufferedReader(new FileReader("losers.txt"));
        String wLine;
        String lLine;
        while ((wLine = wReader.readLine()) != null && (lLine = lReader.readLine()) != null) {
            gamesList.add(new Game(wLine, lLine));
        }
        System.out.println("Read games successfully!");
    }
}
