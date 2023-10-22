class Agricultura 
{
    constructor(plantacao, tecnologia) {
        this.plantacao = plantacao;
        this.tecnologia = tecnologia;
    }
    
    informacao() 
    {
        return `A plantação de ${this.plantacao} está utilizando a tecnologia ${this.tecnologia}.`;
    }
}
function criar()
{
    var plantacao = document.querySelector("#plantacao").value;
    var tecnologia = document.querySelector("#tecnologia").value;
if (plantacao == "" || tecnologia == "") {
    alert("Preencha todos os campos!");
    return;
}
Agricultura = new Agricultura(plantacao, tecnologia);
}

function status() 
{
    if (Agricultura.tecnologia > 0 && Agricultura.tecnologia <= 3) {
        document.querySelector("[name='txtstart']").value = "A plantação está em fase inicial.";
    }
    else if (Agricultura.tecnologia > 3 && Agricultura.tecnologia <= 6) {
        document.querySelector("[name='txtstart']").value = "A plantação está em fase de crescimento.";
    }
    else if (Agricultura.tecnologia > 6 && Agricultura.tecnologia <= 9) {
        document.querySelector("[name='txtstart']").value = "A plantação está em fase de maturação.";
    }
    else if (Agricultura.tecnologia > 9 && Agricultura.tecnologia <= 12) {
        document.querySelector("[name='txtstart']").value = "A plantação está em fase de colheita.";
    }
    else 
    {
        document.querySelector("[name='txtstart']").value = "A plantação está em fase de decomposição.";
    }
}