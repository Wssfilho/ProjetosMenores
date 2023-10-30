import javax.security.sasl.SaslServer;

public class MotComb extends motores{
    private double capComb;
    private double consMed;

    public double getConsMed() {
        return consMed;
    }

    public double getCapComb() {
        return capComb;
    }

    @Override
    public double CalcularAutonimia()
    {
        System.out.println("Execultando Calcular autunomia de MotorComb");
        //return getCap * getCon;
        return this.capComb * this.consMed;
    }
}
/*

//abstrato para poder usar o calcular autonomia em outras classes (filhas) mudando a maneira de calcular
public abstract class Motor {
    public double calcularAutonomia() {
        return 0;
    }
}

public class MotorEletrico extends Motor {
    private double capacidadeBateria; // em Amperes-Hora
    private double consumo; // em Amperes por hora

    public MotorEletrico(double capacidadeBateria, double consumo) {
        this.capacidadeBateria = capacidadeBateria;
        this.consumo = consumo;
    }

    @Override //calcular autonimia de MotorEletrico
    public double calcularAutonomia() {
        return capacidadeBateria / consumo; // retorna a autonomia em horas
    }
}

public class MotorCombustao extends Motor {
    private double capacidadeTanque; // em litros
    private double consumoMedio; // em km/l

    public MotorCombustao(double capacidadeTanque, double consumoMedio) {
        this.capacidadeTanque = capacidadeTanque;
        this.consumoMedio = consumoMedio;
    }

    @Override
    public double calcularAutonomia() {
        return capacidadeTanque * consumoMedio; // retorna a autonomia em km
    }
}


 */