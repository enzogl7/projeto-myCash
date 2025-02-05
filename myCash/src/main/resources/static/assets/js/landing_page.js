document.getElementById('botao-comecar').addEventListener('click', function(event) {
    event.preventDefault();
    document.getElementById('secao-form').scrollIntoView({
        behavior: 'smooth',
        block: 'start'
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const dots = document.querySelectorAll(".dot");
    const sections = document.querySelectorAll("section");

    if (dots.length === 0 || sections.length === 0) return;

    dots.forEach(dot => {
        dot.addEventListener("click", () => {
            const targetSection = document.getElementById(dot.dataset.target);
            if (targetSection) {
                targetSection.scrollIntoView({ behavior: "smooth" });
            }
        });
    });

    const updateActiveDot = () => {
        let currentIndex = 0;
        sections.forEach((section, index) => {
            const sectionTop = section.getBoundingClientRect().top;
            if (sectionTop < window.innerHeight / 2) {
                currentIndex = index;
            }
        });

        dots.forEach(dot => dot.classList.remove("active"));
        dots[currentIndex].classList.add("active");
    };

    window.addEventListener("scroll", updateActiveDot);

    updateActiveDot();
});

function registrar() {
    var nome = document.getElementById('nome').value;
    var email = document.getElementById('email').value;
    var senha = document.getElementById('senha').value;
    var confirmarSenha = document.getElementById('confirmarSenha').value;
    var telefone = document.getElementById('telefone').value;
    var moedaPrincipal = document.getElementById('moedaPrincipal').value;

    if (senha !== confirmarSenha) {
        exibirMensagemErro(mensagemErro, 'As senhas não coincidem. Tente novamente.');
        return false;
    }

    if (!nome || !email || !senha || !confirmarSenha || !telefone || !moedaPrincipal) {
        exibirMensagemErro(mensagemErro, 'Todos os campos devem ser preenchidos.')
        return false;
    }

    var senhaRequisitos = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[\W_]).{8,}$/;
    if (!senhaRequisitos.test(senha)) {
        exibirMensagemErro(mensagemErro, 'Senha fora dos requisitos.');
        return false;
    }

    $.ajax({
        url: '/registrar',
        type: 'POST',
        data: {
            nome: nome,
            email: email,
            senha: senha,
            telefone: telefone,
            moedaPrincipal: moedaPrincipal,
        },
        complete: function(xhr, status) {
            switch (xhr.status) {
                case 200:
                    window.location.href = '/entrar'
                    break;
                case 302:
                    exibirMensagemErro(mensagemErro, 'Ops! este email já foi cadastrado.')
                    break;
                case 406:
                    exibirMensagemErro(mensagemErro, 'Ops! este número de telefone já foi cadastrado.')
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

function mostrarSenha() {
    const senhaInput = document.getElementById("senha");
    const confirmarSenhaInput = document.getElementById("confirmarSenha")
    const iconSenha = document.getElementById("iconSenha");

    if (senhaInput.type === "password" && confirmarSenhaInput.type === "password") {
        senhaInput.type = "text";
        confirmarSenhaInput.type = "text";
        iconSenha.classList.remove("bi-eye");
        iconSenha.classList.add("bi-eye-slash");
    } else {
        senhaInput.type = "password";
        confirmarSenhaInput.type = "password";
        iconSenha.classList.remove("bi-eye-slash");
        iconSenha.classList.add("bi-eye");
    }
}