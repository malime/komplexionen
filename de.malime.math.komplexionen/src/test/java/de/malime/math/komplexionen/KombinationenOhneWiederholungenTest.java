/**********************************************************************************
Copyright (c) 2016, malime           _JNNN                                         
                                  _NNF""        ____NNNNNNNN4___                   
All rights reserved.           _JNF`       ,__NN""""        """4NN4__              
                             _NF"          ´"                     "4_F_.           
Redistribution and use     ,JN"                                      ´LFL.         
in source and binary       4"                                          ´\/\.       
forms, with or without     ,_            JN`                              "L4     
modification, are          ´NL.         JNF    __.                         ´LL.   
permitted provided that      "JN______JNF`      N()              _           ´.L.  
the following conditions       """"""""`´NL.   ()N`           _JN"            4/L  
are met:                                  ´`N_NNF           _N"               4J) 
                                            ,NN` ,_.      ,NN                 ´LN 
 * Redistributions of source code         JN"    ""NN)___NN`                  N(.
    must retain the above copyright       NF             N4)    "4L_           NJ 
    notice, this list of conditions                      F()       4F)         NN 
    and the following disclaimer.                        ""         4N.        NJ.
 * Redistribution in binary form must reproduce                     \N       ,NN 
    the above copyright notice, this list of               ,_J`      (N)     (FN` 
    conditions and the following disclaimer in           _NN"        (N)     NJ)  
    the documentation and/or other materials            (NF          (N     (N)   
    provided with the distribution.                     NN          ,N`    JNF    
                                                        "NL.            ,JNN`      
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS        "NF_.     __JNN"`        
AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED          """"""""""            
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
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

/**
 * @author malime
 *
 */
public class KombinationenOhneWiederholungenTest {
	private KombinationenOhneWiederholungen KoW; 

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
		KoW = new KombinationenOhneWiederholungen( 6, 3 );
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
		assertTrue( KoW.getElemente() == 6 );
	}

	/**
	 * Test method for {@link de.malime.math.komplexionen.KombinationenOhneWiederholungen#getUmfang()}.
	 */
	@Test
	public void testGetUmfang() {
		assertTrue( KoW.getUmfang() == 3 );
	}

	/**
	 * Test method for {@link de.malime.math.komplexionen.KombinationenOhneWiederholungen#KombinationenOhneWiederholungen(int, int)}.
	 */
	@Test
	public void testIsBijective() {
		for( int ii = 0; ii < KoW.komplexionen(); ii++ ) {
			int[] komplexion = KoW.komplexion( ii );
			int index = KoW.index( komplexion );
			assertTrue( ii == index );
			assertTrue( KoW.isValid( index ) );
			assertTrue( KoW.isValid( komplexion ) );
		}
		// Hier koennte man auch randomisieren 
		assertFalse( KoW.isValid( -1) );
		assertFalse( KoW.isValid( KoW.komplexionen() ) );
	}

	/**
	 * Test method for {@link de.malime.math.komplexionen._Komplexionen#isValid(int)}.
	 */
	@Test
	public void testKomplexionen() {
		assertTrue( KombinationenOhneWiederholungen.komplexionen(49,6) == 13983816 );
		assertTrue( KombinationenOhneWiederholungen.komplexionen(6,6) == 1 );
		assertTrue( KombinationenOhneWiederholungen.komplexionen(6,5) == 6 );
		assertTrue( KombinationenOhneWiederholungen.komplexionen(6,4) == 15 );
		assertTrue( KombinationenOhneWiederholungen.komplexionen(6,3) == 20 );
		assertTrue( KombinationenOhneWiederholungen.komplexionen(6,2) == 15 );
		assertTrue( KombinationenOhneWiederholungen.komplexionen(6,1) == 6 );
	}
	
	@Test
	public void testToString() {
		int[] komplexion = null;
		assertEquals( "", KoW.toString( komplexion, 1 )  );
		komplexion = new int[]{};
		assertEquals( "", KoW.toString( komplexion, 1 )  );
		komplexion = KoW.komplexion( 0 );
		assertEquals( "0 1 2", KoW.toString( komplexion, 1 )  );
		komplexion = KoW.komplexion( KoW.komplexionen() - 1 );
		assertEquals( "3 4 5", KoW.toString( komplexion, 1 ) );
	}

}
