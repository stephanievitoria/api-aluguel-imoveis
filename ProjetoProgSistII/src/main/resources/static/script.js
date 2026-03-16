let inquilinos = [
    { id: 1, nome: "João", email: "joao@gmail.com", telefone: "111111111" },
    { id: 2, nome: "Maria", email: "maria@gmail.com", telefone: "222222222" }
];

let imoveis = [
    { idImovel: 1, endereco: "Rua A", cidade: "Cidade A", bairro: "Bairro A", preco: 500.00, disponivel: true, inquilinoId: null },
    { idImovel: 2, endereco: "Rua B", cidade: "Cidade B", bairro: "Bairro B", preco: 1000.00, disponivel: false, inquilinoId: 1 }
];

let editandoImovelId = null;
let editandoInquilinoId = null;

// Gerenciamento de Inquilinos
function listarInquilinos() {
    const tabela = document.getElementById("tabelaInquilinos").querySelector("tbody");
    tabela.innerHTML = "";

    inquilinos.forEach(inquilino => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${inquilino.id}</td>
            <td>${inquilino.nome}</td>
            <td>${inquilino.email}</td>
            <td>${inquilino.telefone}</td>
            <td>
                <button onclick="editarInquilino(${inquilino.id})">Editar</button>
                <button onclick="deletarInquilino(${inquilino.id})">Excluir</button>
            </td>
        `;
        tabela.appendChild(row);
    });

    // Atualizar lista de inquilinos no select de imóveis
    atualizarSelectInquilinos();
}

function salvarInquilino() {
    const nome = document.getElementById("txtNomeInquilino").value;
    const email = document.getElementById("txtEmailInquilino").value;
    const telefone = document.getElementById("txtTelefoneInquilino").value;

    if (!nome || !email || !telefone) {
        alert("Por favor, preencha todos os campos.");
        return;
    }

    if (editandoInquilinoId) {
        const inquilino = inquilinos.find(inq => inq.id === editandoInquilinoId);
        inquilino.nome = nome;
        inquilino.email = email;
        inquilino.telefone = telefone;
        editandoInquilinoId = null;
    } else {
        const novoInquilino = {
            id: inquilinos.length + 1,
            nome,
            email,
            telefone
        };
        inquilinos.push(novoInquilino);
    }

    listarInquilinos();
    apagarFormularioInquilino();
}

function editarInquilino(id) {
    const inquilino = inquilinos.find(inq => inq.id === id);
    if (!inquilino) return;

    document.getElementById("txtNomeInquilino").value = inquilino.nome;
    document.getElementById("txtEmailInquilino").value = inquilino.email;
    document.getElementById("txtTelefoneInquilino").value = inquilino.telefone;

    editandoInquilinoId = id;
}

function deletarInquilino(id) {
    inquilinos = inquilinos.filter(inq => inq.id !== id);
    listarInquilinos();
}

function apagarFormularioInquilino() {
    document.getElementById("txtNomeInquilino").value = "";
    document.getElementById("txtEmailInquilino").value = "";
    document.getElementById("txtTelefoneInquilino").value = "";
    editandoInquilinoId = null;
}

function cancelarEdicaoInquilino() {
    apagarFormularioInquilino();
    alert("Edição cancelada.");
}

// Atualizar select de inquilinos
function atualizarSelectInquilinos() {
    const select = document.getElementById("selectInquilino");
    select.innerHTML = `<option value="">Nenhum</option>`;

    inquilinos.forEach(inquilino => {
        const option = document.createElement("option");
        option.value = inquilino.id;
        option.textContent = inquilino.nome;
        select.appendChild(option);
    });
}

// Gerenciamento de Imóveis
function listarImoveis() {
    const tabela = document.getElementById("tabelaImoveis").querySelector("tbody");
    tabela.innerHTML = ""; // Limpar a tabela

    imoveis.forEach(imovel => {
        const inquilinoNome = imovel.inquilinoId
            ? inquilinos.find(inquilino => inquilino.id === imovel.inquilinoId)?.nome || "Desconhecido"
            : "Nenhum";

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${imovel.idImovel}</td>
            <td>${imovel.endereco}</td>
            <td>${imovel.cidade}</td>
            <td>${imovel.bairro}</td>
            <td>${imovel.preco.toFixed(2)}</td>
            <td>${imovel.disponivel ? "Sim" : "Não"}</td>
            <td>${inquilinoNome}</td>
            <td>
                <button onclick="editarImovel(${imovel.idImovel})">Editar</button>
                <button onclick="deletarImovel(${imovel.idImovel})">Excluir</button>
            </td>
        `;
        tabela.appendChild(row);
    });

    listarImoveisAlugados();
}

function salvarImovel() {
    const endereco = document.getElementById("txtEndereco").value;
    const cidade = document.getElementById("txtCidade").value;
    const bairro = document.getElementById("txtBairro").value;
    const preco = parseFloat(document.getElementById("txtPreco").value) || 0;
    const disponivel = document.getElementById("chkDisponivel").checked;
    const inquilinoId = parseInt(document.getElementById("selectInquilino").value) || null;

    if (!endereco || !cidade || !bairro || preco <= 0) {
        alert("Por favor, preencha todos os campos corretamente.");
        return;
    }

    if (editandoImovelId) {
        const imovel = imoveis.find(imovel => imovel.idImovel === editandoImovelId);
        imovel.endereco = endereco;
        imovel.cidade = cidade;
        imovel.bairro = bairro;
        imovel.preco = preco;
        imovel.disponivel = disponivel;
        imovel.inquilinoId = inquilinoId;

        alert("Imóvel atualizado com sucesso!");
        editandoImovelId = null;
    } else {
        const novoImovel = {
            idImovel: imoveis.length + 1,
            endereco,
            cidade,
            bairro,
            preco,
            disponivel,
            inquilinoId
        };

        imoveis.push(novoImovel);
        alert("Imóvel salvo com sucesso!");
    }

    listarImoveis();
    apagarFormularioImovel();
}

function editarImovel(id) {
    const imovel = imoveis.find(imovel => imovel.idImovel === id);
    if (!imovel) return;

    document.getElementById("txtEndereco").value = imovel.endereco;
    document.getElementById("txtCidade").value = imovel.cidade;
    document.getElementById("txtBairro").value = imovel.bairro;
    document.getElementById("txtPreco").value = imovel.preco;
    document.getElementById("chkDisponivel").checked = imovel.disponivel;
    document.getElementById("selectInquilino").value = imovel.inquilinoId || "";

    editandoImovelId = id;
}

function deletarImovel(id) {
    imoveis = imoveis.filter(imovel => imovel.idImovel !== id);
    alert(`Imóvel com ID ${id} excluído.`);
    listarImoveis();
}

function apagarFormularioImovel() {
    document.getElementById("txtEndereco").value = "";
    document.getElementById("txtCidade").value = "";
    document.getElementById("txtBairro").value = "";
    document.getElementById("txtPreco").value = "";
    document.getElementById("chkDisponivel").checked = false;
    document.getElementById("selectInquilino").value = "";
    editandoImovelId = null;
}

function cancelarEdicaoImovel() {
    apagarFormularioImovel();
    alert("Edição de imóvel cancelada.");
}

function listarImoveisAlugados() {
    const tabela = document.getElementById("tabelaImoveisAlugados").querySelector("tbody");
    tabela.innerHTML = "";

    imoveis
        .filter(imovel => imovel.inquilinoId)
        .forEach(imovel => {
            const inquilinoNome = inquilinos.find(inquilino => inquilino.id === imovel.inquilinoId)?.nome || "Desconhecido";

            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${imovel.idImovel}</td>
                <td>${imovel.endereco}</td>
                <td>${inquilinoNome}</td>
            `;
            tabela.appendChild(row);
        });
}

document.addEventListener("DOMContentLoaded", () => {
    listarInquilinos();
    listarImoveis();
});
