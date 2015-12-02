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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Die Klasse VariationenOhneWiederholungen.
 * Aus einer Menge von n Elementen existieren bei k Ziehungen
 * ( n \over k ) k! Variationen mit Wiederholungen
 */
public class VariationenOhneWiederholungen extends _Komplexionen {
    /** The logger. */
    private static Logger logger = LogManager.getLogger(VariationenOhneWiederholungen.class);
    /** The m elemente. */
    private int mElemente;
    /** The m umfang. */
    private int mUmfang;
    /**
     * Instantiates a new variationen ohne wiederholungen.
     */
    public VariationenOhneWiederholungen() {
    }

    /**
     * Instantiates a new variationen ohne wiederholungen.
     *
     * @param elemente the elemente
     * @param umfang the umfang
     */
    public VariationenOhneWiederholungen(int elemente, int umfang) {
        mElemente = elemente;
        mUmfang = umfang;
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen._Komplexionen#index(int[])
     */
    public int index(int[] komplexion) {
        int index = 0;
        for( int jj = 0; jj < mUmfang; jj++ ) {
        	int d = 0;
        	for( int ii = 0; ii < jj; ii++ ) {
        		if( komplexion[ ii ] < komplexion[ jj ] ) d++;
        	}
        	index = index * ( mElemente - jj ) + ( komplexion[ jj ] - d );
        }
        return index;
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen._Komplexionen#komplexion(int)
     */
    public int[] komplexion(int nr) {
        int[] komplexion = new int[mUmfang];
        for (int jj = mUmfang - 1; jj >= 0; jj-- ) {
            komplexion[ jj ] = nr % ( mElemente - jj );
            nr = ( nr - komplexion[ jj ] ) / ( mElemente - jj );
        }
        for( int jj = 1; jj < mUmfang; jj++ ) {
        	int d0 = 0;
        	int d1 = -1;
        	while( d0 != d1 ) {
        		d1 = d0;
        		d0 = 0;
        		for( int ii = 0; ii < jj; ii++ ) {
        			if( komplexion[ jj ] + d1 >= komplexion[ ii ] ) d0++;
        		}
        	}
        	komplexion[ jj ] += d0;
        }
        return komplexion;
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen._Komplexionen#komplexionen()
     */
    public int komplexionen() {
        int komplexionen = 1;
        for (int ii = 0; ii < mUmfang; ii++) {
            komplexionen *= ( mElemente - ii );
        }
        return komplexionen;
    }

    /**
     * Komplexionen.
     *
     * @param elemente the elemente
     * @param umfang the umfang
     *
     * @return the int
     */
    public static int komplexionen(int elemente, int umfang) {
        int komplexionen = 1;
        for (int ii = 0; ii < umfang; ii++) {
            komplexionen *= ( elemente - ii );
        }
        return komplexionen;
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen._Komplexionen#getElemente()
     */
    public int getElemente() {
        return mElemente;
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen._Komplexionen#getUmfang()
     */
    public int getUmfang() {
        return mUmfang;
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen._Komplexionen#isValid(int[])
     */
    @Override
	public boolean isValid(int[] komplexion) {
        if (logger.isTraceEnabled()) {
            logger.trace("Validating whether the given array '" + toString(komplexion, 1 + (getElemente() / 10)) + "' of integer forms a variation without repeation of length '" + getUmfang() + "'");
        }
        if (komplexion.length != getUmfang()) {
            if (logger.isTraceEnabled()) {
                logger.trace("The given array '" + toString(komplexion, 1 + (getElemente() / 10)) + "' of integer is not a variation without repeation of length '" + getUmfang() + "'");
            }
            return false;
        }
        for (int ii = 0; ii < getUmfang(); ii++) {
            if ( komplexion[ ii ] < 0 || getElemente() < komplexion[ ii ] ) {
                if (logger.isTraceEnabled()) {
                    logger.trace("The given array '" + toString(komplexion, 1 + (getElemente() / 10)) + "' of integer is not a variation without repeation of length '" + getUmfang() 
                    		   + "', because the integer '" + komplexion[ii] + "' at position '" + ii + "' is not in the range from '0' to '" + ( getElemente() - 1 ) + "' at position '" + (ii + 1) + "'");
                }
                return false;
            }
            for( int jj = 0; jj < ii; jj++ ) {
            	if ( komplexion[ ii ] == komplexion[ jj ] ) {
                    if (logger.isTraceEnabled()) {
                        logger.trace("The given array '" + toString(komplexion, 1 + (getElemente() / 10)) + "' of integer is not a variation without repeation of length '" + getUmfang() 
                        		   + "', because the integer '" + komplexion[ii] + "' at position '" + ii + "' is already used at position '" + jj + "'");
                    }
                    return false;
                }
            }
        }
        if (logger.isTraceEnabled()) {
            logger.trace("The given array '" + toString(komplexion, 1 + (getElemente() / 10)) + "' of integer forms a variation without repeation of length '" + getUmfang() + "'");
        }
        return true;
	}
}
