package QuestionPicker;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class StriverSheetQuestionPickerRandom {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static File ques = new File("src/QuestionPicker/quesList.txt");
    static ArrayList<String> quesList = new ArrayList<>();
    static final int toleranceLimit = 100;

    public static void main(String[] args) throws IOException {
        System.out.println("Enter 1 for Getting ques\nEnter 2 for marking ques done");
        int choice = Integer.parseInt(br.readLine());

        switch (choice){
            case 1: findQues();
            break;
            case 2: markDone();
        }
    }

    private static void findQues() throws IOException {
        //NOTE: path must be relative to Project folder -> here, MyJavaProgs
        File ques = new File("src/QuestionPicker/quesList.txt");
        Scanner readFile = new Scanner(ques);

        while (readFile.hasNextLine()) {
            quesList.add(readFile.nextLine());
        }

        //Writing to file.
        Random randomQues = new Random();
        File writeFile = new File("src/QuestionPicker/todaysQuesList.txt");
        FileWriter fw = new FileWriter(writeFile);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date();

        fw.write("Today's Ques - " + dateFormat.format(date));
        //Choose 5 ques per day
        HashSet<Integer> set = new HashSet<>();
        int trials = 0;
        for (int i = 0; i < 5; i++) {
            int quesNumber = randomQues.nextInt(quesList.size());

            if ((set.contains(quesNumber) || quesList.get(quesNumber).contains(" ---Done--- "))) {
                if (trials <= toleranceLimit)
                    i--;
                trials++;
            }

            else {
                fw.write("\n"+quesNumber+" - "+quesList.get(quesNumber));
            }
            set.add(quesNumber);
        }
        fw.close();
        System.out.println("Ques Assigned!!");
    }

    private static void markDone() throws IOException {
        System.out.println("Enter ques numbers that are done(space separated)");
        String[] temp = br.readLine().split(" ");
        int[] done = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            done[i] = Integer.parseInt(temp[i]);
        }

        Scanner readFile = new Scanner(ques);

        while (readFile.hasNextLine()) {
            quesList.add(readFile.nextLine());
        }
        FileWriter fw = new FileWriter(ques);
        for (int i = 0; i < done.length; i++) {
            if (done[i] >= quesList.size()) {
                System.out.println("INVALID QUES NO.");
                continue;
            }
            quesList.set(done[i], " ---Done--- "+quesList.get(done[i]));
        }

        for (String line : quesList) {
            fw.write(line);
            fw.write("\n");
        }

        fw.close();
    }
}
