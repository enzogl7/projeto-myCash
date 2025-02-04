package com.ogl.mycash.model;

import jakarta.persistence.*;

import java.time.LocalDate;

    @Entity
    @Table(name = "receita")
    public class Receita {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String descricao;
        private Double valor;
        private LocalDate dataRecebimento;
        private String categoria;

        @ManyToOne
        @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
        private Usuario usuario;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public Double getValor() {
            return valor;
        }

        public void setValor(Double valor) {
            this.valor = valor;
        }

        public LocalDate getDataRecebimento() {
            return dataRecebimento;
        }

        public void setDataRecebimento(LocalDate dataRecebimento) {
            this.dataRecebimento = dataRecebimento;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }
    }
