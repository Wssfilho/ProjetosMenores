class Sensor {
    constructor(id, descricao, valorLeitura) {
        this.id = id;
        this.descricao = descricao;
        this.valorLeitura = valorLeitura;
    }
}

class Bomba {
    constructor(id, descricao, status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status; // true para ligada, false para desligada
    }

    ligar() {
        this.status = true;
    }

    desligar() {
        this.status = false;
    }
}

class AtuadorGeoreferenciado extends Bomba {
    constructor(id, descricao, status, latitude, longitude) {
        super(id, descricao, status);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

var atuadores = []; // array para armazenar os objetos

function criar() {
    var latitude = document.querySelector("#latitude").value;
    var longitude = document.querySelector("#longitude").value;
    
    if (latitude == "" || longitude == "") {
        alert("Preencha todos os campos!");
        return;
    }
    
    var novoAtuador = new AtuadorGeoreferenciado(1, "Atuador Principal", false, latitude, longitude);
    
    atuadores.push(novoAtuador); // adiciona o novo objeto ao array
}

function status() {
    
   var AtuadorGeoreferenciado = atuadores[atuadores.length - 1]; // pega o último objeto adicionado
   
   if (AtuadorGeoreferenciado.latitude > 0 && AtuadorGeoreferenciado.longitude <= 3) {

       document.querySelector("[name='txtstart']").value = "Fase inicial.";
       AtuadorGeoreferenciado.ligar();
       document.querySelector("[name='tipo']").value = AtuadorGeoreferenciado.status ? "Bomba Ligada" : "Bomba Desligada";
   }
   else if (AtuadorGeoreferenciado.latitude > 3 && AtuadorGeoreferenciado.longitude <= 6) {

       document.querySelector("[name='txtstart']").value = "Fase de crescimento.";
       AtuadorGeoreferenciado.desligar();
       document.querySelector("[name='tipo']").value = AtuadorGeoreferenciado.status ? "Bomba Ligada" : "Bomba Desligada";

   }
   else if (AtuadorGeoreferenciado.latitude > 6 && AtuadorGeoreferenciado.longitude <= 9) {

       document.querySelector("[name='txtstart']").value = "Fase de maturação.";
       AtuadorGeoreferenciado.ligar();
       document.querySelector("[name='tipo']").value = AtuadorGeoreferenciado.status ? "Bomba Ligada" : "Bomba Desligada";

   }
   else if (AtuadorGeoreferenciado.latitude > 9 && AtuadorGeoreferenciado.longitude <= 12) {

       document.querySelector("[name='txtstart']").value = "Fase de colheita.";
       AtuadorGeoreferenciado.desligar();
       document.querySelector("[name='tipo']").value = AtuadorGeoreferenciado.status ? "Bomba Ligada" : "Bomba Desligada";

   }
   else {

       document.querySelector("txtstart").value = "A plantação está em fase de decomposição.";
       AtuadorGeoreferenciado.desligar();
       document.querySelector("[name='tipo']").value = AtuadorGeoreferenciado.status ? "Bomba Ligada" : "Bomba Desligada";

   }
}
