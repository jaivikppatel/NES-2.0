package EncryptorSubsystem;

import java.util.ArrayList;
//
// @author Bill Phillips
//
//
public class Reflector 
{

   private final int nRec;
	
   //
   // List of
   // audit records.
   //
   private final ArrayList<Integer> recSto;
	
   //
   // Singleton instance
   // initially set to null
   //
   private static Reflector 
	     instance = null;

   private void load()
   {
      recSto.add(45);
      recSto.add(58);
      recSto.add(66);
      recSto.add(47);
      recSto.add(16);
      recSto.add(25);
      recSto.add(15);
      recSto.add(88);
      recSto.add(74);
      recSto.add(62);

      recSto.add(51);
      recSto.add(77);
      recSto.add(90);
      recSto.add(61);
      recSto.add(71);
      recSto.add (6);
      recSto.add (4);
      recSto.add(80);
      recSto.add(19);
      recSto.add(18);

      recSto.add(94);
      recSto.add(75);
      recSto.add(53);
      recSto.add(86);
      recSto.add(28);
      recSto.add (5);
      recSto.add(76);
      recSto.add(89);
      recSto.add(24);
      recSto.add(55);

      recSto.add(92);
      recSto.add(34);
      recSto.add(81);
      recSto.add(82);
      recSto.add(31);
      recSto.add(63);
      recSto.add(84);
      recSto.add(93);
      recSto.add(67);
      recSto.add(50);

      recSto.add(91);
      recSto.add(72);
      recSto.add(57);
      recSto.add(68);
      recSto.add(54);
      recSto.add (0);
      recSto.add(60);
      recSto.add (3);
      recSto.add(78);
      recSto.add(52);

      recSto.add(39);
      recSto.add(10);
      recSto.add(49);
      recSto.add(22);
      recSto.add(44);
      recSto.add(29);
      recSto.add(69);
      recSto.add(42);
      recSto.add (1);
      recSto.add(64);

      recSto.add(46);
      recSto.add(13);
      recSto.add (9);
      recSto.add(35);
      recSto.add(59);
      recSto.add(73);
      recSto.add (2);
      recSto.add(38);
      recSto.add(43);
      recSto.add(56);

      recSto.add(79);
      recSto.add(14);
      recSto.add(41);
      recSto.add(65);
      recSto.add (8);
      recSto.add(21);
      recSto.add(26);
      recSto.add(11);
      recSto.add(48);
      recSto.add(70);

      recSto.add(17);
      recSto.add(32);
      recSto.add(33);
      recSto.add(83);
      recSto.add(36);
      recSto.add(87);
      recSto.add(23);
      recSto.add(85);
      recSto.add (7);
      recSto.add(27);

      recSto.add(12);
      recSto.add(40);
      recSto.add(30);
      recSto.add(37);
      recSto.add(20);
   }
	
   private Reflector()
   {
      recSto = new ArrayList<>();
      load();
      nRec = recSto.size();
   }
	
   //
   // Singleton design
   // pattern Instance() 
   // method.
   //
   public static Reflector Instance()
   {
      if(instance == null) instance = 
                        new Reflector();
      return instance;
   }
	
   //
   // Get and set 
   // functions. 
   //
   public Integer reflect(final int i)  
   { 
      if ((i < 0) || (i >=nRec))
         return -1;
      else
      {
         final Integer ret = recSto.get(i);
         return ret;
      }
   }

   public Integer unconvert(final int i)
   {
      for (int j = 0; j < nRec; ++j )
         if ( recSto.get(j) == i ) 
            return j;
      return -1;
   }
	
}









