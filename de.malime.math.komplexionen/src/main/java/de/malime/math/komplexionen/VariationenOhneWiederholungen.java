/**********************************************************************************
Copyright (c) 2009, malime.de
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

/**
 * Die Klasse VariationenOhneWiederholungen.
 * Aus einer Menge von n Elementen existieren bei k Ziehungen
 * ( n \over k ) k! Variationen mit Wiederholungen
 */
public class VariationenOhneWiederholungen extends _Komplexionen {
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
        if (komplexion.length != getUmfang()) {
            return false;
        }
        for (int ii = 0; ii < getUmfang(); ii++) {
            if ( komplexion[ ii ] < 0 || getElemente() < komplexion[ ii ] ) {
                return false;
            }
            for( int jj = 0; jj < ii; jj++ ) {
            	if ( komplexion[ ii ] == komplexion[ jj ] ) {
                    return false;
                }
            }
        }
        return true;
	}
}
