/**********************************************************************************
Copyright (c) 2009, malime           _JNNN                                         
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
 * Die Klasse KombinationenOhneWiederholungen
 * Aus einer Menge von n Elementen existieren sich bei k Ziehungen
 * ( n \over k ) Kombinationen ohne Wiederholungen
 */
public class KombinationenOhneWiederholungen extends _Komplexionen {
    /** The logger. */
    private static Logger logger = LogManager.getLogger(KombinationenOhneWiederholungen.class);

    /** The Constant PRECALCSIZEY. */
    private static final int PRECALCSIZEY = 7;

    /** The Constant PRECALCSIZEX. */
    private static final int PRECALCSIZEX = 42;

    /** Dieses Array dient der Optimierung, indem häufig verwendete Berechungnen durch das Vorhalten 
     *  von einfachen Zahlen vermieden werden kann. */
    private static int[][] mElements = null;

    static {
        if (mElements == null) {
            mElements = new int[PRECALCSIZEX][PRECALCSIZEY];
            int jj;
            int ii;
            mElements[0][0] = 6;
            for (ii = 1; ii < PRECALCSIZEX; ii++) {
                mElements[ii][0] = 3 + ii + mElements[ii - 1][0];
            }
            for (jj = 1; jj < PRECALCSIZEY; jj++) {
                mElements[0][jj] = 3 + jj + mElements[0][jj - 1];
            }
            for (ii = 1; ii < PRECALCSIZEX; ii++) {
                for (jj = 1; jj < PRECALCSIZEY; jj++) {
                    mElements[ii][jj] = mElements[ii - 1][jj] + mElements[ii][jj - 1];
                }
            }
            if (logger.isTraceEnabled()) {
                for (ii = 0; ii < PRECALCSIZEX; ii++) {
                    StringBuffer line = new StringBuffer();
                    StringBuffer value = new StringBuffer();
                    for (jj = 0; jj < PRECALCSIZEY; jj++) {
                        value.append("             " + mElements[ii][jj]);
                        line.append(value.substring(value.length() - 11));
                    }
                    logger.trace(line);
                }
            }
        }
    }

    /** The Elemente. */
    private int mElemente;

    /** The Umfang. */
    private int mUmfang;

    @SuppressWarnings("unused")
    private KombinationenOhneWiederholungen() {
    }

    /**
     * Instantiates a new kombinationen ohne wiederholungen.
     *
     * @param elemente the elemente
     * @param umfang the umfang
     */
    public KombinationenOhneWiederholungen(int elemente, int umfang) {
        mElemente = elemente;
        mUmfang = umfang;
    }

    /**
     * Anzahl.
     *
     * @param summe the sum
     * @param freiheiten the freedom
     *
     * @return the int
     */
    private static int anzahl(int summe, int freiheiten) {
        int anzahl = ((summe == 0) || (freiheiten == 0)) ? 1 : ( ( summe == 1 ) ? (freiheiten + 1 ) : ( (freiheiten == 1) ? (summe + 1) : (((summe < PRECALCSIZEX) && (freiheiten < PRECALCSIZEY)) ? mElements[summe - 2][freiheiten - 2] : (anzahl(summe - 1, freiheiten) + anzahl(summe, freiheiten - 1)))));
        if (logger.isTraceEnabled()) {
            logger.trace("Getting the index for the values sum = '" + summe + "' freedom '" + freiheiten + "' is '" + anzahl + "'");
        }
        return anzahl;
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen._Komplexionen#index(int[])
     */
    public int index(int[] komplexion) {
        int nr = 0;
        for (int jj = 0; jj < mUmfang; jj++) {
            if (komplexion[jj] > jj) {
                nr += anzahl(komplexion[jj] - 1 - jj, jj + 1);
            }
        }
        return nr;
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen._Komplexionen#komplexion(int)
     */
    public int[] komplexion( int nummer ) {
        int[] zahl = new int[mUmfang];
        for (int jj = mUmfang - 1; jj >= 0; jj--) {
            if (anzahl(0, jj + 1) > nummer) {
                zahl[jj] = jj;
            } else {
                int kk = 0;
                while (anzahl(kk + 1, jj + 1) <= nummer) {
                    kk++;
                }
                zahl[jj] = kk + 1 + jj;
                nummer -= anzahl(kk, jj + 1);
            }
        }
        return zahl;
    }

    /* (non-Javadoc)
     * @see de.malime.math.komplexionen._Komplexionen#komplexionen()
     */
    public int komplexionen() {
        return anzahl(mElemente - mUmfang, mUmfang);
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
        return anzahl(elemente - umfang, umfang);
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
    public boolean isValid(int[] komplexion) {
        if (logger.isTraceEnabled()) {
            logger.trace("Validating whether the given array '" + toString(komplexion, 1 + (getElemente() / 10)) + "' of integer forms a kombination without repeation of length '" + getUmfang() + "'");
        }
        if (komplexion.length != getUmfang()) {
            if (logger.isTraceEnabled()) {
                logger.trace("The given array '" + toString(komplexion, 1 + (getElemente() / 10)) + "' of integer is not a kombination without repeation of length '" + getUmfang() + "'");
            }
            return false;
        }
        if (komplexion[0] < 0) {
            if (logger.isTraceEnabled()) {
                logger.trace("The given array '" + toString(komplexion, 1 + (getElemente() / 10)) + "' of integer is not a kombination without repeation of length '" + getUmfang() + "'");
            }
            return false;
        }
        if (komplexion[getUmfang() - 1] > getElemente()) {
            if (logger.isTraceEnabled()) {
                logger.trace("The given array '" + toString(komplexion, 1 + (getElemente() / 10)) + "' of integer is not a kombination without repeation of length '" + getUmfang() + "'");
            }
            return false;
        }
        for (int ii = 0; ii < (mUmfang - 1); ii++) {
            if (komplexion[ii] >= komplexion[ii + 1]) {
                if (logger.isTraceEnabled()) {
                    logger.trace("The given array '" + toString(komplexion, 1 + (getElemente() / 10)) + "' of integer is not a kombination without repeation of length '" + getUmfang() 
                    		   + "', because the integer '" + komplexion[ii] + "' at position '" + ii + "' is not less than the integer '" + komplexion[ii + 1] + "' at position '" + (ii + 1) + "'");
                }
                return false;
            }
        }
        if (logger.isTraceEnabled()) {
            logger.trace("The given array '" + toString(komplexion, 1 + (getElemente() / 10)) + "' of integer forms a kombination without repeation of length '" + getUmfang() + "'");
        }
        return true;
    }
}
