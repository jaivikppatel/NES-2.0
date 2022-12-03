import EncryptorSubsystem.EncryptorFacade;
import FileInterfacePackage.RedFileInterface;
import FileInterfacePackage.BlackFileInterface;
import P2PInterfacePackage.*;

import java.util.Scanner;

public class EncryptorDriver {
    static AuditInterfaceFactory auditFac = new AuditInterfaceFactory();
    static AuditInterfaceStubBase audit = auditFac.create(1);
    static String teamName, teamPass;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        AuthorizationInterfaceFactory authFac = new AuthorizationInterfaceFactory();
        AuthorizationInterfaceStubBase auth = authFac.create(1);

        boolean authFlag;

        do {
            System.out.println("\n\nENTER CREDENTIALS");
            System.out.println("-----------------------");
            System.out.print("Team Name: ");
            teamName = scan.nextLine();
            System.out.print("Team Password: ");
            teamPass = scan.nextLine();

            authFlag = auth.authenticate(teamName, teamPass);
            if (!authFlag) audit.SendLNOK(teamName, "Invalid Credentials!", true);
        } while (!authFlag);
        audit.SendLOK(teamName, "Logged In!", false);


        System.out.println("MAIN MENU");
        System.out.println("1 --> Encrypt");
        System.out.println("2 --> Decrypt");
        System.out.println("Other --> Exit");
        System.out.print("Choice? ");
        int choice = scan.nextInt();

        switch (choice) {
            case 1 -> encryptOption();
            case 2 -> decryptOption();
            default -> System.exit(0);
        }
    }

    static void encryptOption(){
        audit.FreeText(teamName, "Encryption Option Select!", false);
        Scanner scan = new Scanner(System.in);
        EncryptorFacade facade = EncryptorFacade.Instance();
        RedFileInterface rfi  = new RedFileInterface();
        BlackFileInterface bfi = new BlackFileInterface();

        System.out.print("File to Encrypt: ");
        rfi.openForRead(scan.nextLine());
        bfi.openForWrite("MyOutFile.dat");
        char c = rfi.readInputFile();
        while (! (rfi.EOF()) ){
            int con = rfi.convert(c);
            int econ = facade.encrypt(con);
            bfi.writeToFile(econ);
            c = rfi.readInputFile();
        }
        audit.SendEOK(teamName, "Encrypt Successful", false);
        rfi.closeInFile();
        bfi.closeOutFile();
        scan.close();
    }

    static void decryptOption(){

    }
}
