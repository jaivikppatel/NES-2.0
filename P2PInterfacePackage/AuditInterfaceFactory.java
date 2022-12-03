package P2PInterfacePackage;
//
// @author Bill Phillips
//
//
public class AuditInterfaceFactory 
{
   
   protected AuditInterfaceStubBase aS = create(0);
   
   public AuditInterfaceStubBase getAuditInterface() 
                                      {return aS;}
   
   public AuditInterfaceStubBase create(int i)
   {
      if (i == 0) return new AuditInterfaceStubBase();
      else return        new  P2PAuditInterface();
   }
   
}
