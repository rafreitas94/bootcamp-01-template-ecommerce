package br.com.itau.mercadolivre.novacategoria.model;

import br.com.itau.mercadolivre.validator.AtributoUnico;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaRequest {

    @NotBlank
    @AtributoUnico(nomeAtributo = "nome", classeDominio = Categoria.class)
    private String nome;
    @Positive
    private Long idCategoriaMae;

    public CategoriaRequest(@NotBlank String nome, @Positive Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(EntityManager manager) {
        Categoria categoria = new Categoria(this.nome);

        if (this.idCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class, this.idCategoriaMae);
            Assert.notNull(categoriaMae, "o ID fornecido não corresponde a nenhuma categoria cadastrada");
            categoria.setCategoriaMae(categoriaMae);
        }

        return categoria;
    }
}
