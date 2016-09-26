/**********************************************************************************
Copyright (c) 2016, malime.de
                         __  __    __    __    ____  __  __  ____    ____  ____ 
All rights reserved.    (  \/  )  /__\  (  )  (_  _)(  \/  )( ___)  (  _ \( ___)
                         )    (  /(__)\  )(__  _)(_  )    (  )__)    )(_) ))__)    
Redistribution and use  (_/\/\_)(__)(__)(____)(____)(_/\/\_)(____)()(____/(____)  
in source and binary                                                              
forms, with or without modification, are permitted provided that the following 
conditions are met: 
  * Redistributions of source code must retain the above copyright notice, this 
    list of conditions and the following disclaimer. 
  * Redistribution in binary form must reproduce the above copyright notice, this
    list of conditions and the following disclaimer in the documentation and/or 
    other materials provided with the distribution. 
                                                    
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
************************************************************************** rame **/
package de.malime.math.komplexionen;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VariationenOhneWiederholungenTest {
	private VariationenOhneWiederholungen VoW; 

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		VoW = new VariationenOhneWiederholungen( 6, 3 );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link de.malime.math.komplexionen.KombinationenOhneWiederholungen#getElemente()}.
	 */
	@Test
	public void testGetElemente() {
		assertEquals( 6, VoW.getElemente() );
	}

	/**
	 * Test method for {@link de.malime.math.komplexionen.KombinationenOhneWiederholungen#getUmfang()}.
	 */
	@Test
	public void testGetUmfang() {
		assertEquals( 3, VoW.getUmfang() );
	}

	/**
	 * Test method for {@link de.malime.math.komplexionen.KombinationenOhneWiederholungen#KombinationenOhneWiederholungen(int, int)}.
	 */
	@Test
	public void testisBijective() {
		for( int ii = 0; ii < VoW.komplexionen(); ii++ ) {
			int[] komplexion = VoW.komplexion( ii );
			int index = VoW.index( komplexion );
			assertEquals( ii, index );
			assertTrue( VoW.isValid( index ) );
			assertTrue( VoW.isValid( komplexion ) );
		}
	}

	/**
	 * Test method for {@link de.malime.math.komplexionen._Komplexionen#isValid(int)}.
	 */
	@Test
	public void testKomplexionen() {
		assertEquals( 120, VoW.komplexionen() );
	}

}
