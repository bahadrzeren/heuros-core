package org.heuros.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.heuros.data.model.Leg;
import org.heuros.loader.legs.LegsParser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class LegsParseTest 
    extends TestCase
{

	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LegsParseTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( LegsParseTest.class );
    }

	private static String sampleLegsLine = "TK,882,,IST,TBZ,316,4,2013-12-20T01:40,2013-12-20T04:15,120,210,J,true,true";

	/**
     * Test LEGS line parse.
     */
    public void testLegsLineParse()
    {
    	List<Leg> legs = new ArrayList<Leg>();
    	LegsParser legsParser = new LegsParser(legs, null);
    	try {
			legsParser.parseLine(LegsParseTest.sampleLegsLine);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
        assertTrue(legs.size() == 1);
        Leg leg = legs.get(0);
        assertTrue(leg.getCarrier().equals("TK"));
        assertTrue(leg.getAcType().equals("316"));
        assertTrue(leg.getAcSequence() == 4);
        assertTrue(leg.getDep().equals("IST"));
        assertTrue(leg.getArr().equals("TBZ"));
        assertTrue(leg.getServiceType().equals("J"));
        assertTrue((leg.getSuffix() == null) || leg.getSuffix().equals(""));
        assertTrue(leg.getSobt().isEqual(LocalDateTime.of(2013, 12, 20, 1, 40)));
        assertTrue(leg.getSibt().isEqual(LocalDateTime.of(2013, 12, 20, 4, 15)));
        assertTrue(leg.getFligtNo() == 882);
        assertTrue(leg.getDepOffset() == 120);
        assertTrue(leg.getArrOffset() == 210);
        assertTrue(leg.isNeedsCockpitCrew());
        assertTrue(leg.isNeedsCabinCrew());
    }
}
