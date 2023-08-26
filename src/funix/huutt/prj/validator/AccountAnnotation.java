package funix.huutt.prj.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = AccountValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountAnnotation {
	
	// define default course code
	public String value() default "Mật khẩu không được bỏ trống";
	
	// define default error message
	public String message() default "must start with COD";
	
	// define default groups
	public Class<?>[] groups() default {};
	
	
	// define default payloads
	public Class<? extends Payload>[] payload() default {};

}
