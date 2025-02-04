function entrar() {
    var email = document.getElementById('emailLogin').value;
    var senha = document.getElementById('senhaLogin').value;

    $.ajax({
        url: '/logar',
        type: 'POST',
        data: {
            email: email,
            senha: senha
        },
        complete: function(xhr, status) {
            switch (xhr.status) {
                case 200:
                    window.location.href = '/home'
                    break;
                case 401:
                    exibirMensagemErro(mensagemErro, 'Ops! email/senha incorreta.')
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