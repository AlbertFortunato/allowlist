package org.com.allowlist.utils;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCPF {

    public static boolean validar(String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        try { cpfValidator.assertValid(cpf);
            return true;
        } catch (InvalidStateException e){
            e.getMessage();
            return false;
        }
    }
}
