package assignment1.RankingSystem;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

public class getRank
{
    public static int power10(int n)
    {
        int x=1;
        for(int i=1;i<=n;i++)
            x*=10;
        return x;
    }
    public DoubleLink getRankFromFile(String path)
    {
        //Build a rank object:
        DoubleLink rank=new DoubleLink();

        File file=new File(path);
        File[] arr=file.listFiles();

        //File IDs and Marks:
        int[] mark=new int[100];
        String[] ID=new String[100];
        String[] input0 = new String[100];
        String[] input3 = new String[100];
        int n=0,k=0;
        while(n<arr.length) {
            try(FileReader fr = new FileReader(arr[n]))
            {
                BufferedReader br = new BufferedReader(fr);
                int i=0;
                String line=br.readLine();
                input0[n] = line;
                i=i+1;
                while(line!=null)
                {
                    line=br.readLine();
                    if(i==3)
                        input3[n]=line;
                    i++;

                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            n++;
        }
        while(k<n)
        {
            char[] in0 = input0[k].toCharArray();
            char[] chID=new char[100];
            int j=0;
            for(int i=0;i<in0.length;i++)
            {
                if(in0[i]==' ')
                {
                    i++;
                    for(;i<in0.length;i++,j++)
                        chID[j]=in0[i];
                }
            }
            char[] ch=new char[j];
            for(int a=0;a<j;a++)
                ch[a]=chID[a];
            String str=new String(ch);
            ID[k]=str;

            char[] in3 = input3[k].toCharArray();
            int power=0;
            int point=0;
            for(int i=0;i<in3.length;i++)
            {
                if(in3[i]=='-')
                {

                    i++;
                    power=in3.length-1-i;
                    for(;i<in3.length;i++) {
                        int base = (int) in3[i]-48;
                        point+=(base*power10(power));
                        power--;
                    }
                }
            }
            mark[k]=point;
            k++;
        }

        //Insert ID and mark into the rank:
        for(k=0;k<n;k++)
            rank.insertRank(mark[k],ID[k]);
        //print the rank:
        return rank;

    }

}
