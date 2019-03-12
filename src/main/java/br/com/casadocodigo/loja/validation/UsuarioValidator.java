package br.com.casadocodigo.loja.validation;

import br.com.casadocodigo.loja.models.Usuario;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UsuarioValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "senha", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");

        Usuario usuario = (Usuario) target;
        if (!(usuario.checkSenha(usuario))) {
            errors.rejectValue("senharep", "field.required");
        }
    }
}
