async function login() {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();

    if (!username || !password) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    try {
        const credentials = btoa(`${username}:${password}`);
        localStorage.setItem('auth', credentials);

        const response = await fetch('http://localhost:8080/user/usercadastrados', {
            method: 'GET',
            headers: {
                'Authorization': `Basic ${credentials}`
            }
        });

        if (response.ok) {
            alert('Login realizado com sucesso!');
            window.location.href = 'index.html';
        } else if (response.status === 401) {
            alert('Usuário ou senha inválidos!');
        } else {
            alert(`Erro: ${response.status} ${response.statusText}`);
        }

    } catch (error) {
        console.error('Erro na requisição:', error);
        alert('Erro de conexão com o servidor.');
    }
}
