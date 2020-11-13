# Algoritmo CYK

Implementação do algoritmo CYK para gramáticas livres de contexto. Exercício programa de Introdução à Teoria da Computação

## Como Rodar

Clone o repositório, compile o código e execute com o java:

```bash
git clone https://github.com/GuilhermeBalog/algoritmo-cyk.git
cd algoritmo-cyk

javac *.java
java glc
```

## Objetivo

Desenvolver um programa para processamento de Gramáticas Livres do Contexto (GLCs). Dada a especificação de uma GLC *G* na Forma Normal de Chomsky e uma cadeia *w* ∈ Σ∗, seu programa deve determinar se *G* gera *w*, e devolver a matriz *Tabela* (vide descrição do algoritmo) e o status (aceita / rejeita). Em outras palavras, o problema é decidir se *w*  pertence a *L(G)*.

## Sobre a solução

A solução implementa o algoritmo CYK (Cocke–Younger–Kasami), descrito da seguinte forma:

```
D = "On input w = w_1 ... w_n:
  1.  If w = & and S -> & is a rule, accept 
  2.  For i = 1 to n:
  3.    For each variable A:
  4.      Test whether A -> b is a rule, where b = w_i
  5.      If so, place A in table(i, i).
  6.  For l = 2 to n:
  7.    For i = 1 to n - l + 1
  8.      Let j = i + l - 1,
  9.      For k = i to j - 1:
  10.       For each rule A -> BC:
  11.         If table(i, k) contains B 
              and table(k + 1, j) contains C, 
              put A in table(i, j)
  12. If S is in table(1, n), accept. Otherwise, reject." 
```

## Autores

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/gmichelassi">
        <img src="https://avatars2.githubusercontent.com/u/49728225?v=4" width="100px;"/>
        <br />
        <sub>
          <b>Gabriel de Castro Michelassi</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="http://guilhermebalog.github.io">
        <img src="https://avatars0.githubusercontent.com/u/38947601?v=4" width="100px;"/>
        <br />
        <sub>
          <b>Guilherme Balog Gardino</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
