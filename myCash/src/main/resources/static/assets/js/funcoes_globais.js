function exibirMensagemErro(elemento, mensagem) {
    elemento.textContent = mensagem;
    elemento.classList.remove('mensagem-escondida');
    elemento.classList.add('mensagem-visivel');

    setTimeout(function () {
        elemento.classList.remove('mensagem-visivel');
        elemento.classList.add('mensagem-escondida');
    }, 2500);
}

const toggleThemeBtn = document.getElementById('toggleThemeBtn');
toggleThemeBtn.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode');
    toggleThemeBtn.textContent = document.body.classList.contains('dark-mode') ? '☀️' : '🌙';
});