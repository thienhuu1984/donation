package funix.huutt.prj.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import funix.huutt.prj.view.AccountView;

public class AccountValidator implements ConstraintValidator<AccountAnnotation, AccountView> {

	@Override
	public boolean isValid(AccountView view, ConstraintValidatorContext message) {
		
		if (!(view instanceof AccountView)) {
            throw new IllegalArgumentException("@AccountAnnotation only applies to AccountView objects");
        }
		
		if(view.getPassword().equals(view.getConfirmPassword()))
			return true;
		
		return false;
	}



}
