
package com.company;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String h[];
        boolean more = decoder();
        String[][] book = null;
        if (more)
            book = lexicon();
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n","Name", "Secret Number", "Average", "Score 1", "Score 2");
        int x = 28;
        Scanner reader = new Scanner(new File("rawDataU6.txt"));
        Student list[] = new Student[28];
        for (int u = 0; u < list.length; u++)
            list[u] = new Student(u+1);
        for (int e = 0; e < x; e++) {
            if (!(e+1 == 15 || e+1 == 21 || e+1 == 25 || e+1 == 26))
                grade(process(reader), process(reader), 0, 0, list[e], true);
            else {
                h = process(reader);
                grade(h, h, 0, 0, list[e], true);
            }
        }
        for (int r = 0; r < x; r++)
            printer(list[r], book);


    }

    public static void grade(String[] gr1, String[] gr2, int start1, int start2, Student x, boolean q1) {
        boolean secret = true;
        double sum = 0;
        String holder;
        int place = start1;
        int locale = start2;
        while (place < gr1.length && !gr1[place].contains("+"))
            place++;
        if (place >= gr1.length) {
            secret=false;
        }
        while(secret)
        {
            Scanner inuse = new Scanner(gr1[place]);
            while (inuse.hasNext())
            {
                if (inuse.next().equalsIgnoreCase("+"))
                {
                    holder = inuse.next();
                    if (holder.contains("."))
                        sum = sum + Double.parseDouble(holder.substring(1))/10.0;
                    else if (!holder.contains("w"))
                        sum = sum + Double.parseDouble(holder);
                }
            }
            if (place + 1 >= gr1.length || !gr1[place+1].contains("+"))
                break;
            else
                place++;
        }
        place++;
        while (place < gr1.length && !gr1[place].contains("+"))
        {
            if (gr1[place].length()==1)
            {
                sum = sum - Double.parseDouble(gr1[place])*0.25;
                place ++;
                break;
            }
            place++;
        }

        while (locale < gr2.length && !gr2[locale].contains("+"))
            locale++;
        if (locale >= gr2.length)
            return;
        while(true)
        {
            Scanner inuse = new Scanner(gr2[locale]);
            while (inuse.hasNext())
            {
                if (inuse.next().equalsIgnoreCase("+"))
                {
                    holder = inuse.next();
                    if (holder.contains("."))
                        sum = sum + Double.parseDouble(holder.substring(1))/10.0;
                    else if (!holder.contains("w"))
                        sum = sum + Double.parseDouble(holder);
                }
            }
            if (locale + 1 >= gr2.length || !gr2[locale+1].contains("+"))
                break;
            else
                locale++;
        }
        locale++;
        while (locale < gr2.length && !gr2[locale].contains("+"))
        {
            if (gr2[locale].length()==1)
            {
                sum = sum - Double.parseDouble(gr2[locale])*0.25;
                locale ++;
                break;
            }
            locale++;
        }
        if (q1) {
            x.score1 = (sum / 2.0);
            grade(gr1, gr2, place, locale, x, false);
        }
        else
            x.score2=(sum/2.0);
        return;




    }

    public static String[] process (Scanner work)
    {
        String s = work.nextLine();
        String[] r;
        if (s.contains("+"))
        {
            r = s.split("\\t");
            for (int a = 0; a < r.length; a++) {
                r[a] = r[a].replaceAll("\"", "");
            }

            return r;
        }
        return process(work);
    }
    public static void printer(Student todo, String[][] forth)
    {
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n",search(forth, todo.id), todo.id, todo.score1+todo.score2, Math.round(todo.score1),Math.round(todo.score2));
    }
    public static boolean decoder() throws IOException
    {
        try {
            Scanner j = new Scanner(new File("names.txt"));
        }
        catch (java.io.FileNotFoundException e)
        {
            return false;
        }
        return true;

    }
    public static String[][] lexicon() throws IOException
    {
        String[][] enigma;
        int count = 0;
        Scanner kb = new Scanner(new File("names.txt"));
        while (kb.hasNextLine())
        {
            count++;
            kb.nextLine();
        }
        if (count > 0)
            enigma = new String[count][];
        else
            enigma=null;
        kb = new Scanner(new File("names.txt"));
        for (int t = 0; t < count; t++)
        {
            enigma[t] = kb.nextLine().split("\\s+");
        }
        return enigma;
    }
    public static String search(String[][] b, int c)
    {

        String led = "?";
        String f = String.valueOf(c);
        if (b != null)
        {
            for (int row = 0; row < b.length; row++)
                for (int col = 0; col < b[row].length; col++) {
                    if (f.equalsIgnoreCase(b[row][col])) {
                        col--;
                        led = b[row][col];
                        col++;
                    }
                }

        }
        return led;
    }
}