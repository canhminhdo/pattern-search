package pattern.entity;

public abstract class Pattern {
	
	public enum PatternType {
		P1, P2, P3, // ReadWrite Patterns
		P4, P5, P6, P7, P8, // Falcon Patterns
		P9, P10, P11, P12, P13, P14, P15, P16, P17 // UniconPatterns
	}
	
	public abstract PatternType getPatternType();
}
