package exercicio12;

public class ItemCompra {
    private String itemdec;
    private float valor;
    private int qtd;
    private float perDesconto;

    ItemCompra(String desc, float valor, int pqtd, float descon)
    {
        this.itemdec = desc;
        this.qtd = pqtd;
        this.valor = valor;
        this.perDesconto = descon;
    }

    public void setPerDesconto(float perDesconto) {
        this.perDesconto = perDesconto;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public void setItemdec(String itemdec) {
        this.itemdec = itemdec;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getItemdec() {
        return itemdec;
    }

    public float getPerDesconto() {
        return perDesconto;
    }

    public int getQtd() {
        return qtd;
    }

    public float getValor() {
        return valor;
    }

    public float ValorDes()
    {
        float aux;
        aux = valor * (perDesconto/100);
        valor = valor - aux;
        return valor;
    }
    public float ValorTitem()
    {
        valor = (valor * qtd);
        return valor;
    }
}
