package P2PInterfacePackage;

//
// @author Bill Phillips
//
//
public class AuthorizationInterfaceStubBase 
{
   
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

