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
![alt text](https://raw.githubusercontent.com/mathdev03/Cyberfox_Store_Mobile/refs/heads/master/IMAGENS/Criar%20conta.jpeg "Tela de cadastro")

Tela 3 — Cadastro usuário (apelido / perfil)
Objetivo: completar perfil com apelido e avatar opcional.
![alt text](https://raw.githubusercontent.com/mathdev03/Cyberfox_Store_Mobile/refs/heads/master/IMAGENS/Cadastro%2002.jpeg "Tela de cadastro usuário")

Tela 4 — Logar Usuário
Objetivo: usuário poder logar assim que estiver cadastro no app
![alt text](https://raw.githubusercontent.com/mathdev03/Cyberfox_Store_Mobile/refs/heads/master/IMAGENS/Login.jpeg "Tela de login")

Tela 5 — Dashboard (Principal / Vitrine)
Objetivo: apresentar jogos, barra de busca.
![alt text](https://raw.githubusercontent.com/mathdev03/Cyberfox_Store_Mobile/refs/heads/master/IMAGENS/Dashboard.jpeg "Tela de Dashboard")

Tela 6 — Biblioteca
Objetivo: listar jogos comprados/ativados pelo usuário.
![alt text](https://raw.githubusercontent.com/mathdev03/Cyberfox_Store_Mobile/refs/heads/master/IMAGENS/Biblioteca.jpeg "Tela Biblioteca")

Tela 7 — Descrição do Jogo (Produto)
Objetivo: apresentar detalhes do jogo: imagens, descrição, requisitos, avaliações, preço, botão Comprar.
![alt text](https://raw.githubusercontent.com/mathdev03/Cyberfox_Store_Mobile/refs/heads/master/IMAGENS/Descrição%20Jogo.jpeg "Tela do produto")
