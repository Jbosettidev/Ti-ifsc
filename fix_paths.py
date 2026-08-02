#!/usr/bin/env python3
"""
Corrige caminhos relativos nos arquivos HTML de src/main/resources/static.

Rode este script a partir da RAIZ do projeto (onde fica o pom.xml):

    python fix_paths.py

O que ele faz:
1. Para CSS/JS/imagens (href/src que nao sejam .html): reescreve como
   caminho absoluto a partir da raiz do static (ex: "style.css" dentro de
   TelasIniciais/login.html vira "/TelasIniciais/style.css").
2. Para links entre paginas (.html, seja em href="" ou em JS via
   window.location.href / location.replace): reescreve para a rota "limpa"
   correspondente (ex: "cadastro.html" vira "/cadastro"), usando o mapa
   CLEAN_ROUTES abaixo. index.html vira "/" (pagina de welcome do Spring).

Nao mexe em URLs absolutas (http://, https://, //cdn...), em caminhos que
ja comecam com "/", nem em "#", "mailto:" etc.

Se aparecer um aviso de "arquivo .html desconhecido", significa que o
script achou uma referencia a um .html que nao esta no mapa CLEAN_ROUTES
-- adicione a entrada correspondente e rode de novo.
"""

import os
import re
import posixpath

STATIC_DIR = os.path.join("src", "main", "resources", "static")

# Mapeia: caminho do arquivo (relativo a static/, com barras "/") -> nome da rota limpa
# Precisa bater com o mapa PAGINAS_ESPECIFICAS no ViewController.java
CLEAN_ROUTES = {
    "TelasIniciais/login.html": "login",
    "TelasIniciais/cadastro.html": "cadastro",
    "TelasIniciais/senha1.html": "senha1",
    "TelasIniciais/senha2.html": "senha2",
    "TelasIniciais/nova-senha.html": "nova-senha",
    "perfil-usuario/perfil.html": "perfil",
    "perfil-usuario/configuracoes.html": "configuracoes",
    "perfil-usuario/seguranca.html": "seguranca",
    "perfil-usuario/excluir.html": "excluir",
    "tela-conquistas/conquistas.html": "conquistas",
    "niveis/Nivel-Geral.html": "nivel-geral",
    "Jogo1-Email/email1.html": "email1",
}

# index.html na raiz do static tem tratamento especial: vira "/"
INDEX_FILE = "index.html"

SKIP_PREFIXES = ("http://", "https://", "//", "#", "mailto:", "tel:", "data:", "/")

ATTR_RE = re.compile(r'(href|src)="([^"]+)"')
JS_RE = re.compile(
    r"(window\.location(?:\.href)?\s*=\s*|location\.replace\(\s*)'([^']+)'"
)


def resolve_target(file_dir, value):
    """Resolve 'value' (caminho relativo escrito no arquivo) para um caminho
    relativo a raiz do static/, usando a pasta do arquivo (file_dir) como base."""
    joined = posixpath.normpath(posixpath.join(file_dir, value))
    return joined.replace("\\", "/")


def new_value_for(resolved):
    if resolved.lower().endswith(".html"):
        if resolved == INDEX_FILE:
            return "/"
        if resolved in CLEAN_ROUTES:
            return "/" + CLEAN_ROUTES[resolved]
        print(f"  [AVISO] .html desconhecido, nao mapeado em CLEAN_ROUTES: {resolved}")
        return None
    return "/" + resolved


def fix_file(path, static_dir):
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()

    rel_dir = posixpath.dirname(
        os.path.relpath(path, static_dir).replace(os.sep, "/")
    )
    changed = False

    def replace_attr(match):
        nonlocal changed
        attr, value = match.group(1), match.group(2)
        if value.startswith(SKIP_PREFIXES):
            return match.group(0)
        resolved = resolve_target(rel_dir, value)
        new_val = new_value_for(resolved)
        if new_val is None or new_val == value:
            return match.group(0)
        changed = True
        return f'{attr}="{new_val}"'

    def replace_js(match):
        nonlocal changed
        prefix, value = match.group(1), match.group(2)
        if value.startswith(SKIP_PREFIXES):
            return match.group(0)
        resolved = resolve_target(rel_dir, value)
        new_val = new_value_for(resolved)
        if new_val is None or new_val == value:
            return match.group(0)
        changed = True
        return f"{prefix}'{new_val}'"

    content = ATTR_RE.sub(replace_attr, content)
    content = JS_RE.sub(replace_js, content)

    if changed:
        with open(path, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"  corrigido: {os.path.relpath(path, static_dir)}")


def main():
    if not os.path.isdir(STATIC_DIR):
        print(f"Nao encontrei {STATIC_DIR}. Rode este script a partir da raiz do projeto (onde fica o pom.xml).")
        return

    print(f"Corrigindo arquivos HTML em {STATIC_DIR} ...")
    for root, _dirs, files in os.walk(STATIC_DIR):
        for name in files:
            if name.lower().endswith(".html"):
                fix_file(os.path.join(root, name), STATIC_DIR)
    print("Concluido.")
    print()
    print("Lembrete: o ViewController.java precisa ter o mesmo mapa CLEAN_ROUTES")
    print("(PAGINAS_ESPECIFICAS) para as rotas limpas resolverem certo.")


if __name__ == "__main__":
    main()