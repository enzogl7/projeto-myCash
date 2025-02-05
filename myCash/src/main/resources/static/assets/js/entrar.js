function entrar() {
    var email = document.getElementById('emailLogin').value;
    var senha = document.getElementById('senhaLogin').value;

    if (!email || !senha) {
        exibirMensagemErro(mensagemErro, 'Todos os campos devem ser preenchidos.')
        return false;
    }

    $.ajax({
        url: '/logar',
        type: 'POST',
        data: {
            email: email,
            senha: senha,
        },
        complete: function(xhr, status) {
            switch (xhr.status) {
                case 202:
                    window.location.href = '/home'
                    break;
                case 200:
                    window.location.href = '/dashboard'
                case 401:
                    exibirMensagemErro(mensagemErro, 'Ops! email/senha incorreta.')
                    break;
                case 404:
                    exibirMensagemErro(mensagemErro, 'Usuário não encontrado.')
                    break;
                case 500:
                    alert("erro")
                    break;
                default:
                    alert("Erro desconhecido: " + status);
            }
        }
    });


}