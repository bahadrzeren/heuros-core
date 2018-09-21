package org.heuros.test;

import org.heuros.core.rule.intf.Rule;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.rule.DutyRuleContext;
import org.heuros.rule.LegRuleContext;
import org.heuros.test.rule.DutyRuleFull;
import org.heuros.test.rule.LegIntroducer;
import org.heuros.test.rule.LegRuleExtended;
import org.heuros.test.rule.LegRuleWithoutAnnotation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class RuleContextTest extends TestCase {

	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RuleContextTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RuleContextTest.class );
    }

    /**
     * Test Rule Annotation is missing exception test.
     * Rule classes must use @RuleImplementation annotation!
     */
    public void testRuleAnnotationMissingCase()
    {
    	LegRuleContext context = new LegRuleContext(1);

    	Rule rule = new LegRuleWithoutAnnotation();

    	try {
    		context.registerRule(rule);
    	} catch (RuleAnnotationIsMissing ex) {
    		assertTrue(true);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		assertTrue(false);
    	}
    }

    /**
     * Test LegIntroducer rule registration.
     * Other repositories must not include the rule that implements just Introducer interface.
     */
    public void testLegIntroducerRegistration()
    {
    	LegRuleContext context = new LegRuleContext(1);

    	Rule rule = new LegIntroducer();

    	try {
    		context.registerRule(rule);

    		assertTrue(context.getIntroducerRepo().getRules().size() == 1);
    		assertTrue(context.getConnectionCheckerRepo().getRules().size() == 0);

    	} catch (Exception ex) {
    		ex.printStackTrace();
    		assertTrue(false);
    	}
    }

    /**
     * Test Leg Introducer and ConnectionChecker implementer rule registration.
     */
    public void testLegIntroducerAndConnectionCheckerRegistration()
    {
    	LegRuleContext context = new LegRuleContext(1);

    	Rule rule = new LegRuleExtended();

    	try {
    		context.registerRule(rule);

    		assertTrue(context.getIntroducerRepo().getRules().size() == 1);
    		assertTrue(context.getConnectionCheckerRepo().getRules().size() == 1);

    	} catch (Exception ex) {
    		ex.printStackTrace();
    		assertTrue(false);
    	}
    }

    /**
     * Test Duty rule registration.
     * Since the rule does not implement StarterChecker and Aggregator interfaces repos of those classes must be zero.
     */
    public void testDutyRuleRegistration()
    {
    	DutyRuleContext context = new DutyRuleContext(1);

    	Rule rule = new DutyRuleFull();

    	try {
    		context.registerRule(rule);

    		assertTrue(context.getConnectionCheckerRepo().getRules().size() == 1);
    		assertTrue(context.getAppendabilityCheckerRepo().getRules().size() == 1);
    		assertTrue(context.getFinalCheckerRepo().getRules().size() == 1);
    		assertTrue(context.getStarterCheckerRepo().getRules().size() == 0);
    		assertTrue(context.getAggregatorProxy() == null);

    	} catch (Exception ex) {
    		ex.printStackTrace();
    		assertTrue(false);
    	}
    }

    /**
     * Test Leg and Duty rule proxies.
     */
    public void testLegAndDutyRules()
    {
    	LegRuleContext legContext = new LegRuleContext(1);
    	DutyRuleContext dutyContext = new DutyRuleContext(1);

    	Rule legRule = new LegRuleExtended();
    	Rule dutyRule = new DutyRuleFull();

    	try {
    		legContext.registerRule(legRule);
    		dutyContext.registerRule(dutyRule);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		assertTrue(false);
    	}

		assertTrue(legContext.getIntroducerProxy().introduce(null));
		assertTrue(legContext.getConnectionCheckerProxy().areConnectable(-1, null, null));
		assertTrue(dutyContext.getConnectionCheckerProxy().areConnectable(-1, null, null));
		assertTrue(dutyContext.getAppendabilityCheckerProxy().isAppendable(-1, null, null));
		assertTrue(dutyContext.getFinalCheckerProxy().acceptable(-1, null));
    }
}
