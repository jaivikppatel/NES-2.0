
package P2PInterfacePackage;

/**
 *
 * @author billp
 */
public class AuthorizationInterfaceFactory 
{
   protected AuthorizationInterfaceStubBase aS = create(0);
   
   public AuthorizationInterfaceStubBase getAuditInterface() 
                                      {return aS;}
   
   public AuthorizationInterfaceStubBase create(int i)
   {
      if (i == 0) return new AuthorizationInterfaceStubBase();
      else return        new  P2PAuthorizationInterface();
   }
   
}
