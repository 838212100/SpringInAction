package com.springinaction4_1;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class EncoreableIntroducer {
	
	@DeclareParents(value="com.springinaction4_1.Performance+",defaultImpl=DefaultEncoreable.class)
	public static Encoreable encoreable;

}
