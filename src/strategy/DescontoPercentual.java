package strategy;

public class DescontoPercentual implements EstrategiaDesconto {
    private Double porcentagem;

    public DescontoPercentual(double porcentagem) {
        this.porcentagem = porcentagem;
    }

    @Override
    public double aplicarDesconto(double valorOriginal) {
        double valorDoDesconto = valorOriginal * (this.porcentagem / 100);
        return Math.max(0, valorOriginal - valorDoDesconto);
    }
}