package P2PInterfacePackage;
//
// @author Bill Phillips
//
//
public class AuditInterfaceStubBase 
{
   private void prt
      (String TeamName, String msg, boolean alert)
   { 
      System.out.print(TeamName);
      System.out.print(" ");
      System.out.print(msg);
      System.out.print(" ");
      if (alert)
         System.out.println("Alert");
      else
         System.out.println();
   }
   
   public void SendLOK
      (String TeamName, String msg, boolean alert)
   { 
      System.out.println("Login OK");
      prt(TeamName, msg, alert);
   }
 
   public void SendLNOK
      (String TeamName, String msg, boolean alert)
   { 
      System.out.println("Login NOT OK");
      prt(TeamName, msg, alert);
   }                   
   public void SendEOK
      (String TeamName, String msg, boolean alert)
   { 
      System.out.println("Encrypt OK");
      prt(TeamName, msg, alert);
   }
 
   public void SendENOK
      (String TeamName, String msg, boolean alert)
   { 
      System.out.println("Encrypt NOT OK");
      prt(TeamName, msg, alert);
   }
                   
   public void SendDOK
      (String TeamName, String msg, boolean alert)
   { 
      System.out.println("Decrypt OK");
      prt(TeamName, msg, alert);
   }
 
   public void SendDNOK
      (String TeamName, String msg, boolean alert)
   { 
      System.out.println("Decrypt NOT OK");
      prt(TeamName, msg, alert);
   }
                   
   public void FreeText
      (String TeamName, String msg, boolean alert)
   { 
      System.out.println("FREETEXT");
      prt(TeamName, msg, alert);
   }
}
