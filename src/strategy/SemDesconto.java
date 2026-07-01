package strategy;

public class SemDesconto implements EstrategiaDesconto {
    @Override
    public double aplicarDesconto(double valorOriginal) {
        return valorOriginal;
    }
}