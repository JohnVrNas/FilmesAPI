package com.aula.joaoJava.User;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    // Criar usuario
    @PostMapping("/novouser")
    public ResponseEntity<?> criar(@RequestBody UserModel userModel) {
        Optional<UserModel> usuarioExistente = userRepository.findByUsername(userModel.getUsername());

        if (usuarioExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe!");
        }

        var hashsenha = BCrypt.withDefaults().hashToString(12, userModel.getSenha().toCharArray());
        userModel.setSenha(hashsenha);

        UserModel criado = userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // Listar usuarios - apenas autenticados
    @GetMapping("/usercadastrados")
    public ResponseEntity<?> listarUsuarios(HttpServletRequest request) {
        String username = AuthUtil.extractUsername(request);

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }

        List<UserModel> usuarios = userRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // Deletar usuario
    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userRepository.deleteById(id);
    }

    // Atualizar usuario
    @PutMapping("/atualiza/{id}")
    public ResponseEntity<?> atualizaUser(@PathVariable UUID id, @RequestBody UserModel userModel) {
        return userRepository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNome(userModel.getNome());
            usuarioExistente.setUsername(userModel.getUsername());

            var hashsenha = BCrypt.withDefaults().hashToString(12, userModel.getSenha().toCharArray());
            usuarioExistente.setSenha(hashsenha);

            usuarioExistente.setTelefone(userModel.getTelefone());
            usuarioExistente.setEmail(userModel.getEmail());

            var atualizado = userRepository.save(usuarioExistente);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}
