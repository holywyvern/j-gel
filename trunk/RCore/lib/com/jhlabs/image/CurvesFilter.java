/*
Copyright 2006 Jerry Huxtable

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.jhlabs.image;

import java.awt.*;
import java.awt.image.*;

// TODO: Auto-generated Javadoc
/**
 * The Class CurvesFilter.
 */
public class CurvesFilter extends TransferFilter {

	/** The curves. */
	private Curve[] curves = new Curve[1];
	
    /**
	 * Instantiates a new curves filter.
	 */
    public CurvesFilter() {
        curves = new Curve[3];
        curves[0] = new Curve();
        curves[1] = new Curve();
        curves[2] = new Curve();
    }
    
	/* (non-Javadoc)
	 * @see com.jhlabs.image.TransferFilter#initialize()
	 */
	protected void initialize() {
		initialized = true;
		if ( curves.length == 1 )
            rTable = gTable = bTable = curves[0].makeTable();
        else {
            rTable = curves[0].makeTable();
            gTable = curves[1].makeTable();
            bTable = curves[2].makeTable();
        }
	}

	/**
	 * Sets the curve.
	 * 
	 * @param curve
	 *            the new curve
	 */
	public void setCurve( Curve curve ) {
        curves = new Curve[] { curve };
		initialized = false;
	}
	
	/**
	 * Sets the curves.
	 * 
	 * @param curves
	 *            the new curves
	 */
	public void setCurves( Curve[] curves ) {
		if ( curves == null || (curves.length != 1 && curves.length != 3) )
            throw new IllegalArgumentException( "Curves must be length 1 or 3" );
        this.curves = curves;
		initialized = false;
	}
	
	/**
	 * Gets the curves.
	 * 
	 * @return the curves
	 */
	public Curve[] getCurves() {
		return curves;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Colors/Curves...";
	}

}

