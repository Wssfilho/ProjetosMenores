class Agricultura {
    constructor(plantacao, tecnologia) {
        this.plantacao = plantacao;
        this.tecnologia = tecnologia;
    }

    informacao() {
        return `A plantação de ${this.plantacao} está utilizando a tecnologia ${this.tecnologia}.`;
    }
}

var agriculturas = []; // array para armazenar os objetos

function criar() {
    var plantacao = document.querySelector("#plantacao").value;
    var tecnologia = document.querySelector("#tecnologia").value;
    if (plantacao == "" || tecnologia == "") {
        alert("Preencha todos os campos!");
        return;
    }
    var novaAgricultura = new Agricultura(plantacao, tecnologia);
    agriculturas.push(novaAgricultura); // adiciona o novo objeto ao array
}

function status() {
    var Agricultura = agriculturas[agriculturas.length - 1]; // pega o último objeto adicionado
    if (Agricultura.tecnologia > 0 && Agricultura.tecnologia <= 3) {
        document.querySelector("[name='txtstart']").value = "Fase inicial.";
    }
    else if (Agricultura.tecnologia > 3 && Agricultura.tecnologia <= 6) {
        document.querySelector("[name='txtstart']").value = "Fase de crescimento.";
    }
    else if (Agricultura.tecnologia > 6 && Agricultura.tecnologia <= 9) {
        document.querySelector("[name='txtstart']").value = "Fase de maturação.";
    }
    else if (Agricultura.tecnologia > 9 && Agricultura.tecnologia <= 12) {
        document.querySelector("[name='txtstart']").value = "Fase de colheita.";
    }
    else {
        document.querySelector("txtstart").value = "A plantação está em fase de decomposição.";
    }
}
