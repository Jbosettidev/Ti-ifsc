import os
from pathlib import Path

# Extensões dos arquivos que serão incluídos
extensoes = {'.java', '.xml', '.html', '.css', '.properties', '.sql', '.md'}
pasta_projeto = Path(".")  # Utiliza a pasta onde o script está sendo executado
arquivo_saida = "projeto_completo_ti.md"

with open(arquivo_saida, "w", encoding="utf-8") as out:
    out.write("# Código Fonte do Projeto\n\n")
    for filepath in pasta_projeto.rglob("*"):
        # Garante que ignora o próprio arquivo de saída
        if filepath.is_file() and filepath.suffix.lower() in extensoes and filepath.name != arquivo_saida:
            out.write(f"## Arquivo: `{filepath}`\n")
            out.write(f"```{filepath.suffix[1:]}\n")
            try:
                out.write(filepath.read_text(encoding="utf-8", errors="ignore"))
            except Exception as e:
                out.write(f"// Erro ao ler arquivo: {e}")
            out.write("\n```\n\n")

print(f"Arquivo '{arquivo_saida}' gerado com sucesso!")