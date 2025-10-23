# ATIVIDADE PRÁTICA SUPERVISIONADA 2 — CÓDIGO MORSE (ÁRVORE BINÁRIA)

Projeto que implementa **Árvore Binária de Código Morse** (letras A–Z e dígitos 0–9) **com inserção, busca, remoção, codificação e decodificação**, para a disciplina de **Resoluções de Problemas Estruturados em Computação**.

---

## Sobre o projeto

- **Árvore Binária** onde:
  - **Esquerda (`.`)** representa **ponto** e **Direita (`-`)** representa **traço**.
  - Cada nó guarda **um símbolo** (`"A"`…`"Z"`, `"0"`…`"9"`) **ou vazio** (`""`).

- **Operações**
  - **Inserção por código** (`inserir(".-", "A")`) — cria nós conforme o caminho.
  - **Busca por código** (`buscarPorCodigo(".-") → "A"`).
  - **Busca de código por símbolo** (`buscarCodigoPorSimbolo("A") → ".-"`) via **DFS recursiva**.
  - **Remoção** _sem perder a estrutura_ (apenas limpa o valor do nó).
  - **Codificar texto → Morse** e **Decodificar Morse → texto**.

- **Tokenização de Morse sem `split`**:
  - `Tokenizer` percorre a `String` manualmente (apenas `substring`, `length`), detectando **um espaço** (separa letras) e **dois espaços** (separa palavras).

- **Exibição hierárquica** da árvore (texto com indentação).

---

## Estrutura do projeto

```
atps2-morse-tree/
└─ src/
   ├─ Main.java  # menu / fluxo principal (CLI)
   ├─ Nodo.java   # nó da árvore (valor/esq/dir)
   ├─ ArvoreBinariaMorse.java  # lógica da árvore e operações
   └─ Tokenizer.java  # parser manual de tokens Morse (sem split)
```

---

## Como rodar

```bash
javac src/*.java
java -cp src Main
```

**Requisito**: JDK 8+.

---

## Menu e funcionalidades

- **1** – Codificar **TEXTO → MORSE**
- **2** – Decodificar **MORSE → TEXTO**

  > Dica: separe **letras com 1 espaço** e **palavras com 2 espaços**

- **3** – Buscar **código** por **símbolo** (A–Z / 0–9)
- **4** – Buscar **símbolo** por **código** (`.` e `-`)
- **5** – **Inserir** símbolo manualmente (código + símbolo)
- **6** – **Remover por código** (limpa o valor do nó)
- **7** – **Remover por símbolo** (localiza e limpa)
- **8** – Exibir **árvore hierárquica**
- **0** – Sair

---

## Regras do projeto

- **Sem** `List`, `ArrayList`, **sem arrays**, `Map`, `StringBuilder`, `split`, `regex` e afins.
- **Somente** `String`, tipos primitivos (`int`, `float`) e **I/O simples**.
- **`length` apenas em `String`**.
- **Sem** `throws`, `Exception` e estruturas especiais.
- A **remoção** não apaga nós; **apenas limpa o valor** do nó para **não quebrar caminhos** (`.`/`-`).
