class Agricultura {
    constructor(plantacao, tecnologia) {
        this.plantacao = plantacao;
        this.tecnologia = tecnologia;
    }

    informacao() {
        return `Usando: Tecnologia ${this.tecnologia}`;
    }
    get plantacao() {
        return this._plantacao;
    }
    set plantacao(value) {
        this._plantacao = value;
    }
    get tecnologia() {
        return this._tecnologia;
    }
    set tecnologia(value) {
        this._tecnologia = value;
    }
}
class Motor extends Agricultura {
    constructor(plantacao, tecnologia, tipo) {
        super(plantacao, tecnologia);
        this.tipo = tipo;
    }

    informacao() {
        return `Usando: ${this.tipo}`;
    }

    get tipo() {
        return this._tipo;
    }

    set tipo(value) {
        this._tipo = value;
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
    Agricultura = new Motor()
    var Agricultura = agriculturas[agriculturas.length - 1]; // pega o último objeto adicionado
    if (Agricultura.tecnologia > 0 && Agricultura.tecnologia <= 3) {
        document.querySelector("[name='txtstart']").value = "Fase inicial.";
        var motor = new Motor(Agricultura.plantacao, Agricultura.tecnologia, "Trator");
        document.querySelector("[name='tipo']").value = motor.informacao()
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

