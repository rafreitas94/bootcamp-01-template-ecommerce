package br.com.itau.mercadolivre.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class AtributoUnicoValidator implements ConstraintValidator<AtributoUnico, String> {

    @PersistenceContext
    private EntityManager manager;

    private String nomeAtributo;
    private Class<?> classeDominio;

    @Override
    public void initialize(AtributoUnico constraintAnnotation) {
        nomeAtributo = constraintAnnotation.nomeAtributo();
        classeDominio = constraintAnnotation.classeDominio();
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        Query query = manager.createQuery("SELECT 1 FROM " + classeDominio.getName() + " WHERE " + nomeAtributo + " =:VALUE");
        query.setParameter("VALUE", valor);

        List<?> resultList = query.getResultList();
        return resultList.isEmpty();
    }
}
