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
    	LegRuleContext context = new LegRuleContext();

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
     * Other repositories must not include the rule that implements Introducer interface only. 
     */
    public void testLegIntroducerRegistration()
    {
    	LegRuleContext context = new LegRuleContext();

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
     * Test Leg Introducer and ConnectionChecker rule registration.
     * Validator repository must not include the rule.
     */
    public void testLegIntroducerAndConnectionCheckerRegistration()
    {
    	LegRuleContext context = new LegRuleContext();

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
     * Since the rule implements all the interfaces, all repos must have the rule.
     */
    public void testDutyRuleRegistration()
    {
    	DutyRuleContext context = new DutyRuleContext();

    	Rule rule = new DutyRuleFull();

    	try {
    		context.registerRule(rule);

    		assertTrue(context.getConnectionCheckerRepo().getRules().size() == 1);
    		assertTrue(context.getAppendabilityCheckerRepo().getRules().size() == 1);
    		assertTrue(context.getValidatorRepo().getRules().size() == 1);

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
    	LegRuleContext legContext = new LegRuleContext();
    	DutyRuleContext dutyContext = new DutyRuleContext();

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
		assertTrue(legContext.getConnectionCheckerProxy().areConnectable(null, null));
		assertTrue(dutyContext.getConnectionCheckerProxy().areConnectable(null, null));
		assertTrue(dutyContext.getAppendabilityCheckerProxy().isAppendable(null, null));
		assertTrue(dutyContext.getValidatorProxy().isValid(null));
    }
}
