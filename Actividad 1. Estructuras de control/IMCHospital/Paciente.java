
public class Paciente 
{
    // Atributos privados (ENCAPSULAMIENTO)
    private double peso;      // en kilogramos
    private double estatura;  // en metros

    // Constructor
    public Paciente(double peso, double estatura) {
        this.peso = peso;
        this.estatura = estatura;
    }

    // Getters y setters
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    // Método que calcula el IMC: peso / (estatura * estatura)
    public double calcularIMC() {
        return peso / (estatura * estatura);
    }

    // Método que clasifica el IMC según la tabla de la actividad
    public String clasificar() {
        double imc = calcularIMC();

        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else if (imc < 35) {
            return "Obesidad grado I";
        } else if (imc < 40) {
            return "Obesidad grado II";
        } else {
            return "Obesidad grado III";
        }
    }
}

