formatElements('#data-receita', formatDate);

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



