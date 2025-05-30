package com.aula.joaoJava.Filmes;

import com.aula.joaoJava.User.IUserRepository;
import com.aula.joaoJava.User.UserModel;
import com.aula.joaoJava.Utils.AuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/filmes")
public class FilmesController {

    @Autowired
    private IFilmesRepository filmesRepository;

    @Autowired
    private IUserRepository userRepository;

    // ✅ Criar filme
    @PostMapping("/criarfilme")
    private ResponseEntity<?> criarfilme(@RequestBody FilmesModel filmesModel, HttpServletRequest request) {
        String username = AuthUtil.extractUsername(request);

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }

        Optional<UserModel> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
        }

        filmesModel.setUsuario(userOpt.get());

        // ✅ Checa se esse usuário já tem esse filme
        Optional<FilmesModel> filmeExis = filmesRepository.findByTituloAndUsuario(filmesModel.getTitulo(), userOpt.get());

        if (filmeExis.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Filme já existe na sua lista!");
        }

        FilmesModel criado = filmesRepository.save(filmesModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // ✅ Listar filmes do usuário autenticado
    @GetMapping("/listafilme")
    public ResponseEntity<?> listarFilmes(HttpServletRequest request) {
        String username = AuthUtil.extractUsername(request);

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }

        Optional<UserModel> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
        }

        List<FilmesModel> filmes = filmesRepository.findByUsuario(userOpt.get());

        return ResponseEntity.ok(filmes);
    }

    // ✅ Deletar filme
    @DeleteMapping("/deletarfilme/{id}")
    public void deleteFilme(@PathVariable UUID id) {
        filmesRepository.deleteById(id);
    }

    // ✅ Atualizar Filme
    @PutMapping("/atualizarfilme/{id}")
    public ResponseEntity<?> atualizaFilme(@PathVariable UUID id, @RequestBody FilmesModel filmeModel) {
        return filmesRepository.findById(id).map(filmeExistente -> {

            if (filmeModel.getTitulo() != null) {
                filmeExistente.setTitulo(filmeModel.getTitulo());
            }
            if (filmeModel.getData() != null) {
                filmeExistente.setData(filmeModel.getData());
            }
            if (filmeModel.getComentarios() != null) {
                filmeExistente.setComentarios(filmeModel.getComentarios());
            }

            filmeExistente.setNota(filmeModel.getNota());
            filmeExistente.setGostou(filmeModel.getGostou());

            var atualizado = filmesRepository.save(filmeExistente);
            return ResponseEntity.ok(atualizado);

        }).orElse(ResponseEntity.notFound().build());
    }
}
