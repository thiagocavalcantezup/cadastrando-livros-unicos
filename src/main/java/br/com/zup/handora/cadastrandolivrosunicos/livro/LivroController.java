package br.com.zup.handora.cadastrandolivrosunicos.livro;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(LivroController.BASE_URI)
public class LivroController {

    public final static String BASE_URI = "/livros";

    private final LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid LivroRequest livroRequest,
                                       UriComponentsBuilder ucb) {
        if (livroRepository.existsByIsbn(livroRequest.getIsbn())) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "O livro já está cadastrado."
            );
        }

        Livro livro = livroRepository.save(livroRequest.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
