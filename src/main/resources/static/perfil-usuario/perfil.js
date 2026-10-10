async function carregarPerfil() {
    const res = await fetch('/usuarios/me');

    if (res.status === 401) {          // não está logado
        window.location.href = '/login';
        return;
    }

    const usuario = await res.json();
    document.getElementById('nome').textContent = usuario.nome;
}

carregarPerfil().catch(console.error);
