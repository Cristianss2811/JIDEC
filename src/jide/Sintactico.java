package jide;

import java.util.Stack;

public class Sintactico {

    private int is, itf;
    private String resultado, estado, accion;
    public String error;

    private String simbolos[] = {"id", "num", "int", "float", "char", ",", ";", "+", "-", "*", "/", "=", "(", ")", "$", "P", "Tipo", "V", "A", "Exp", "E", "Term", "T", "F"};
    private String producciones[] = {"P'>P", "P>Tipo id V", "P>A", "Tipo>int", "Tipo>float", "Tipo>char", "V>, id V", "V>; P", "A>id = Exp ;", "Exp>+ Term E", "Exp>- Term E", "Exp>Term E", "E>+ Term E", "E>- Term E", "E>Vacia", "Term>F T", "T>* F T", "T>/ F T", "T>Vacia", "F>id", "F>num", "F>( Exp )"};

    private String tabla[][] = {
        {"I7", "error", "I4", "I5", "I6", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I1", "I2", "error", "I3", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "P0", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"I8", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "P2", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"P3", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"P4", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"P5", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I9", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "I11", "I12", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I10", "error", "error", "error", "error", "error", "error"},
        {"I18", "I19", "error", "error", "error", "error", "error", "I14", "I15", "error", "error", "error", "I20", "error", "error", "error", "error", "error", "error", "I13", "error", "I16", "error", "I17"},
        {"error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "P1", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"I21", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"I7", "error", "I4", "I5", "I6", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I22", "I2", "error", "I3", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "I23", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"I18", "I19", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I20", "error", "error", "error", "error", "error", "error", "error", "error", "I24", "error", "I17"},
        {"I18", "I19", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I20", "error", "error", "error", "error", "error", "error", "error", "error", "I25", "error", "I17"},
        {"error", "error", "error", "error", "error", "error", "P14", "I27", "I28", "error", "error", "error", "error", "P14", "error", "error", "error", "error", "error", "error", "I26", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P18", "P18", "P18", "I30", "I31", "error", "error", "P18", "error", "error", "error", "error", "error", "error", "error", "error", "I29", "error"},
        {"error", "error", "error", "error", "error", "error", "P19", "P19", "P19", "P19", "P19", "error", "error", "P19", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P20", "P20", "P20", "P20", "P20", "error", "error", "P20", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"I18", "I19", "error", "error", "error", "error", "error", "I14", "I15", "error", "error", "error", "I20", "error", "error", "error", "error", "error", "error", "I32", "error", "I16", "error", "I17"},
        {"error", "error", "error", "error", "error", "I11", "I12", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I33", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "P7", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "P8", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P14", "I27", "I28", "error", "error", "error", "error", "P14", "error", "error", "error", "error", "error", "error", "I34", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P14", "I27", "I28", "error", "error", "error", "error", "P14", "error", "error", "error", "error", "error", "error", "I35", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P11", "error", "error", "error", "error", "error", "error", "P11", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"I18", "I19", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I20", "error", "error", "error", "error", "error", "error", "error", "error", "I36", "error", "I17"},
        {"I18", "I19", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I20", "error", "error", "error", "error", "error", "error", "error", "error", "I37", "error", "I17"},
        {"error", "error", "error", "error", "error", "error", "P15", "P15", "P15", "error", "error", "error", "error", "P15", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"I18", "I19", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I20", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I38"},
        {"I18", "I19", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I20", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I39"},
        {"error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "I40", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error", "P6", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P9", "error", "error", "error", "error", "error", "error", "P9", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P10", "error", "error", "error", "error", "error", "error", "P10", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P14", "I27", "I28", "error", "error", "error", "error", "P14", "error", "error", "error", "error", "error", "error", "I41", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P14", "I27", "I28", "error", "error", "error", "error", "P14", "error", "error", "error", "error", "error", "error", "I42", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P18", "P18", "P18", "I30", "I31", "error", "error", "P18", "error", "error", "error", "error", "error", "error", "error", "error", "I43", "error"},
        {"error", "error", "error", "error", "error", "error", "P18", "P18", "P18", "I30", "I31", "error", "error", "P18", "error", "error", "error", "error", "error", "error", "error", "error", "I44", "error"},
        {"error", "error", "error", "error", "error", "error", "P21", "P21", "P21", "P21", "P21", "error", "error", "P21", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P12", "error", "error", "error", "error", "error", "error", "P12", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P13", "error", "error", "error", "error", "error", "error", "P13", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P16", "P16", "P16", "error", "error", "error", "error", "P16", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"},
        {"error", "error", "error", "error", "error", "error", "P17", "P17", "P17", "error", "error", "error", "error", "P17", "error", "error", "error", "error", "error", "error", "error", "error", "error", "error"}};
    private Stack<String> pila;
    private Stack<String> pilaux;

    public Sintactico() {
        pila = new Stack();
        pilaux = new Stack();
        pila.push("$");
        pila.push("I0");
        pilaux.push("z");
        resultado = "$ I0\n";
        error = "";
    }

    public void Analisis(String token, int linea) {
        for (is = 0; is < simbolos.length; is++) {
            if (simbolos[is].equals(token)) {
                this.Accion(token, linea);
            }
        }
    }

    private void Accion(String token, int linea) {
        boolean ban = true;
        while (ban) {
            if (pila.peek().equals("error")) {
                error += "Cadena Inválida" + linea;
                ban = false;
                return;
            }
            estado = pila.peek().substring(1);
            accion = tabla[Integer.parseInt(estado)][is];

            if (accion.startsWith("I")) {
                pila.push(token);
                pila.push(accion);
                añadirResultado();
                return;
            } else if (accion.startsWith("P")) {
                if (accion.startsWith("P0")) {
                    resultado += "Acepta :)";
                    return;
                } else {
                    System.out.println(accion);
                    String[] mitad = producciones[Integer.parseInt(accion.substring(1))].split(">");
                    String izquierda = mitad[0];
                    String derecha = mitad[1];
                    System.out.println(izquierda);
                    System.out.println(derecha);
                    String[] arrderecha = derecha.split(" ");
                    if (!arrderecha[0].equals("Vacia")) {
                        for (int i = 0; i < arrderecha.length * 2; i++) {
                            pila.pop();
                        }
                    }
                    String estado2 = pila.peek().substring(1);
                    pila.push(izquierda);
                    for (int j = 0; j < simbolos.length; j++) {
                        if (simbolos[j].equals(izquierda)) {
                            pila.push(tabla[Integer.parseInt(estado2)][j]);
                        }

                    }
                    añadirResultado();
                }

            } else if (accion.equals("error")) {
                error += "Cadena Inválida" + linea;
                return;
            }
        }
    }

    private void añadirResultado() {
        while (!pila.peek().equals("$")) {
            pilaux.push(pila.pop());
        }
        resultado += "$";
        while (!pilaux.peek().equals("z")) {
            resultado += " " + pila.push(pilaux.pop());
        }
        resultado += "\n";
    }

    public String Resultado() {
        return resultado;
    }
}
