/*******************************************************************************
 * Copyright (c) 2009 ETH Zurich.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ETH Zurich - initial API and implementation
 *******************************************************************************/

package ch.ethz.eventb.internal.decomposition.tests;

import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;

import ch.ethz.eventb.decomposition.IModelDecomposition;
import ch.ethz.eventb.decomposition.ISubModel;
import ch.ethz.eventb.internal.decomposition.astyle.ModelDecomposition;

/**
 * @author htson
 *         <p>
 *         Abstract class for tests related to decomposition, <i>e.g.</i> for
 *         {@link ISubModel}, {@link IModelDecomposition},
 *         {@link DecompostionUtils}.
 *         </p>
 */
public abstract class AbstractDecompositionTests extends EventBTests {
	
	final static IProgressMonitor monitor = new NullProgressMonitor();

	// Some model distributions.
	protected IModelDecomposition modelDecomp1;

	protected IModelDecomposition modelDecomp2;
	
	protected IModelDecomposition modelDecomp3;

	// Some element distributions.
	protected ISubModel subModel1;
	
	protected ISubModel subModel2;
	
	protected ISubModel subModel3;
	
	/* (non-Javadoc)
	 * @see ch.ethz.eventb.internal.decomposition.tests.astyle.EventBTests#setUp()
	 */
	@Before
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// Create the model distributions.
		modelDecomp1 = new ModelDecomposition(mch1_1);
		modelDecomp2 = new ModelDecomposition(mch1_2);
		modelDecomp3 = new ModelDecomposition(mch1_3);
		
		// Create the element distributions.
		subModel1 = modelDecomp3.addSubModel();
		subModel1.setElements(evt1_3_1, evt1_3_5);
		
		subModel2 = modelDecomp3.addSubModel();
		subModel2.setElements(evt1_3_2, evt1_3_3);

		subModel3 = modelDecomp3.addSubModel();
		subModel3.setElements(evt1_3_4);
		
		// ensure autobuilding is turned on
		IWorkspaceDescription wsDescription = workspace.getDescription();
		if (wsDescription.isAutoBuilding()) {
			wsDescription.setAutoBuilding(true);
			workspace.setDescription(wsDescription);
		}

		workspace.build(IncrementalProjectBuilder.FULL_BUILD,
				new NullProgressMonitor());
	}

}