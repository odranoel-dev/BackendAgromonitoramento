package com.example.agromonitoramento.backendagromonitoramento.service;

import com.example.agromonitoramento.backendagromonitoramento.dto.UpdateUserIndividualDTO;
import com.example.agromonitoramento.backendagromonitoramento.dto.RegisterUserBusinessDTO;
import com.example.agromonitoramento.backendagromonitoramento.dto.LoginUserDTO;
import com.example.agromonitoramento.backendagromonitoramento.model.UserIndividualModel;
import com.example.agromonitoramento.backendagromonitoramento.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private StatusUsuarioEnum statusUsuarioEnum;


    public void cadastrarUsuario(RegisterUserBusinessDTO dto) {

        String cpf = dto.getCpf();
        String email = dto.getEmail();
        String senha = dto.getSenha();
        LocalDate dataNascimento = dto.getDataNascimento();
        String telefone1 = dto.getTelefone1();
        String telefone2 = dto.getTelefone2();
        String telefone3 = dto.getTelefone3();
        String nomeCompleto = dto.getNomeCompleto();
//        GeneroUsuarioEnum genero = GeneroUsuarioEnum.valueOf(dto.getGenero().toUpperCase());
        //String genero = dto.getGenero();

        validarDataNascimento(dataNascimento);
        validarSenha(senha);
        validarTelefones(telefone1, telefone2, telefone3);
        validarCpfEmail(cpf, email);

        UserIndividualModel usuario = new UserIndividualModel();
        //Criptografa a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setDataNascimento(dataNascimento);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setGenero(genero);
        usuario.setNomeCompleto(nomeCompleto);
        usuario.setTelefone1(telefone1);
        usuario.setTelefone2(telefone2);
        usuario.setTelefone3(telefone3);
        usuario.setStatusDoUsuario(statusUsuarioEnum.ATIVO);
        usuarioRepository.save(usuario);

    }

    public void validarCpfEmail(String cpf, String email) {

        //Verifica se CPF ou email já existem no banco, consultando o repositório.

        if (usuarioRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("O CPF já está cadastrado.");
        }

        if (usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("O e-mail já está cadastrado.");
        }

        System.out.println("Passou pelo validar cpf e email");

    }

    private boolean telefoneJaExiste(String telefone) {
        return usuarioRepository.existsByTelefone1(telefone)
                || usuarioRepository.existsByTelefone2(telefone)
                || usuarioRepository.existsByTelefone3(telefone);
    }


    public void validarTelefones(String telefone1, String telefone2, String telefone3) {

        //isEmpty()	A string não tem nenhum caractere
        //isBlank()	A string está vazia ou só tem espaços
        boolean telefone1Vazio = telefone1 == null || telefone1.isBlank();
        boolean telefone2Vazio = telefone2 == null || telefone2.isBlank();
        boolean telefone3Vazio = telefone3 == null || telefone3.isBlank();

        if (telefone1Vazio && telefone2Vazio && telefone3Vazio) {
            throw new IllegalArgumentException("Preencha ao menos um telefone (Telefone 1, Telefone 2 ou Telefone 3).");
        }

        if ((!telefone1.isBlank() && telefone1.equals(telefone2) ||
                (!telefone1.isBlank() && telefone1.equals(telefone3) ||
                        !telefone2.isBlank() && telefone2.equals(telefone3)))) {
            throw new IllegalArgumentException("Os telefones não podem ser iguais");

        }


        List<String> telefonesInformados = Stream.of(telefone1, telefone2, telefone3)
                .filter(t -> t != null && !t.isBlank())
                .toList();

        boolean algumTelefoneInformado = !telefonesInformados.isEmpty();

        boolean telefoneJaCadastrado = telefonesInformados.stream()
                .anyMatch(this::telefoneJaExiste);

        if (algumTelefoneInformado && telefoneJaCadastrado) {
            throw new IllegalArgumentException("Um dos telefones já está cadastrado!");
        }

    }

    public void validarDataNascimento(LocalDate dataNascimento) {

        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento é obrigatória.");
        }

        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode ser no futuro.");
        }

        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

        if (idade < 18) {
            throw new IllegalArgumentException("O usuário deve ter pelo menos 18 anos.");
        }
    }

    public void validarSenha(String senha) {

        //validar senha (Total 8 caracteres, sendo maiusculo, minusculo, especial e numerico)

        if (senha == null || senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 8 caracteres.");
        }

        if (!senha.matches(".*[A-Z].*") || !senha.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("A senha deve conter letras maiúsculas e minúsculas.");
        }

        if (!senha.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("A senha deve conter ao menos um número.");
        }

        if (!senha.matches(".*[^a-zA-Z0-9].*")) {
            throw new IllegalArgumentException("A senha deve conter ao menos um caractere especial.");
        }
    }

    public List<UserIndividualModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public void excluirUsuario(UserIndividualModel id) {

        usuarioRepository.delete(id);
    }

    public void autenticarLogin(LoginUserDTO loginUsuarioDTO) {

        String email = loginUsuarioDTO.getEmail();
        String senha = loginUsuarioDTO.getSenha();

        //verifica se os campos email e senha foram preenchidos -> feito no dto c/NotBlank


        //verifica se o email digitado existe no banco
        UserIndividualModel usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("E-mail não cadastrado"));

        //verifica se o usuário está ativo
        if (!statusUsuarioEnum.ATIVO.equals(usuario.getStatusDoUsuario())){
            throw new IllegalArgumentException("Usuário inativo");
        }

        //verifica se a senha digitada está correta
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            usuario.setTentativasFalhasLogin(usuario.getTentativasFalhasLogin()+1);
            usuarioRepository.save(usuario);

            //Inativa o usuário caso errar a senha 3x
            if (usuario.getTentativasFalhasLogin()==3){
                usuario.setStatusDoUsuario(statusUsuarioEnum.INATIVO);
                usuarioRepository.save(usuario);
                throw new IllegalArgumentException("Usuário inativado por excesso de tentativa");
            }

            throw new IllegalArgumentException("Senha incorreta!");
        }

        //zerar as tentativas de erro quando acertar a senha
        if (usuario.getTentativasFalhasLogin()>0){
            usuario.setTentativasFalhasLogin(0);
            usuarioRepository.save(usuario);
        }


        // [A fazer] -> Retornar um token JWT ou DTO com os dados do usuário logado!!

    }

    public void statusUsuario (UserIndividualModel usuarios) {

        //[A Fazer] -> Inativar o usuário caso ficar +30 dias sem acessar

    }

    public void atualizarUsuario(UUID id, UpdateUserIndividualDTO atualizarUsuarioDTO){


        UserIndividualModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));




        //reutilizar os metodos de senha, e-mail e telefone.
        //[A Fazer] -> atualizar as informações do usuário

    }
}



