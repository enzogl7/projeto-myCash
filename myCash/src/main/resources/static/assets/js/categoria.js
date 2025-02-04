document.getElementById('adicionarCategoria').addEventListener('click', function() {
    const novaCategoria = document.createElement('div');
    novaCategoria.classList.add('form-group', 'mt-3', 'categoria-item');
    novaCategoria.innerHTML = `
      <label for="nomeCategoria">Nome</label>
      <input type="text" name="nomeCategoria" class="form-control" placeholder="Ex: Presente, Bico" required>
    `;

    document.getElementById('categorias-container').appendChild(novaCategoria);
});