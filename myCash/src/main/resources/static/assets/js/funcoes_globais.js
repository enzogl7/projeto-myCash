function exibirMensagemErro(elemento, mensagem) {
    elemento.textContent = mensagem;
    elemento.classList.remove('mensagem-escondida');
    elemento.classList.add('mensagem-visivel');

    setTimeout(function () {
        elemento.classList.remove('mensagem-visivel');
        elemento.classList.add('mensagem-escondida');
    }, 2500);
}

function formatElements(selector, formatFunction) {
    document.querySelectorAll(selector).forEach(function (element) {
        const originalValue = element.textContent.trim();
        const formattedValue = formatFunction(originalValue);
        element.textContent = formattedValue;
    });
}

function formatDate(dateString) {
    const [year, month, day] = dateString.split('-');
    return `${day}/${month}/${year}`;
}

function mostrarSenha(inputId, iconId) {
    const senhaInput = document.getElementById(inputId);
    const iconSenha = document.getElementById(iconId);

    if (senhaInput.type === "password") {
        senhaInput.type = "text";
        iconSenha.classList.remove("bi-eye");
        iconSenha.classList.add("bi-eye-slash");
    } else {
        senhaInput.type = "password";
        iconSenha.classList.remove("bi-eye-slash");
        iconSenha.classList.add("bi-eye");
    }
}

