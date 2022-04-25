package br.com.zup.handora.cadastrandolivrosunicos.livro;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.ISBN.Type;

public class LivroRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    @ISBN(type = Type.ANY)
    private String isbn;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past
    private LocalDate dataPublicacao;

    public LivroRequest() {}

    public LivroRequest(@NotBlank String titulo, @NotBlank @ISBN(type = Type.ANY) String isbn,
                        @Past LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
    }

    public Livro toModel() {
        String novoIsbn = isbn.replaceAll("[^0-9X]", "");

        return new Livro(titulo, novoIsbn, dataPublicacao);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

}
