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

package com.jhlabs.composite;

import java.awt.*;
import java.awt.image.*;

// TODO: Auto-generated Javadoc
/**
 * The Class MiscComposite.
 */
public final class MiscComposite implements Composite {

	/** The Constant BLEND. */
	public final static int BLEND = 0;
	
	/** The Constant ADD. */
	public final static int ADD = 1;
	
	/** The Constant SUBTRACT. */
	public final static int SUBTRACT = 2;
	
	/** The Constant DIFFERENCE. */
	public final static int DIFFERENCE = 3;

	/** The Constant MULTIPLY. */
	public final static int MULTIPLY = 4;
	
	/** The Constant DARKEN. */
	public final static int DARKEN = 5;
	
	/** The Constant BURN. */
	public final static int BURN = 6;
	
	/** The Constant COLOR_BURN. */
	public final static int COLOR_BURN = 7;

	/** The Constant SCREEN. */
	public final static int SCREEN = 8;
	
	/** The Constant LIGHTEN. */
	public final static int LIGHTEN = 9;
	
	/** The Constant DODGE. */
	public final static int DODGE = 10;
	
	/** The Constant COLOR_DODGE. */
	public final static int COLOR_DODGE = 11;

	/** The Constant HUE. */
	public final static int HUE = 12;
	
	/** The Constant SATURATION. */
	public final static int SATURATION = 13;
	
	/** The Constant VALUE. */
	public final static int VALUE = 14;
	
	/** The Constant COLOR. */
	public final static int COLOR = 15;

	/** The Constant OVERLAY. */
	public final static int OVERLAY = 16;
	
	/** The Constant SOFT_LIGHT. */
	public final static int SOFT_LIGHT = 17;
	
	/** The Constant HARD_LIGHT. */
	public final static int HARD_LIGHT = 18;
	
	/** The Constant PIN_LIGHT. */
	public final static int PIN_LIGHT = 19;

	/** The Constant EXCLUSION. */
	public final static int EXCLUSION = 20;
	
	/** The Constant NEGATION. */
	public final static int NEGATION = 21;
	
	/** The Constant AVERAGE. */
	public final static int AVERAGE = 22;

	/** The Constant STENCIL. */
	public final static int STENCIL = 23;
	
	/** The Constant SILHOUETTE. */
	public final static int SILHOUETTE = 24;

	/** The Constant MIN_RULE. */
	private static final int MIN_RULE = BLEND;
	
	/** The Constant MAX_RULE. */
	private static final int MAX_RULE = SILHOUETTE;

	/** The RUL e_ names. */
	public static String[] RULE_NAMES = {
		"Normal",
		"Add",
		"Subtract",
		"Difference",

		"Multiply",
		"Darken",
		"Burn",
		"Color Burn",
		
		"Screen",
		"Lighten",
		"Dodge",
		"Color Dodge",

		"Hue",
		"Saturation",
		"Brightness",
		"Color",
		
		"Overlay",
		"Soft Light",
		"Hard Light",
		"Pin Light",

		"Exclusion",
		"Negation",
		"Average",

		"Stencil",
		"Silhouette",
	};

	/** The extra alpha. */
	protected float extraAlpha;
	
	/** The rule. */
	protected int rule;

	/**
	 * Instantiates a new misc composite.
	 * 
	 * @param rule
	 *            the rule
	 */
	private MiscComposite(int rule) {
		this(rule, 1.0f);
	}

	/**
	 * Instantiates a new misc composite.
	 * 
	 * @param rule
	 *            the rule
	 * @param alpha
	 *            the alpha
	 */
	private MiscComposite(int rule, float alpha) {
		if (alpha < 0.0f || alpha > 1.0f)
			throw new IllegalArgumentException("alpha value out of range");
		if (rule < MIN_RULE || rule > MAX_RULE)
			throw new IllegalArgumentException("unknown composite rule");
		this.rule = rule;
		this.extraAlpha = alpha;
	}

	/**
	 * Gets the single instance of MiscComposite.
	 * 
	 * @param rule
	 *            the rule
	 * @param alpha
	 *            the alpha
	 * @return single instance of MiscComposite
	 */
	public static Composite getInstance(int rule, float alpha) {
		switch ( rule ) {
		case MiscComposite.BLEND:
			return AlphaComposite.getInstance( AlphaComposite.SRC_OVER, alpha );
		case MiscComposite.ADD:
			return new AddComposite( alpha );
		case MiscComposite.SUBTRACT:
			return new SubtractComposite( alpha );
		case MiscComposite.DIFFERENCE:
			return new DifferenceComposite( alpha );
		case MiscComposite.MULTIPLY:
			return new MultiplyComposite( alpha );
		case MiscComposite.DARKEN:
			return new DarkenComposite( alpha );
		case MiscComposite.BURN:
			return new BurnComposite( alpha );
		case MiscComposite.COLOR_BURN:
			return new ColorBurnComposite( alpha );
		case MiscComposite.SCREEN:
			return new ScreenComposite( alpha );
		case MiscComposite.LIGHTEN:
			return new LightenComposite( alpha );
		case MiscComposite.DODGE:
			return new DodgeComposite( alpha );
		case MiscComposite.COLOR_DODGE:
			return new ColorDodgeComposite( alpha );
		case MiscComposite.HUE:
			return new HueComposite( alpha );
		case MiscComposite.SATURATION:
			return new SaturationComposite( alpha );
		case MiscComposite.VALUE:
			return new ValueComposite( alpha );
		case MiscComposite.COLOR:
			return new ColorComposite( alpha );
		case MiscComposite.OVERLAY:
			return new OverlayComposite( alpha );
		case MiscComposite.SOFT_LIGHT:
			return new SoftLightComposite( alpha );
		case MiscComposite.HARD_LIGHT:
			return new HardLightComposite( alpha );
		case MiscComposite.PIN_LIGHT:
			return new PinLightComposite( alpha );
		case MiscComposite.EXCLUSION:
			return new ExclusionComposite( alpha );
		case MiscComposite.NEGATION:
			return new NegationComposite( alpha );
		case MiscComposite.AVERAGE:
			return new AverageComposite( alpha );
		case MiscComposite.STENCIL:
			return AlphaComposite.getInstance( AlphaComposite.DST_IN, alpha );
		case MiscComposite.SILHOUETTE:
			return AlphaComposite.getInstance( AlphaComposite.DST_OUT, alpha );
		}
		return new MiscComposite(rule, alpha);
	}

	/* (non-Javadoc)
	 * @see java.awt.Composite#createContext(java.awt.image.ColorModel, java.awt.image.ColorModel, java.awt.RenderingHints)
	 */
	public CompositeContext createContext(ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints) {
		return new MiscCompositeContext( rule, extraAlpha, srcColorModel, dstColorModel );
	}

	/**
	 * Gets the alpha.
	 * 
	 * @return the alpha
	 */
	public float getAlpha() {
		return extraAlpha;
	}

	/**
	 * Gets the rule.
	 * 
	 * @return the rule
	 */
	public int getRule() {
		return rule;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (Float.floatToIntBits(extraAlpha) * 31 + rule);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (!(o instanceof MiscComposite))
			return false;
		MiscComposite c = (MiscComposite)o;

		if (rule != c.rule)
			return false;
		if (extraAlpha != c.extraAlpha)
			return false;
		return true;
	}
			
}
