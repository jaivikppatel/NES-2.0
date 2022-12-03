
package P2PInterfacePackage;

/**
 *
 * @author billp
 */
public class P2PAuthorizationInterface extends 
                    AuthorizationInterfaceStubBase
{
   @Override
   public boolean authenticate
        (String teamName, String password) 
   {
      if (!"Team1".equals(teamName))
                             return false;
      final boolean t =   
              "Team1Pass".equals(password);
      return t;
   }   
}
