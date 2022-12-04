import EncryptorSubsystem.EncryptorFacade;
import FileInterfacePackage.RedFileInterface;
import FileInterfacePackage.BlackFileInterface;
import P2PInterfacePackage.*;

import java.util.Random;
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

        mainMenu();
    }

    static void mainMenu(){
        Scanner scan = new Scanner(System.in);
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

        System.out.print("Wheel 1 Pos: ");
        facade.setWheel1Pos(scan.nextInt());
        System.out.print("Wheel 2 Pos: ");
        facade.setWheel2Pos(scan.nextInt());
        System.out.print("Wheel 3 Pos: ");
        facade.setWheel3Pos(scan.nextInt());

        bfi.openForWrite("MyOutFile.dat");
        char c = rfi.readInputFile();
        while (! (rfi.EOF()) ){
            int con = rfi.convert(c);
            int econ = facade.encrypt(con);
            bfi.writeToFile(econ);
            c = rfi.readInputFile();
        }
        audit.SendEOK(teamName, "Encrypt Successful", false);
        System.out.print("Wheel Positions are ");
        System.out.print(facade.getWheel1Pos());
        System.out.print(facade.getWheel2Pos());
        System.out.println(facade.getWheel3Pos());
        rfi.closeInFile();
        bfi.closeOutFile();
        scan.close();
        mainMenu();
    }

    static void decryptOption(){
        audit.FreeText(teamName, "Decryption Option Select!", false);
        Scanner scan = new Scanner(System.in);
        EncryptorFacade facade = EncryptorFacade.Instance();
        RedFileInterface rfi  = new RedFileInterface();
        BlackFileInterface bfi = new BlackFileInterface();

        System.out.print("Wheel 1 Pos: ");
        facade.setWheel1Pos(scan.nextInt());
        System.out.print("Wheel 2 Pos: ");
        facade.setWheel2Pos(scan.nextInt());
        System.out.print("Wheel 3 Pos: ");
        facade.setWheel3Pos(scan.nextInt());

        bfi.openForRead("MyOutFile.dat");
        int i = bfi.readFromFile();
        while (bfi.EOF() == false){
            rfi.addToBuf(i);
            i = bfi.readFromFile();
        }
        bfi.closeInFile();

        rfi.openForWrite("MyOutFile.txt");

        while (rfi.emptyBuf() == false){
            int j = rfi.removeFromBuf();
            j = facade.decrypt(j);
            rfi.addToRevBuf(j);
        }

        char ch;
        while (rfi.emptyRevBuf() == false){
            int k = rfi.removeFromRevBuf();
            ch = rfi.unConvert(k);
            rfi.writeToFile(ch);
        }
        audit.SendDOK(teamName, "Decryption SuccessFul!", false);
        mainMenu();
    }
}
