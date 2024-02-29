import customexceptions.IncompletePromptException;
import interactions.*;
import customexceptions.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
public class Moby {
    protected static void chat(Chatbot bot, TaskList list, String line)
            throws IncompletePromptException, UnknownPromptException {
        String[] words = line.split(" ");
        String keyword = words[0];
        String[] botInstructions = Chatbot.INSTRUCTIONS;
        boolean isTypo = bot.isTypo(keyword);
        if (words.length == 1) {
            if (Arrays.binarySearch(botInstructions, keyword) >= 0) {
                throw new IncompletePromptException();
            }
            if (!isTypo) {
                throw new UnknownPromptException();
            }
        } else {
            int index;
            switch (keyword) {
            case "echo":
                bot.echo(line);
                break;
            case "rename":
                bot.rename(line);
                break;
            case "mark":
                list.mark(line, true);
                break;
            case "unmark":
                list.mark(line, false);
                break;
            case "todo":
                list.addNewTask(line, "todo");
                break;
            case "deadline":
                list.addNewTask(line, "deadline");
                break;
            case "event":
                list.addNewTask(line, "event");
                break;
            case "delete":
                list.deleteTask(bot.parseIndex(line, keyword));
                break;
            default:
                if (!isTypo) {
                    throw new UnknownPromptException();
                }
            }
        }
    }

    public static void main(String[] args) {
        Chatbot bot = new Chatbot();
        TaskList list = new TaskList();
        bot.greet();
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("YOU: ");
            line = in.nextLine();
            System.out.print(bot.getName() + ": ");
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                list.printList();
            } else {
                try {
                    chat(bot, list, line);
                } catch (IncompletePromptException e) {
                    System.out.println("Sorry, your sentence appears to be incomplete. Could you complete your sentence?");
                } catch (UnknownPromptException e) {
                    System.out.println("Sorry, I do not understand what you're saying.");
                }
            }
            try {
                list.saveFile("./data/list.txt");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        bot.exit();
    }
}
