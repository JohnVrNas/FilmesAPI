function getAuthHeader() {
    const auth = localStorage.getItem('auth');
    if (!auth) {
        return {};
    }
    return {
        'Authorization': `Basic ${auth}`
    };
}

const urlParams = new URLSearchParams(window.location.search);
const filmeId = urlParams.get('id');

if (!filmeId) {
    alert('ID do filme não fornecido!');
    window.location.href = 'listagem.html';
}

document.addEventListener('DOMContentLoaded', () => {
    // Carrega os dados do filme e preenche o formulário
    fetch(`http://localhost:8080/filmes/listafilme`, {
        method: 'GET',
        headers: getAuthHeader()
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao buscar filmes');
        }
        return response.json();
    })
    .then(data => {
        const filme = data.find(f => f.id == filmeId);
        if (!filme) {
            alert('Filme não encontrado!');
            window.location.href = 'listagem.html';
            return;
        }

        // Preenche o formulário
        document.getElementById('filmeId').value = filme.id;
        document.getElementById('titulo').value = filme.titulo;
        document.getElementById('data').value = filme.data;
        document.getElementById('comentarios').value = filme.comentarios;
        document.getElementById('nota').value = filme.nota;
        document.querySelector(`input[name="gostou"][value="${filme.gostou}"]`).checked = true;
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao carregar dados do filme!');
    });
});

// Quando enviar o formulário de edição
document.getElementById('formEditar').addEventListener('submit', function(e) {
    e.preventDefault();

    const filmeAtualizado = {
        titulo: document.getElementById('titulo').value,
        data: document.getElementById('data').value,
        comentarios: document.getElementById('comentarios').value,
        nota: parseFloat(document.getElementById('nota').value),
        gostou: document.querySelector('input[name="gostou"]:checked').value === "true"
    };

    fetch(`http://localhost:8080/filmes/atualizarfilme/${filmeId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            ...getAuthHeader()
        },
        body: JSON.stringify(filmeAtualizado)
    })
    .then(response => {
        if (response.ok) {
            alert('Filme atualizado com sucesso!');
            window.location.href = 'listagem.html';
        } else {
            alert('Erro ao atualizar filme!');
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao atualizar filme!');
    });
});
