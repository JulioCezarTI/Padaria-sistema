const API_URL = "http://localhost:8081/usuario"; // backend Spring Boot

// ----- Abas -----
const tabButtons = document.querySelectorAll('.tab-button');
const tabContents = document.querySelectorAll('.tab-content');

function abrirAba(id) {
    // remove "active" de tudo
    tabButtons.forEach(b => b.classList.remove('active'));
    tabContents.forEach(c => c.classList.remove('active'));

    // ativa botão e conteúdo
    const botao = [...tabButtons].find(b => b.dataset.tab === id);
    const conteudo = document.getElementById(id);
    if (botao) botao.classList.add('active');
    if (conteudo) conteudo.classList.add('active');
}

tabButtons.forEach(btn => {
    btn.addEventListener('click', () => abrirAba(btn.dataset.tab));
});

// ----- Variáveis globais -----
let clienteAtual = null;
let produtosSelecionados = [];

// ----- Produtos -----
const produtosTradicionais = [
    { id: 1, nome: "Pão de Queijo", preco: 3.5, compraId: null, imagem: "https://cdn.pixabay.com/photo/2016/03/05/19/02/cheese-bread-1238619_960_720.jpg" },
    { id: 2, nome: "Croissant", preco: 5.0, compraId: null, imagem: "https://cdn.pixabay.com/photo/2014/10/19/20/59/croissant-494958_960_720.jpg" },
    { id: 3, nome: "Pão Francês", preco: 1.2, compraId: null, imagem: "https://cdn.pixabay.com/photo/2016/11/29/04/14/bread-1867208_960_720.jpg" },
    { id: 4, nome: "Bolo de Cenoura", preco: 7.5, compraId: null, imagem: "https://cdn.pixabay.com/photo/2021/08/06/20/27/cake-6527274_960_720.jpg" },
    { id: 5, nome: "Brigadeiro", preco: 2.5, compraId: null, imagem: "https://cdn.pixabay.com/photo/2021/12/06/13/39/brigadeiro-6849845_960_720.jpg" },
    { id: 6, nome: "Coxinha", preco: 4.0, compraId: null, imagem: "https://cdn.pixabay.com/photo/2020/08/04/00/05/brazilian-snack-5461893_960_720.jpg" },
    { id: 7, nome: "Sonho com Doce de Leite", preco: 6.0, compraId: null, imagem: "https://cdn.pixabay.com/photo/2023/01/06/20/28/donut-7701441_960_720.jpg" },
    { id: 8, nome: "Torta de Frango", preco: 5.5, compraId: null, imagem: "https://cdn.pixabay.com/photo/2020/05/04/18/32/empanada-5128891_960_720.jpg" },
    { id: 9, nome: "Café Expresso", preco: 4.0, compraId: null, imagem: "https://cdn.pixabay.com/photo/2015/06/24/16/36/coffee-820659_960_720.jpg" },
    { id: 10, nome: "Bolo de Chocolate", preco: 8.0, compraId: null, imagem: "https://cdn.pixabay.com/photo/2014/04/22/02/56/cake-329522_960_720.jpg" }
];

// ----- Referências DOM -----
const produtosContainer = document.querySelector('.produtos-lista'); // <div class="produtos-lista">
const nomeUsuarioInput = document.getElementById('nomeUsuario');
const telefoneUsuarioInput = document.getElementById('telefoneUsuario');
const emailUsuarioInput = document.getElementById('emailUsuario');
const cpfUsuarioInput = document.getElementById('cpfUsuario');
const dataNascimentoInput = document.getElementById('dataNascimentoUsuario');
const btnRegistrarUsuario = document.getElementById('btnRegistrarUsuario');

const mensagemSucessoDiv = document.getElementById('mensagemSucesso');
const clientesListDiv = document.getElementById('clientesList');
const notaFiscalDiv = document.getElementById('notaFiscal');

// ----- Renderização de produtos -----
function renderizarProdutos() {
    if (!produtosContainer) return;
    produtosContainer.innerHTML = '';
    produtosTradicionais.forEach(prod => {
        const div = document.createElement('div');
        div.classList.add('produto');
        div.innerHTML = `
      <img src="${prod.imagem}" alt="${prod.nome}" />
      <h3>${prod.nome}</h3>
      <p>R$ ${prod.preco.toFixed(2)}</p>
      <button data-id="${prod.id}">Adicionar ao Carrinho</button>
    `;
        div.querySelector('button').addEventListener('click', () => adicionarProdutoAoCarrinho(prod.id));
        produtosContainer.appendChild(div);
    });
}

// ----- Carrinho / Nota Fiscal -----
function adicionarProdutoAoCarrinho(idProduto) {
    if (!clienteAtual) {
        alert('Cadastre um usuário antes de adicionar produtos.');
        return;
    }
    const produto = produtosTradicionais.find(p => p.id === idProduto);
    if (!produto) return;

    const item = produtosSelecionados.find(p => p.id === idProduto);
    if (item) item.quantidade++;
    else produtosSelecionados.push({ id: produto.id, nome: produto.nome, preco: produto.preco, quantidade: 1 });

    mensagemSucessoDiv.innerText = `🍞 Produto ${produto.nome} adicionado!`;
    atualizarNotaFiscal();
}

function atualizarNotaFiscal() {
    if (!clienteAtual) {
        notaFiscalDiv.innerText = "Nenhum cliente selecionado.";
        return;
    }
    if (produtosSelecionados.length === 0) {
        notaFiscalDiv.innerText = `Nota Fiscal - ${clienteAtual.nome}\n\nNenhum produto adicionado ainda.`;
        return;
    }
    let total = 0;
    let texto = `Nota Fiscal - ${clienteAtual.nome}\n\nItens:\n`;
    produtosSelecionados.forEach(item => {
        const subtotal = item.quantidade * item.preco;
        total += subtotal;
        texto += `${item.nome} - ${item.quantidade} x R$ ${item.preco.toFixed(2)} = R$ ${subtotal.toFixed(2)}\n`;
    });
    texto += `\nTotal: R$ ${total.toFixed(2)}`;
    notaFiscalDiv.innerText = texto;
}

function atualizarListaClientes() {
    if (!clientesListDiv) return;
    clientesListDiv.innerHTML = clienteAtual
        ? `<div>👤 ${clienteAtual.nome} - ${clienteAtual.telefone} - ${clienteAtual.email}</div>`
        : 'Nenhum cliente cadastrado.';
}

// ----- Registro de usuário -----
btnRegistrarUsuario.addEventListener('click', async () => {
    const nome = nomeUsuarioInput.value.trim();
    const telefone = telefoneUsuarioInput.value.trim();
    const email = emailUsuarioInput.value.trim();
    const cpf = cpfUsuarioInput.value.trim();
    const dataNascimento = dataNascimentoInput.value;

    if (!nome || !telefone || !email || !cpf || !dataNascimento) {
        alert('Preencha todos os campos!');
        return;
    }

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome, telefone, email, cpf, dataNascimento })
        });

        if (!response.ok) {
            alert('Erro ao cadastrar usuário.');
            return;
        }

        const usuario = await response.json();
        clienteAtual = usuario;
        produtosSelecionados = [];
        mensagemSucessoDiv.innerText = `✅ Usuário ${usuario.nome} cadastrado com sucesso!`;
        atualizarListaClientes();
        atualizarNotaFiscal();

        // Abrir automaticamente a aba de produtos
        abrirAba('tab-produtos');
    } catch (err) {
        console.error(err);
        alert('Erro de conexão com o servidor.');
    }
});

// ----- Inicialização -----
document.addEventListener('DOMContentLoaded', () => {
    // garante que só a primeira aba apareça
    const ativa = document.querySelector('.tab-button.active');
    abrirAba(ativa ? ativa.dataset.tab : (tabButtons[0]?.dataset.tab || 'tab-usuarios'));

    renderizarProdutos();
});
