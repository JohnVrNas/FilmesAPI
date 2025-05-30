function cadastrar() {
    const nome = document.getElementById('nome').value;
    const username = document.getElementById('username').value;
    const senha = document.getElementById('senha').value;
    const telefone = document.getElementById('telefone').value;
    const email = document.getElementById('email').value;

    const user = {
        nome,
        username,
        senha,
        telefone,
        email
    };

    fetch('http://localhost:8080/user/novouser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    .then(response => {
        if (response.ok) {
            alert('Usuário cadastrado com sucesso!');
            window.location.href = 'login.html';
        } else if (response.status === 409) {
            alert('Usuário já existe!');
        } else {
            alert('Erro ao cadastrar usuário.');
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro na conexão com o servidor.');
    });
}
