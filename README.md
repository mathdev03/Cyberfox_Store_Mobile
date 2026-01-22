# Loja de Jogos Android — Mobile Client + PHP Backend

> Aplicativo Android (Java + XML) que conecta a um backend PHP (REST JSON) para venda e gestão de jogos.  
> Status: **Protótipo funcional** — autenticação por JWT, banco MySQL.  

[![build status](https://img.shields.io/badge/build-local-yellow)](#) [![license](https://img.shields.io/badge/license-MIT-blue)](LICENSE) [![status](https://img.shields.io/badge/status-prot%C3%B3tipo-orange)](#)

---

## Sumário
- [Visão geral](#visão-geral)  
- [Demo / Screenshots](#demo--screenshots)  
- [Tecnologias](#tecnologias)  
- [Estrutura](#estrutura)

---

## Visão geral
Aplicativo Android que permite navegar, comprar e gerenciar jogos digitais. Cliente em Java/XML que se comunica com um backend PHP via JSON (Ion no cliente). O backend expõe endpoints REST que autenticam via JWT e acessam um banco MySQL.

---

## Demo / Screenshots
> Tela de ínicio
<img src="https://raw.githubusercontent.com/mathdev03/Cyberfox_Store_Mobile/refs/heads/master/IMAGENS/Inicio.jpeg" width="30%" alt="Tela inicial do app">

---

## Tecnologias
- **Android**: Java, XML (Activities / Fragments)  
- **HTTP client (Android)**: Ion  
- **Backend**: PHP (REST API)  
- **Banco**: MySQL  
- **Autenticação recomendada**: JWT (acesso + refresh token)  
- **Produção**: HTTPS obrigatório (TLS)

---

## Estrutura (resumo)
- Cliente Android envia requests com Ion → `POST /api/auth/login`, `GET /api/games` etc.  
- Backend valida, autentica, aplica regras de negócio e acessa o banco.  
- **Nunca confiar** nas validações do cliente — validar tudo no servidor.

---

## Instalação (rápida)
**Pré-requisitos**: Java JDK, Android Studio, PHP 7.4+, MySQL, Composer (se usar libs PHP).  

1. Clone o repositório:
```bash
git clone https://github.com/SEU-USER/loja-jogos.git
cd loja-jogos
