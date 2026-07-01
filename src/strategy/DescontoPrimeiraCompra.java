package strategy;

public class DescontoPrimeiraCompra implements EstrategiaDesconto{
    @Override
    public double aplicarDesconto(double valorOriginal){
        return Math.max(0, valorOriginal - 10.00);
    }
}
