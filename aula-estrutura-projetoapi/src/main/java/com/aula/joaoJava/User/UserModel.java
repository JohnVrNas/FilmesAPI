package com.aula.joaoJava.User;

import com.aula.joaoJava.Filmes.FilmesModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity(name = "tb_usuario")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nome;
    private String username;
    private String senha;
    private String email;
    private String telefone;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore  // <- IMPORTANTE
    private List<FilmesModel> filmes;


    // Getters e setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<FilmesModel> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<FilmesModel> filmes) {
        this.filmes = filmes;
    }
}


