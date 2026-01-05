Visão geral

Aplicativo Android (Android Studio) — loja virtual para venda de jogos.
Cliente em Java + XML, comunicação com backend PHP via Ion (JSON). Banco de dados: MySQL.

Tecnologias
* Android: Java, XML (Activities / Fragments)
* HTTP client: Ion
* Backend: PHP (endpoints REST que trocam JSON)
* Banco: MySQL
* utenticação recomendada: JWT (fortemente recomendado)
* Produção: obrigar HTTPS (TLS)

Estrutura da aplicação
* Cliente envia requests com Ion.
* Backend valida, autentica, aplica regras de negócio e acessa o banco.
* Não confiar em validações do cliente — tudo deve ser validado no servidor.

Telas

Tela 1 — Tela Inicial
Objetivo: carregar configurações e direcionar para login ou cadastro.
![alt text](https://raw.githubusercontent.com/mathdev03/Cyberfox_Store_Mobile/refs/heads/master/IMAGENS/Inicio.jpeg "Tela de ínicio")

Tela 2 — Cadastro (email e senha)
Objetivo: criar conta com email + senha.
![alt text](github.com/mathdev03/Cyberfox_Store_Mobile/blob/master/IMAGENS/Criar%20conta.jpeg?raw=true "Tela de cadastro")
