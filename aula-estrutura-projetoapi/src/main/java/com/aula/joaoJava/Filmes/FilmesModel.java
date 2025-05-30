package com.aula.joaoJava.Filmes;

import com.aula.joaoJava.User.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "tb_filmes")
public class FilmesModel {

    @Id
    @GeneratedValue(generator = "UUID")

    private UUID id;

    private String titulo;
    private String data;
    private String comentarios;
    private float nota;
    private boolean gostou;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"senha", "email", "telefone", "filmes"})  // <- Evita expor dados sensíveis ou loops.
    private UserModel usuario;


    // Getters e setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public boolean getGostou() {
        return gostou;
    }

    public void setGostou(boolean gostou) {
        this.gostou = gostou;
    }

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }

}

