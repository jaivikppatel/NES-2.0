
package FileInterfacePackage;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
/**
 *
 * @author billp
 */
public class BlackFileInterface 
{
   private FileOutputStream fos;
   private DataOutputStream dos;
   private FileInputStream fis;
   private DataInputStream dis;
   private String fN;
   private int r;
   
   public void openForRead(String fileName)
   {
      fN = fileName;
      try
      {
         fis = new FileInputStream(fileName);  
         dis = new DataInputStream(fis);
      }
      catch (IOException e)
      {
         System.out.print
                        ("Unable to open file: ");
         System.out.println(fN);
         System.exit(0);
      }
   }
   
   public void openForWrite(String fileName)
   {
      fN = fileName;
      try
      {
         fos = new FileOutputStream(fileName);  
         dos = new DataOutputStream(fos);
      }
      catch (IOException e)
      {
         System.out.print
                        ("Unable to open file: ");
         System.out.println(fN);
         System.exit(0);
      }
   }
   
   public boolean EOF()
   {
      boolean t = (r == -1);
      return t;
   }
   
   public int readFromFile()
   {
      int r2 = 0;
      try
      {
         r = 0;
         r2 = dis.readInt();
      }
      catch (IOException e)
      {
         r = -1;
      }
      return r2;
   }
   
   public void writeToFile(int i)
   {
      try
      {
         dos.writeInt(i);
      }
      catch (IOException e)
      {
         System.out.print
                       ("Unable to write file: ");
         System.out.println(fN);
         System.exit(0);
      }
   }
   
   public void closeInFile()
   {
      try
      {
         dis.close();
      }
      catch (IOException e)
      {
         System.out.print
                       ("Unable to close file: ");
         System.out.println(fN);
         System.exit(0);
      }
   }   
   
   public void closeOutFile()
   {
      try
      {
         dos.close();
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
