import EncryptorSubsystem.EncryptorFacade;
import FileInterfacePackage.RedFileInterface;
import FileInterfacePackage.BlackFileInterface;
import P2PInterfacePackage.*;
import java.util.ArrayList;

public class WheelImp
{

    public static void main(String[] args)
    {
        final int    wS =    95;

        ArrayList<Integer> iList  = new
                ArrayList<>();

        AuditInterfaceFactory aIf =
                new AuditInterfaceFactory();

        AuditInterfaceStubBase aInt = aIf.create(1);

        EncryptorFacade eFac =
                EncryptorFacade.Instance();

        for (int i=0; i<5; ++i)
        {
            final int t1 = eFac.encrypt(i%wS);
            iList.add(t1);
        }

        aInt.SendEOK("Teamx", "good", true);

        int kLast = 0;
        for (int i=4; i>=0; --i)
        {
            int t1 = iList.get(i);

            int k  = eFac.decrypt(t1);

            if ((( k - kLast ) > 1 )&&( kLast != 0 ))
            {
                System.out.println ("ERROR ********");
                System.exit(k);
            }
            kLast = k;
            System.out.println(k);
        }

        aInt.SendDOK("Teamx", "good", true);

        RedFileInterface dfi = new RedFileInterface();
        BlackFileInterface efi = new BlackFileInterface();

        dfi.openForRead("MyInFile.txt");
        efi.openForWrite("MyOutFile.dat");
        char c = dfi.readInputFile();
        while (dfi.EOF() == false)
        {
            System.out.print(c);
            System.out.print(" ");
            int outc = dfi.convert(c);
            System.out.print(outc);
            int oute = eFac.encrypt(outc);
            System.out.print(" ");
            efi.writeToFile(oute);
            System.out.println(oute);
            c = dfi.readInputFile();
        }
        dfi.closeInFile();
        efi.closeOutFile();

        efi.openForRead("MyOutFile.dat");

        int ine = efi.readFromFile();
        while (efi.EOF() == false)
        {
            dfi.addToBuf(ine);
            ine = efi.readFromFile();
        }
        efi.closeInFile();

        dfi.openForWrite("MyOutFile.txt");

        while (dfi.emptyBuf() == false)
        {
            int id = dfi.removeFromBuf();
            id = eFac.decrypt(id);
            dfi.addToRevBuf(id);

        }

        while (dfi.emptyRevBuf() == false)
        {
            int id = dfi.removeFromRevBuf();
            c = dfi.unConvert(id);
            dfi.writeToFile(c);
        }

        dfi.closeOutFile();

    }

}
