package org.heuros.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.heuros.core.data.model.Leg;
import org.heuros.loader.ssim.SsimParser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SsimParseTest 
    extends TestCase
{

	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SsimParseTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SsimParseTest.class );
    }

	private static String sampleSsimLine = "3 TK  0010101J20DEC1306JAN141 3 56  IST11251125+0200  JFK22202220-0500  77C                                                              TK  012                            F63C28Y246            000003";

    /**
     * Test SSIM line parse.
     */
    public void testSsimLineParse()
    {
    	List<Leg> legs = new ArrayList<Leg>();
    	SsimParser ssimParser = new SsimParser(legs, null);
    	try {
			ssimParser.parseLine(SsimParseTest.sampleSsimLine);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
        assertTrue(legs.size() == 11);
        Leg leg = legs.get(0);
        assertTrue(leg.getCarrier().equals("TK"));
        assertTrue(leg.getAcType().equals("77C"));
        assertTrue(leg.getDep().equals("IST"));
        assertTrue(leg.getArr().equals("JFK"));
        assertTrue(leg.getServiceType().equals("J"));
        assertTrue((leg.getSuffix() == null) || leg.getSuffix().equals(""));
        assertTrue(leg.getSobt().isEqual(LocalDateTime.of(2013, 12, 20, 11, 25)));
        assertTrue(leg.getSibt().isEqual(LocalDateTime.of(2013, 12, 20, 22, 20)));
        assertTrue(leg.getFligtNo() == 1);
        assertTrue(leg.getDepOffset() == 120);
        assertTrue(leg.getArrOffset() == -300);
    }
}
