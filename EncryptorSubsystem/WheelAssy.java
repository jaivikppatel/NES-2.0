package EncryptorSubsystem;

//
// @author Bill Phillips
//
//
public class WheelAssy
{
    private final int nRecs = 95;
    private final Wheel w1;
    private final Wheel w2;
    private final Wheel w3;
    private final long s1 = 1001L;
    private final long s2 = 2002L;
    private final long s3 = 3003L;
    private final int n1 = 91;
    private final int n2 = 91;
    private final int n3 = 17;
    private final int c1 = 4;
    private final int c2 = 84;
    private final int c3 = 74;
    //
    // Singleton instance
    // initially set to null
    //
    private static WheelAssy
            instance = null;

    //
    // Singleton design
    // pattern Instance()
    // method.
    //
    public static WheelAssy Instance()
    {
        if(instance == null) instance =
                new WheelAssy();
        return instance;
    }

    //
    // Constructor is
    // private since this
    // is a singleton.
    //
    private WheelAssy()
    {
        //
        // Create the wheels.
        //
        w1 = new Wheel(nRecs,s1,c1,n1);
        w2 = new Wheel(nRecs,s2,c2,n2);
        w3 = new Wheel(nRecs,s3,c3,n3);
    }

    //
    // advance and
    // reverse the wheels
    //
    public void advance()
    {
        final boolean w2atNotch = w2.atNotch();
        if (w1.atNotch()) w2.advance();
        final boolean w2atNotch2 =
                (w2atNotch && !w2.atNotch());
        if (w2atNotch2) w3.advance();
        w1.advance();
    }

    public void reverse()
    {
        w1.reverse();
        final boolean w2atNotch = w2.atNotch();
        if (w1.atNotch()) w2.reverse();
        final boolean w2atNotch2 =
                (w2.atNotch() && !w2atNotch);
        if (w2atNotch2) w3.reverse();
    }

    public int getRtoL(int i)
    {
        final int t1 = w1.getRtoL(i);
        final int t2 = w2.getRtoL(t1);
        final int t3 = w3.getRtoL(t2);
        return t3;
    }

    public int getLtoR(int i)
    {
        final int t3 = w3.getLtoR(i);
        final int t2 = w2.getLtoR(t3);
        final int t1 = w1.getLtoR(t2);
        return t1;
    }

    public void setCurPos1(int i)
    {
        w1.setCurPos(i);
    }
    public void setCurPos2(int i)
    {
        w2.setCurPos(i);
    }
    public void setCurPos3(int i)
    {
        w3.setCurPos(i);
    }

    public int getCurPos1()
    {
        return w1.getCurPos();
    }

    public int getCurPos2()
    {
        return w2.getCurPos();
    }

    public int getCurPos3()
    {
        return w3.getCurPos();
    }
}
