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
 * Das Interface _Komplexionen dient der Definition von mathematischen Komplexionen.
 */
public abstract class _Komplexionen {
    /**
     * Index.
     *
     * @param komplexion the komplexion
     *
     * @return the int
     */
    public abstract int index(int[] komplexion);

    /**
     * Komplexion.
     *
     * @param nr the nr
     *
     * @return the int[]
     */
    public abstract int[] komplexion(int nr);

    /**
     * Komplexionen.
     *
     * @return the int
     */
    public abstract int komplexionen();

    /**
     * Gets the elemente.
     *
     * @return the elemente
     */
    public abstract int getElemente();

    /**
     * Gets the umfang.
     *
     * @return the umfang
     */
    public abstract int getUmfang();

    /**
     * Checks if is valid.
     *
     * @param index the index
     *
     * @return true, if is valid
     */
    public boolean isValid(final int index) {
        return ((index >= 0) && (index < komplexionen()));
    }

    /**
     * Checks if is valid.
     *
     * @param komplexion the komplexion
     *
     * @return true, if is valid
     */
    public abstract boolean isValid(int[] komplexion);
    
    /**
     * Ausgabe einer Komplexion ( Zahlenfolge ) als ein String von durch Leerzeichen getrennte Zahlen
     *
     * @param komplexion Die Komplexion die als String bestimmt werden soll
     * @param width Die Anzahl der Zeichen, die eine Zahl mindestens hat
     * @return Die Komplexion ( Zahlenfolge ) als Leerzeichen getrennter String 
     */
    public String toString(final int[] komplexion, final int width ) {
    	return toString( komplexion, width, "", " ", "" );
    }
    
    /**
     * @param komplexion
     * @param width
     * @param prefix
     * @param infix
     * @param postfix
     * @return Die Komplexion als String, der durch vorgegebener Pre-, In- und Postfix getrennten Zahlen
     */
    public String toString(final int[] komplexion, final int width,final String prefix, final String infix,final String postfix) {
        if ((komplexion != null) && (komplexion.length > 0)) {
            StringBuffer _komplexion = new StringBuffer(String.format(prefix+"%" + width + "d", komplexion[0]));
            for (int ii = 1; ii < komplexion.length; ii++) {
                _komplexion = _komplexion.append(String.format(infix+"%" + width + "d", komplexion[ii]));
            }
            return _komplexion.append(postfix).toString();
        }
        return prefix+postfix;
    }
}
