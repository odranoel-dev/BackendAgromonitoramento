package com.example.agromonitoramento.backendagromonitoramento.controller;

import com.example.agromonitoramento.backendagromonitoramento.dto.AtualizarUsuarioDTO;
import com.example.agromonitoramento.backendagromonitoramento.dto.CadastroUsuarioDTO;

import com.example.agromonitoramento.backendagromonitoramento.dto.LoginUsuarioDTO;
import com.example.agromonitoramento.backendagromonitoramento.model.Usuario;
import com.example.agromonitoramento.backendagromonitoramento.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity criarUsuario (@RequestBody @Valid CadastroUsuarioDTO cadastroUsuarioDTO){

        usuarioService.cadastrarUsuario(cadastroUsuarioDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity realizarLogin(@RequestBody @Valid LoginUsuarioDTO loginUsuarioDTO){

        usuarioService.autenticarLogin(loginUsuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Login realizado com sucesso");
    }

    @GetMapping("/")
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarUsuario(@PathVariable Usuario id){

        usuarioService.excluirUsuario(id);

        return ResponseEntity.status(HttpStatus.OK).body("Usuário excluido com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarUsuario(@PathVariable UUID id,
                                           @RequestBody AtualizarUsuarioDTO atualizarUsuarioDTO){
        usuarioService.atualizarUsuario(id,atualizarUsuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Dados atualizados com sucesso");
    }

}
