package EncryptorSubsystem;

//
// @author Bill Phillips
//
//
public class EncryptorFacade
{
    private final WheelAssy wAssy;
    private final PlugBoard pBd;
    private final Reflector ref;

    private EncryptorFacade()
    {
        wAssy = WheelAssy.Instance();
        pBd   = new PlugBoard();
        ref   = Reflector.Instance();
    }

    //
    // Singleton instance
    // initially set to null
    //
    private static EncryptorFacade
            instance = null;

    //
    // Singleton design
    // pattern Instance()
    // method.
    //
    public static EncryptorFacade Instance()
    {
        if(instance == null) instance =
                new EncryptorFacade();
        return instance;
    }

    public int encrypt(int i)
    {
        final int t1 = pBd.convert(i);
        wAssy.advance();
        final int t2 = wAssy.getRtoL(t1);
        final int t3 =   ref.reflect(t2);
        final int t4 = wAssy.getLtoR(t3);
        return t4;
    }

    public int decrypt(int i)
    {
        final int t1 =    wAssy.getRtoL(i);
        final int t2 =   ref.reflect(t1);
        final int t3 = wAssy.getLtoR(t2);
        wAssy.reverse();
        final int t4 = pBd.convert(t3);
        return t4;
    }

    public void setWheel1Pos(final int i)
    {
        wAssy.setCurPos1(i);
    }

    public void setWheel2Pos(final int i)
    {
        wAssy.setCurPos2(i);
    }

    public void setWheel3Pos(final int i)
    {
        wAssy.setCurPos3(i);
    }

    public int getWheel1Pos()
    {
        final int wp = wAssy.getCurPos1();
        return wp;
    }
    public int getWheel2Pos()
    {
        final int wp = wAssy.getCurPos2();
        return wp;
    }

    public int getWheel3Pos()
    {
        final int wp = wAssy.getCurPos3();
        return wp;
    }

}

