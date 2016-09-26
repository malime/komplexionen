/**********************************************************************************
Copyright (c) 2013, malime           _JNNN                                         
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

/**
 * Die KombinationenMitWiederholungen lassen sich auf KombinationenOhneWiederholung bijektiv abbilden
 * So ist KmW(5,3) identisch zu KoW(3+5-1=7,3) ...
 * KoW KmW
 * 123 111
 * 124 112
 * .......
 * 127 115
 * .......
 * Aus einer Menge von n Elementen existieren bei k Ziehungen
 * ( n + k - 1 \over k ) Kombinationen mit Wiederholungen
 */
public class KombinationenMitWiederholungen extends KombinationenOhneWiederholungen {
    /**
     * Instantiates a new KombinationenMitWiederholungen.
     * 
     * @param elemente the elemente
     * @param umfang the umfang
     */
    public KombinationenMitWiederholungen(int elemente, int umfang) {
        super( elemente + umfang - 1, umfang );
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen.KombinationenOhneWiederholungen#index(int[])
     */
    public int index(int[] kmw) {
        int[] kow = new int[ kmw.length ];
        kow[ 0 ] = kmw[ 0 ];
        for( int ii = 1; ii < kmw.length; ii++ ) {
        	kow[ ii ] = kow[ ii - 1 ] + ( kmw[ ii ] - kmw[ ii - 1 ] ) + 1;
        }
        return super.index( kow );
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen.KombinationenOhneWiederholungen#komplexion(int)
     */
    public int[] komplexion(int nr) {
        int[] kow = super.komplexion( nr );
        int[] kmw = new int[ kow.length ];
        kmw[ 0 ] = kow[ 0 ];
        for( int ii = 1; ii < kmw.length; ii++ ) {
        	kmw[ ii ] = kow[ ii ] - kow[ ii - 1 ] - 1 + kmw[ ii - 1 ];
        }
        return kmw;
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen.KombinationenOhneWiederholungen#getElemente()
     */
    @Override
    public int getElemente() {
        return super.getElemente() - super.getUmfang() + 1;
    }

	/* (non-Javadoc)
	 * @see de.malime.math.komplexionen.KombinationenOhneWiederholungen#isValid(int[])
	 */
	public boolean isValid(int[] komplexion) {
        if (komplexion.length != getUmfang()) {
            return false;
        }
        if (komplexion[0] < 0) {
            return false;
        }
        if (komplexion[getUmfang() - 1] > getElemente() ) {
            return false;
        }
        for (int ii = 0; ii < ( getUmfang() - 1); ii++) {
            if (komplexion[ ii ] > komplexion[ ii + 1 ] ) {
                return false;
            }
        }
        return true;
	}
}
