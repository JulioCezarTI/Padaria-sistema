document.getElementById("formCompra").addEventListener("submit", function (event) {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const item = document.getElementById("item").value;
    const quantidade = document.getElementById("quantidade").value;
    const preco = document.getElementById("preco").value;

    alert(`✅ Compra salva!\n\n👤 Cliente: ${nome}\n🥐 Item: ${item}\n🔢 Quantidade: ${quantidade}\n💲 Preço: R$${preco}`);
});
