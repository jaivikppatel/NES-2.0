
package FileInterfacePackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author billp
 */
public class RedFileInterface
{
    private final Deque<Integer> deque
            = new LinkedList<>();
    private final Deque<Integer> revDeque
            = new LinkedList<>();
    private File f;
    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;
    private String fN;
    private int r;

    public void clearRevBuf()
    {
        revDeque.clear();
    }

    public void addToRevBuf(int i)
    {
        revDeque.addLast(i);
    }

    public int removeFromRevBuf()
    {
        final int i = revDeque.removeLast();
        return i;
    }

    public boolean emptyRevBuf()
    {
        final boolean b = revDeque.isEmpty();
        return b;
    }

    public void clearBuf()
    {
        deque.clear();
    }

    public void addToBuf(int i)
    {
        deque.addLast(i);
    }

    public int removeFromBuf()
    {
        final int i = deque.removeLast();
        return i;
    }

    public boolean emptyBuf()
    {
        final boolean b = deque.isEmpty();
        return b;
    }

    public char unConvert(int i)
    {
        char ct = (char) (i + 32);
        return ct;
    }
    public int convert(char c)
    {
        int ct = 0;
        if (c >= 32) ct = c - 32;
        return ct;
    }

    public void openForWrite(String fileName)
    {
        r = 0;
        try
        {
            fN = fileName;
            f=new File(fileName);
            fw=new FileWriter(f);
            bw=new BufferedWriter(fw);
        }
        catch (IOException e)
        {
            System.out.print("Unable to open file: ");
            System.out.println(fileName);
            System.exit(0);
        }

    }

    public void openForRead(String fileName)
    {
        r = 0;
        try
        {
            fN = fileName;
            f=new File(fileName);
            fr=new FileReader(f);
            br=new BufferedReader(fr);
        }
        catch (IOException e)
        {
            System.out.print("Unable to open file: ");
            System.out.println(fileName);
            System.exit(0);
        }

    }

    public boolean EOF()
    {
        boolean b = (r == -1);
        return b;
    }

    public void writeToFile(char c)
    {
        try
        {
            bw.write(c);
        }
        catch (IOException e)
        {
            System.out.print
                    ("Unable to write to file: ");
            System.out.println(fN);
            System.exit(0);
        }
    }

    public char readInputFile()
    {
        char c = 0;
        try
        {
            r = br.read();
            if (r==-1) return 0;
            c = (char) r;
            return c;
        }
        catch (IOException e)
        {
            System.out.print
                    ("Unable to read file: ");
            System.out.println(fN);
            System.exit(0);
        }
        return c;
    }

    public void closeOutFile()
    {
        try
        {
            bw.close();
        }
        catch (IOException e)
        {
            System.out.print
                    ("Unable to close file: ");
            System.out.println(fN);
            System.exit(0);
        }
    }

    public void closeInFile()
    {
        try
        {
            br.close();
        }
        catch (IOException e)
        {
            System.out.print
                    ("Unable to close file: ");
            System.out.println(fN);
            System.exit(0);
        }
    }

}
