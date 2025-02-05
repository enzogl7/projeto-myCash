formatElements('#data-despesa', formatDate);

function confirmarExclusaoDespesa(button) {
    var idDespesaExclusao = button.getAttribute('data-id')
    Swal.fire({
        title: 'Tem certeza que deseja excluir esta despesa?',
        text: "Essa ação não pode ser desfeita!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sim',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            excluirDespesa(idDespesaExclusao);
        }
    });
}

function excluirDespesa(idButton) {
    $.ajax({
        url: '/despesa/excluir',
        type: 'POST',
        data: {
            idDespesa: idButton
        },
        complete: function(xhr, status) {
            switch (xhr.status) {
                case 200:
                    Swal.fire({
                        title: "Pronto!",
                        text: "A despesa foi excluída com sucesso!",
                        icon: "success",
                        confirmButtonText: 'OK'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = "/despesa/minhas-despesas";
                        }
                    });
                    break;
                case 500:
                    Swal.fire({
                        title: "Erro!",
                        text: "Ocorreu um erro ao excluir esta despesa.",
                        icon: "error"
                    });
                    break;
                default:
                    alert("Erro desconhecido: " + status);
            }
        }
    });
}

function desabilitarInputArquivo() {
    const select = document.getElementById('iconeDespesaExistente');
    const fileInput = document.getElementById('iconeDespesa');
    fileInput.disabled = select.value !== "";
}