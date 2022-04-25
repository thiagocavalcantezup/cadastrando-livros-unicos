package br.com.zup.handora.cadastrandolivrosunicos.livro;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.ISBN.Type;

@Entity
@Table(name = "livros", uniqueConstraints = {
        @UniqueConstraint(name = "UK_LIVRO_ISBN", columnNames = {"isbn"})})
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    @ISBN(type = Type.ANY)
    private String isbn;

    @Column(nullable = false)
    @Past
    private LocalDate dataPublicacao;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Livro() {}

    public Livro(String titulo, @ISBN(type = Type.ANY) String isbn,
                 @Past LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
    }

    public Long getId() {
        return id;
    }

}
