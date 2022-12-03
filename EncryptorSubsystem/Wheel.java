package EncryptorSubsystem;

import java.util.ArrayList;
import java.util.Random;
//
// @author Bill Phillips
//
//
public class Wheel 
{


   //
   // lToR and rToL arraylists.
   //
   private final ArrayList<Integer> lToR;
   private final ArrayList<Integer> rToL;
   
   private final int                nRec;	   
   private final int            notchLoc;
   private       int              curPos;
   private final long               seed;
   
   private final static boolean 
                            DEBUG = false;

   private void loadLToR()
   {
      final int rRange   = nRec - 2;
      Random r = new Random(rRange);
      r.setSeed(seed);
      
      for (int i = 0; i<nRec; ++i) 
                             lToR.add(i);
      for (int i = 0; i<nRec; ++i)
      {
         final int iSav = lToR.get   (i);
         int j = r.nextInt(rRange);
         
         //
         // Here is the check
         // to make sure we do
         // not swap in an invalid
         // number (i.e., lToR[i] = i.
         //
         while ((i == lToR.get(j)) || 
                (i == lToR.get(j+1)))
            j = r.nextInt(rRange);
         
         //
         // Swap the entries at i
         // and j or j+1.
         //
         if (i>j)
         {
            final int jSav = lToR.get(j);
            lToR.set(i,jSav);
            lToR.set(j,iSav);
         } 
         else
         {
            final int jpos = j + 1;
            final int jSav = lToR.get(jpos);
            lToR.set(i,jSav);
            lToR.set(jpos,iSav);            
         }
      }
         
      if (DEBUG)
      {
         System.out.print  ("nRec = ");
         System.out.print       (nRec);
         System.out.print (" nLoc = ");
         System.out.print   (notchLoc);
         System.out.print (" sPos = ");
         System.out.print     (curPos);
         System.out.print("  seed = ");
         System.out.print       (seed);
         System.out.println      ("L");
         
         for (int i = 0; i<nRec; ++i)
         {
            final int j = lToR.get(i);
            System.out.print  ("i = ");
            System.out.print       (i);
            if(i<10) 
                 System.out.print(" ");
            System.out.print("  j = ");
            System.out.println     (j); 
            if (i==j)
            {
               System.out.println
                          ("ERROR!!!");
               System.exit         (i);
            }
         }
         for (int i = 0; i<nRec; ++i)
         {
            boolean found = false;
            for (int j = 0; j<nRec && (found == false); ++j)
               found = ( j == lToR.get(i) );
             if (!found)
            {
               System.out.print(i);
               System.out.print(" NOT FOUND ");
               System.out.print("  ");
               System.out.println
                          ("ERROR!!!");
               System.exit         (i);
            }                  
         }            
      }
      
   }
   
   //
   // O(n squared)
   // function to load
   // rToL
   //
   private void loadRToL()
   {      
      for (int i = 0; i < nRec; ++i)
      {
         boolean found = false;
         int at = 0;
         while (!found)
         {
            final int j = lToR.get(at);
            if ( i == j )
            {
               found    =     true;
               rToL.add(at);
            }
            else ++at;
         }
      }
      if(DEBUG)
         for (int i = 0; i<nRec; ++i)
         {
            boolean found = false;
            for (int j = 0; j<nRec && (found == false); ++j)
               found = ( j == rToL.get(i) );
             if (!found)
            {
               System.out.print(i);
               System.out.print(" NOT FOUND ");
               System.out.print("  ");
               System.out.println
                          ("ERROR!!!");
               System.exit         (i);
            }                  
         } 
   }

   //
   // Converts wheel entry 
   // to a value to be added
   // to or subtracted from
   // the character position
   // mod nRec.
   //
   private void convertToOffset()
   {
      for (int i =0; i<nRec; ++i)
      {
         int offset = lToR.get(i) - i;
         lToR.set(i,offset);
         offset = rToL.get(i) - i;
         rToL.set(i,offset);
      }
   }

   //
   // nEnts = size of Wheel
   // seedIn = randome number seed 
   //   to produce unique connections
   // cPos = current wheel position
   // nPos = notch position
   //
   public Wheel(int nEnts, 
                long seedIn, 
                int cPos, 
                int nPos)
   {
      nRec =             nEnts;
      notchLoc =          nPos;
      curPos   =          cPos;
      seed     =        seedIn;
      lToR = new ArrayList<>();
      rToL = new ArrayList<>();
      loadLToR              ();
      loadRToL              ();
      convertToOffset       ();
   }
	
   //
   // Returns true if at 
   // notch, false otherwise.
   //
   public boolean atNotch()
   {
      boolean aN = ( curPos == notchLoc );
      return aN;
   }
   
   public void setCurPos(final int i)
   {
      curPos = i;
   }
   
   public int getCurPos()
   {
      return curPos;
   }
   
   public int getLtoR(final int i)
   {
      final int index = (curPos + i) % nRec;
      int val = lToR.get(index);
      val += i;
      if ( val < 0 ) val += nRec;
      else if (val >= nRec) val-= nRec;
      return val;
   }
   
   public int getRtoL(final int i)
   {
      final int index = (curPos + i) % nRec;
      int val = rToL.get(index);
      val += i;
      if ( val < 0 ) val += nRec;
      else if (val >= nRec) val-= nRec;
      return val;
   }
   
   public void advance() 
   {
      ++curPos;
      curPos = (curPos >= nRec) ? 0 : curPos;
   }
   
   public void reverse() 
   {
      --curPos;
      curPos = (curPos < 0) ? nRec-1 : curPos;
   }
}









