function getAuthHeader() {
    const auth = localStorage.getItem('auth');
    if (!auth) {
        return {};
    }
    return {
        'Authorization': `Basic ${auth}`
    };
}

function carregarFilmes() {
    fetch('http://localhost:8080/filmes/listafilme', {
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
        const tabela = document.getElementById('filmesTabela');
        tabela.innerHTML = '';

        data.forEach(filme => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${filme.titulo}</td>
                <td>${filme.data}</td>
                <td>${filme.nota}</td>
                <td>${filme.gostou ? 'Sim' : 'Não'}</td>
                <td>
                    <button class="btn btn-sm btn-warning" onclick="editarFilme('${filme.id}')">Editar</button>
                    <button class="btn btn-sm btn-danger" onclick="deletarFilme('${filme.id}')">Excluir</button>
                </td>
            `;
            tabela.appendChild(row);
        });
    })
    .catch(error => {
        console.error(error);
        alert('Erro ao carregar filmes.');
    });
}

function deletarFilme(id) {
    if (!confirm('Tem certeza que deseja excluir este filme?')) return;

    fetch(`http://localhost:8080/filmes/deletarfilme/${id}`, {
        method: 'DELETE',
        headers: getAuthHeader()
    })
    .then(response => {
        if (response.ok) {
            alert('Filme excluído com sucesso!');
            carregarFilmes();
        } else {
            alert('Erro ao excluir filme.');
        }
    })
    .catch(error => console.error('Erro:', error));
}

function editarFilme(id) {
    window.location.href = `editar.html?id=${id}`;
}

window.onload = carregarFilmes;
