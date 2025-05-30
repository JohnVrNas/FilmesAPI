function getAuthHeader() {
    const auth = localStorage.getItem('auth');
    if (!auth) {
        return {};
    }
    return {
        'Authorization': `Basic ${auth}`
    };
}

document.getElementById('formAdicionar').addEventListener('submit', function(e) {
    e.preventDefault();

    const titulo = document.getElementById('titulo').value;
    const data = document.getElementById('data').value;
    const comentarios = document.getElementById('comentarios').value;
    const nota = parseFloat(document.getElementById('nota').value);
    const gostou = document.querySelector('input[name="gostou"]:checked').value === "true";

    const filme = {
        titulo,
        data,
        comentarios,
        nota,
        gostou
    };

    fetch('http://localhost:8080/filmes/criarfilme', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            ...getAuthHeader()  // ✅ Correção: espalha corretamente a autenticação
        },
        body: JSON.stringify(filme)
    })
    .then(response => {
        if (response.ok) {
            alert('Filme adicionado com sucesso!');
            window.location.href = 'listagem.html';
        } else if (response.status === 409) {
            alert('Filme já existe na lista!');
        } else {
            alert('Erro ao adicionar filme!');
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao adicionar filme!');
    });
});
