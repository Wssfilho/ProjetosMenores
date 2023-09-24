package exercicio12;

public class ItemCompra {
    private String itemdec;
    private float valor;
    private int qtd;
    private float perDesconto;

    ItemCompra(String desc, int pqtd, float value)
    {
        this.itemdec = desc;
        this.qtd = pqtd;
        this.valor = value;
    }
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getItemdec() {
        return itemdec;
    }

    public void setItemdec(String itemdec) {
        this.itemdec = itemdec;
    }


}
