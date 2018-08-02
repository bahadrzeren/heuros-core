package org.heuros.test;

import org.heuros.core.base.RuleContext;
import org.heuros.core.model.Leg;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.proxy.IntroducerProxy;
import org.heuros.core.rule.repo.RuleRepo;
import org.heuros.core.rule.repo.RuleRepository;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.exception.RuleRepoIsMissing;
import org.heuros.pair.PairingRuleContext;

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
     * Rule Annotation is missing exception test.
     */
    public void testRuleAnnotationMissingCase()
    {
    	RuleContext ruleContext = new PairingRuleContext();

    	Rule rule = new RuleWithoutAnnotation();

    	try {
    		ruleContext.registerRule(rule);
    	} catch (RuleAnnotationIsMissing ex) {
    		assertTrue(true);
    	} catch (Exception ex) {
    		assertTrue(false);
    	}
    }

    /**
     * Rule Repo is missing exception test.
     */
    public void testRuleRepoMissingCase()
    {
    	RuleContext ruleContext = new PairingRuleContext();

    	Rule rule = new RuleWithAnnotationLeg();

    	try {
    		ruleContext.registerRule(rule);
    	} catch (RuleRepoIsMissing ex) {
    		assertTrue(true);
    	} catch (Exception ex) {
    		assertTrue(false);
    	}
    }

    /**
     * Leg introducer rule registration test.
     */
    public void testRuleWithAnnotationLegRegistration()
    {
    	RuleContext ruleContext = new PairingRuleContext();

//    	Rule rule = new RuleWithAnnotationLeg();

    	RuleRepo<Introducer<Leg>, Leg> legIntroducerRepo =
    			new RuleRepository<Introducer<Leg>, Leg>(Introducer.class,
    														Leg.class);

    	try {
    		ruleContext.registerRule(rule);
    	} catch (RuleRepoIsMissing ex) {
    		assertTrue(true);
    	} catch (Exception ex) {
    		assertTrue(false);
    	}
    }
}
