package TripleFederation;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import c2w.hla.InteractionRoot;
import c2w.hla.ObjectRoot;

/**
 * The federate federate for the federation federation designed in WebGME.
 *
 * This federate MUST join the federation before the federation manager starts the experiment.
 * This means that, when the federate joins, the federation logical time must be 0 and both
 * the readyToPopulate and the readyToRun synchronization points must be defined.
 */
public class TripleSend extends TripleSendBase {
    
	private final static Logger log = LogManager.getLogger(TripleSend.class);

	public TripleSend(String[] args) throws Exception {
		super(args);
	}

	private void execute() throws Exception {
		log.info("executing==>");
		double logicalTime = 0;
		InteractionRoot interaction = null;
		ObjectReflector reflector = null;

        ////////////////////////////////////////
        // perform basic initialization below //
        ////////////////////////////////////////

		AdvanceTimeRequest atr = new AdvanceTimeRequest(logicalTime);
		putAdvanceTimeRequest(atr);

		readyToPopulate();
        //////////////////////////////////////////////////////////////////
		// perform initialization that depends on other federates below //
        //////////////////////////////////////////////////////////////////
		Random rand = new Random();
		// NOTE: do initialization that depends on other federates here
		readyToRun();

		startAdvanceTimeThread();

		// executes until the federate terminates
		while (true) {
            /////////////////////////////////////////////////////////
            // update step size for logical time progression below //
            /////////////////////////////////////////////////////////
			logicalTime += 1.0;

			atr.requestSyncStart();
			
			Int1 int1 = create_Int1();
			int1.sendInteraction(getRTI());
			log.info("Sent Int1");
			
			Int2 int2 = create_Int2();
			int2.sendInteraction(getRTI());
			log.info("Sent Int2");
			
			Int3 int3 = create_Int3();
			int3.sendInteraction(getRTI());
			log.info("Sent Int3");
			
            ///////////////////////////////////////////////////////////////////////
			// send interactions that must be sent every logical time step below //
            ///////////////////////////////////////////////////////////////////////
			while ((interaction = getNextInteractionNoWait()) != null) {
				
							
			}

			while ((reflector = getNextObjectReflectorNoWait()) != null) {
				reflector.reflect();
				ObjectRoot object = reflector.getObjectRoot();
				
							
			}

			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// DO NOT MODIFY THE FOLLOWING LINES
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			AdvanceTimeRequest newATR = new AdvanceTimeRequest(logicalTime);
			putAdvanceTimeRequest(newATR);
			atr.requestSyncEnd();
			atr = newATR;
		}
	}
		
		

	public static void main(String[] args) {
		try {
			TripleSend instance = new TripleSend(args);
			log.info("start==>");
			instance.execute();
		} catch (Exception e) {
			log.error("Exception caught: " + e);
		}
	}	
}