// Armazena as credenciais no localStorage
function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (!username || !password) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    const credentials = btoa(`${username}:${password}`);
    localStorage.setItem('auth', credentials);

    // Opcional: testa autenticação com o backend
    fetch('http://localhost:8080/user/usercadastrados', {
        method: 'GET',
        headers: {
            'Authorization': `Basic ${credentials}`
        }
    })
    .then(response => {
        if (response.ok) {
            alert('Login realizado com sucesso!');
            window.location.href = 'index.html';
        } else if (response.status === 401) {
            alert('Usuário ou senha inválidos!');
        } else {
            alert('Erro ao autenticar. Tente novamente.');
        }
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
        alert('Erro de conexão com o servidor.');
    });
}

// Remove as credenciais e redireciona para login
function logout() {
    localStorage.removeItem('auth');
    window.location.href = 'login.html';
}

// Verifica se o usuário está autenticado
function verificarAutenticacao() {
    const auth = localStorage.getItem('auth');
    if (!auth) {
        window.location.href = 'login.html';
    }
}

// Pega o header Authorization para usar nas requisições
function getAuthHeader() {
    const auth = localStorage.getItem('auth');
    if (!auth) return {};
    return {
        'Authorization': `Basic ${auth}`
    };
}
