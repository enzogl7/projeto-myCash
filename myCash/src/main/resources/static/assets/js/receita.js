function formatDate(dateString) {
    const date = new Date(dateString);
    const day = ("0" + date.getDate()).slice(-2);
    const month = ("0" + (date.getMonth() + 1)).slice(-2);
    const year = date.getFullYear();

    return `${day}/${month}/${year}`;
}

document.querySelectorAll('#data-receita').forEach(function(element) {
    const originalDate = element.textContent.trim();
    const formattedDate = formatDate(originalDate);
    element.textContent = formattedDate;
});

function confirmarExclusaoReceita(button) {
    var idReceitaExclusao = button.getAttribute('data-id')
    Swal.fire({
        title: 'Tem certeza que deseja excluir esta receita?',
        text: "Essa ação não pode ser desfeita!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sim',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            excluirReceita(idReceitaExclusao);
        } else {
            Swal.fire('Cancelado', 'A receita não foi excluída', 'info');
        }
    });
}

function excluirReceita(idButton) {
    $.ajax({
        url: '/receita/excluir',
        type: 'POST',
        data: {
            idReceita: idButton
        },
        complete: function(xhr, status) {
            switch (xhr.status) {
                case 200:
                    Swal.fire({
                        title: "Pronto!",
                        text: "A receita foi excluída com sucesso!",
                        icon: "success",
                        confirmButtonText: 'OK'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = "/receita/minhas-receitas";
                        }
                    });
                    break;
                case 500:
                    Swal.fire({
                        title: "Erro!",
                        text: "Ocorreu um erro ao excluir esta receita.",
                        icon: "error"
                    });
                    break;
                default:
                    alert("Erro desconhecido: " + status);
            }
        }
    });
}


